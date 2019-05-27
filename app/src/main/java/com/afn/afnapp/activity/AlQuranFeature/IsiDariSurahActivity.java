package com.afn.afnapp.activity.AlQuranFeature;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.afn.afnapp.R;
import com.afn.afnapp.adapter.AyahAdapter;
import com.afn.afnapp.database.AyatDataHelper;
import com.afn.afnapp.model.AyahModel;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import java.util.ArrayList;
import java.util.List;

public class IsiDariSurahActivity extends AppCompatActivity {
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

        df = new AyatDataHelper(this);

        adapter = new AyahAdapter(this, listAyah);
        adapter.notifyDataSetChanged();

        setLayout();

        noAyah = getIntent().getIntExtra("noSurah", 0);
        tvTitle.setText(getIntent().getStringExtra("namaSurah"));

        setKlik();
    }


    void setLayout() {
        this.lvSurah = (ExpandableHeightListView) findViewById(R.id.lvSurah);
        this.tvTitle = (TextView) findViewById(R.id.tvTitle);

        this.lvSurah.setExpanded(true);
    }

    void setKlik() {
    }

    //read from csv
    /*private void readData(int noSurah) {
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
                    AyahModel sm = new AyahModel();
                    sm.setAyah(tokensArabic[3].replace("\"", ""));
                    sm.setAyahTranslate(tokensIndonesia[2]);
                    sm.setNoAyah(Integer.parseInt(tokensArabic[2]));
                    sm.setSurahId(Integer.parseInt(tokensArabic[1]));
                    listAyah.add(sm);
                }
            }
        } catch (IOException e1) {
            Log.e("MainActivity", "Error" + line1, e1);
            Log.e("MainActivity", "Error" + line2, e1);
            e1.printStackTrace();
        }

        lvSurah.setAdapter(adapter);
        setKlik();
    }*/
}
