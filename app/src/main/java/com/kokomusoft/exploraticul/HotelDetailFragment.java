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


import com.kokomusoft.exploraticul.Model.Hotel;

import java.util.ArrayList;

/**
 * A fragment representing a single Hotel detail screen.
 * This fragment is either contained in a {@link HotelListActivity}
 * in two-pane mode (on tablets) or a {@link HotelDetailActivity}
 * on handsets.
 */
public class HotelDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    OnFragmentSendText mSendText;
    private Hotel hotel;
    private ArrayList<Hotel> hotels = new ArrayList<>();

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
    public HotelDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        hotel = new Hotel(getResources().getDrawable(R.drawable.zap01), 180.00 ,
                "Aria Shoes", "C 24 x 13 y 13 #345f","9971042362", "http://kokomusoft.com/", 1);
        hotels.add(hotel);

        hotel = new Hotel(getResources().getDrawable(R.drawable.zap02),
                220.00, "Zoo tycon","C 26 x 15 y 17 #123D","9971128336",
                "http://kokomusoft.com/", 2);
        hotels.add(hotel);

        hotel = new Hotel(getResources().getDrawable(R.drawable.zap03), 310.00,
                "Bella brand", "C 26 x 15 y 17 #123D", "9971128385", "http://kokomusoft.com/", 3);
        hotels.add(hotel);

        hotel = new Hotel(getResources().getDrawable(R.drawable.zap02), 180.00,
                "Amaga collection", "C 26 x 15 y 17 #123D", "9971042362", "http://kokomusoft.com/", 4);
        hotels.add(hotel);

        hotel = new Hotel(getResources().getDrawable(R.drawable.zap05), 400.00,
                "Umbrella shoes", "C 26 x 15 y 17 #123D", "9971128385", "http://kokomusoft.com/", 5);
        hotels.add(hotel);

        hotel = new Hotel(getResources().getDrawable(R.drawable.superior_room2),
                350.00, "Clarsie shoe Stores","C 26 x 15 y 17 #123D" , "9971128385",
                "http://kokomusoft.com/", 6);
        hotels.add(hotel);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            for (Hotel aHotel: hotels){
                if (aHotel.getHotelId() == Integer.valueOf(getArguments().getString(ARG_ITEM_ID))){
                    hotel = aHotel;
                    break;
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hotel_detail, container, false);

        if (hotel != null) {
            ((ImageView) rootView.findViewById(R.id.hotelImage)).setImageDrawable(hotel.getImage());
            ((TextView) rootView.findViewById(R.id.hotelNametTxtView)).setText(hotel.getName());
            ((TextView) rootView.findViewById(R.id.hotelBasePriceTextView)).setText("$ " + hotel.getBasePrice());
            ((TextView) rootView.findViewById(R.id.hotelAdressTextView)).setText(hotel.getAddress());

            Button phoneButton = (Button)rootView.findViewById(R.id.phoneNumber);
            phoneButton.setText(hotel.getPhoneNumber());
            phoneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + hotel.getPhoneNumber()));
                    startActivity(callIntent);
                }
            });

            Button webSiteButton = (Button)rootView.findViewById(R.id.webSiteUrl);
            String shortUrl = shortUrl(hotel.getWebsite());
            webSiteButton.setText(shortUrl);
            webSiteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    mSendText.onSendText(hotel.getWebsite());

                    WebViewFragment webViewFragment = new WebViewFragment();
                    bundle.putString("text", hotel.getWebsite());
                    webViewFragment.setArguments(bundle);
                    FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transaction.replace(R.id.hotel_detail_container, webViewFragment, "webViewFragment");
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
                    transaction.replace(R.id.hotel_detail_container, mapsFragment, "mapsFragment");
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
