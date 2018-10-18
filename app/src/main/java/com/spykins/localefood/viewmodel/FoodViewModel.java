package com.spykins.localefood.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.spykins.localefood.model.DataFetcher;
import com.spykins.localefood.model.DataFetcherNotifier;
import com.spykins.localefood.model.Restaurant;
import com.spykins.localefood.repository.http.RetrofitProvider;

import java.util.List;

public class FoodViewModel extends AndroidViewModel implements DataFetcherNotifier {
    private MutableLiveData<List<Restaurant>> restaurants = new MutableLiveData<>();
    private MutableLiveData<String> address = new MutableLiveData<>();
    private DataFetcher dataFetcher;
    private RetrofitProvider retrofitProvider;



    //restaurants.postValue when fetched from api

    public FoodViewModel(@NonNull Application application) {
        super(application);
        retrofitProvider = new RetrofitProvider();
        dataFetcher = new DataFetcher(this, retrofitProvider);
    }

    public MutableLiveData<List<Restaurant>> getRestaurants() {
        return restaurants;
    }

    public void searchForRestaurant(double longitude, double latitude) {
        Log.d("FOODVIEW_RESTAURANT", latitude + " : " + longitude);
        dataFetcher.fetchData(latitude, longitude);
    }


    @Override
    public void onSuccess(Restaurant body) {
        Log.d("FOODVIEW_RESTAURANT", body.toString());
    }

    @Override
    public void onFailure(String message) {
        Log.d("FOODVIEW_RESTAURANT", message);
    }
}
