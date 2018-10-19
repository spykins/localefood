package com.spykins.localefood.model;

import com.spykins.localefood.BuildConfig;
import com.spykins.localefood.repository.http.RetrofitProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataFetcher {
    private  DataFetcherNotifier dataFetcherNotifier;
    private RetrofitProvider retrofitProvider;

    public DataFetcher(DataFetcherNotifier dataFetcherNotifier, RetrofitProvider retrofitProvider) {
        this.dataFetcherNotifier = dataFetcherNotifier;
        this.retrofitProvider = retrofitProvider;
    }

    public void fetchData(double latitude, double longitude) {
        String fourSquareClientId = BuildConfig.FoursquareClientId;
        String fourSquareClientSecret = BuildConfig.FoursquareClientSecret;

        retrofitProvider.provideForsquareService()
                .restaurantList("4d4b7105d754a06374d81259",
                        fourSquareClientId,
                        fourSquareClientSecret,
                            latitude + "," + longitude,
                        "20181017",
                        10
                        )
                .enqueue(new Callback<Restaurant>() {
                    @Override
                    public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                        if (response.isSuccessful()) {
                            dataFetcherNotifier.onSuccess(response.body().response.venues);
                        }
                    }

                    @Override
                    public void onFailure(Call<Restaurant> call, Throwable t) {
                        dataFetcherNotifier.onFailure(t.getMessage());
                    }
                });
    }
}
