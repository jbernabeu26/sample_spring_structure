package com.waigo.backend_api.model.entities;

import com.waigo.backend_api.config.TestConfig;
import com.waigo.backend_api.model.repositories.EventRepository;
import com.waigo.backend_api.model.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class EventTest {

    @Autowired
    private EventRepository eventRepository;
    final String validName = new String(new char[50]).replace("\0", "a");
    final String validDescription = new String(new char[150]).replace("\0", "a");
    final LocalDateTime validStartDate = LocalDateTime.of(2023, 12, 12, 12, 0);
    final LocalDateTime validEndDate = LocalDateTime.of(2023, 12, 12, 14, 30);
    final Integer validMaxParticipants = 50;
    //TODO: The format of this variable defined according to google maps api? String[2]
    //TODO: This should probably be a string to avoid problems when handling decimals
    final double[] validGeolocation = {40.009656,-105.244660};
    final Event.PrivacyStatus validPrivacy = Event.PrivacyStatus.MIXED;
    final Set<Category> validCategory =  new HashSet<>(Arrays.asList(new Category("Furbito"), new Category("Escalada")));
    //TODO: Valid fields could be reused from CustomUserTest
    final CustomUser validOwner = new CustomUser("John", "Doe", "john.doe@gmail.com", "password", new String(new char[250]).replace("\0", "a"));

    @BeforeEach
    public void setUp() {eventRepository.deleteAll();}

    @Test
    public void testCreatingEventWithoutNameField(){
        String withNullName = null;
        final Event event = new Event(withNullName, validDescription, validStartDate, validEndDate, validPrivacy, validMaxParticipants, validCategory, validOwner, validGeolocation);
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("event.null_name");
    }

}
