package com.test.weatherproject.service;

import com.test.weatherproject.domain.Event;
import com.test.weatherproject.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{

    private EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void add(Event event) {
        eventRepository.save(event);
    }

    @Override
    public List<Event> findAllByLocationAndDate(Event event, int rows) {
        Pageable rowsPerPage = new PageRequest(0, rows);
        return eventRepository.findAllByLatitudeAndLongitudeAndDate(event.getLatitude(), event.getLongitude(), event.getDate(), rowsPerPage);
    }

}
