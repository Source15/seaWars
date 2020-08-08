package dushkof.seaWars.controllers;

import dushkof.seaWars.repo.FieldRepo;
import dushkof.seaWars.repo.GameRepo;
import dushkof.seaWars.services.PlayService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("play")
public class PlayController {

    @Resource
    private FieldRepo fieldRepo;

    @Resource
    private GameRepo gameRepo;

    @Resource
    private PlayService playService;

    @RequestMapping(value = "shoot", method = RequestMethod.GET)
    public String shipShoot(@RequestParam(value = "cellId") final Long cellId) {
        return playService.shoot(cellId);
    }

    @RequestMapping(value = "turn", method = RequestMethod.GET)
    public String whoseTurn(@RequestParam(value = "playerName") final String playerName,
                            @RequestParam(value = "gameId") final Long gameId) {
        return playService.whoseTurn(playerName, gameId);
    }
}

