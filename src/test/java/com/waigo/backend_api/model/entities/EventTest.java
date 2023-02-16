package com.waigo.backend_api.model.entities;


import com.waigo.backend_api.config.TestConfig;
import com.waigo.backend_api.model.repositories.EventRepository;
import com.waigo.backend_api.model.repositories.UserRepository;
import com.waigo.backend_api.utils.SetUp;
import jakarta.validation.ConstraintViolationException;
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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.waigo.backend_api.utils.Constants.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EventTest {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    private final SetUp data = new SetUp();
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

    @Test
    public void testCreateEventWithoutAnyData(){

        final Event event = new Event();

        //TODO: Check what is the error here
        Assertions.assertThatThrownBy(() -> userRepository.saveAndFlush(event)).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void testCreateEventWithNameToOverflowJava(){

        var tooLongEventName = new String(new char[EVENT_NAME_MAX + 1]).replace("\0", "a");

        final Event event = new Event(tooLongEventName, data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), data.getValidMaxParticipants(),
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("event.name_size");
    }
    @Test
    public void testCreateEventWithName100Chars(){

        var validEventName = new String(new char[EVENT_NAME_MAX]).replace("\0", "a");

        final Event event = new Event(validEventName, data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), data.getValidMaxParticipants(),
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        eventRepository.saveAndFlush(event);
        Assertions.assertThat(event.getId()).isNotNull();
    }
    @Test
    public void testCreateEventWithNameLessThan100Chars(){

        var validEventName = new String(new char[EVENT_NAME_MAX-1]).replace("\0", "a");

        final Event event = new Event(validEventName, data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), data.getValidMaxParticipants(),
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        eventRepository.saveAndFlush(event);
        Assertions.assertThat(event.getId()).isNotNull();
    }
    @Test
    public void testCreateEventWithValidDescription(){

        var DescriptionLength = new String(new char[EVENT_DESCRIPTION_MIN+1]).replace("\0", "a");

        final Event event = new Event(data.getValidEventName(), DescriptionLength, data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), data.getValidMaxParticipants(),
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        eventRepository.saveAndFlush(event);
        Assertions.assertThat(event.getId()).isNotNull();
    }
    @Test
    public void testCreateEventWithValidDescription2(){

        var DescriptionLength = new String(new char[EVENT_DESCRIPTION_MAX-1]).replace("\0", "a");

        final Event event = new Event(data.getValidEventName(), DescriptionLength, data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), data.getValidMaxParticipants(),
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        eventRepository.saveAndFlush(event);
        Assertions.assertThat(event.getId()).isNotNull();
    }
    @Test
    public void testCreateEventWithMinDescriptionLength(){

        var DescriptionLength = new String(new char[EVENT_DESCRIPTION_MIN]).replace("\0", "a");

        final Event event = new Event(data.getValidEventName(), DescriptionLength, data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), data.getValidMaxParticipants(),
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        eventRepository.saveAndFlush(event);
        Assertions.assertThat(event.getId()).isNotNull();
    }
    @Test
    public void testCreateEventWithMaxDescriptionLength(){

        var DescriptionLength = new String(new char[EVENT_DESCRIPTION_MAX]).replace("\0", "a");
        final Event event = new Event(data.getValidEventName(), DescriptionLength, data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), data.getValidMaxParticipants(),
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        eventRepository.saveAndFlush(event);
        Assertions.assertThat(event.getId()).isNotNull();
    }
    @Test
    public void testCreateEventWithTooShortDescription(){

        var DescriptionLength = new String(new char[EVENT_DESCRIPTION_MIN-1]).replace("\0", "a");

        final Event event = new Event(data.getValidEventName(), DescriptionLength, data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), data.getValidMaxParticipants(),
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("event.description_size");
    }
    @Test
    public void testCreateEventWithTooShortDescription2(){

        var DescriptionLength = new String(new char[EVENT_DESCRIPTION_MIN/2]).replace("\0", "a");

        final Event event = new Event(data.getValidEventName(), DescriptionLength, data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), data.getValidMaxParticipants(),
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("event.description_size");
    }
    @Test
    public void testCreateEventWithTooLongDescription(){

        var DescriptionLength = new String(new char[EVENT_DESCRIPTION_MAX + 1]).replace("\0", "a");

        final Event event = new Event(data.getValidEventName(), DescriptionLength, data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), data.getValidMaxParticipants(),
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("event.description_size");
    }
    @Test
    public void testCreateEventWithTooLongDescription2(){

        var DescriptionLength = new String(new char[EVENT_DESCRIPTION_MAX * 2]).replace("\0", "a");

        final Event event = new Event(data.getValidEventName(), DescriptionLength, data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), data.getValidMaxParticipants(),
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("event.description_size");
    }
    @Test
    public void testCreateEventWithoutDescription(){

        final Event event = new Event(data.getValidEventName(), null, data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), data.getValidMaxParticipants(),
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("event.null_description");
    }
    @Test
    public void testCreateEventWithoutStartDate(){

        final Event event = new Event(data.getValidEventName(), data.getValidDescription(), null, data.getValidEndDate(), data.getValidPrivacy(), data.getValidMaxParticipants(),
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("event.null_startDate");
    }
    @Test
    public void testCreateEventWithoutEndDate(){

        final Event event = new Event(data.getValidEventName(), data.getValidDescription(), data.getValidStartDate(), null, data.getValidPrivacy(), data.getValidMaxParticipants(),
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("event.null_endDate");
    }
    @Test
    public void testCreateEventWithoutCategory(){
        Set<Category> invalidCategorySet =  new HashSet<>(Arrays.asList(new Category(null)));
        final Event event = new Event(data.getValidEventName(), data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), data.getValidMaxParticipants(),
                invalidCategorySet, data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("category.name_not_null");
    }

    @Test
    public void testCreateEventWithoutPrivacy(){
        final Event event = new Event(data.getValidEventName(), data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), null, data.getValidMaxParticipants(),
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("category.null_privacy");
    }
    //TODO: Check how to do this test
    @Test
    public void testCreateEventDefaultPrivacy(){
        Event.PrivacyStatus invalidPrivacy = null;
        final Event event = new Event(data.getValidEventName(), data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), invalidPrivacy, data.getValidMaxParticipants(),
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("category.null_privacy");
    }
    @Test
    public void testCreateEventWithoutUser(){

        final Event event = new Event(data.getValidEventName(), data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), data.getValidMaxParticipants(),
                data.getValidCategorySet(), null, data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining(" customuser.null_endDate");
    }

    @Test
    public void testCreateEventWithMaxParticipants(){
        final Event event = new Event(data.getValidEventName(), data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), EVENT_PARTICIPANTS_MAX,
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThat(event.getId()).isNotNull();
    }

    @Test
    public void testCreateEventWithTooManyParticipants(){
        final Event event = new Event(data.getValidEventName(), data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), EVENT_PARTICIPANTS_MAX+1,
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining(" customuser.null_endDate");

    }


    @Test
    public void testCreateEventWithMinParticipants(){
        final Event event = new Event(data.getValidEventName(), data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), EVENT_PARTICIPANTS_MIN,
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThat(event.getId()).isNotNull();
    }
    @Test
    public void testCreateEventWithTooFewParticipants(){
        final Event event = new Event(data.getValidEventName(), data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), EVENT_PARTICIPANTS_MIN-1,
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThat(event.getId()).isNotNull();
    }
    @Test
    public void testCreateEventWithoutParticipants(){
        final Event event = new Event(data.getValidEventName(), data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), 0,
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThat(event.getId()).isNotNull();
    }
}
