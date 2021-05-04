package com.example.newProject.Nurture.farm.Interface;

import com.example.newProject.Nurture.farm.dto.FarmRequest;
import com.example.newProject.Nurture.farm.dto.FarmResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface FarmInterface {

    // fillData api fills the data in d
    @PostMapping("/api/v1/fillData")
    ResponseEntity<FarmResponse> fillFarmCropDetails(@RequestBody FarmRequest request);

    @GetMapping("/api/v1/getData")
    ResponseEntity<FarmResponse> getLatestCropData(@RequestParam("state") String state, @RequestParam("crop") String crop,
                                                   @RequestParam("district") String district, @RequestParam("variety") String variety);

    @GetMapping("/api/v1/getData")
    ResponseEntity<FarmResponse> getLatestCropData(@RequestParam("state") String state, @RequestParam("crop") String crop,
                                                   @RequestParam("district") String district, @RequestParam("variety") String variety);
}
