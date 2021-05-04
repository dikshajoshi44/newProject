package com.example.newProject.Nurture.farm.Service;

import com.example.newProject.Nurture.farm.Util.FarmUtil;
import com.example.newProject.Nurture.farm.DTO.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Service
public class CropService {

    Map<String, Mandi> mandiMap = new HashMap<>();
    Map<String, State> stateMap = new HashMap<>();
    Map<String, Crop> cropMap = new HashMap<>();
    Map<String, Variety> varietyMap = new HashMap<>();


    public FarmResponse fillCropData(FarmRequest request) {

        FarmResponse res = new FarmResponse();

        FarmUtil.validateRequest(request);

        List<FarmData> details = request.getDetails();
        List<District> districts = new ArrayList<>();

        for(FarmData item : details) {

            Mandi mandi = getMandiWthName(item);
            Crop crop = getCropWithName(item);

            LocalDate date = LocalDate.parse(item.getDate());
            PriceDetails priceDetails = new PriceDetails(crop, date, item.getPrice());

            mandi.addCropPrice(date, crop, priceDetails);

        }

        res.setMessage("Successfully Update");
        res.setStatus(HttpStatus.OK.toString());

        return res;

    }

    public FarmResponse getData(String state, String crop, String district, String variety) {

        FarmResponse res = new FarmResponse();

        if(!stateMap.isEmpty()) {
            District currDist = stateMap.get(state).getDistrict(district);
            Crop cropNew = cropMap.get(crop + variety);
            res.setData(currDist.getCropPriceDetails(cropNew));

        }
        res.setMessage("Success");
        res.setStatus(HttpStatus.OK.toString());

        return res;

    }

    public Object getDataByLimit(String mandiName, String cropName, String variety, int elements, int limit) {

        FarmResponse res = new FarmResponse();

        Crop crop = cropMap.get(cropName + variety);
        Mandi mandi = mandiMap.get(mandiName);
        LocalDate todayDate = LocalDate.now();

        List<PriceDetails> priceDetails = mandi.getPricesWithCrop(crop);

        List<PriceDetails> priceDetailsFilteredSorted = priceDetails.stream()
                .filter(priceDetail ->  priceDetail.getTimestamp().isAfter(todayDate.minusDays(elements)))
                .sorted()
                .limit(limit)
                .collect(Collectors.toList());

        res.setStatus(HttpStatus.OK.toString());
        res.setMessage("Success");
        res.setData(priceDetailsFilteredSorted);
        return res;
    }



    public Mandi getMandiWthName(FarmData item) {
        if(!mandiMap.containsKey(item.getMandiName())) {

            State state = getStateWithName(item.getState());
            District district = state.addDistrictIfNotPresent(item.getDistrict());

            Mandi mandi = new Mandi(item.getMandiName(), new Address(state, district));
            district.addMandiInDistrict(mandi);

            mandiMap.put(item.getMandiName(), mandi);
        }

        return mandiMap.get(item.getMandiName());
    }

    public Crop getCropWithName(FarmData item) {
        if(!cropMap.containsKey(item.getCropName() + item.getVariety())) {
            Variety variety = getVarietyWithName(item);
            Crop crop = new Crop(item.getCropName(), variety);

            cropMap.put(item.getCropName() + item.getVariety(), crop);
        }

        return cropMap.get(item.getCropName() + item.getVariety());
    }


    public Variety getVarietyWithName(FarmData item) {
        if(!varietyMap.containsKey(item.getVariety())) {
            Variety variety = new Variety(item.getVariety());

            varietyMap.put(item.getVariety(), variety);
        }

        return varietyMap.get(item.getVariety());
    }


    public State getStateWithName(String stateName) {

        if(!stateMap.containsKey(stateName)){
            State state = new State(stateName);
            stateMap.put(stateName, state);
        }

        return stateMap.get(stateName);

    }

}
