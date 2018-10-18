package com.spykins.localefood.repository;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.Nullable;

import com.spykins.localefood.R;
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

        String queryAddress = intent.getStringExtra(Constants.LOCATION_ADDRESS);

        Geocoder gc = new Geocoder(this);
        if(Geocoder.isPresent()) {
            List<Address> list = null;
            try {
                list = gc.getFromLocationName(queryAddress, 1);
                Address address = list.get(0);
                double lat = address.getLatitude();
                double lng = address.getLongitude();
                deliverResultToReceiver(Constants.SUCCESS_RESULT, lat, lng, "" );
            } catch (IndexOutOfBoundsException e) {
                deliverResultToReceiver(Constants.FAILURE_RESULT, 0l,
                        0l, getString(R.string.address_error));
            } catch (IOException e) {
                e.printStackTrace();
                deliverResultToReceiver(Constants.FAILURE_RESULT, 0l,
                        0l, getString(R.string.system_error));
            }
        } else {
            deliverResultToReceiver(Constants.FAILURE_RESULT, 0l,
                    0l, getString(R.string.location_off));
        }
    }

    private void deliverResultToReceiver(int resultCode, double latitude, double longitude, String error) {
        Intent intent = new Intent();
        intent.setAction(Constants.RECEIVER);
        intent.putExtra(Constants.LATITUDE, latitude);
        intent.putExtra(Constants.LONGITUDE, longitude);
        intent.putExtra(Constants.RESULT_DATA_KEY, resultCode);
        intent.putExtra(Constants.LOCATION_ERROR, error);
        sendBroadcast(intent);
    }
}
