package com.kokomusoft.exploraticul;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.kokomusoft.exploraticul.Model.Restaurant;

import java.util.ArrayList;

/**
 * A fragment representing a single Restaurant detail screen.
 * This fragment is either contained in a {@link RestaurantListActivity}
 * in two-pane mode (on tablets) or a {@link RestaurantDetailActivity}
 * on handsets.
 */
public class RestaurantDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    OnFragmentSendText mSendText;
    private Restaurant restaurant;
    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    private ArrayList<String> horarios = new ArrayList<>();

    public interface OnFragmentSendText{
        public void onSendText(String text);
    }

    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            mSendText = (OnFragmentSendText)activity;
        } catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + " must implement OnFragmentSendText");
        }
    }



    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RestaurantDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        horarios.add("08:00 AM - 02:00PM");
        horarios.add("04:00 PM - 08:00 Pm");

        restaurant = new Restaurant(getResources().getDrawable(R.drawable.zap01), "Mariscos" ,
                "Aria Shoes", "C 24 x 13 y 13 #345f","9971042362", "http://kokomusoft.com/", horarios, 1);
        restaurants.add(restaurant);

        restaurant = new Restaurant(getResources().getDrawable(R.drawable.zap02),
                "Comida Regional", "Zoo tycon","C 26 x 15 y 17 #123D","9971128336",
                "http://kokomusoft.com/", horarios, 2);
        restaurants.add(restaurant);

        restaurant = new Restaurant(getResources().getDrawable(R.drawable.zap03), "Mariscos",
                "Bella brand", "C 26 x 15 y 17 #123D", "9971128385", "http://kokomusoft.com/", horarios, 3);
        restaurants.add(restaurant);

        restaurant = new Restaurant(getResources().getDrawable(R.drawable.zap02), "Comida Regioanl",
                "Amaga collection", "C 26 x 15 y 17 #123D", "9971042362", "http://kokomusoft.com/", horarios, 4);
        restaurants.add(restaurant);

        restaurant = new Restaurant(getResources().getDrawable(R.drawable.zap05), "Comida China",
                "Umbrella shoes", "C 26 x 15 y 17 #123D", "9971128385", "http://kokomusoft.com/", horarios ,5);
        restaurants.add(restaurant);

        restaurant = new Restaurant(getResources().getDrawable(R.drawable.superior_room2),
                "Buffet y parrilla", "Clarsie shoe Stores","C 26 x 15 y 17 #123D" , "9971128385",
                "http://kokomusoft.com/", horarios, 6);
        restaurants.add(restaurant);


        if (getArguments().containsKey(ARG_ITEM_ID)) {
            for (Restaurant aRestaurant: restaurants){
                if (aRestaurant.getRestaurantId() == Integer.valueOf(getArguments().getString(ARG_ITEM_ID))){
                    restaurant = aRestaurant;
                    break;
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_restaurant_detail, container, false);

        if (restaurant != null) {
            ((ImageView) rootView.findViewById(R.id.restaurantImage)).setImageDrawable(restaurant.getImage());
            ((TextView) rootView.findViewById(R.id.restaurantNametTxtView)).setText(restaurant.getName());
            ((TextView) rootView.findViewById(R.id.restaurantDescription)).setText(restaurant.getDescription());
            ((TextView) rootView.findViewById(R.id.restaurantAdressTextView)).setText(restaurant.getAddress());

            Button phoneButton = (Button)rootView.findViewById(R.id.phoneNumber);
            phoneButton.setText(restaurant.getPhoneNumber());
            phoneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + restaurant.getPhoneNumber()));
                    startActivity(callIntent);
                }
            });

            Button webSiteButton = (Button)rootView.findViewById(R.id.webSiteUrl);
            String shortUrl = shortUrl(restaurant.getWebsite());
            webSiteButton.setText(shortUrl);
            webSiteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    mSendText.onSendText(restaurant.getWebsite());

                    WebViewFragment webViewFragment = new WebViewFragment();
                    bundle.putString("text", restaurant.getWebsite());
                    webViewFragment.setArguments(bundle);
                    FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transaction.replace(R.id.restaurant_detail_container, webViewFragment, "webViewFragment");
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
            });

            rootView.findViewById(R.id.howToGetButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GoogleMapsFragment mapsFragment = new GoogleMapsFragment();
                    FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
                    transaction.setTransition(FragmentTransaction.TRANSIT_UNSET);
                    transaction.replace(R.id.restaurant_detail_container, mapsFragment, "mapsFragment");
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });

        }

        return rootView;
    }

    private String shortUrl(String url){
        String urlToShort = url;
        String shortenedUrl;
        shortenedUrl = urlToShort.substring(7);
        return shortenedUrl;
    }
}
