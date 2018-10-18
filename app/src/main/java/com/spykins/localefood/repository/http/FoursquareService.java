package com.spykins.localefood.repository.http;

import com.spykins.localefood.model.Restaurant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoursquareService {
    @GET("v2/venues/search")
    Call<Restaurant> restaurantList(
            @Query("categoryId") String categoryId,
            @Query("client_id") String clientId,
            @Query("client_secret") String clientSecret,
            @Query("ll") String latitudeAndLongitude,
            @Query("v") String version
    );
}
