package com.jay.weatherservice.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class Alert {
	private String sender_name;
    private String event;
    private long start;
    private long end;
    private String description;
    private List<String> tags;
}
