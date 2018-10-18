package com.spykins.localefood;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.spykins.localefood.model.Restaurant;
import com.spykins.localefood.viewmodel.FoodViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FoodViewModel foodViewModel;
    private EditText addressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addressText = findViewById(R.id.address_text);

        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        subscribe();
    }

    private void subscribe() {

        final Observer<List<Restaurant>> restaurants = new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(@Nullable List<Restaurant> restaurants) {
                //Log.d("RESTAURANR", restaurants.toString());
            }
        };

        foodViewModel.getRestaurants().observe(this, restaurants);
    }

    public void onsubmit(View view) {

    }
}
