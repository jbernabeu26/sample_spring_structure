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

    final String validDescription = new String(new char[301]).replace("\0", "a");
    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testCreatingUserWithoutEmailFieldExpectingConstraintViolation() {

        final CustomUser customUser = new CustomUser("John", "Doe", null, "password", validDescription);
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void testCreatingUserWithoutEmailFieldExpectingUserNameNotNullCode() {
        final CustomUser customUser = new CustomUser("John", "Doe", null, "password", validDescription);
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.null_email");

    }

    @Test
    public void testCreatingUserWithWrongEmailFormat() {
        final CustomUser customUser = new CustomUser("John", "Doe", "wrongEmailFormat", "password", validDescription);
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.not_valid_email");
    }

    @Test
    public void testCreatingUserWithCorrectEmailFormat() {
        final CustomUser customUser = new CustomUser("John", "Doe", "john.doe@mail.com", "password", validDescription);
        try {
            userRepository.saveAndFlush(customUser);
        } catch (Exception e) {
            Assertions.fail("Exception thrown when creating user with correct email format");
        }

        Assertions.assertThat(customUser.getId()).isNotNull();
    }

    @Test
    public void testCreatingUserWithoutAnyDataExpectingConstraintViolation() {
        final CustomUser customUser = new CustomUser();
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).isInstanceOf(ConstraintViolationException.class);
    }


    //Tests related to first name field validation.

    @Test
    public void testCreatingUserWithFirstNameFieldUsing100LengthString() {
        var with100CharsFirstName = new String(new char[100]).replace("\0", "a");
        System.out.println(with100CharsFirstName);
        final CustomUser customUser = new CustomUser(with100CharsFirstName, "Doe", "doe@gmail.com", "password", validDescription);
        try {
            userRepository.saveAndFlush(customUser);
        } catch (Exception e) {
            Assertions.fail("Exception thrown when creating user with correct first name format");
        }
        Assertions.assertThat(customUser.getId()).isNotNull();
    }

    @Test
    public void testCreatingUserWithFirstNameFieldWithLessThan100AndMoreThan2LengthString() {
        var with100CharsFirstName = new String(new char[99]).replace("\0", "a");
        System.out.println(with100CharsFirstName);
        final CustomUser customUser = new CustomUser(with100CharsFirstName, "Doe", "doe@gmail.com", "password", validDescription);
        try {
            userRepository.saveAndFlush(customUser);
        } catch (Exception e) {
            Assertions.fail("Exception thrown when creating user with correct first name format");
        }
        Assertions.assertThat(customUser.getId()).isNotNull();
    }

    @Test
    public void testCreatingUserWithFirstNameFieldUsing2LengthString() {
        var with2CharsFirstName = new String(new char[2]).replace("\0", "a");
        final CustomUser customUser = new CustomUser(with2CharsFirstName, "Doe", "doe@gmail.com", "password", validDescription);
        try {
            userRepository.saveAndFlush(customUser);
        } catch (Exception e) {
            Assertions.fail("Exception thrown when creating user with correct first name format");
        }
        Assertions.assertThat(customUser.getId()).isNotNull();

    }

    @Test
    public void testCreatingUserWithFirstNameFieldUsingGreaterThan100LengthString() {
        var with101CharsFirstName = new String(new char[101]).replace("\0", "a");
        final CustomUser customUser = new CustomUser(with101CharsFirstName, "Doe", "doe@gmail.com", "password", validDescription);

        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.first_name_size");
    }

    @Test
    public void testCreatingUserWithFirstNameFieldUsingGreaterThan1000LengthString() {
        var with101CharsFirstName = new String(new char[1001]).replace("\0", "a");
        final CustomUser customUser = new CustomUser(with101CharsFirstName, "Doe", "doe@gmail.com", "password", validDescription);

        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.first_name_size");
    }

    @Test
    public void testCreatingUserWithNullFirstNameField() {
        final CustomUser customUser = new CustomUser(null, "Doe", "doe@gmail.com", "password", validDescription);
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.null_first_name");
    }

    @Test
    public void testCreatingUserWithBlankFirstNameField() {
        final CustomUser customUser = new CustomUser("", "Doe", "doe@gmail.com", "password", validDescription);
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.blank_first_name");
    }


    //Tests related to last name field validation.


    @Test
    public void testCreatingUserWithLastNameFieldUsing100LengthString() {
        var with100CharsLastName = new String(new char[100]).replace("\0", "a");

        final CustomUser customUser = new CustomUser("John", with100CharsLastName, "doe@gmail.com", "password", validDescription);
        try {
            userRepository.saveAndFlush(customUser);
        } catch (Exception e) {
            Assertions.fail("Exception thrown when creating user with correct last name format");
        }

        Assertions.assertThat(customUser.getId()).isNotNull();
    }

    @Test
    public void testCreatingUserWithLastNameFieldWithLessThan100AndMoreThan2LengthString() {
        var with100CharsLastName = new String(new char[99]).replace("\0", "a");

        final CustomUser customUser = new CustomUser("John", with100CharsLastName, "doe@gmail.com", "password", validDescription);
        try {
            userRepository.saveAndFlush(customUser);
        } catch (Exception e) {
            Assertions.fail("Exception thrown when creating user with correct last name format");
        }
        Assertions.assertThat(customUser.getId()).isNotNull();
    }

    @Test
    public void testCreatingUserWithLastNameFieldUsing2LengthString() {
        var with100CharsLastName = new String(new char[2]).replace("\0", "a");
        final CustomUser customUser = new CustomUser("John", with100CharsLastName, "doe@gmail.com", "password", validDescription);
        try {
            userRepository.saveAndFlush(customUser);
        } catch (Exception e) {
            Assertions.fail("Exception thrown when creating user with correct last name format");
        }
        Assertions.assertThat(customUser.getId()).isNotNull();

    }

    @Test
    public void testCreatingUserWithLastNameFieldUsingGreaterThan100LengthString() {
        var with101CharsLastName = new String(new char[101]).replace("\0", "a");
        final CustomUser customUser = new CustomUser("John", with101CharsLastName, "doe@gmail.com", "password", validDescription);

        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.last_name_size");
    }

    @Test
    public void testCreatingUserWithLastNameFieldUsingGreaterThan1000LengthString() {
        var with101CharsLastName = new String(new char[1001]).replace("\0", "a");
        final CustomUser customUser = new CustomUser("John", with101CharsLastName, "doe@gmail.com", "password", validDescription);

        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.last_name_size");
    }

    @Test
    public void testCreatingUserWithNullLastNameField() {
        final CustomUser customUser = new CustomUser("John", null, "doe@gmail.com", "password", validDescription);
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.null_last_name");
    }

    @Test
    public void testCreatingUserWithBlankLastNameField() {
        final CustomUser customUser = new CustomUser("John", "", "doe@gmail.com", "password", validDescription);
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.blank_last_name");
    }


    //Tests related to description field validation.

    @Test
    public void testCreatingUserWithDescriptionFieldUsing500LengthString() {
        var with500CharsDescription = new String(new char[500]).replace("\0", "a");

        final CustomUser customUser = new CustomUser("John", "Doe", "doe@gmail.com", "password", with500CharsDescription);
        try {
            userRepository.saveAndFlush(customUser);
        } catch (Exception e) {
            Assertions.fail("Exception thrown when creating user with correct description format");
        }

        Assertions.assertThat(customUser.getId()).isNotNull();
    }

    @Test
    public void testCreatingUserWithDescriptionFieldWithLessThan500AndMoreThan100LengthString() {
        var with199CharsDescription = new String(new char[199]).replace("\0", "a");

        final CustomUser customUser = new CustomUser("John", "Doe", "doe@gmail.com", "password", with199CharsDescription);
        try {
            userRepository.saveAndFlush(customUser);
        } catch (Exception e) {
            Assertions.fail("Exception thrown when creating user with correct description format");
        }
        Assertions.assertThat(customUser.getId()).isNotNull();
    }

    @Test
    public void testCreatingUserWithDescriptionFieldUsing100LengthString() {
        var with100CharsDescription = new String(new char[100]).replace("\0", "a");
        final CustomUser customUser = new CustomUser("John", "Doe", "doe@gmail.com", "password", with100CharsDescription);
        try {
            userRepository.saveAndFlush(customUser);
        } catch (Exception e) {
            Assertions.fail("Exception thrown when creating user with correct description format");
        }
        Assertions.assertThat(customUser.getId()).isNotNull();

    }

    @Test
    public void testCreatingUserWithDescriptionFieldUsingGreaterThan100LengthString() {
        var with99CharsDescription = new String(new char[99]).replace("\0", "a");
        final CustomUser customUser = new CustomUser("John", "Doe", "doe@gmail.com", "password", with99CharsDescription);

        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.description_size");
    }

    @Test
    public void testCreatingUserWithDescriptionFieldUsingGreaterThan500LengthString() {
        var with1001CharsDescription = new String(new char[1001]).replace("\0", "a");
        final CustomUser customUser = new CustomUser("John", "Doe", "doe@gmail.com", "password", with1001CharsDescription);

        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.description_size");
    }

    @Test
    public void testCreatingUserWithNullDescriptionField() {
        final CustomUser customUser = new CustomUser("John", "Doe", "doe@gmail.com", "password", null);
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.null_description");
    }

    @Test
    public void testCreatingUserWithBlankDescriptionField() {
        final CustomUser customUser = new CustomUser("John", "Doe", "doe@gmail.com", "password", "");
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.blank_description");
    }


}
