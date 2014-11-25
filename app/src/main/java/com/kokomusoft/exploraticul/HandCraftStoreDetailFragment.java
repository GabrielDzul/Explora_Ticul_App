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

import com.kokomusoft.exploraticul.Model.HandCraftStore;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A fragment representing a single HandCraftStore detail screen.
 * This fragment is either contained in a {@link HandCraftStoreListActivity}
 * in two-pane mode (on tablets) or a {@link HandCraftStoreDetailActivity}
 * on handsets.
 */
public class HandCraftStoreDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    OnFragmentSendText mSendText;
    private HandCraftStore handCraftStore;
    private ArrayList<HandCraftStore> handCraftStores = new ArrayList<>();
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
    public HandCraftStoreDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        horarios.add("08:00 AM - 02:00PM");
        horarios.add("04:00 PM - 08:00 Pm");

        handCraftStore = new HandCraftStore(getResources().getDrawable(R.drawable.zap01), "Aria Shoes",
                "C 24 x 13 y 13 #345f","9971042362", "http://kokomusoft.com/", horarios, 1);

        handCraftStores.add(handCraftStore);
        handCraftStore = new HandCraftStore(getResources().getDrawable(R.drawable.zap02), "Zoo tycon",
                "C 26 x 15 y 17 #123D","9971128336", "http://kokomusoft.com/", horarios, 2);
        handCraftStores.add(handCraftStore);
        handCraftStore = new HandCraftStore(getResources().getDrawable(R.drawable.zap03), "Bella brand",
                "C 26 x 15 y 17 #123D", "9971128385", "http://kokomusoft.com/", horarios, 3);
        handCraftStores.add(handCraftStore);
        handCraftStore = new HandCraftStore(getResources().getDrawable(R.drawable.zap02), "Amaga collection",
                "C 26 x 15 y 17 #123D", "9971042362", "http://kokomusoft.com/", horarios, 4);
        handCraftStores.add(handCraftStore);
        handCraftStore = new HandCraftStore(getResources().getDrawable(R.drawable.zap05), "Umbrella shoes",
                "C 26 x 15 y 17 #123D", "9971128385", "http://kokomusoft.com/", horarios ,5);
        handCraftStores.add(handCraftStore);
        handCraftStore = new HandCraftStore(getResources().getDrawable(R.drawable.superior_room2), "Clarsie shoe Stores",
                "C 26 x 15 y 17 #123D" , "9971128385", "http://kokomusoft.com/", horarios, 6);
        handCraftStores.add(handCraftStore);
        handCraftStore = new HandCraftStore(getResources().getDrawable(R.drawable.superior_room), "Maggie tycon",
                "C 26 x 15 y 17 #123D", "9971128385", "http://kokomusoft.com/", horarios ,7);
        handCraftStores.add(handCraftStore);
        handCraftStore = new HandCraftStore(getResources().getDrawable(R.drawable.superior_room2), "Rose ambar",
                "C 26 x 15 y 17 #123D" , "9971128385", "http://kokomusoft.com/", horarios ,8);
        handCraftStores.add(handCraftStore);
        handCraftStore = new HandCraftStore(getResources().getDrawable(R.drawable.superior_room), "Press Zon",
                "C 26 x 15 y 17 #123D", "9971128385", "http://kokomusoft.com/", horarios ,0);
        handCraftStores.add(handCraftStore);

        Collections.sort(handCraftStores);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            for (HandCraftStore aHandCraftStore: handCraftStores){
                if (aHandCraftStore.getHandCraftStoreId() == Integer.valueOf(getArguments().getString(ARG_ITEM_ID))){
                    handCraftStore = aHandCraftStore;
                    break;
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_handcraftstore_detail, container, false);

        if (handCraftStore != null) {
            ((ImageView) rootView.findViewById(R.id.handCraftStoreImage)).setImageDrawable(handCraftStore.getImage());
            ((TextView) rootView.findViewById(R.id.handCraftStoreNametTxtView)).setText(handCraftStore.getName());
            ((TextView) rootView.findViewById(R.id.handCraftStoreAdressTextView)).setText(handCraftStore.getAddress());

            Button phoneButton = (Button)rootView.findViewById(R.id.phoneNumber);
            phoneButton.setText(handCraftStore.getPhoneNumber());
            phoneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL );
                    callIntent.setData(Uri.parse("tel:" + handCraftStore.getPhoneNumber()));
                    startActivity(callIntent);
                }
            });

            Button webSiteButton = (Button)rootView.findViewById(R.id.webSiteUrl);
            String shortUrl = shortUrl(handCraftStore.getWebsite());
            webSiteButton.setText(shortUrl);
            webSiteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    mSendText.onSendText(handCraftStore.getWebsite());

                    WebViewFragment webViewFragment = new WebViewFragment();
                    bundle.putString("text", handCraftStore.getWebsite());
                    webViewFragment.setArguments(bundle);
                    FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transaction.replace(R.id.handcraftstore_detail_container, webViewFragment, "webViewFragment");
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
                    transaction.replace(R.id.handcraftstore_detail_container, mapsFragment, "mapsFragment");
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
