package com.example.newProject.Nurture.farm.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Crop {

    @JsonProperty("name")
    private String  name;

    @JsonProperty("variety")
    private Variety variety;

    public Crop(String name, Variety variety) {
        this.name = name;
        this.variety = variety;
    }
}
