package com.example.newProject.Nurture.farm.Util;


import com.example.newProject.Nurture.farm.Exceptions.FarmException;
import com.example.newProject.Nurture.farm.dto.FarmRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

public class FarmUtil {

    static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");


    public static Date parseDate(String dateString) {
        Date value = null;
        try {
            value = formatter.parse(dateString);
        } catch(ParseException ex) {}

        return value;
    }

    public static Boolean validateRequest(FarmRequest request) {

        if(request.getDetails().isEmpty()) {
            throw new FarmException("Please add data for population", HttpStatus.BAD_REQUEST.value(), null, null, null, null);
        }

        return true;
    }
}
