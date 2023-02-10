package com.waigo.backend_api.model.entities;


import com.waigo.backend_api.model.repositories.UserRepository;
import com.waigo.backend_api.config.TestConfig;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class CustomUserTest {

    @Autowired
    private UserRepository userRepository;

    final String validDescription = new String(new char[250]).replace("\0", "a");
    final String validPassword = "password";
    final String validFirstName = "John";
    final String validLastName = "Doe";
    final String validEmail = "john.doe@mail.com";

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testCreatingUserWithoutEmailFieldExpectingConstraintViolation() {

        final CustomUser customUser = new CustomUser(validFirstName, validLastName, null, validPassword, validDescription);
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void testCreatingUserWithoutEmailFieldExpectingUserNameNotNullCode() {
        final CustomUser customUser = new CustomUser(validFirstName, validLastName, null, validPassword, validDescription);
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.null_email");

    }

    @Test
    public void testCreatingUserWithWrongEmailFormat() {
        final CustomUser customUser = new CustomUser(validFirstName, validLastName, "wrongEmailFormat", validPassword, validDescription);
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.not_valid_email");
    }

    @Test
    public void testCreatingUserWithCorrectEmailFormat() {
        final CustomUser customUser = new CustomUser(validFirstName, validLastName, "john.doe@mail.com", validPassword, validDescription);

        userRepository.saveAndFlush(customUser);


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
        final CustomUser customUser = new CustomUser(with100CharsFirstName, validLastName, validEmail, validPassword, validDescription);

        userRepository.saveAndFlush(customUser);

        Assertions.assertThat(customUser.getId()).isNotNull();
    }

    @Test
    public void testCreatingUserWithFirstNameFieldWithLessThan100AndMoreThan2LengthString() {
        var with100CharsFirstName = new String(new char[99]).replace("\0", "a");
        final CustomUser customUser = new CustomUser(with100CharsFirstName, validLastName, validEmail, validPassword, validDescription);

        userRepository.saveAndFlush(customUser);

        Assertions.assertThat(customUser.getId()).isNotNull();
    }

    @Test
    public void testCreatingUserWithFirstNameFieldUsing2LengthString() {
        var with2CharsFirstName = new String(new char[2]).replace("\0", "a");
        final CustomUser customUser = new CustomUser(with2CharsFirstName, validLastName, validEmail, validPassword, validDescription);

        userRepository.saveAndFlush(customUser);

        Assertions.assertThat(customUser.getId()).isNotNull();

    }

    @Test
    public void testCreatingUserWithFirstNameFieldUsingGreaterThan100LengthString() {
        var with101CharsFirstName = new String(new char[101]).replace("\0", "a");
        final CustomUser customUser = new CustomUser(with101CharsFirstName, validLastName, validEmail, validPassword, validDescription);

        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.first_name_size");
    }

    @Test
    public void testCreatingUserWithFirstNameFieldUsingGreaterThan1000LengthString() {
        var with101CharsFirstName = new String(new char[1001]).replace("\0", "a");
        final CustomUser customUser = new CustomUser(with101CharsFirstName, validLastName, validEmail, validPassword, validDescription);

        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.first_name_size");
    }

    @Test
    public void testCreatingUserWithNullFirstNameField() {
        final CustomUser customUser = new CustomUser(null, validLastName, validEmail, validPassword, validDescription);
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.null_first_name");
    }

    @Test
    public void testCreatingUserWithBlankFirstNameField() {
        final CustomUser customUser = new CustomUser("", validLastName, validEmail, validPassword, validDescription);
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.blank_first_name");
    }


    //Tests related to last name field validation.


    @Test
    public void testCreatingUserWithLastNameFieldUsing100LengthString() {
        var with100CharsLastName = new String(new char[100]).replace("\0", "a");

        final CustomUser customUser = new CustomUser(validFirstName, with100CharsLastName, validEmail, validPassword, validDescription);

        userRepository.saveAndFlush(customUser);


        Assertions.assertThat(customUser.getId()).isNotNull();
    }

    @Test
    @DisplayName("Test creating user with last name field using 100 length string")
    public void testCreatingUserWithLastNameFieldWithLessThan100AndMoreThan2LengthString() {
        var with99CharsLastName = new String(new char[99]).replace("\0", "a");

        final CustomUser customUser = new CustomUser(validFirstName, with99CharsLastName, validEmail, validPassword, validDescription);

        userRepository.saveAndFlush(customUser);

        Assertions.assertThat(customUser.getId()).isNotNull();
    }

    @Test
    public void testCreatingUserWithLastNameFieldUsing2LengthString() {
        var with100CharsLastName = new String(new char[2]).replace("\0", "a");
        final CustomUser customUser = new CustomUser(validFirstName, with100CharsLastName, validEmail, validPassword, validDescription);
        userRepository.saveAndFlush(customUser);

        Assertions.assertThat(customUser.getId()).isNotNull();

    }

    @Test
    public void testCreatingUserWithLastNameFieldUsingGreaterThan100LengthString() {
        var with101CharsLastName = new String(new char[101]).replace("\0", "a");
        final CustomUser customUser = new CustomUser(validFirstName, with101CharsLastName, validEmail, validPassword, validDescription);

        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.last_name_size");
    }

    @Test
    public void testCreatingUserWithLastNameFieldUsingGreaterThan1000LengthString() {
        var with101CharsLastName = new String(new char[1001]).replace("\0", "a");
        final CustomUser customUser = new CustomUser(validFirstName, with101CharsLastName, validEmail, validPassword, validDescription);

        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.last_name_size");
    }

    @Test
    public void testCreatingUserWithNullLastNameField() {
        final CustomUser customUser = new CustomUser(validFirstName, null, validEmail, validPassword, validDescription);
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.null_last_name");
    }

    @Test
    public void testCreatingUserWithBlankLastNameField() {
        final CustomUser customUser = new CustomUser(validFirstName, "", validEmail, validPassword, validDescription);
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.blank_last_name");
    }


    //Tests related to description field validation.

    @Test
    public void testCreatingUserWithDescriptionFieldUsing500LengthString() {
        var with500CharsDescription = new String(new char[500]).replace("\0", "a");

        final CustomUser customUser = new CustomUser(validFirstName, validLastName, validEmail, validPassword, with500CharsDescription);

        userRepository.saveAndFlush(customUser);


        Assertions.assertThat(customUser.getId()).isNotNull();
    }

    @Test
    public void testCreatingUserWithDescriptionFieldWithLessThan500AndMoreThan100LengthString() {
        var with199CharsDescription = new String(new char[199]).replace("\0", "a");

        final CustomUser customUser = new CustomUser(validFirstName, validLastName, validEmail, validPassword, with199CharsDescription);

        userRepository.saveAndFlush(customUser);

        Assertions.assertThat(customUser.getId()).isNotNull();
    }

    @Test
    public void testCreatingUserWithDescriptionFieldUsing100LengthString() {
        var with100CharsDescription = new String(new char[100]).replace("\0", "a");
        final CustomUser customUser = new CustomUser(validFirstName, validLastName, validEmail, validPassword, with100CharsDescription);

        userRepository.saveAndFlush(customUser);


        Assertions.assertThat(customUser.getId()).isNotNull();

    }

    @Test
    public void testCreatingUserWithDescriptionFieldUsingGreaterThan100LengthString() {
        var with99CharsDescription = new String(new char[99]).replace("\0", "a");
        final CustomUser customUser = new CustomUser(validFirstName, validLastName, validEmail, validPassword, with99CharsDescription);

        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.description_size");
    }

    @Test
    public void testCreatingUserWithDescriptionFieldUsingGreaterThan500LengthString() {
        var with1001CharsDescription = new String(new char[1001]).replace("\0", "a");
        final CustomUser customUser = new CustomUser(validFirstName, validLastName, validEmail, validPassword, with1001CharsDescription);

        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.description_size");
    }

    @Test
    public void testCreatingUserWithNullDescriptionField() {
        final CustomUser customUser = new CustomUser(validFirstName, validLastName, validEmail, validPassword, null);
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.null_description");
    }

    @Test
    public void testCreatingUserWithBlankDescriptionField() {
        final CustomUser customUser = new CustomUser(validFirstName, validLastName, validEmail, validPassword, "");
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.blank_description");
    }

    @Test
    public void testCreatingUserWithFirstNameToOverfloat() {

        var with101CharsName = new String(new char[101]).replace("\0", "a");
        final CustomUser customUser = new CustomUser(with101CharsName, validLastName, validEmail, validPassword, validDescription);
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.first_name_size");
    }

    @Test
    public void testCreatingUserWithCorrectFirstNameFormat() {

        var with99CharsName = new String(new char[99]).replace("\0", "a");
        final CustomUser customUser = new CustomUser(with99CharsName, validLastName, validEmail, validPassword, validDescription);

        userRepository.saveAndFlush(customUser);

        Assertions.assertThat(customUser.getId()).isNotNull();
    }

    @Test
    public void testCreatingUserWithMinValidData() {

        final CustomUser customUser = new CustomUser(validFirstName, validLastName, validEmail, validPassword, validDescription);

        userRepository.saveAndFlush(customUser);

        Assertions.assertThat(customUser.getId()).isNotNull();
    }

    @Test
    public void testCreatingUserWithNullPassword() {

        final CustomUser customUser = new CustomUser(validFirstName, validLastName, validEmail, null, validDescription);
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.null_password");
    }

    @Test
    public void testCreatingUserWithBlankPassword() {

        final CustomUser customUser = new CustomUser(validFirstName, validLastName, validEmail, " ", validDescription);
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(customUser)).hasMessageContaining("user.blank_password");
    }


    @Test
    public void testFindingUserById() {
        final CustomUser customUser = new CustomUser(validFirstName, validLastName, validEmail, validPassword, validDescription);
        userRepository.saveAndFlush(customUser);
        final Optional<CustomUser> foundUser = userRepository.findById(customUser.getId());
        Assertions.assertThat(foundUser.isPresent()).isTrue();
        Assertions.assertThat(foundUser.get().getId()).isEqualTo(customUser.getId());
    }

    @Test
    public void testFindingUserByEmail() {
        final CustomUser customUser = new CustomUser(validFirstName, validLastName, validEmail, validPassword, validDescription);
        userRepository.saveAndFlush(customUser);
        final Optional<CustomUser> foundUser = userRepository.findByEmail(customUser.getEmail());
        Assertions.assertThat(foundUser.isPresent()).isTrue();
        Assertions.assertThat(foundUser.get().getEmail()).isEqualTo(customUser.getEmail());
    }


    @Test
    public void testDeleteUserById() {
        final CustomUser customUser = new CustomUser(validFirstName, validLastName, validEmail, validPassword, validDescription);
        userRepository.saveAndFlush(customUser);
        userRepository.deleteById(customUser.getId());
        final Optional<CustomUser> foundUser = userRepository.findById(customUser.getId());
        Assertions.assertThat(foundUser.isPresent()).isFalse();
    }

    @Test
    public void testDeleteByExistingEmail() {
        final CustomUser customUser = new CustomUser(validFirstName, validLastName, validEmail, validPassword, validDescription);
        userRepository.saveAndFlush(customUser);
        userRepository.deleteByEmail(customUser.getEmail());
        final Optional<CustomUser> foundUser = userRepository.findByEmail(customUser.getEmail());
        Assertions.assertThat(foundUser.isPresent()).isFalse();
    }

}
