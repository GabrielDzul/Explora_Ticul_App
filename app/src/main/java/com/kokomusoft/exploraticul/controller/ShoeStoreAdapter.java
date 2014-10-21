package com.kokomusoft.exploraticul.controller;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.kokomusoft.exploraticul.Model.ShoeStore;
import com.kokomusoft.exploraticul.R;

import java.util.ArrayList;

/**
 * Created by Gabriel on 13/10/2014.
 */
public class ShoeStoreAdapter extends BaseAdapter implements Filterable{
    protected Activity activity;
    protected ArrayList<ShoeStore> shoeStores;

    ShoeStoreFilter shoeStoreFilter;

    public ShoeStoreAdapter(Activity activity, ArrayList<ShoeStore> shoeStores) {
        this.activity = activity;
        this.shoeStores = shoeStores;
    }

    @Override
    public int getCount() {
        return shoeStores.size();
    }

    @Override
    public Object getItem(int arg0) {
        return shoeStores.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return shoeStores.get(position).getShoeStoreId();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //viewHolder object
        ShoeStoreViewHolder shoeStoreViewHolder;
        //Generate a convertview for efficiency
        View view = convertView;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)activity.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.shoe_stores_list_style, null);
            shoeStoreViewHolder = new ShoeStoreViewHolder();
            shoeStoreViewHolder.name = (TextView)view.findViewById(R.id.List_Item_Name);
            shoeStoreViewHolder.address = (TextView)view.findViewById(R.id.List_Item_Address);
            shoeStoreViewHolder.image = (ImageView)view.findViewById(R.id.List_Image_item);
            view.setTag(shoeStoreViewHolder);
        }
        //Create a shoestore Object
        ShoeStore shoeStore = shoeStores.get(position);

        //Fill the information with the shoeStoreViewHolder
        shoeStoreViewHolder = (ShoeStoreViewHolder)view.getTag();
        shoeStoreViewHolder.name.setText(shoeStore.getName());
        shoeStoreViewHolder.address.setText(shoeStore.getAddress());
        shoeStoreViewHolder.image.setImageDrawable(shoeStore.getImage());

        return view;
    }

    @Override
    public Filter getFilter() {
        if (shoeStoreFilter == null){
            shoeStoreFilter = new ShoeStoreFilter();
        }
        return shoeStoreFilter;
    }

    private static class ShoeStoreViewHolder {
        public TextView name;
        public TextView address;
        public ImageView image;

    }

    private class ShoeStoreFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            if (charSequence == null || charSequence.length() == 0){
                filterResults.values = shoeStores;
                filterResults.count = shoeStores.size();
                Log.d("Evento", "Me quedue en el primer if :(");
            }
            else {
                ArrayList<ShoeStore> filteredShoeStores = new ArrayList<ShoeStore>();
                Log.d("Evento", "Entre al else del pirmer filter");
                for (ShoeStore shoeStore: shoeStores){
                    if (shoeStore.getName().toUpperCase().startsWith(charSequence.toString().toUpperCase())){
                        filteredShoeStores.add(shoeStore);
                    }

                }

                filterResults.values = filteredShoeStores;
                Log.d("Evento", filteredShoeStores.toString());
                filterResults.count = filteredShoeStores.size();
                Log.d("Tamano arreglo", String.valueOf(filteredShoeStores.size()));
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            if (filterResults.count == 0){
                notifyDataSetInvalidated();
            }
            else{
                shoeStores = (ArrayList<ShoeStore>)filterResults.values;
                notifyDataSetChanged();
            }

        }
    }
}


