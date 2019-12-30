package com.afn.afnapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

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
    private com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView lvFeed;

    private FeedAdapter adapter;
    private List<FeedModel> listNa = new ArrayList<>();

    private int isi[] = {
            R.drawable.ic_facebook,
            R.drawable.ic_instagram,
            R.drawable.ic_telegram,
            R.drawable.ic_twitter,
            R.drawable.ic_youtube,
    };

    private String isi2[] = {
            "Facebook",
            "Instagram",
            "Telegram",
            "Twitter",
            "Youtube",
    };

    private String isi3[] = {
            "https://www.facebook.com/alfirqotunnajiyahcom/",
            "https://www.instagram.com/al.firqotun.najiyah/",
            "https://t.me/al_firqotunnajiyah",
            "https://twitter.com/alfirqotunnjyh",
            "https://www.youtube.com/channel/UCB2Q857sapIb-Xuj9r36EnA",
    };

    private String isi4[] = {
            "@alfirqotunnajiyahcom",
            "@al.firqotun.najiyah",
            "@alfirqotunnjyh",
            "Al Firqotun Najiyah",
            "Al Firqotun Najiyah",
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
            mm.setTitle(isi4[i]);
            mm.setLinkImage(isi3[i]);
            mm.setCaption(isi2[i]);
            listNa.add(mm);
        }

        lvFeed.setAdapter(adapter);
    }

}
