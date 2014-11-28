package com.kokomusoft.exploraticul;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * An activity representing a list of HandCraftStore. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link HandCraftStoreDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link HandCraftStoreListFragment} and the item details
 * (if present) is a {@link HandCraftStoreDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link HandCraftStoreListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class HandCraftStoreListActivity extends ActionBarActivity
        implements HandCraftStoreListFragment.Callbacks, HandCraftStoreDetailFragment.OnFragmentSendText {
    private String[] mDrawerMenuOptions;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private List<HashMap<String,String>> mList ;
    private SimpleAdapter mAdapter;
    final private String OPTION = "menu_option";
    final private String ICON = "icon";

    int[] mImagesMenu = new int[]{
            R.drawable.ic_shoe_stores,
            R.drawable.ic_handcraft_stores,
            R.drawable.ic_restaurants,
            R.drawable.ic_hotels
    };

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handcraftstore_list);

        if (findViewById(R.id.handcraftstore_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((HandCraftStoreListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.handcraftstore_list))
                    .setActivateOnItemClick(true);
        }

        getDrawerMenu();
        // TODO: If exposing deep links into your app, handle intents here.
    }

    private void getDrawerMenu() {
        mDrawerMenuOptions = getResources().getStringArray(R.array.drawerOptions);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mList = new ArrayList<HashMap<String,String>>();
        for(int i=0;i<4;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put(OPTION, mDrawerMenuOptions[i]);
            hm.put(ICON, Integer.toString(mImagesMenu[i]) );
            mList.add(hm);
        }
        // Keys used in Hashmap
        String[] from = { ICON,OPTION};

        // Ids of views in listview_layout
        int[] to = { R.id.icon , R.id.menu_option};

        // Instantiating an adapter to store each items
        // R.layout.drawer_layout defines the layout of each item
        mAdapter = new SimpleAdapter(this, mList, R.layout.drawer_layout, from, to);
        mDrawerList.setAdapter(mAdapter);

        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.drawable.ic_drawer,
                R.string.drawer_open, R.string.drawer_close){


        };
        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        configureActionBar();

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        Intent shoeStoresIntent = new Intent(getApplicationContext(),
                                ShoestoreListActivity.class);
                        startActivity(shoeStoresIntent);
                        finish();
                        break;
                    case 1:
                        Intent handcraftStoreIntent = new Intent(getApplicationContext(),
                                HandCraftStoreListActivity.class);
                        startActivity(handcraftStoreIntent);
                        finish();
                        break;
                    case 2:
                        Intent restaurantsIntent = new Intent(getApplicationContext(),
                                RestaurantListActivity.class);
                        startActivity(restaurantsIntent);
                        finish();
                        break;
                    case 3:
                        Intent hotelsIntent = new Intent(getApplicationContext(),
                                HotelListActivity.class);
                        startActivity(hotelsIntent);
                        finish();
                        break;
                }

            }
        });
    }

    private void configureActionBar() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    /**
     * Callback method from {@link HandCraftStoreListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(HandCraftStoreDetailFragment.ARG_ITEM_ID, id);
            HandCraftStoreDetailFragment fragment = new HandCraftStoreDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.handcraftstore_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, HandCraftStoreDetailActivity.class);
            detailIntent.putExtra(HandCraftStoreDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }

    @Override
    public void onSendText(String text) {

    }
}
