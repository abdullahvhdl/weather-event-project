package com.test.weatherproject.service;

import com.test.weatherproject.configuration.ExternalWeatherApiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class WeatherServiceImpl implements WeatherService {

    private ExternalWeatherApiData apiData;

    @Autowired
    public WeatherServiceImpl(ExternalWeatherApiData apiData) {
        this.apiData = apiData;
    }

    public String getExternalWeatherData() {
        HttpHeaders headers = getHttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        String url = getExternalWeatherServiceUrl();

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> stringResponseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        return stringResponseEntity.getBody();
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private String getExternalWeatherServiceUrl() {
        List<Integer> cityCodeList = apiData.getCityCodes();
        int cityIndex = getRandomCityIndex(cityCodeList.size());
        return apiData.getApiUrl() + cityCodeList.get(cityIndex) + "&APPID=" + apiData.getApiKey();
    }

    private int getRandomCityIndex(int maxValue) {
        return ThreadLocalRandom.current().nextInt(0, maxValue);
    }

}
