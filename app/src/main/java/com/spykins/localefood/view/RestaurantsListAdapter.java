package com.spykins.localefood.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
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
    private List<Venue> venues;
    private RestaurantItemClicked restaurantItemClicked;

    public RestaurantsListAdapter(RestaurantItemClicked restaurantItemClicked) {
        venues = new ArrayList<Venue>();
        this.restaurantItemClicked = restaurantItemClicked;
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
        final Venue currentVenue = this.venues.get(i);
        restaurantViewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurantItemClicked.onRestaurantItemClicked(currentVenue);
            }
        });
        restaurantViewHolder.bind(currentVenue);
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
        private CardView rootView;


        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantAddress = itemView.findViewById(R.id.restaurant_address);
            restaurantName = itemView.findViewById(R.id.restaurant_name);
            restaurantDistance = itemView.findViewById(R.id.restaurant_distance);
            rootView = itemView.findViewById(R.id.card_view);
        }

        public void bind(Venue venue) {
            restaurantAddress.setText(venue.location.address);
            restaurantName.setText(venue.name);
            restaurantDistance.setText(String.format("%d m", venue.location.distance));

        }
    }
}
