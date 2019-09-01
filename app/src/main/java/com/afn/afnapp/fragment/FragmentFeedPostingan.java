package com.afn.afnapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.GridView;
import android.widget.ListView;

import com.afn.afnapp.R;
import com.afn.afnapp.adapter.FeedAdapter;
import com.afn.afnapp.model.FeedModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFeedPostingan extends Fragment {


    public FragmentFeedPostingan() {
        // Required empty public constructor
    }

    private WebView web_view;
    private View view;
    private com.github.paolorotolo.expandableheightlistview.ExpandableHeightGridView lvFeed;

    private FeedAdapter adapter;
    private List<FeedModel> listNa = new ArrayList<>();

    private int isi[] = {
            R.drawable.afn1,
            R.drawable.afn2,
            R.drawable.afn3,
            R.drawable.afn4,
            R.drawable.afn5,
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_feed_postingan, container, false);

        /*web_view = (WebView)view.findViewById(R.id.web_view);

        web_view.loadUrl("http://www.alfirqotunnajiyah.com/poster-dakwah/");
        web_view.setWebViewClient(new WebViewClient());*/

        adapter = new FeedAdapter(getActivity(), listNa);
        adapter.notifyDataSetChanged();

        initLayout();
        pullData();

        return view;
    }

    void initLayout() {
        lvFeed = view.findViewById(R.id.lvFeed);
    }

    void pullData() {
        for (int i = 0; i < 5; i++) {
            FeedModel mm = new FeedModel();
            mm.setIdResource(isi[i]);
            listNa.add(mm);
        }

        lvFeed.setAdapter(adapter);
    }

}
