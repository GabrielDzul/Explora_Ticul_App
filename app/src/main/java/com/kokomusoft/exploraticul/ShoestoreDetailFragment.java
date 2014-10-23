package com.kokomusoft.exploraticul;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.kokomusoft.exploraticul.Model.ShoeStore;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A fragment representing a single Shoe store detail screen.
 * This fragment is either contained in a {@link ShoestoreListActivity}
 * in two-pane mode (on tablets) or a {@link ShoestoreDetailActivity}
 * on handsets.
 */
public class ShoestoreDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    OnFragmentSendText mSendText;
    private ShoeStore shoeStore;
    private ArrayList<ShoeStore> shoeStores = new ArrayList<>();
    ArrayList<String> horarios = new ArrayList<>();

    public interface OnFragmentSendText{
        public void onSendText(String text);
    }


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ShoestoreDetailFragment() {
    }

    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            mSendText = (OnFragmentSendText)activity;
        } catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + " must implement OnFragmentSendText");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        horarios.add("08:00 AM - 02:00PM");
        horarios.add("04:00 PM - 08:00 Pm");

        shoeStore = new ShoeStore(getResources().getDrawable(R.drawable.zap01), "Aria Shoes",
                "C 24 x 13 y 13 #345f","9971042362", "http://kokomusoft.com/", "https://www.facebook.com/KokomuSoftworks",
                horarios, 1);

        shoeStores.add(shoeStore);
        shoeStore = new ShoeStore(getResources().getDrawable(R.drawable.zap02), "Zoo tycon",
                "C 26 x 15 y 17 #123D","9971128336", "http://kokomusoft.com/", "https://www.facebook.com/KokomuSoftworks",
                horarios, 2);
        shoeStores.add(shoeStore);
        shoeStore = new ShoeStore(getResources().getDrawable(R.drawable.zap03), "Bella brand",
                "C 26 x 15 y 17 #123D", "9971128385", "http://kokomusoft.com/", "https://www.facebook.com/KokomuSoftworks",
                horarios, 3);
        shoeStores.add(shoeStore);
        shoeStore = new ShoeStore(getResources().getDrawable(R.drawable.zap02), "Amaga collection",
                "C 26 x 15 y 17 #123D", "9971042362", "http://kokomusoft.com/", "https://www.facebook.com/KokomuSoftworks",
                horarios, 4);
        shoeStores.add(shoeStore);
        shoeStore = new ShoeStore(getResources().getDrawable(R.drawable.zap05), "Umbrella shoes",
                "C 26 x 15 y 17 #123D", "9971128385", "http://kokomusoft.com/", "https://www.facebook.com/KokomuSoftworks",
                horarios ,5);
        shoeStores.add(shoeStore);
        shoeStore = new ShoeStore(getResources().getDrawable(R.drawable.superior_room2), "Clarsie shoe Stores",
                "C 26 x 15 y 17 #123D" , "9971128385", "http://kokomusoft.com/", "https://www.facebook.com/KokomuSoftworks",
                horarios, 6);
        shoeStores.add(shoeStore);
        shoeStore = new ShoeStore(getResources().getDrawable(R.drawable.superior_room), "Maggie tycon",
                "C 26 x 15 y 17 #123D", "9971128385", "http://kokomusoft.com/", "https://www.facebook.com/KokomuSoftworks",
                horarios ,7);
        shoeStores.add(shoeStore);
        shoeStore = new ShoeStore(getResources().getDrawable(R.drawable.superior_room2), "Rose ambar",
                "C 26 x 15 y 17 #123D" , "9971128385", "http://kokomusoft.com/", "https://www.facebook.com/KokomuSoftworks",
                horarios ,8);
        shoeStores.add(shoeStore);
        shoeStore = new ShoeStore(getResources().getDrawable(R.drawable.superior_room), "Press Zon",
                "C 26 x 15 y 17 #123D", "9971128385", "http://kokomusoft.com/", "https://www.facebook.com/KokomuSoftworks",
                horarios ,0);
        shoeStores.add(shoeStore);
        Collections.sort(shoeStores);



        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            //mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            for (ShoeStore aShoeStore: shoeStores){
                if (aShoeStore.getShoeStoreId() == Integer.valueOf(getArguments().getString(ARG_ITEM_ID))){
                    shoeStore = aShoeStore;
                    break;
                }
            }

            //shoeStore = shoeStores.get((Integer.valueOf(getArguments().getString(ARG_ITEM_ID))));
            Log.d("Y pongo",getArguments().getString(ARG_ITEM_ID) );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shoestore_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (shoeStore != null) {
            ((ImageView) rootView.findViewById(R.id.shoeStoreImage)).setImageDrawable(shoeStore.getImage());
            ((TextView) rootView.findViewById(R.id.shoeStoreNametTxtView)).setText(shoeStore.getName());
            ((TextView) rootView.findViewById(R.id.shoeStoreAdressTextView)).setText(shoeStore.getAddress());
            ((Button) rootView.findViewById(R.id.phoneNumber)).setText(shoeStore.getPhoneNumber());
            Log.d("Y pongo",shoeStore.getPhoneNumber() );

            (rootView.findViewById(R.id.phoneNumber)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL );
                    callIntent.setData(Uri.parse("tel:" + shoeStore.getPhoneNumber() ));
                    startActivity(callIntent);
                }
            });


            ((Button)rootView.findViewById(R.id.webSiteUrl)).setText(shoeStore.getWebSite());
            rootView.findViewById(R.id.webSiteUrl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    mSendText.onSendText(shoeStore.getWebSite());

                    WebViewFragment webViewFragment = new WebViewFragment();
                    bundle.putString("text", shoeStore.getWebSite());
                    webViewFragment.setArguments(bundle);
                    FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transaction.replace(R.id.shoestore_detail_container, webViewFragment, "webViewFragment");
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
                    transaction.replace(R.id.shoestore_detail_container, mapsFragment, "mapsFragment");
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });

        }

        return rootView;
    }
}
