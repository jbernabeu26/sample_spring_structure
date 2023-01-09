package com.waigo.backend_api.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/event")
public class EventController {
    /*@Autowired
    private EventRepository eventRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser(@RequestBody Event bodyEvent) {
        System.out.println(bodyEvent);
        Event event = new Event();
        event.setPhotos(bodyEvent.getPhotos());
        event.setGeolocation(bodyEvent.getGeolocation());

        eventRepository.save(event);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Event> getAllEvents() {
        return eventRepository.findAll();
}*/
}
