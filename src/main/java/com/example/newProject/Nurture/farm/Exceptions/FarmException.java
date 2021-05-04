package com.example.newProject.Nurture.farm.Exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientResponseException;

import java.nio.charset.Charset;

public class FarmException extends RestClientResponseException {

    public FarmException(String message, int statusCode, String statusText, HttpHeaders responseHeaders, byte[] responseBody, Charset responseCharset) {
        super(message, statusCode, statusText, responseHeaders, responseBody, responseCharset);
    }
}
