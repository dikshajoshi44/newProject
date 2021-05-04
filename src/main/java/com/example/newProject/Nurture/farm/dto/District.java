package com.example.newProject.Nurture.farm.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class District {

    @JsonProperty("name")
    private String name;

    Map<String, Mandi> mandiMap;


    public District(String name) {
        this.name = name;
        this.mandiMap = new HashMap<>();
    }

    public void addMandiInDistrict(Mandi mandi) {

        mandiMap.put(mandi.getName(), mandi);
    }

    public Map<Mandi, List<PriceDetails>> getCropPriceDetails(Crop crop) {

        Map<Mandi, List<PriceDetails>> latestPrices = new HashMap<>();

        Map<Mandi, List<PriceDetails>> prices = new HashMap<>();

        mandiMap.forEach((key, mandi) -> {
            prices.put(mandi, mandi.getPricesWithCrop(crop));
        });

        for(Mandi mandi: prices.keySet()) {
            latestPrices.put(mandi, new ArrayList<>());
        }

        prices.forEach((mandi, priceDetailsList) -> {

            PriorityQueue<PriceDetails> pq = new PriorityQueue<>(Collections.reverseOrder());
            pq.addAll(priceDetailsList);

            Set<Crop> crops = new HashSet<>();
            while(!pq.isEmpty()) {
                PriceDetails pd = pq.poll();

                if(!crops.contains(pd.getCrop())) {
                    latestPrices.get(mandi).add(pd);
                    crops.add(pd.getCrop());
                }

            }

        });

        return latestPrices;
    }
}
