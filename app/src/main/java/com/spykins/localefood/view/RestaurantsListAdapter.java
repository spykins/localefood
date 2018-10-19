package com.spykins.localefood.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spykins.localefood.R;
import com.spykins.localefood.model.Venue;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsListAdapter extends RecyclerView.Adapter<RestaurantsListAdapter.RestaurantViewHolder> {
    List<Venue> venues;
    public RestaurantsListAdapter() {
        venues = new ArrayList<Venue>();
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_item, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder restaurantViewHolder, int i) {
        restaurantViewHolder.bind(this.venues.get(i));
    }

    public void setRestaurant(List<Venue> venue) {
        this.venues = venue;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return venues.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder{

        private TextView restaurantAddress;
        private TextView restaurantName;
        private TextView restaurantDistance;


        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantAddress = itemView.findViewById(R.id.restaurant_address);
            restaurantName = itemView.findViewById(R.id.restaurant_name);
            restaurantDistance = itemView.findViewById(R.id.restaurant_distance);
        }

        public void bind(Venue venue) {
            restaurantAddress.setText(venue.location.address);
            restaurantName.setText(venue.name);
            restaurantDistance.setText(String.format("%d m", venue.location.distance));

        }
    }
}
