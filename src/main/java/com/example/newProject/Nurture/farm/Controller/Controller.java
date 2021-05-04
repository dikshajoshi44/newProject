package com.example.newProject.Nurture.farm.Controller;

import com.example.newProject.Nurture.farm.Exceptions.FarmException;
import com.example.newProject.Nurture.farm.Interface.FarmInterface;
import com.example.newProject.Nurture.farm.dto.FarmRequest;
import com.example.newProject.Nurture.farm.dto.FarmResponse;
import com.example.newProject.Nurture.farm.Service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

public class Controller implements FarmInterface {

    CropService cropService;

    @Autowired
    public Controller(CropService cropService) {
        this.cropService = cropService;
    }

    public ResponseEntity<FarmResponse> fillFarmCropDetails(@RequestBody FarmRequest request){
        try {

            return new ResponseEntity(cropService.fillCropData(request), HttpStatus.valueOf(HttpStatus.OK.value()));

        } catch(FarmException ex) {

            Map<String, String> failureMessage = new HashMap<>();
            failureMessage.put("message", ex.getMessage());
            failureMessage.put("status", ex.getStatusText());

            return new ResponseEntity(failureMessage, HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
        }

    }

    public ResponseEntity<FarmResponse> getLatestCropData(@RequestParam("state") String state, @RequestParam("crop") String crop,
                                                          @RequestParam("district") String district, @RequestParam("variety") String variety){
        try {

            return new ResponseEntity(cropService.getData(state, crop, district, variety), HttpStatus.valueOf(HttpStatus.OK.value()));

        } catch(FarmException ex) {

            Map<String, String> failureMessage = new HashMap<>();
            failureMessage.put("message", ex.getMessage());
            failureMessage.put("status", ex.getStatusText());

            return new ResponseEntity(failureMessage, HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
        }

    }


}
