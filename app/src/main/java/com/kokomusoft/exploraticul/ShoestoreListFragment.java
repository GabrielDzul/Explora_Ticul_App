package com.kokomusoft.exploraticul;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.MenuItemCompat;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import com.kokomusoft.exploraticul.Model.ShoeStore;
import com.kokomusoft.exploraticul.controller.ShoeStoreAdapter;

import org.apache.http.client.methods.HttpOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * A list fragment representing a list of Shoes stores. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link ShoestoreDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class ShoestoreListFragment extends ListFragment {
    ShoeStoreAdapter adapter;
    /*ListView hotelsListView;
    String[] hotelImages;
    String[] hotelNames;
    String[] hotelAddresses;
    String jsonString = null;

    private final String KEY_NAME = "name";
    private final  String KEY_ADDRESS = "address";
    private final String KEY_IMAGE = "image";*/

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(String id);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ShoestoreListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("Evento", "Ya entre a Oncreate de ListFragment");
        ArrayList<ShoeStore> shoeStores = getShoeStores();
        setAdapter(shoeStores);

    }

    private void setAdapter(ArrayList<ShoeStore> shoeStores) {
        adapter = new ShoeStoreAdapter(getActivity(), shoeStores );
        setListAdapter(adapter);
    }

    private ArrayList<ShoeStore> getShoeStores() {
        ShoeStore shoeStore;
        ArrayList<ShoeStore> shoeStores = new ArrayList<ShoeStore>();
        shoeStores.clear();

        //Create some objects
        shoeStore = new ShoeStore(getResources().getDrawable(R.drawable.zap01), "Aria Shoes", "C 24 x 13 y 13 #345f",1);
        shoeStores.add(shoeStore);
        shoeStore = new ShoeStore(getResources().getDrawable(R.drawable.zap02), "Zoo tycon", "C 26 x 15 y 17 #123D",2);
        shoeStores.add(shoeStore);
        shoeStore = new ShoeStore(getResources().getDrawable(R.drawable.zap03), "Bella brand", "C 26 x 15 y 17 #123D",3);
        shoeStores.add(shoeStore);
        shoeStore = new ShoeStore(getResources().getDrawable(R.drawable.zap02), "Amaga collection", "C 26 x 15 y 17 #123D",4);
        shoeStores.add(shoeStore);
        shoeStore = new ShoeStore(getResources().getDrawable(R.drawable.zap05), "Umbrella shoes", "C 26 x 15 y 17 #123D",5);
        shoeStores.add(shoeStore);
        shoeStore = new ShoeStore(getResources().getDrawable(R.drawable.superior_room2), "Clarsie shoe Stores", "C 26 x 15 y 17 #123D",6);
        shoeStores.add(shoeStore);
        shoeStore = new ShoeStore(getResources().getDrawable(R.drawable.superior_room), "Maggie tycon", "C 26 x 15 y 17 #123D",7);
        shoeStores.add(shoeStore);
        shoeStore = new ShoeStore(getResources().getDrawable(R.drawable.superior_room2), "Rose ambar", "C 26 x 15 y 17 #123D",8);
        shoeStores.add(shoeStore);
        shoeStore = new ShoeStore(getResources().getDrawable(R.drawable.superior_room), "Press Zon", "C 26 x 15 y 17 #123D",0);
        shoeStores.add(shoeStore);

        Collections.sort(shoeStores);
        return shoeStores;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);


        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        //mCallbacks.onItemSelected(DummyContent.ITEMS.get(position).id);
        mCallbacks.onItemSelected(String.valueOf(getListAdapter().getItemId(position)));
        Log.d("Escojio",String.valueOf(getListAdapter().getItemId(position)));

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_list_shoe_stores, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search_item).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                int filterString = s.length();
                if (filterString == 0){
                    ArrayList<ShoeStore> shoeStores = getShoeStores();
                    setAdapter(shoeStores);
                }
                adapter.getFilter().filter(s);
                Log.d("Puso", s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                int filterString = s.length();
                if (filterString == 0){
                    ArrayList<ShoeStore> shoeStores = getShoeStores();
                    setAdapter(shoeStores);
                }
                adapter.getFilter().filter(s);
                Log.d("Puso", s);
                return false;
            }


        });

        super.onCreateOptionsMenu(menu, inflater);

    }

}