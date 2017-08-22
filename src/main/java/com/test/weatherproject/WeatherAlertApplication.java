package com.test.weatherproject;

import com.test.weatherproject.configuration.ExternalWeatherApiData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(ExternalWeatherApiData.class)
public class WeatherAlertApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherAlertApplication.class, args);
	}
}
