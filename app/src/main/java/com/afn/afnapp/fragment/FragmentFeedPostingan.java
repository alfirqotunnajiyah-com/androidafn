package com.afn.afnapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.afn.afnapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFeedPostingan extends Fragment {


    public FragmentFeedPostingan() {
        // Required empty public constructor
    }

    private WebView web_view;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_feed_postingan, container, false);

        web_view = (WebView)view.findViewById(R.id.web_view);

        web_view.loadUrl("http://www.alfirqotunnajiyah.com/poster-dakwah/");
        web_view.setWebViewClient(new WebViewClient());

        return view;
    }

}
