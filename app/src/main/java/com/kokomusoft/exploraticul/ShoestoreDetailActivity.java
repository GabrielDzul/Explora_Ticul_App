package com.kokomusoft.exploraticul;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;


/**
 * An activity representing a single Shoe store detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ShoestoreListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link ShoestoreDetailFragment}.
 */
public class ShoestoreDetailActivity extends ActionBarActivity implements
        ShoestoreDetailFragment.OnFragmentSendText{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoestore_detail);

        // Show the Up button in the action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ShoestoreDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(ShoestoreDetailFragment.ARG_ITEM_ID));
            ShoestoreDetailFragment fragment = new ShoestoreDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.shoestore_detail_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, ShoestoreListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSendText(String text) {
        WebViewFragment webViewFragment = (WebViewFragment)getFragmentManager().findFragmentByTag("webViewFragment");
        if (webViewFragment != null){
            Log.d("Evento", "Entre al  ifOnsetText");
            webViewFragment.setUrl(text);
        }
        else{
            Log.d("Evento", "Entre al else OnsetText");
            WebViewFragment viewFragment = new WebViewFragment();
            Bundle args = new Bundle();
            args.putString("text", text);
            viewFragment.setArguments(args);
            FragmentTransaction transaction = getFragmentManager()
                    .beginTransaction();
                    transaction.replace(R.id.shoestore_detail_container, viewFragment, "webViewFragment")
                    .addToBackStack(null)
                    .commit();
        }
    }
}
