package com.spykins.localefood.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.spykins.localefood.model.Restaurant;

import java.util.List;

public class FoodViewModel extends AndroidViewModel {
    private MutableLiveData<List<Restaurant>> restaurants = new MutableLiveData<>();
    private MutableLiveData<String> address = new MutableLiveData<>();

    //restaurants.postValue when fetched from api

    public FoodViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Restaurant>> getRestaurants() {
        return restaurants;
    }

    public void searchForRestaurant(double longitude, double latitude) {
        Log.d("FOODVIEW_RESTAURANT", latitude + " : " + longitude);

    }
}
