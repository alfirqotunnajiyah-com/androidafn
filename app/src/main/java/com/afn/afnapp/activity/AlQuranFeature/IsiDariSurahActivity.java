package com.afn.afnapp.activity.AlQuranFeature;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afn.afnapp.R;
import com.afn.afnapp.adapter.AyahRAdapter;
import com.afn.afnapp.database.AyatDataHelper;
import com.afn.afnapp.model.AyahModel;
import com.mlsdev.animatedrv.AnimatedRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class IsiDariSurahActivity extends AppCompatActivity {
    private TextView tvTitle;
    private RecyclerView lvSurah;
    private TextView tvKalimahBasmalah;
    private android.widget.ImageView ivLeft;
    private android.widget.ImageView ivRight;
    private android.support.design.widget.AppBarLayout appbar;
    private android.widget.Button btnKlik;

    public static TextView tvMohonTunggu;

    private List<AyahModel> listAyahFromDb = new ArrayList<>();
    private List<AyahModel> listAyah = new ArrayList<>();
    private AyahRAdapter adapter;
    public static AyatDataHelper df;

    //dll
    public static ProgressDialog progress;
    private int surahId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isi_dari_surah);
        this.tvKalimahBasmalah = (TextView) findViewById(R.id.tvKalimahBasmalah);
        //getDataFromIntent
        surahId = getIntent().getIntExtra("noSurah", 0);

        //getData
        df = new AyatDataHelper(this);

        //initialize
        this.adapter = new AyahRAdapter(IsiDariSurahActivity.this, listAyah);
        this.adapter.notifyDataSetChanged();

        this.tvTitle = (TextView) findViewById(R.id.tvTitle);
        this.tvMohonTunggu = (TextView) findViewById(R.id.tvMohonTunggu);
        this.btnKlik = (Button) findViewById(R.id.btnKlik);
        this.appbar = (AppBarLayout) findViewById(R.id.appbar);
        this.ivRight = (ImageView) findViewById(R.id.ivRight);
        this.ivLeft = (ImageView) findViewById(R.id.ivLeft);

        this.lvSurah = (AnimatedRecyclerView) findViewById(R.id.lvSurah);
        this.lvSurah.scheduleLayoutAnimation();
        this.lvSurah.setNestedScrollingEnabled(false);
        this.lvSurah.setHasFixedSize(false);
        //this.lvSurah.setExpanded(true);

        this.progress = new ProgressDialog(this);
        //end initialize

        //startServiceTask
        progress.setMessage("Loading...");
        progress.setCancelable(false);
        //progress.show();
        new AsyncTaskSaya(progress).execute();

        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(IsiDariSurahActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_to_ayah, null);
                dialog.setView(dialogView);
                dialog.setCancelable(true);

                final EditText etAyah = dialogView.findViewById(R.id.etAyahSearch);

                dialog.setTitle("Ke Ayat :");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lvSurah.scrollToPosition(Integer.parseInt(etAyah.getText().toString()));
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        tvTitle.setText(getIntent().getStringExtra("namaSurahIndo"));

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                    long downloadId = intent.getLongExtra(
                            DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(enqueue);
                    Cursor c = dm.query(query);
                    if (c.moveToFirst()) {
                        int columnIndex = c
                                .getColumnIndex(DownloadManager.COLUMN_STATUS);
                        if (DownloadManager.STATUS_SUCCESSFUL == c
                                .getInt(columnIndex)) {

                            ivRight.setVisibility(View.GONE);

                                    /*ImageView view = (ImageView) findViewById(R.id.imageView1);
                                    String uriString = c
                                            .getString(c
                                                    .getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                                    view.setImageURI(Uri.parse(uriString));*/
                        }
                    }
                }
            }
        };

        registerReceiver(receiver, new IntentFilter(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        onClick(ivRight);
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
            if (surahId == 1 || surahId == 9) {
            } else {
                AyahModel qq = new AyahModel();
                qq.setSurahId(surahId);
                qq.setAyahTranslate("");
                qq.setAyah("بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيم");
                qq.setNoAyah(0);
                qq.setId(0);
                listAyah.add(qq);
            }
            for (int i = 0; i < listAyahFromDb.size(); i++) {
                AyahModel am = listAyahFromDb.get(i);
                AyahModel qq = new AyahModel();
                qq.setSurahId(am.getSurahId());
                qq.setAyahTranslate(am.getAyahTranslate());
                qq.setAyah(am.getAyah());
                qq.setNoAyah(am.getNoAyah());
                qq.setId(am.getId());

                String noSurahStr;
                String noAyahStr;
                if (qq.getSurahId() < 10) {
                    noSurahStr = "00" + qq.getSurahId();
                } else if (qq.getSurahId() < 100) {
                    noSurahStr = "0" + qq.getSurahId();
                } else {
                    noSurahStr = "" + qq.getSurahId();
                }

                if (qq.getNoAyah() < 10) {
                    noAyahStr = "00" + qq.getNoAyah();
                } else if (qq.getNoAyah() < 100) {
                    noAyahStr = "0" + qq.getNoAyah();
                } else {
                    noAyahStr = "" + qq.getNoAyah();
                }
                qq.setStrLink("http://www.everyayah.com/data/Alafasy_64kbps/" + noSurahStr + noAyahStr + ".mp3");

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

    private long enqueue;
    private DownloadManager dm;

    public void onClick(View view) {
        dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(
                Uri.parse("http://www.everyayah.com/data/Alafasy_64kbps/001005.mp3"));
        enqueue = dm.enqueue(request);
    }

    public void showDownload(View view) {
        Intent i = new Intent();
        i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
        startActivity(i);
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

