package com.test.weatherproject.controller;

import com.test.weatherproject.domain.Event;
import com.test.weatherproject.exception.EventNotFoundException;
import com.test.weatherproject.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("weatherservice")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/weather")
    public ResponseEntity<List<Event>> getEvents( @RequestParam("lon") Double longitude,
                                           @RequestParam("lat") Double latitude,
                                           @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("date")  Date date) throws EventNotFoundException {
        Event event = new Event();
        event.setLongitude(longitude);
        event.setLatitude(latitude);
        if( date == null ){
            event.setDate(new Date());
        } else {
            event.setDate(date);
        }

        List<Event> eventList = eventService.findAllByLocationAndDate(event);

        if (eventList.size() == 0){
            throw new EventNotFoundException("No Events found for the given criteria.");
        }

        //List<Event> eventList = eventService.findAllByLatitude(event);

        //List<Event> eventList = eventService.findAllByLatitudeAndLongitude(event);

        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }


    @GetMapping("/test")
    public ResponseEntity<List<Event>> getEvent( @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("date")  Date date) {

        Event event = new Event();
        event.setDate(date);

        List<Event> eventList = eventService.findAllByDate(event);

        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }


    @ExceptionHandler(EventNotFoundException.class)
    public void handleEventNotFound(EventNotFoundException exception, HttpServletResponse response) throws IOException {
        response.sendError( HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }


}
