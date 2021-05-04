package com.example.newProject.Nurture.farm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    @JsonProperty("name")
    private String name;

    @JsonProperty("state")
    private State state;

    @JsonProperty("district")
    private District district;

    public Address(State state, District district) {
        this.state = state;
        this.district = district;
    }
}
