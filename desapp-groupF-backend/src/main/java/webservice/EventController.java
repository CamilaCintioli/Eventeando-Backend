package webservice;

import model.event.Basket;
import model.event.Event;
import model.event.Party;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.dtos.EventDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
    public class EventController {

        @RequestMapping("/events")
        public List<EventDTO> getAllEvents() {

            //servicio.dameEventos()
            List<Event> events = Arrays.asList(new Party(), new Basket());
            List<EventDTO> eventDtos= new ArrayList<>();

            for(Event e: events){
                eventDtos.add(new EventDTO(e));
            }

            return eventDtos;

        }
    }


