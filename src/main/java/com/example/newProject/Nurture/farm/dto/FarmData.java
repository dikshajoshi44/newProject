package com.example.newProject.Nurture.farm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarmData {

    @JsonProperty("state")
    private String state;

    @JsonProperty("district")
    private String district;

    @JsonProperty("mandi_name")
    private String mandiName;

    @JsonProperty("crop_name")
    private String cropName;

    @JsonProperty("variety")
    private String variety;

    @JsonProperty("date")
    private String date;

    @JsonProperty("price")
    private Double price;
}
