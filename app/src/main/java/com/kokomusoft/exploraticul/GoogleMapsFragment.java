package com.kokomusoft.exploraticul;



import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class GoogleMapsFragment extends Fragment {
    private GoogleMap mMap;
    private LatLng establishmentCoordenates = new LatLng(20.4096144, -89.5304547);
    private String establishmentTitle = "Mira";


    public GoogleMapsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_google_maps, container, false);
        Log.d("Evento", "Entre onCreate GMapsFragment");
        setUpMapIfNeeded();

        ToggleButton changeMapTypeToggleButton = (ToggleButton) rootView.findViewById(R.id.mapTypeToggleButton);
        changeMapTypeToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    setMapTypeToHybrid();
                } else{
                    setmMapTypeToNormal();
                }
            }
        });

        return rootView;
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link com.google.android.gms.maps.SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(establishmentCoordenates, 15);
        mMap.animateCamera(cameraUpdate);
        mMap.addMarker(new MarkerOptions().position(establishmentCoordenates).title(establishmentTitle));
        mMap.setMyLocationEnabled(true);

    }

    private void setMapTypeToHybrid(){
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }

    private void setmMapTypeToNormal(){
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }


}
