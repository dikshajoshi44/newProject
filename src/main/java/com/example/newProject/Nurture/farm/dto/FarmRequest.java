package com.example.newProject.Nurture.farm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FarmRequest {


    @JsonProperty("details")
    private List<FarmData> details;



}
