package com.example.tomek.howlongapp.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tomek.howlongapp.BuildConfig;
import com.example.tomek.howlongapp.R;
import com.example.tomek.howlongapp.data.model.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by tslabiak on 03.03.2018.
 */

public class RestaurantsAdapter extends ArrayAdapter<Restaurant> implements Filterable{


    private List<Restaurant> mRestaurants = new ArrayList<Restaurant>();
    private List<Restaurant> filteredRestaurants = new ArrayList<Restaurant>();
    private ItemFilter mFilter = new ItemFilter();

    @Inject
    public RestaurantsAdapter(Context context) {
        super(context, 0);
        this.mRestaurants = new ArrayList<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Restaurant restaurant = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_restaurant, parent, false);
        }
        // Lookup view for data population
        ImageView image = convertView.findViewById(R.id.imageView);
        TextView name = convertView.findViewById(R.id.restaurant_name);
        TextView address = convertView.findViewById(R.id.address);
        TextView wait_time = convertView.findViewById(R.id.wait_time);
        // Populate the data into the template view using the data object
        String reference = restaurant.getPhotoReference();
        String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=100&photoreference=" +reference +"&key=" + BuildConfig.GoogleSecAPIKEY.toString();
        Picasso.get().load(url).into(image);
        name.setText(restaurant.getName());
        address.setText(restaurant.getAddress());
        wait_time.setText(Integer.toString(restaurant.getMean()));
        // Return the completed view to render on screen
        return convertView;
    }


    public int getCount() {
        return filteredRestaurants.size();
    }

    public Restaurant getItem(int position) {
        return filteredRestaurants.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public List<Restaurant> getmRestaurants() {
        return mRestaurants;
    }

    public void setmRestaurants(List<Restaurant> mRestaurants) {
        this.mRestaurants = mRestaurants;
        this.filteredRestaurants = mRestaurants;
        this.addAll(mRestaurants);
    }


    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<Restaurant> list = mRestaurants;

            int count = list.size();
            final ArrayList<Restaurant> nlist = new ArrayList<Restaurant>(count);

            Restaurant filterableRestaurant ;

            for (int i = 0; i < count; i++) {
                filterableRestaurant = list.get(i);
                if (filterableRestaurant.getName().toLowerCase().contains(filterString)) {
                    nlist.add(filterableRestaurant);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredRestaurants = (ArrayList<Restaurant>) results.values;
            notifyDataSetChanged();
        }

    }





}