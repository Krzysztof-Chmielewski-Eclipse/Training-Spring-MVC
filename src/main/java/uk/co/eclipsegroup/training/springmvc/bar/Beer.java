package uk.co.eclipsegroup.training.springmvc.bar;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Beer {
    private final String id;
    private final String name;
    private final boolean alcoholic;

    public Beer(@JsonProperty("id") String id, @JsonProperty("name") String name, @JsonProperty("alcoholic") boolean alcoholic) {
        this.id = id;
        this.name = name;
        this.alcoholic = alcoholic;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public boolean isAlcoholic() {
        return alcoholic;
    }
}
