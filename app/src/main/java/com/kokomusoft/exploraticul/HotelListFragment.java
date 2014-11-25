package com.kokomusoft.exploraticul;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;


import com.kokomusoft.exploraticul.ListAdapters.HotelAdapter;
import com.kokomusoft.exploraticul.Model.Hotel;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A list fragment representing a list of Hotels. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link HotelDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class HotelListFragment extends ListFragment {
    HotelAdapter adapter;

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
    public HotelListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<Hotel> hotels = getHotels();
        setAdapter(hotels);
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

        mCallbacks.onItemSelected(String.valueOf(getListAdapter().getItemId(position)));
        Log.d("Escojio", String.valueOf(getListAdapter().getItemId(position)));
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_list_handcraft_stores, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search_item).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                int filterString = s.length();
                if (filterString == 0){
                    ArrayList<Hotel> hotels = getHotels();
                    setAdapter(hotels);
                }
                adapter.getFilter().filter(s);
                Log.d("Puso", s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                int filterString = s.length();
                if (filterString == 0){
                    ArrayList<Hotel> hotels = getHotels();
                    setAdapter(hotels);
                }
                adapter.getFilter().filter(s);
                Log.d("Puso", s);
                return false;
            }


        });

        super.onCreateOptionsMenu(menu, inflater);

    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }

    private ArrayList<Hotel> getHotels(){
        Hotel hotel;
        ArrayList<Hotel> hotels = new ArrayList<>();
        hotels.clear();

        hotel = new Hotel(getResources().getDrawable(R.drawable.zap01), 180.00,
                "Aria Shoes", "C 24 x 13 y 13 #345f",1);
        hotels.add(hotel);
        hotel = new Hotel(getResources().getDrawable(R.drawable.zap02), 220.00,
                "Zoo tycon", "C 26 x 15 y 17 #123D",2);
        hotels.add(hotel);
        hotel = new Hotel(getResources().getDrawable(R.drawable.zap03),
                310.00,"Bella brand", "C 26 x 15 y 17 #123D",3);
        hotels.add(hotel);
        hotel = new Hotel(getResources().getDrawable(R.drawable.zap02),180.00,
                "Amaga collection", "C 26 x 15 y 17 #123D",4);
        hotels.add(hotel);
        hotel = new Hotel(getResources().getDrawable(R.drawable.zap05), 400.00,
                "Umbrella shoes", "C 26 x 15 y 17 #123D",5);
        hotels.add(hotel);
        hotel = new Hotel(getResources().getDrawable(R.drawable.superior_room2),
                350.00, "Clarsie shoe Stores", "C 26 x 15 y 17 #123D",6);
        hotels.add(hotel);

        Collections.sort(hotels);
        return hotels;

    }

    private void setAdapter(ArrayList<Hotel> hotels){
        adapter = new HotelAdapter(getActivity(), hotels);
        setListAdapter(adapter);
    }


}
