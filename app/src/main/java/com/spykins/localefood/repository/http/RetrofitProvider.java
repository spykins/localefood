package com.spykins.localefood.repository.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {

    private Retrofit retrofit = null;

    public Retrofit provideRetrofit() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.foursquare.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public FoursquareService provideForsquareService() {
        return provideRetrofit().create(FoursquareService.class);
    }
}
