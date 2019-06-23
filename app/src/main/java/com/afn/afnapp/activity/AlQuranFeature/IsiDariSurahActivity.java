package com.afn.afnapp.activity.AlQuranFeature;

import android.Manifest;
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
import android.os.Environment;
import android.support.annotation.NonNull;
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
import android.widget.Toast;

import com.afn.afnapp.MainActivity;
import com.afn.afnapp.R;
import com.afn.afnapp.adapter.AyahRAdapter;
import com.afn.afnapp.classfungsi.CheckForSDCard;
import com.afn.afnapp.database.AyatDataHelper;
import com.afn.afnapp.model.AyahModel;
import com.mlsdev.animatedrv.AnimatedRecyclerView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class IsiDariSurahActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    private TextView tvTitle;
    private RecyclerView lvSurah;
    private android.widget.ImageView ivLeft;
    private android.widget.ImageView ivRight;
    private android.support.design.widget.AppBarLayout appbar;
    private android.widget.Button btnKlik;

    public static TextView tvMohonTunggu;

    private List<AyahModel> listAyahFromDb = new ArrayList<>();
    private List<AyahModel> listAyah = new ArrayList<>();
    private AyahRAdapter adapter;
    public static AyatDataHelper df;

    //audio
    private static final int WRITE_REQUEST_CODE = 300;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String url = "http://www.everyayah.com/data/Alafasy_64kbps/";

    //dll
    private int surahId = 0;
    private int jumlahAyat = 0;
    private int currentIndex = 0;
    private String noSurahStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isi_dari_surah);

        //getDataFromIntent
        surahId = getIntent().getIntExtra("noSurah", 0);

        if (surahId < 10) {
            noSurahStr = "00" + surahId;
        } else if (surahId < 100) {
            noSurahStr = "0" + surahId;
        } else {
            noSurahStr = "" + surahId;
        }

        //getData
        df = new AyatDataHelper(this);

        //initialize adapter
        this.adapter = new AyahRAdapter(IsiDariSurahActivity.this, listAyah);
        this.adapter.notifyDataSetChanged();

        setUI();
        //end initialize

        //startServiceTask
        new AsyncTaskSaya().execute();

        //set
        tvTitle.setText(getIntent().getStringExtra("namaSurahIndo"));

        setOnClik();
    }

    void setUI() {
        this.tvTitle = (TextView) findViewById(R.id.tvTitle);
        this.tvMohonTunggu = (TextView) findViewById(R.id.tvMohonTunggu);
        this.btnKlik = (Button) findViewById(R.id.btnKlik);
        this.appbar = (AppBarLayout) findViewById(R.id.appbar);
        this.ivRight = (ImageView) findViewById(R.id.ivRight);
        this.ivLeft = (ImageView) findViewById(R.id.ivLeft);

        //RecyclerView
        this.lvSurah = (AnimatedRecyclerView) findViewById(R.id.lvSurah);
        this.lvSurah.scheduleLayoutAnimation();
        this.lvSurah.setNestedScrollingEnabled(false);
        this.lvSurah.setHasFixedSize(false);
        //this.lvSurah.setExpanded(true);
    }

    void setOnClik() {
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

        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckForSDCard.isSDCardPresent()) {

                    //check if app has permission to write to the external storage.
                    if (EasyPermissions.hasPermissions(IsiDariSurahActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        //Get the URL entered
                        currentIndex = 1;
                        String urlNa = url + noSurahStr + "001" + ".mp3";
                        new DownloadFile().execute(urlNa);
                    } else {
                        //If permission is not present request for the same.
                        EasyPermissions.requestPermissions(IsiDariSurahActivity.this, "Memeriksa akses penyimpanan", WRITE_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
                    }


                } else {
                    Toast.makeText(getApplicationContext(),
                            "SD Card not found", Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    public class AsyncTaskSaya extends AsyncTask<String, Integer, Integer> {
        public AsyncTaskSaya() {
        }

        public void onPreExecute() {

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
                jumlahAyat = am.getNoAyah();

                String noAyahStr;
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
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, IsiDariSurahActivity.this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //Download the file once permission is granted
        /*url = "http://www.everyayah.com/data/Alafasy_64kbps/" + "002" + "001" + ".mp3";
        new DownloadFile().execute(url);*/
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");
    }

    /**
     * Async Task to download file from URL
     */
    private class DownloadFile extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog;
        private String fileName;
        private String folder;
        private boolean isDownloaded;

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = new ProgressDialog(IsiDariSurahActivity.this);
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();


                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

                //Extract file name from URL
                fileName = f_url[0].substring(f_url[0].lastIndexOf('/') + 1, f_url[0].length());

                //Append timestamp to file name
                //fileName = timestamp + "_" + fileName;
                //fileName = fileName;

                //External directory path to save file
                folder = Environment.getExternalStorageDirectory() + File.separator + "AFNFile/" + surahId + "/";

                //Create androiddeft folder if it does not exist
                File directory = new File(folder);

                if (!directory.exists()) {
                    directory.mkdirs();
                }

                // Output stream to write file
                OutputStream output = new FileOutputStream(folder + fileName);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lengthOfFile));
                    Log.d(TAG, "Progress: " + (int) ((total * 100) / lengthOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

                return "success";

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return "Something went wrong";
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            progressDialog.setProgress(Integer.parseInt(progress[0]));
        }


        @Override
        protected void onPostExecute(String message) {
            // dismiss the dialog after the file was downloaded
            this.progressDialog.dismiss();

            // Display File path after downloading
            Toast.makeText(getApplicationContext(),
                    currentIndex + " dari " + jumlahAyat, Toast.LENGTH_LONG).show();
            nextProccess();
        }
    }

    void nextProccess() {
        currentIndex++;
        if (currentIndex <= jumlahAyat) {
            String noAyahStr;
            if (currentIndex < 10) {
                noAyahStr = "00" + currentIndex;
            } else if (currentIndex < 100) {
                noAyahStr = "0" + currentIndex;
            } else {
                noAyahStr = "" + currentIndex;
            }
            String urlNa = url + noSurahStr + noAyahStr + ".mp3";
            new DownloadFile().execute(urlNa);
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

