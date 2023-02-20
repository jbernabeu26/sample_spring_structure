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

    /** General **/

    @Test
    public void testCreateEventWithoutAnyData(){

        final Event event = new Event();
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void testCreateEventWithOnlyName(){

        final Event event = new Event(data.getValidEventName(), null, null, null, null, null, null, null, null);
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    public void testCreateEventWithBlankName(){

        var blankname = "     ";
        final Event event = new Event(blankname, data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), data.getValidMaxParticipants(),

                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("event.blank_name");
    }

    /** Name **/

    @Test
    public void testCreatingEventWithoutNameField(){

        final Event event = new Event(null, data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), data.getValidMaxParticipants(),

                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("event.null_name");
    }
    @Test
    public void testCreateEventWithNameToOverflowJava(){

        var tooLongEventName = new String(new char[EVENT_NAME_MAX + 1]).replace("\0", "a");

        final Event event = new Event(tooLongEventName, data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), data.getValidMaxParticipants(),
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("event.name_size");
    }

    /** Description **/

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

    /** Date **/

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


    /** Privacy **/

    @Test
    public void testCreateEventWithoutPrivacy(){
        Event.PrivacyStatus null_privacy = null;
        final Event event = new Event(data.getValidEventName(), data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), null_privacy, data.getValidMaxParticipants(),
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("event.null_privacy");
    }

    /** Participants **/

    @Test
    public void testCreateEventWithTooManyParticipants(){
        final Event event = new Event(data.getValidEventName(), data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), EVENT_PARTICIPANTS_MAX+1,
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("event.participants_size");

    }

    @Test
    public void testCreateEventWithTooFewParticipants(){
        final Event event = new Event(data.getValidEventName(), data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), EVENT_PARTICIPANTS_MIN-1,
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("event.participants_size");
    }
    @Test
    public void testCreateEventWithoutParticipants(){
        var Invalid_format_participants = 0;
        final Event event = new Event(data.getValidEventName(), data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), Invalid_format_participants,
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("event.participants_size");

    }

    @Test
    public void testCreateEventWrongNumberParticipants(){
        var Invalid_format_participants = -1;
        final Event event = new Event(data.getValidEventName(), data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), Invalid_format_participants,
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("event.participants_size");
    }

    @Test
    public void testCreateEventWrongNumberParticipants2(){
        final Event event = new Event(data.getValidEventName(), data.getValidDescription(), data.getValidStartDate(), data.getValidEndDate(), data.getValidPrivacy(), -1*EVENT_PARTICIPANTS_MAX*2,
                data.getValidCategorySet(), data.getValidCustomUser(), data.getValidGeolocation());
        Assertions.assertThatThrownBy(() -> eventRepository.saveAndFlush(event)).hasMessageContaining("event.participants_size");
    }

}
