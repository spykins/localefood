package com.spykins.localefood.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    public Response response = new Response();
    public class Response {
        public List<Venue> venues = new ArrayList<>();
    }
}



