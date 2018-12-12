package com.example.dialg.projectlayouts;

public class DataStructure {
    private String temperature;
    private String timestamp;

    public DataStructure(){

    }


    public DataStructure(String temperature, String timestamp) {
        this.temperature = temperature;
        this.timestamp = timestamp;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
