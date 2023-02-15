package com.waigo.backend_api.model.entities;

import com.waigo.backend_api.config.TestConfig;
import com.waigo.backend_api.model.repositories.EventRepository;
import com.waigo.backend_api.model.repositories.UserRepository;
import com.waigo.backend_api.utils.SetUp;
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

    private final SetUp data = new SetUp();
    @BeforeEach
    public void setUp() {eventRepository.deleteAll();}

    @Test
    public void testCreatingEventWithoutNameField(){
        final Event event = new Event(null, data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), data.getValidMaxParticipants(),
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("event.null_name");
    }

}
