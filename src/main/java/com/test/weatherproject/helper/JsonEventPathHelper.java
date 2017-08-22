package com.test.weatherproject.helper;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.test.weatherproject.domain.Event;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JsonEventPathHelper implements JsonPathHelper {


    public Event convertJsonToEventObject(String json) {
        DocumentContext jsonContext = JsonPath.parse(json);
        Event event = new Event();

        try {
            Double latitude = jsonContext.read("$['coord']['lat']");
            event.setLatitude(latitude);
        } catch (ClassCastException e) {
            Integer latitudeInt = jsonContext.read("$['coord']['lat']");
            event.setLatitude((double)latitudeInt);
        }

        try {
            Double longitude = jsonContext.read("$['coord']['lon']");
            event.setLongitude(longitude);
        } catch (ClassCastException e) {
            Integer longitudeInt = jsonContext.read("$['coord']['lon']");
            event.setLongitude((double)longitudeInt);
        }

        Integer timeStamp = jsonContext.read("$['dt']");
        event.setDate(new Date(timeStamp * 1000L));
        event.setLocation(jsonContext.read("$['name']"));
        event.setAlert(jsonContext.read("$['weather'][0]['description']"));
        return event;
    }

}
