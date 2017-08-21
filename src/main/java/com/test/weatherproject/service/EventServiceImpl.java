package com.test.weatherproject.service;

import com.test.weatherproject.domain.Event;
import com.test.weatherproject.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Event> findAllByLocationAndDate(Event event) {
        return eventRepository.findAllByLatitudeAndLongitudeAndDate(event.getLatitude(), event.getLongitude(), event.getDate());
    }

    @Override
    public List<Event> findAllByLatitude(Event event) {
        return eventRepository.findAllByLatitude(event.getLatitude());
    }

    @Override
    public List<Event> findAllByLatitudeAndLongitude(Event event) {
        return eventRepository.findAllByLatitudeAndLongitude(event.getLatitude(), event.getLongitude());
    }

    @Override
    public List<Event> findAllByDate(Event event) {
        return eventRepository.findAllByDate(event.getDate());
    }


}
