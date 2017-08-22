package com.test.weatherproject.helper;

import com.test.weatherproject.domain.Event;

public interface JsonPathHelper {

    Event convertJsonToEventObject(String json);

}
