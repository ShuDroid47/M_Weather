package com.example.m_weather.helpers;

import java.io.IOException;

public class NoNetworkException extends IOException {
    private String message="";


    public NoNetworkException(String message) {
        this.message = message;
    }
}
