package com.test.weatherproject.schedule;

import com.test.weatherproject.domain.Event;
import com.test.weatherproject.helper.JsonPathHelper;
import com.test.weatherproject.service.EventService;
import com.test.weatherproject.service.WeatherService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

@Component
public class EventScheduler {

    private Logger logger = Logger.getLogger(EventScheduler.class);

    private EventService eventService;
    private JsonPathHelper jsonPathHelper;
    private WeatherService weatherService;

    @Autowired
    public EventScheduler(EventService eventService, JsonPathHelper jsonPathHelper, WeatherService weatherService) {
        this.eventService = eventService;
        this.jsonPathHelper = jsonPathHelper;
        this.weatherService = weatherService;
    }

    @Scheduled(fixedRate = 2000)
    public void persistWeatherInformation() throws URISyntaxException {
        String json = weatherService.getExternalWeatherData();
        Event event = jsonPathHelper.convertJsonToEventObject(json);
        eventService.add(event);
        logger.info("Added new Event: " + event);
    }

}
