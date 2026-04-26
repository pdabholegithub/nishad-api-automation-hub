package com.nishad.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 📦 Course POJO (Plain Old Java Object)
 * Represents the data structure of a Course for API requests and responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true) // Ignores extra fields in response during deserialization
public class Course {
    private String id;
    private String title;
    private String description;
    private String duration;
    private String level;
    private int price;
    private String technologies;
    private boolean popular;

    // Default Constructor (Required for Jackson)
    public Course() {}

    // Overloaded Constructor for easy object creation
    public Course(String title, int price, String level, String duration) {
        this.title = title;
        this.price = price;
        this.level = level;
        this.duration = duration;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getTechnologies() { return technologies; }
    public void setTechnologies(String technologies) { this.technologies = technologies; }

    public boolean isPopular() { return popular; }
    public void setPopular(boolean popular) { this.popular = popular; }
}
