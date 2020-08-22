package dushkof.seaWars.controllers;


import dushkof.seaWars.form.UserForm;
import dushkof.seaWars.objects.*;
import dushkof.seaWars.repo.*;
import dushkof.seaWars.services.GameService;
import dushkof.seaWars.services.UserService;
import org.apache.commons.lang.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);


    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @Resource
    UserRepo userRepo;

    @Resource
    GameRepo gameRepo;

    @Resource
    CellRepo cellRepo;

    @Resource
    FieldRepo fieldRepo;

    @Resource
    ShipRepo shipRepo;

    @Resource
    GameService gameService;

    @Resource
    UserService userService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

    @RequestMapping(value = {"/userList"}, method = RequestMethod.GET)
    public String personList(Model model) {
        List<User> persons = userRepo.findAll();

        model.addAttribute("persons", persons);

        return "personList";
    }

    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);

        return "addPerson";
    }

    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.POST)
    public String savePerson(Model model,
                             @ModelAttribute("userForm") UserForm userForm) {

        String name = userForm.getName();
        String password = userForm.getPassword();

        User user = new User(name, password);
        try {
            userRepo.save(user);
            LOGGER.info("User " + name + " is created");
            return "redirect:/userList";
        } catch (Exception e) {
            LOGGER.info("User " + name + " not created");
            LOGGER.info(e.getMessage());
            model.addAttribute("errorMessage", errorMessage);
            return "addPerson";
        }

    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String tryLogin(Model model) {

        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "login";
    }


    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String login(Model model,
                        @ModelAttribute("userForm") UserForm userForm) {
        String name = userForm.getName();
        String password = userForm.getPassword();
        if ( userService.checkUserPassword(name, password) == "OK" ) {
            LOGGER.info("User " + name + "has logged in");
            return "redirect:/lobby/?name=" + name;
        } else {
            LOGGER.info("User " + name + " has not logged in");
            model.addAttribute("errorMessage", errorMessage);
            return "redirect:/index";
        }
    }

    @RequestMapping(value = {"/lobby"}, method = RequestMethod.GET)
    public String freeGameList(Model model, @RequestParam(value = "name") final String name) {
        List<Game> games = gameService.foundNewGames();
        model.addAttribute("games", games);
        model.addAttribute("name", name);
        model.addAttribute("lobbyLink", "/room/?name=" + name);
        return "lobby";
    }

    @RequestMapping(value = {"/createRoom"}, method = RequestMethod.GET)
    public String roomWithPlayers(Model model, @RequestParam(value = "name") final String name) throws IOException {
        gameService.createGame(name);
        return "redirect:/room/?name=" + name + "&game=" + foundMyGame(name).getId();
    }

    @RequestMapping(value = {"/joinRoom"}, method = RequestMethod.GET)
    public String joinRoom(Model model, @RequestParam(value = "name") final String name, @RequestParam(value = "game") final Long id ) {
        gameService.connectSecondUser(id, name);
        return "redirect:/room/?name=" + name + "&game=" + id;
    }

    @RequestMapping(value = {"/room"}, method = RequestMethod.GET)
    public String updateRoom(Model model, @RequestParam(value = "name") final String name, @RequestParam(value = "game") final Long id) {
        User user = userRepo.findByName(name);
        Game game = gameRepo.findGameById(id);
        model.addAttribute("user", user);
        model.addAttribute("game", game);
        return "room";
    }

    @RequestMapping(value = {"/createField"}, method = RequestMethod.GET)
    public String createField(Model model, @RequestParam(value = "name") final String name, @RequestParam(value = "game") final Long id) throws IOException {
        try {
            Field field = gameService.createField(name, id);
            return "redirect:/field/?name=" + name + "&game=" + id +"&field=" + field.getId();
        } catch (IOException e) {
            e.printStackTrace();
            return "/lobby";
        }
    }

    @RequestMapping(value = {"/field"}, method = RequestMethod.GET)
    public String placeSHips(Model model, @RequestParam(value = "name") final String name, @RequestParam(value = "game") final Long id, @RequestParam(value = "field") final Long fieldId){
        Game game = gameRepo.findGameById(id);
        User user = userRepo.findByName(name);
        model.addAttribute("user", user);
        model.addAttribute("game", game);
        Field field = fieldRepo.findFieldById(fieldId);
        List<Cell> cells = field.getCells();
        model.addAttribute("cells", cells);
        List<Cell> x1cell = new ArrayList<>();

        List<Cell> x2cell = new ArrayList<>();

        List<Cell> x3cell = new ArrayList<>();

        List<Cell> x4cell = new ArrayList<>();
        for (Cell cell : cells) {
            if(cell.getX() == 1) {
                x1cell.add(cell);
            }else if (cell.getX() == 2) {
                x2cell.add(cell);
            } else if (cell.getX() == 3){
                x3cell.add(cell);
            } else {
                x4cell.add(cell);
            }

        }
        model.addAttribute("x1cell", x1cell);
        model.addAttribute("x2cell", x2cell);
        model.addAttribute("x3cell", x3cell);
        model.addAttribute("x4cell", x4cell);

        return "/field";
    }

    @RequestMapping(value = "/placeShip", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String placeShip(@RequestParam(value = "user") final Long userId, @RequestParam(value = "game") final Long gameId, @RequestParam(value = "cellId") final Long cellId) {
        User user = userRepo.getOne(userId);
        Cell cell = cellRepo.getOne(cellId);
        Field field = fieldRepo.findFieldByCellsContains(cell);
        for (Ship ship : field.getShips()) {
            if(ship.getAllCells().isEmpty() && cell.isAvailableForShip()){
                ship.getAllCells().add(cell);
                shipRepo.save(ship);
                cell.setStatus(ship.getId());
                cellRepo.save(cell);
                marcAroundCellsWithCoordinats(cell.getX(), cell.getY(), field.getCells());
            }
        }
        if(isNotFilledShips(field)){
            return "redirect:/field/?name=" + user.getName() + "&game=" + gameId +"&field=" + field.getId();
        }
        return "/index";
    }

    private boolean isNotFilledShips(Field field) {
        return field.getShips().stream().anyMatch(ship -> ship.getAllCells().isEmpty());
    }

    private void marcAroundCellsWithCoordinats(Integer x, Integer y, List<Cell> cells) {
        for(int i = -1 ; i <= 1; i++){
            for(int k = -1; k <= 1; k++){
                marcAvailabilityCellWithCoordinats(x+i, y+k, cells);
            }
        }
    }

    private void marcAvailabilityCellWithCoordinats(Integer x, Integer y, List<Cell> cells) {
        if(x > 4 || y > 4) {
            return;
        }
        for (Cell cell : cells) {
            if(cell.getY() == y && cell.getX() == x){
                cell.setAvailableForShip(Boolean.FALSE);
                cellRepo.save(cell);
            }
        }
    }


    private Game foundMyGame(String name) {
        User user = userRepo.findByName(name);
        List<Game> games = gameRepo.findByUserHost(user);

        for (Game game : games) {
            if ( BooleanUtils.isNotTrue(game.getFinished()) ) {
                return game;
            }
        }
        return null;
    }

}
