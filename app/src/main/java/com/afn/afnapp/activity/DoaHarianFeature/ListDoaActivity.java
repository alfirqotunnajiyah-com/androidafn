package com.afn.afnapp.activity.DoaHarianFeature;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.afn.afnapp.R;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

public class ListDoaActivity extends AppCompatActivity {

    private AppBarLayout appbar;
    private Toolbar toolbar;
    private ExpandableHeightListView lvDoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_doa);


        initView();
        initClik();
    }

    private void initView() {
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lvDoa = (ExpandableHeightListView) findViewById(R.id.lvDoa);
    }

    private void initClik() {

    }
}
