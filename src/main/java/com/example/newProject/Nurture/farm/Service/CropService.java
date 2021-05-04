package com.example.newProject.Nurture.farm.Service;

import com.example.newProject.Nurture.farm.Util.FarmUtil;
import com.example.newProject.Nurture.farm.dto.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CropService {

    Map<String, Mandi> mandiMap;
    Map<String, State> stateMap;
    Map<String, Crop> cropMap;
    Map<String, Variety> varietyMap;


    public FarmResponse fillCropData(FarmRequest request) {

        FarmResponse res = new FarmResponse();

        FarmUtil.validateRequest(request);

        List<FarmData> details = request.getDetails();
        List<District> districts = new ArrayList<>();

        for(FarmData item : details) {

            Mandi mandi = getMandiWthName(item);
            Crop crop = getCropWithName(item);

            Date date = FarmUtil.parseDate(item.getDate());
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

        return res;

    }

    public FarmResponse getDataByLimit(String mandi, String crop, String variety, Integer elements, Integer limit) {
        

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
