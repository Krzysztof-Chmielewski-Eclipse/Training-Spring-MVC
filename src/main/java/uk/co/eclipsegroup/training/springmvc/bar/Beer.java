package uk.co.eclipsegroup.training.springmvc.bar;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Beer {
    private final String name;

    public Beer(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
