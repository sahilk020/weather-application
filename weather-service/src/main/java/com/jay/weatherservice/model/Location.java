package com.jay.weatherservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    private String name;
    private double lat;
    private double lon;
    private String country;
    private String state;

}

