package com.spykins.localefood.model;

public class Restaurant {
    private String name;
    private double lat;
    private  double lng;
    private double distance;
    private double formattedAddress;
    private String catgeoryName;

    public Restaurant(String name, double lat, double lng, double distance, double formattedAddress, String catgeoryName) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.distance = distance;
        this.formattedAddress = formattedAddress;
        this.catgeoryName = catgeoryName;
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public double getDistance() {
        return distance;
    }

    public double getFormattedAddress() {
        return formattedAddress;
    }

    public String getCatgeoryName() {
        return catgeoryName;
    }
}
