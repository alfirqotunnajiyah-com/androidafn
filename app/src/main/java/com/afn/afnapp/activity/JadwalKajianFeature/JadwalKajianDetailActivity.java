package com.afn.afnapp.activity.JadwalKajianFeature;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afn.afnapp.R;
import com.afn.afnapp.model.JadwalKajianModel;
import com.afn.afnapp.utils.ApiUrl;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JadwalKajianDetailActivity extends AppCompatActivity {

    private AppBarLayout appbar;
    private Toolbar toolbar;
    private ImageView ivPoster;
    private TextView
            tvTanggal,
            tvWaktu,
            tvPemateri,
            tvInfoPemateri,
            tvTema,
            tvInfoTema,
            tvTempat,
            tvAlamatTempat,
            tvLinkMaps,
            tvLblKhusus;

    private int idKajian = 0;
    private ApiUrl apiUrl;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_kajian_detail);

        idKajian = getIntent().getIntExtra("idKajian", 0);
        queue = Volley.newRequestQueue(this);
        apiUrl = new ApiUrl();

        //Toast.makeText(this, idKajian + "", Toast.LENGTH_SHORT).show();
        //Log.d("isiNya", idKajian + "");

        initLayout();
        retrieveData();
    }


    void initLayout() {
        appbar = findViewById(R.id.appbar);
        toolbar = findViewById(R.id.toolbar);

        ivPoster = findViewById(R.id.ivPoster);
        tvTanggal = findViewById(R.id.tvTanggal);
        tvWaktu = findViewById(R.id.tvWaktu);
        tvLblKhusus= findViewById(R.id.tvLblKhusus);

        tvPemateri = findViewById(R.id.tvPemateri);
        tvInfoPemateri = findViewById(R.id.tvInfoPemateri);

        tvTema = findViewById(R.id.tvTema);
        tvInfoTema = findViewById(R.id.tvInfoTema);

        tvTempat = findViewById(R.id.tvTempat);
        tvAlamatTempat = findViewById(R.id.tvAlamatTempat);
        tvLinkMaps = findViewById(R.id.tvLinkMaps);
    }

    void retrieveData() {
        String url = apiUrl.getMainUrl() + "get_data.php?mode=14";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            String statusApi = obj.getString("afn_status");
                            if (statusApi.equalsIgnoreCase("success")) {
                                JSONArray jsonArray = obj.getJSONArray("value");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    tvTema.setText(object.getString("tema"));
                                    toolbar.setTitle(object.getString("tema"));
                                    tvInfoTema.setText(object.getString("infoTema"));
                                    tvPemateri.setText(object.getString("pemateri"));
                                    tvInfoPemateri.setText(object.getString("infoPemateri"));
                                    tvTempat.setText(object.getString("tempat"));
                                    tvAlamatTempat.setText(object.getString("alamat"));
                                    tvLinkMaps.setText(object.getString("linkMaps"));

                                    tvTanggal.setText(showTanggal(object.getString("tanggal")));
                                    tvWaktu.setText(object.getString("waktu"));
                                    if (object.getInt("isKhusus") == 1) {
                                        tvLblKhusus.setBackgroundResource(R.drawable.bg_lbl_ikhwan);
                                        tvLblKhusus.setText("Umum");
                                    } else if (object.getInt("isKhusus") == 2) {
                                        tvLblKhusus.setBackgroundResource(R.drawable.bg_lbl_ikhwan);
                                        tvLblKhusus.setText("Ikhwan");
                                    } else {
                                        tvLblKhusus.setBackgroundResource(R.drawable.bg_lbl_akhwat);
                                        tvLblKhusus.setText("Akhwat");
                                    }
                                }
                            } else {
                                showSnackbar("Kami belum mempunyai jadwal kajian");
                            }
                        } catch (JSONException ex) {
                            showSnackbar("Gagal mengambil data");
                            //ex.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.d("isiResponse", error.getMessage());
                //error.printStackTrace();
                showSnackbar("Tidak terhubung ke internet");
                //Toast.makeText(JadwalKajianDetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_kajian", idKajian + "");
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void showSnackbar(String isiValue) {
        Snackbar.make(tvAlamatTempat, isiValue, Snackbar.LENGTH_SHORT).show();
    }

    private String showTanggal(String tgl) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = fmt.parse(tgl);

            SimpleDateFormat fmtOut = new SimpleDateFormat("E, dd MMMM yyyy");
            return fmtOut.format(date);
        } catch (ParseException ex) {
            return tgl;
        }
    }
}
