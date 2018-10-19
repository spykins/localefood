package com.spykins.localefood.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.spykins.localefood.R;
import com.spykins.localefood.utils.Constants;

public class RestaurantDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        String name = getIntent().getStringExtra(Constants.INTENT_FOR_NAME);
        String address = getIntent().getStringExtra(Constants.INTENT_FOR_ADDRESS);
        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView addressTextView = findViewById(R.id.addressTextView);
        nameTextView.setText(name);
        addressTextView.setText(address);
    }
}
