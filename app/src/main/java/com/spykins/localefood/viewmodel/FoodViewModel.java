package com.spykins.localefood.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.spykins.localefood.model.DataFetcher;
import com.spykins.localefood.model.DataFetcherNotifier;
import com.spykins.localefood.model.Venue;
import com.spykins.localefood.repository.http.RetrofitProvider;

import java.util.List;

public class FoodViewModel extends AndroidViewModel implements DataFetcherNotifier {
    private MutableLiveData<List<Venue>> venues = new MutableLiveData<>();
    private MutableLiveData<String> address = new MutableLiveData<>();
    private DataFetcher dataFetcher;
    private RetrofitProvider retrofitProvider;

    public FoodViewModel(@NonNull Application application) {
        super(application);
        retrofitProvider = new RetrofitProvider();
        dataFetcher = new DataFetcher(this, retrofitProvider);
    }

    public MutableLiveData<List<Venue>> getRestaurants() {
        return venues;
    }

    public void searchForRestaurant(double longitude, double latitude) {
        dataFetcher.fetchData(latitude, longitude);
    }


    @Override
    public void onSuccess(List<Venue> venues) {
        this.venues.postValue(venues);
    }

    @Override
    public void onFailure(String message) {
        Log.d("RESTAURANT_ERROR", message);
    }
}
