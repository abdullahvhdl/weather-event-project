package com.test.weatherproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EVENTS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JsonProperty(value = "description")
    private String alertText;

    @JsonProperty(value = "name")
    private String location;

    @JsonProperty(value = "lon")
    private double longtitude;

    @JsonProperty(value = "lat")
    private double latitude;

    @JsonProperty(value = "dt")
    private Date dateMS;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Date getDateMS() {
        return dateMS;
    }

    public void setDateMS(Date dateMS) {
        this.dateMS = dateMS;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", alertText='" + alertText + '\'' +
                ", location='" + location + '\'' +
                ", longtitude=" + longtitude +
                ", latitude=" + latitude +
                ", dateMS=" + dateMS +
                '}';
    }
}
