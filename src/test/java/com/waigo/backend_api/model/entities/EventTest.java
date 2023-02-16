package com.waigo.backend_api.model.entities;

import com.waigo.backend_api.config.TestConfig;
import com.waigo.backend_api.model.repositories.EventRepository;
import com.waigo.backend_api.model.repositories.UserRepository;
import com.waigo.backend_api.utils.MockDataGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
public class EventTest {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;
    private final MockDataGenerator data = new MockDataGenerator();
    @BeforeAll
    public void setUp() {
        userRepository.saveAndFlush(data.getValidCustomUser());
    }

    @AfterAll
    public void resetData(){
        userRepository.deleteAll();
    }

    @Test
    public void testCreatingEventWithoutNameField(){
        final Event event = new Event(null, data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), data.getValidMaxParticipants(),
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("event.null_name");
    }

}
