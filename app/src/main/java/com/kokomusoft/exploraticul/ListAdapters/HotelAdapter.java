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

import com.kokomusoft.exploraticul.Model.Hotel;
import com.kokomusoft.exploraticul.R;

import java.util.ArrayList;

/**
 * Created by Gabriel on 02/11/2014.
 */
public class HotelAdapter extends BaseAdapter implements Filterable{
    protected Activity activity;
    protected ArrayList<Hotel> hotels;
    private HotelFilter hotelFilter;

    public HotelAdapter(Activity activity, ArrayList<Hotel> hotels) {
        this.activity = activity;
        this.hotels = hotels;
    }

    @Override
    public int getCount() {
        return hotels.size();
    }

    @Override
    public Object getItem(int position) {
        return hotels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return hotels.get(position).getHotelId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HotelViewHolder hotelViewHolder;
        View view = convertView;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)activity.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.hotel_list_style, null);
            hotelViewHolder = new HotelViewHolder();
            hotelViewHolder.name = (TextView)view.findViewById(R.id.list_Item_Name);
            hotelViewHolder.address = (TextView)view.findViewById(R.id.list_Item_Address);
            hotelViewHolder.basePrice = (TextView)view.findViewById(R.id.list_item_base_price);
            hotelViewHolder.image = (ImageView)view.findViewById(R.id.list_Image_item);
            view.setTag(hotelViewHolder);
        }
        //Create a restaurant Object
        Hotel hotel = hotels.get(position);

        //Fill the information with the restaurantViewHolder
        hotelViewHolder = (HotelViewHolder)view.getTag();
        hotelViewHolder.name.setText(hotel.getName());
        hotelViewHolder.address.setText(hotel.getAddress());
        hotelViewHolder.basePrice.setText("$ " + String.valueOf(hotel.getBasePrice()));
        hotelViewHolder.image.setImageDrawable(hotel.getImage());

        return view;
    }

    @Override
    public Filter getFilter() {
        if (hotelFilter == null){
            hotelFilter = new HotelFilter();
        }
        return hotelFilter;
    }

    private class HotelViewHolder{
        public TextView name;
        public TextView address;
        public TextView basePrice;
        public ImageView image;
    }

    private class HotelFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();

            if (charSequence == null || charSequence.length() == 0){
                filterResults.values = hotels;
                filterResults.count = hotels.size();
            }
            else {
                ArrayList<Hotel> filteredRestaurants = new ArrayList<>();
                for (Hotel hotel: hotels){
                    if (hotel.getName().toUpperCase().startsWith(charSequence.toString().toUpperCase())){
                        filteredRestaurants.add(hotel);
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
                hotels = (ArrayList<Hotel>)results.values;
                notifyDataSetChanged();
            }
        }
    }
}
