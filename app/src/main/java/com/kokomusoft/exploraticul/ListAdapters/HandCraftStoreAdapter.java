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

import com.kokomusoft.exploraticul.Model.HandCraftStore;
import com.kokomusoft.exploraticul.R;

import java.util.ArrayList;

/**
 * Created by Gabriel on 30/10/2014.
 */
public class HandCraftStoreAdapter extends BaseAdapter implements Filterable {
    protected Activity activity;
    protected ArrayList<HandCraftStore> handCraftStores;
    protected HandCraftStoresFilter filter;

    public HandCraftStoreAdapter(Activity activity, ArrayList<HandCraftStore> handCraftStores) {
        this.activity = activity;
        this.handCraftStores = handCraftStores;
    }

    @Override
    public int getCount() {
        return handCraftStores.size();
    }

    @Override
    public Object getItem(int position) {
        return handCraftStores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return handCraftStores.get(position).getHandCraftStoreId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HandCraftStoreViewHolder viewHolder;
        View view = convertView;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)activity.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.handcraft_stores_list_style, null);
            viewHolder = new HandCraftStoreViewHolder();
            viewHolder.name = (TextView)view.findViewById(R.id.list_Item_Name);
            viewHolder.address = (TextView)view.findViewById(R.id.list_Item_Address);
            viewHolder.image = (ImageView)view.findViewById(R.id.list_Image_item);
            view.setTag(viewHolder);
        }
        //Create a shoestore Object
        HandCraftStore handCraftStore = handCraftStores.get(position);

        //Fill the information with the shoeStoreViewHolder
        viewHolder = (HandCraftStoreViewHolder)view.getTag();
        viewHolder.name.setText(handCraftStore.getName());
        viewHolder.address.setText(handCraftStore.getAddress());
        viewHolder.image.setImageDrawable(handCraftStore.getImage());

        return view;

    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter = new HandCraftStoresFilter();
        }
        return filter;
    }

    private static class HandCraftStoreViewHolder {
        public TextView name;
        public TextView address;
        public ImageView image;

    }

    private class HandCraftStoresFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();

            if (charSequence == null || charSequence.length() == 0){
                filterResults.values = handCraftStores;
                filterResults.count = handCraftStores.size();
            }
            else {
                ArrayList<HandCraftStore> filteredHandCraftStores = new ArrayList<>();
                for (HandCraftStore handCraftStore: handCraftStores){
                    if (handCraftStore.getName().toUpperCase().startsWith(charSequence.toString().toUpperCase())){
                        filteredHandCraftStores.add(handCraftStore);
                    }

                }

                filterResults.values = filteredHandCraftStores;
                filterResults.count = filteredHandCraftStores.size();
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0){
                notifyDataSetInvalidated();
            }
            else{
                handCraftStores = (ArrayList<HandCraftStore>)results.values;
                notifyDataSetChanged();
            }
        }
    }

}
