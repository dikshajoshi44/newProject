package com.example.newProject.Nurture.farm.Interface;

import com.example.newProject.Nurture.farm.DTO.FarmRequest;
import com.example.newProject.Nurture.farm.DTO.FarmResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface FarmInterface {

    // fillData api fills the data here
    @PostMapping("/v1/fillData")
    ResponseEntity<FarmResponse> fillData(@RequestBody FarmRequest request);

    @GetMapping("/v1/getLatestData")
    ResponseEntity<FarmResponse> getLatestCropData(@RequestParam("state") String state, @RequestParam("crop") String crop,
                                                   @RequestParam("district") String district, @RequestParam("variety") String variety);

    @GetMapping("/v1/getDataByLimit")
    ResponseEntity<FarmResponse> getDataByLimit(@RequestParam("mandi") String mandi, @RequestParam("crop") String crop,
                                                   @RequestParam("variety") String variety, @RequestParam("elements") Integer elements,
                                                   @RequestParam("limit") Integer limit);
}
