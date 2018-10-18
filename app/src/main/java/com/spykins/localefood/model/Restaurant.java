package com.spykins.localefood.model;

import java.util.List;

public class Restaurant {

    public Response response;

    public class Category {
        public String id;
        public String name;
        public String pluralName;
        public String shortName;
        public Icon icon;
        public Boolean primary;
    }

    public class Icon {
        public String prefix;
        public String suffix;
    }

    public class LabeledLatLng {
        public String label;
        public Double lat;
        public Double lng;
    }


    public class Location {
        public String address;
        public String crossStreet;
        public Double lat;
        public Double lng;
        public List<LabeledLatLng> labeledLatLngs = null;
        public Integer distance;
        public String postalCode;
        public String cc;
        public String city;
        public String state;
        public String country;
        public List<String> formattedAddress = null;
        public String neighborhood;
    }

    public class Response {
        public List<Venue> venues = null;
    }

    public class Venue {
        public String id;
        public String name;
        public Location location;
        public List<Category> categories = null;
        public Boolean hasPerk;
        public VenuePage venuePage;
    }


    public class VenuePage {
        public String id;
    }
}



