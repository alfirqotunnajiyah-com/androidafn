package com.afn.afnapp.activity.JadwalKajianFeature;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.afn.afnapp.R;
import com.afn.afnapp.adapter.JadwalKajianAdapter;
import com.afn.afnapp.model.JadwalKajianModel;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightGridView;

import java.util.ArrayList;
import java.util.List;

public class JadwalKajianMainActivity extends AppCompatActivity {

    private JadwalKajianAdapter adapter;
    private List<JadwalKajianModel> listKajian = new ArrayList<>();
    private List<JadwalKajianModel> listKajian2 = new ArrayList<>();
    private ExpandableHeightGridView gvList;
    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_kajian_main);

        adapter = new JadwalKajianAdapter(this, listKajian);
        adapter.notifyDataSetChanged();

        initView();
        initClick();
        retriveData();
    }

    void initView() {
        gvList = (ExpandableHeightGridView) findViewById(R.id.gvList);
        etSearch = (EditText) findViewById(R.id.etSearch);
        gvList.setExpanded(true);
    }

    void initClick() {
        gvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                JadwalKajianModel mm;
                if (etSearch.getText().length() > 0) {
                    mm = listKajian2.get(i);
                } else {
                    mm = listKajian.get(i);
                }

                Toast.makeText(JadwalKajianMainActivity.this, mm.getJudulKajian() + "", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(JadwalKajianMainActivity.this, JadwalKajianDetailActivity.class));
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String isiNa = getTextNa(etSearch);
                listKajian2.clear();
                for (int i = 0; i < listKajian.size(); i++) {
                    JadwalKajianModel ll = listKajian.get(i);
                    if (ll.getJudulKajian().trim().toLowerCase().startsWith(isiNa.trim().toLowerCase())) {
                        ll.setJudulKajian(ll.getJudulKajian());
                        listKajian2.add(ll);
                    }
                }
                adapter = new JadwalKajianAdapter(JadwalKajianMainActivity.this, listKajian2);
                adapter.notifyDataSetChanged();
                gvList.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    String getTextNa(EditText et) {
        return et.getText().toString();
    }

    void retriveData() {
        for (int i = 0; i < 6; i++) {
            JadwalKajianModel l = new JadwalKajianModel();
            if ((i % 2) == 0) {
                l.setJudulKajian("Tematik");
            } else {
                l.setJudulKajian("Riyadhus Shalihin");
            }
            listKajian.add(l);
        }
        gvList.setAdapter(adapter);
    }
}
