package com.kokomusoft.exploraticul;



import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class WebViewFragment extends Fragment {
    Button backButton;
    String url;


    public WebViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_web_view, container, false);

        Bundle b = getArguments();
        url = b.getString("text");
        setUrl(url);

        WebView webView = (WebView)rootView.findViewById(R.id.webView);
        webView.loadUrl(url);
        backButton = (Button)rootView.findViewById(R.id.buttonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
        return rootView ;
    }

    public void setUrl(String text){
    }


}
