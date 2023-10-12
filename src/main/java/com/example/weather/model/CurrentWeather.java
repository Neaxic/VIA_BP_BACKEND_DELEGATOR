package com.example.weather.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
public class CurrentWeather implements Serializable {

    private String city;
    private String country;
    private String description;
    private BigDecimal temperature;
    private BigDecimal feelsLike;
    private BigDecimal windSpeed;

    public CurrentWeather(String city, String country, String description, BigDecimal temperature, BigDecimal feelsLike, BigDecimal windSpeed) {
        this.city = city;
        this.country = country;
        this.description = description;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.windSpeed = windSpeed;
    }
}
