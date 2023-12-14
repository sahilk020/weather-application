package com.jay.weatherservice.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    private String name;
//    private Map<String, String> localNames;
    private double lat;
    private double lon;
    private String country;
    private String state;

    // Constructors, getters, and setters
}

