package com.spykins.localefood;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.spykins.localefood.model.Restaurant;
import com.spykins.localefood.model.Venue;
import com.spykins.localefood.repository.FetchAddressIntentService;
import com.spykins.localefood.utils.Constants;
import com.spykins.localefood.view.RestaurantsListAdapter;
import com.spykins.localefood.viewmodel.FoodViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FoodViewModel foodViewModel;
    private EditText addressText;
    private RecyclerView recyclerView;
    private RestaurantsListAdapter restaurantsListAdapter;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addressText = findViewById(R.id.address_text);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        restaurantsListAdapter = new RestaurantsListAdapter();
        recyclerView.setAdapter(restaurantsListAdapter);


        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        subscribe();
    }

    private void registerBroadCastReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action != null && action.equals(Constants.RECEIVER)) {
                    int result = intent.getIntExtra(Constants.RESULT_DATA_KEY, 0);
                    if (result == Constants.SUCCESS_RESULT) {
                        double longitude = intent.getDoubleExtra(Constants.LONGITUDE, 0l);
                        double latitude = intent.getDoubleExtra(Constants.LATITUDE, 0l);
                        foodViewModel.searchForRestaurant(longitude, latitude);
                    } else {
                        String error = intent.getStringExtra(Constants.LOCATION_ERROR);
                        notifyUserOfError(error);
                    }
                }
            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter(Constants.RECEIVER));
    }

    private void notifyUserOfError(String error) {
        Toast.makeText(getApplication(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerBroadCastReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }

    private void subscribe() {

        final Observer<List<Venue>> restaurants = new Observer<List<Venue>>() {
            @Override
            public void onChanged(@Nullable List<Venue> venues) {
                restaurantsListAdapter.setRestaurant(venues);
            }
        };

        foodViewModel.getRestaurants().observe(this, restaurants);
    }

    public void onsubmit(View view) {
        String address = addressText.getText().toString();
        if (address != null && !address.isEmpty()) {
            startServiceToFetchCoordinate(address);
        }
    }

    private void startServiceToFetchCoordinate(String address) {
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        intent.putExtra(Constants.LOCATION_ADDRESS, address);
        startService(intent);
    }

}
