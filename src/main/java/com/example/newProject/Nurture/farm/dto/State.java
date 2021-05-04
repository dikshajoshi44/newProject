package com.example.newProject.Nurture.farm.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class State {

    @JsonProperty("name")
    private String name;

    @JsonProperty("districts")
    private List<District> districts;

    public State(String name) {
        this.name = name;
        this.districts = new ArrayList<>();
    }

    public District addDistrictIfNotPresent(String districtName) {

        if(!(this.getDistricts().stream().filter(element -> element.getName().equals(districtName)).count() > 0)) {
            this.getDistricts().add(new District(districtName));

        }

        return this.getDistrict(districtName);

    }

    public District getDistrict(String districtName) {
        return this.districts.stream().filter(element -> element.getName().equals(districtName)).findFirst().get();
    }
}
