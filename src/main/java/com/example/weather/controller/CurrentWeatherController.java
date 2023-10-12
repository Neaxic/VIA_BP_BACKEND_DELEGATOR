package com.example.weather.controller;

import com.example.weather.service.WeatherService;
import io.split.client.SplitClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class CurrentWeatherController {

    private final WeatherService weatherService;
    private final SplitClient splitClient;

    public CurrentWeatherController(WeatherService weatherService, SplitClient splitClient) {
        this.weatherService = weatherService;
        this.splitClient = splitClient;
    }

    @GetMapping(value = {"/current-weather", "/current-weather/{location}"})
    public String getCurrentWeather(@PathVariable(name="location") Optional<String> location, Model model) {

        String city;
        String country;

        String treatment = splitClient.getTreatment("anonymous", "CUSTOM_LOCATION");

        if ("on".equals(treatment) && location.isPresent()) {
            city = location.get().split(",")[0];
            country = location.get().split(",")[1];
        } else {
            city = "Detroit";
            country = "US";
        }

        model.addAttribute("currentWeather", weatherService.getCurrentWeather(city, country));
        return "current-weather";
    }
}