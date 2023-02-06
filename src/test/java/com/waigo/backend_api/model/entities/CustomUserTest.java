package com.waigo.backend_api.model.entities;


import com.waigo.backend_api.model.repositories.UserRepository;
import com.waigo.backend_api.config.TestConfig;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class CustomUserTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testCreatingUserWithoutEmailFieldExpectingConstraintViolation() {

        final CustomUser customUser = new CustomUser("John", "Doe", null, "password");
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).isInstanceOf(ConstraintViolationException.class);


    }

    @Test
    public void testCreatingUserWithoutEmailFieldExpectingUserNameNotNullCode() {
        final CustomUser customUser = new CustomUser("John", "Doe", null, "password");
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.name_not_null");

    }

}
