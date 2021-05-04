package com.example.newProject.Nurture.farm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PriceDetails implements Comparable<PriceDetails> {

    @JsonProperty("address")
    private Crop crop;

    @JsonProperty("timestamp")
    private Date timestamp;

    @JsonProperty("price")
    private Double price;


    public PriceDetails(Crop crop, Date timestamp, Double price) {
        this.crop = crop;
        this.timestamp = timestamp;
        this.price = price;
    }

    @Override
    public int compareTo(PriceDetails o) {
        return this.timestamp.compareTo(o.timestamp);
    }
}
