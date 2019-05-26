package com.afn.afnapp.activity.AlQuranFeature;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afn.afnapp.R;
import com.afn.afnapp.adapter.AyahAdapter;
import com.afn.afnapp.database.AyatDataHelper;
import com.afn.afnapp.model.AyahModel;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class IsiDariSurahActivity extends AppCompatActivity {

    private Button btnKlik;
    private TextView tvTitle;
    private ExpandableHeightListView lvSurah;

    private List<AyahModel> listAyah = new ArrayList<>();
    private AyahAdapter adapter;
    private AyatDataHelper df;

    private int noAyah = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_al_quran);

        adapter = new AyahAdapter(this, listAyah);
        adapter.notifyDataSetChanged();
        df = new AyatDataHelper(this);

        setLayout();

        noAyah = getIntent().getIntExtra("noSurah", 0);
        tvTitle.setText(getIntent().getStringExtra("namaSurah"));

        readData(noAyah);
    }


    void setLayout() {
        this.lvSurah = (ExpandableHeightListView) findViewById(R.id.lvSurah);
        this.btnKlik = (Button) findViewById(R.id.btnKlik);
        this.tvTitle = (TextView) findViewById(R.id.tvTitle);

        this.lvSurah.setExpanded(true);
    }

    void setKlik() {
        btnKlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //readData();
            }
        });
    }

    private void readData(int noSurah) {
        listAyah.clear();

        InputStream is1 = getResources().openRawResource(R.raw.arabic);
        InputStream is2 = getResources().openRawResource(R.raw.indonesian);
        BufferedReader reader1 = new BufferedReader(
                new InputStreamReader(is1, Charset.forName("UTF-8")));
        BufferedReader reader2 = new BufferedReader(
                new InputStreamReader(is2, Charset.forName("UTF-8")));
        String line1 = "";
        String line2 = "";

        try {
            while ((line1 = reader1.readLine()) != null && ((line2 = reader2.readLine()) != null)) {
                // Split the line into different tokens (using the comma as a separator).
                String[] tokensArabic = line1.split(";");
                String[] tokensIndonesia = line2.split("\\|");

                if (tokensArabic[1].equalsIgnoreCase(String.valueOf(noSurah))) {
                    /*AyahModel sm = new AyahModel();
                    sm.setArabicAyah(tokensArabic[3].replace("\"", ""));
                    sm.setIndoAyah(tokensIndonesia[2]);
                    sm.setNoAyah(Integer.parseInt(tokensArabic[2]));
                    sm.setNoSurah(Integer.parseInt(tokensArabic[1]));
                    listAyah.add(sm);*/

                }

                // Read the data and store it in the WellData POJO.
            }
        } catch (IOException e1) {
            Log.e("MainActivity", "Error" + line1, e1);
            Log.e("MainActivity", "Error" + line2, e1);
            e1.printStackTrace();
        }

        lvSurah.setAdapter(adapter);
        setKlik();
    }
}
