package com.test.weatherproject.schedule;

import com.test.weatherproject.domain.Event;
import com.test.weatherproject.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class EventScheduler {

    private static final Logger log = LoggerFactory.getLogger(EventScheduler.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private EventService eventService;

    @Autowired
    public EventScheduler(EventService eventService) {
        this.eventService = eventService;
    }

    @Scheduled(fixedRate = 2000)
    public void persistWeatherInformation() throws URISyntaxException {
        //log.info("The time is now {}", dateFormat.format(new Date()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();

        String apiKey = "88939d83390aa6b5998cf0ada758a1bb";
        String url = "http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID="+apiKey;
        // Event event = restTemplate.getForObject(url, Event.class);

        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

        ResponseEntity<Event> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Event.class);

        Event event = responseEntity.getBody();

        log.info(event.toString());


        //HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        //restTemplate.exchange(url, HttpMethod.GET, requestEntity, Event.class);




        // eventService.add(event);
    }
}
