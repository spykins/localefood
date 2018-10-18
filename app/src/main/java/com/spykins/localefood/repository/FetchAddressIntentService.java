package com.spykins.localefood.repository;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.spykins.localefood.utils.Constants;

import java.io.IOException;
import java.util.List;

public class FetchAddressIntentService extends IntentService {

    public FetchAddressIntentService() {
        super("FetchAddressIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent == null) {
            return;
        }
        String errorMessage = "";
        String queryAddress = intent.getStringExtra(Constants.LOCATION_ADDRESS);

        Geocoder gc = new Geocoder(this);
        if(Geocoder.isPresent()) {

            List<Address> list = null;
            try {
                list = gc.getFromLocationName(queryAddress, 1);
                Address address = list.get(0);
                double lat = address.getLatitude();
                double lng = address.getLongitude();
                Log.d("MY_COORDINATE", lat + " :" + lng);
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("MY_COORDINATE", e.getMessage());

            }
        }

    }
}
