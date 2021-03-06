package com.example.newProject.Nurture.farm.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
public class Mandi {

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private Address address;

    Map<LocalDate, Map<Crop, PriceDetails>> pricesMap;


    public Mandi(String name, Address address) {
        this.name = name;
        this.address = address;
        this.pricesMap = new HashMap<>();
    }

    public void addCropPrice(LocalDate date, Crop crop, PriceDetails priceDetails) {

        if(!pricesMap.containsKey(date)) {
            pricesMap.put(date, new HashMap<>());
        }

        Map<Crop, PriceDetails> pricesOnDate = pricesMap.get(date);
        pricesOnDate.put(crop, priceDetails);
    }

    public List<PriceDetails> getPricesWithCrop(Crop newCrop) {

        List<PriceDetails> priceDetailsList = new ArrayList<>();

        if(!pricesMap.isEmpty()) {

            pricesMap.forEach((date, pricesOnDate) -> {

                pricesOnDate.forEach((crop, priceDetails) -> {
                    if(newCrop.equals(crop)) {
                        priceDetailsList.add(priceDetails);
                    }
                });
            });
        }

        return priceDetailsList;

    }

    @Override
    public String toString() {
        return "Mandi{" +
                "name='" + name + '\'' +
                '}';
    }
}
