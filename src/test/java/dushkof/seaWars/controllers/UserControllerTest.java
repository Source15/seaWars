package dushkof.seaWars.controllers;

import dushkof.seaWars.Constants;
import dushkof.seaWars.objects.User;
import dushkof.seaWars.repo.UserRepo;
import dushkof.seaWars.services.UserService;
import dushkof.seaWars.services.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import javax.annotation.Resource;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    @Resource
    UserServiceImpl userService;

    @Resource
    UserController userController;

    @Resource
    UserRepo userRepo;

    @Test
    void createUserTest() {
            String createUserTest = userController.create("test1", "test1");

//            Assert.assertEquals(Constants.OK, createUserTest);

            String createUserTest1 = userController.create("test1", "test1");

            Assert.assertEquals(Constants.NOK, createUserTest1);

            String createUserTest2 = userController.create("123456789", "test1");

            Assert.assertEquals(Constants.NOK, createUserTest2);

    }

    @Test
    void checkUserPasswordTest() {
        String createUserTest = userController.create("test1", "test1");

//        Assert.assertEquals(Constants.OK, createUserTest);

        String checkUserPasswordTest = userService.checkUserPassword("test1", "test1");

        Assert.assertEquals(Constants.OK, checkUserPasswordTest);

        String checkUserPasswordTest1 = userService.checkUserPassword("test1", "12345");

        Assert.assertEquals(Constants.NOK, checkUserPasswordTest1);

    }
}