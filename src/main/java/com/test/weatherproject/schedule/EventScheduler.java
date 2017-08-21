package com.test.weatherproject.schedule;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.test.weatherproject.domain.Event;
import com.test.weatherproject.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.web.JsonPath;
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

        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

        ResponseEntity<String> stringResponseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        String json = stringResponseEntity.getBody();

        DocumentContext jsonContext = JsonPath.parse(json);

        Event event = new Event();
        event.setLatitude(jsonContext.read("$['coord']['lat']"));
        event.setLatitude(jsonContext.read("$['coord']['lon']"));
        //event.setDateMS(new Date(jsonContext.read("$['dt']")));
        event.setLocation(jsonContext.read("$['name']"));
        event.setAlertText(jsonContext.read("$['weather'][0]['description']"));

        log.info(event.toString());

    }
}
