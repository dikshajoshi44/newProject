package com.example.newProject.Nurture.farm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Variety {

    @JsonProperty("name")
    private String  name;

    public Variety(String name) {
        this.name = name;
    }
}
