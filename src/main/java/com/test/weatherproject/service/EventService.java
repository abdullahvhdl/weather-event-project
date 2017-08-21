package com.test.weatherproject.service;

import com.test.weatherproject.domain.Event;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EventService {

    void add(Event event);

    List<Event> findAllByLocationAndDate(Event event);

    List<Event> findAllByLatitude(Event event);

    List<Event> findAllByLatitudeAndLongitude(Event event);

    List<Event> findAllByDate(Event event);
}
