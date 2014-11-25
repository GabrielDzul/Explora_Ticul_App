package com.kokomusoft.exploraticul;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            ImageButton imageButtonExplore = (ImageButton)rootView.findViewById(R.id.imgBtnExploreTicul);
            imageButtonExplore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(rootView.getContext(), "Boton Historia", Toast.LENGTH_SHORT).show();
                }
            });

            ImageButton imageButtonShoeMakers = (ImageButton)rootView.findViewById(R.id.imgBtnShoeMakers);
            imageButtonShoeMakers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent shoeStoresIntent = new Intent(rootView.getContext(), ShoestoreListActivity.class);
                    startActivity(shoeStoresIntent);
                }
            });

            ImageButton imageButtonHandCrafts = (ImageButton)rootView.findViewById(R.id.imgBtnHandcrafts);
            imageButtonHandCrafts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent handCraftStoresIntent = new Intent(rootView.getContext(), HandCraftStoreListActivity.class);
                    startActivity(handCraftStoresIntent);
                }
            });

            ImageButton imageButtonRestaurants = (ImageButton)rootView.findViewById(R.id.imgBtnRestaurants);
            imageButtonRestaurants.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent restaurantsIntent = new Intent(rootView.getContext(),
                            RestaurantListActivity.class);
                    startActivity(restaurantsIntent);
                }
            });

            ImageButton imageButtonHotels = (ImageButton)rootView.findViewById(R.id.imgBtnHotels);
            imageButtonHotels.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent hotelsIntent = new Intent(rootView.getContext(), HotelListActivity.class);
                    startActivity(hotelsIntent);
                }
            });

            ImageButton imageButtonHelp = (ImageButton)rootView.findViewById(R.id.imgBtnHelp);
            imageButtonHelp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent helpIntent = new Intent(rootView.getContext(), AboutActivity.class);
                    startActivity(helpIntent);
                }
            });

            return rootView;
        }
    }
}
