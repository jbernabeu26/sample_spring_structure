package com.waigo.backend_api.services;

import com.waigo.backend_api.config.TestConfig;
import com.waigo.backend_api.utils.MockDataGenerator;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@DataJpaTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomUserServiceTests {

    @Autowired
    private UserServiceImpl customUserService;
    private final MockDataGenerator dataGenerator = new MockDataGenerator();


    //Testing create user operations

    @Test
    public void createUserWithBlankFirstName() {
    }

    @Test
    public void createUserWithBlankLastName() {
    }

    @Test
    public void createUserWithBlankEmail() {
    }

    @Test
    public void createUserWithBlankPassword() {
    }

    @Test
    public void createUserWithBlankDescription() {
    }

    @Test
    public void createUserWithInvalidEmail() {
    }

    @Test
    public void createUserWithInvalidPassword() {
    }

    @Test
    public void createUserWithLessThanMinFirstNameLength() {
    }

    @Test
    public void createUserWithLessThanMinFirstNameLength2() {
    }

    @Test
    public void createUserWithMoreThanMaxFirstNameLength() {
    }

    @Test
    public void createUserWithMoreThanMaxFirstNameLength2() {
    }

    @Test
    public void createUserWithLessThanMinLastNameLength() {
    }

    @Test
    public void createUserWithMoreThanMaxLastNameLength() {
    }

    @Test
    public void createUserWithMoreThanMaxDescriptionLength() {
    }

    @Test
    public void createUserSuccessfully() {
    }

    //Testing find user operations

    @Test
    public void findUserWithBlankEmail() {
    }

    @Test
    public void findUserWithInvalidEmail() {
    }

    @Test
    public void findUserWithNonExistingEmail() {
    }

    @Test
    public void findUserSuccessfullyByEmail() {
    }

    @Test
    public void findUserWithBlankId() {
    }

    @Test
    public void findUserWithInvalidId() {
    }

    @Test
    public void findUserWithNonExistingId() {
    }

    @Test
    public void findUserSuccessfullyById() {
    }

    //Testing delete user operations

    @Test
    public void deleteUserWithBlankEmail() {
    }

    @Test
    public void deleteUserWithInvalidEmail() {
    }

    @Test
    public void deleteUserWithNonExistingEmail() {
    }

    @Test
    public void deleteUserSuccessfullyByEmail() {
    }

    @Test
    public void deleteUserWithBlankId() {
    }

    @Test
    public void deleteUserWithInvalidId() {
    }

    @Test
    public void deleteUserWithNonExistingId() {
    }

    @Test
    public void deleteUserSuccessfullyById() {
    }

}
