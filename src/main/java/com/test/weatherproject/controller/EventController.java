package com.test.weatherproject.controller;

import com.test.weatherproject.domain.Event;
import com.test.weatherproject.exception.EventNotFoundException;
import com.test.weatherproject.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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
    public ResponseListObject getEvents(@RequestParam("lon") Double longitude,
                                         @RequestParam("lat") Double latitude,
                                         @RequestParam(value = "rows", required = false) Integer rows,
                                         @DateTimeFormat(pattern = "yyyy-MM-dd")
                                         @RequestParam(value = "date", required = false) Date date ) throws EventNotFoundException {

        Event event = buildEventObject(longitude, latitude, date);

        final int MAX_ROWS_PER_PAGE = 50;
        if (rows == null){
            rows = MAX_ROWS_PER_PAGE;
        }

        List<Event> eventList = eventService.findAllByLocationAndDate(event, rows);
        if (eventList.size() == 0) {
            throw new EventNotFoundException("No Events found for the given criteria.");
        }

        return buildResponseListObject(rows, eventList);
    }

    private ResponseListObject buildResponseListObject(Integer rows, List<Event> eventList) {
        ResponseListObject responseListObject = new ResponseListObject();
        responseListObject.setCode(200);
        responseListObject.setRows(rows);
        responseListObject.setList(eventList);
        return responseListObject;
    }

    private Event buildEventObject(Double longitude, Double latitude, Date date) {
        Event event = new Event();
        event.setLongitude(longitude);
        event.setLatitude(latitude);

        if (date == null) {
            event.setDate(new Date());
        } else {
            event.setDate(date);
        }
        return event;
    }


    @ExceptionHandler(EventNotFoundException.class)
    public void handleEventNotFound(EventNotFoundException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }


}
