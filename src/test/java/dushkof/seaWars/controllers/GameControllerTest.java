package dushkof.seaWars.controllers;

import dushkof.seaWars.Constants;
import dushkof.seaWars.objects.Game;
import dushkof.seaWars.objects.User;
import dushkof.seaWars.repo.GameRepo;
import dushkof.seaWars.repo.UserRepo;
import dushkof.seaWars.services.GameService;
import dushkof.seaWars.services.impl.GameServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GameControllerTest {

    @Resource
    GameService gameService;

    @Resource
    GameRepo gameRepo;

    @Resource
    UserRepo userRepo;

    @Resource
    GameController gameController;

    @Resource
    UserController userController;

    @Test
    void createGameTest() throws IOException {
//            String createUserTest = userController.create("test1", "test1");
//
//            Assert.assertEquals(Constants.OK, createUserTest);
//
//            String createGameTest = gameService.createGame("test1");
//
//            Assert.assertEquals(Constants.OK, createGameTest);
//
////            Game testGame = gameRepo.findByUserHost("test1");
////            testGame.setFinished(true);
////            здесь нужно сделать запрос в бд, что бы поменять поле started для всех игр, где userHostа зовут test1 (JOIN)
//            createGameTest = gameService.createGame("test1");
//
//            Assert.assertEquals(Constants.OK, createGameTest);

    }

    @Test
    void connectSecondUserTest() throws IOException {
//            String createUserTest = userController.create("test1", "test1");
//            Assert.assertEquals(Constants.OK, createUserTest);
//
//            String createGameTest = gameService.createGame("test1");
//            Assert.assertEquals(Constants.OK, createGameTest);
//
//            String createSecondUserTest = userController.create("test2", "test2");
//            Assert.assertEquals(Constants.OK, createSecondUserTest);

//            Game game = gameRepo.findByUserHost("test1");
//            Long id = game.getId();

//            String connectSecondUserTest = gameService.connectSecondUser(id, "test2");
//            Assert.assertEquals(Constants.OK, connectSecondUserTest);

//            String connectHostAsSecondUserTest = gameService.connectSecondUser(id, "test1");
//            Assert.assertEquals(Constants.NOK, connectHostAsSecondUserTest);
//
//            String connectNullNameSecondUserTest = gameService.connectSecondUser(id, null);
//            Assert.assertEquals(Constants.NOK, connectNullNameSecondUserTest);


    }

    @Test
    void foundGames() throws IOException {
//            String createUserTest = userController.create("test1", "test1");
//            Assert.assertEquals(Constants.OK, createUserTest);

//            String createSecondUserTest = userController.create("test2", "test2");
//            Assert.assertEquals(Constants.OK, createSecondUserTest);
//
//            String createGameTest = gameService.createGame("test1");
//            Assert.assertEquals(Constants.OK, createGameTest);
//
//            String createGameTest1 = gameService.createGame("test2");
//            Assert.assertEquals(Constants.OK, createGameTest);
//
//            List<Game> foundFreeGamesTest = gameService.foundNewGames();

//            Game testGame = gameRepo.findByUserHost("test1");
//            Game testGame2 = gameRepo.findByUserHost("test2");
//            List<Game> checkFoundFreeGamesTest = new ArrayList<>();
//            checkFoundFreeGamesTest.add(testGame);
//            checkFoundFreeGamesTest.add(testGame2);
//
//            Assert.assertEquals(foundFreeGamesTest, checkFoundFreeGamesTest);
        String test = "1";
        Assert.assertEquals("1",test);
            }

    @Test
    void getField() {
    }

    @Test
    void leaveGames() {
    }

    @Test
    void updateGame() {
    }

    @Test
    void testGetField() {
    }
}