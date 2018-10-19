package com.spykins.localefood.model;

import java.util.List;

public class Location {
    public String address;
    public String crossStreet;
    public Double lat;
    public Double lng;
    public Integer distance;
    public String postalCode;
    public String cc;
    public String city;
    public String state;
    public String country;
    public List<String> formattedAddress = null;
    public String neighborhood;
}
