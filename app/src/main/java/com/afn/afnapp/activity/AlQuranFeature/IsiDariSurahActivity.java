package com.afn.afnapp.activity.AlQuranFeature;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.afn.afnapp.R;
import com.afn.afnapp.adapter.AyahAdapter;
import com.afn.afnapp.database.AyatDataHelper;
import com.afn.afnapp.model.AyahModel;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class IsiDariSurahActivity extends AppCompatActivity {
    private TextView tvTitle;
    private ExpandableHeightListView lvSurah;
    public static TextView tvMohonTunggu;

    private List<AyahModel> listAyahFromDb = new ArrayList<>();
    private List<AyahModel> listAyah = new ArrayList<>();
    private AyahAdapter adapter;
    public static AyatDataHelper df;

    //dll
    public static ProgressDialog progress;
    private int surahId = 0;
    private TextView tvKalimahBasmalah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_al_quran);
        this.tvKalimahBasmalah = (TextView) findViewById(R.id.tvKalimahBasmalah);
        //getDataFromIntent
        surahId = getIntent().getIntExtra("noSurah", 0);

        //if
        if (surahId == 1 || surahId == 9) {
            tvKalimahBasmalah.setVisibility(View.GONE);
        }

        //getData
        df = new AyatDataHelper(this);

        //initialize
        this.adapter = new AyahAdapter(IsiDariSurahActivity.this, listAyah);
        this.adapter.notifyDataSetChanged();

        this.tvTitle = (TextView) findViewById(R.id.tvTitle);
        this.tvMohonTunggu = (TextView) findViewById(R.id.tvMohonTunggu);
        this.lvSurah = (ExpandableHeightListView) findViewById(R.id.lvSurah);
        this.lvSurah.setExpanded(true);

        this.progress = new ProgressDialog(this);
        //end initialize

        //startServiceTask
        progress.setMessage("Loading...");
        progress.setCancelable(false);
        progress.show();
        new AsyncTaskSaya(progress).execute();


        tvTitle.setText(getIntent().getStringExtra("namaSurahIndo"));
    }

    public class AsyncTaskSaya extends AsyncTask<String, Integer, Integer> {
        public ProgressDialog progress;

        public AsyncTaskSaya(ProgressDialog progress) {
            this.progress = progress;
        }

        public void onPreExecute() {
            progress.show();
        }

        @Override
        protected Integer doInBackground(String... arg0) {
            listAyahFromDb = df.getAyahListFromSurah(surahId);
            for (int i = 0; i < listAyahFromDb.size(); i++) {
                AyahModel am = listAyahFromDb.get(i);
                AyahModel qq = new AyahModel();
                qq.setSurahId(am.getSurahId());
                qq.setAyahTranslate(am.getAyahTranslate());
                qq.setAyah(am.getAyah());
                qq.setSurahId(am.getSurahId());
                qq.setNoAyah(am.getNoAyah());
                qq.setId(am.getId());
                listAyah.add(qq);
                Log.i("isiSurah", am.getAyahTranslate() + "");
            }
            return 1;
        }

        protected void onPostExecute(Integer result) {
            tvMohonTunggu.setVisibility(View.GONE);
            lvSurah.setAdapter(adapter);
            progress.dismiss();
        }

    }
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

