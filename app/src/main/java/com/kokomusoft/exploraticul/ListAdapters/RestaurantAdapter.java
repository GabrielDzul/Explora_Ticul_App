package com.kokomusoft.exploraticul.ListAdapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.kokomusoft.exploraticul.Model.Restaurant;
import com.kokomusoft.exploraticul.R;

import java.util.ArrayList;

/**
 * Created by Gabriel on 01/11/2014.
 */
public class RestaurantAdapter extends BaseAdapter implements Filterable{
    protected Activity activity;
    protected ArrayList<Restaurant> restaurants;
    private RestaurantsFilter restaurantsFilter;

    public RestaurantAdapter(Activity activity, ArrayList<Restaurant> restaurants) {
        this.activity = activity;
        this.restaurants = restaurants;
    }

    @Override
    public int getCount() {
        return restaurants.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurants.get(position);
    }

    @Override
    public long getItemId(int position) {
        return restaurants.get(position).getRestaurantId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RestaurantViewHolder restaurantViewHolder;
        //Generate a convertview for efficiency
        View view = convertView;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)activity.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.restaurants_list_style, null);
            restaurantViewHolder = new RestaurantViewHolder();
            restaurantViewHolder.name = (TextView)view.findViewById(R.id.list_Item_Name);
            restaurantViewHolder.address = (TextView)view.findViewById(R.id.list_Item_Address);
            restaurantViewHolder.description = (TextView)view.findViewById(R.id.list_item_description);
            restaurantViewHolder.image = (ImageView)view.findViewById(R.id.list_Image_item);
            view.setTag(restaurantViewHolder);
        }
        //Create a restaurant Object
        Restaurant restaurant = restaurants.get(position);

        //Fill the information with the restaurantViewHolder
        restaurantViewHolder = (RestaurantViewHolder)view.getTag();
        restaurantViewHolder.name.setText(restaurant.getName());
        restaurantViewHolder.address.setText(restaurant.getAddress());
        restaurantViewHolder.description.setText(restaurant.getDescription());
        restaurantViewHolder.image.setImageDrawable(restaurant.getImage());

        return view;

    }

    @Override
    public Filter getFilter() {
        if (restaurantsFilter == null){
            restaurantsFilter = new RestaurantsFilter();
        }
        return restaurantsFilter;
    }

    private static class RestaurantViewHolder {
        public TextView name;
        public TextView address;
        public TextView description;
        public ImageView image;

    }

    private class RestaurantsFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();

            if (charSequence == null || charSequence.length() == 0){
                filterResults.values = restaurants;
                filterResults.count = restaurants.size();
            }
            else {
                ArrayList<Restaurant> filteredRestaurants = new ArrayList<>();
                for (Restaurant restaurant: restaurants){
                    if (restaurant.getName().toUpperCase().startsWith(charSequence.toString().toUpperCase())){
                        filteredRestaurants.add(restaurant);
                    }

                }

                filterResults.values = filteredRestaurants;
                filterResults.count = filteredRestaurants.size();
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0){
                notifyDataSetInvalidated();
            }
            else{
                restaurants = (ArrayList<Restaurant>)results.values;
                notifyDataSetChanged();
            }
        }
    }
}
