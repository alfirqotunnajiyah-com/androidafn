package com.afn.afnapp.activity.JadwalKajianFeature;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ScrollView;

import com.afn.afnapp.R;
import com.afn.afnapp.adapter.JadwalKajianAdapter;
import com.afn.afnapp.adapter.JadwalKajianApproveAdapter;
import com.afn.afnapp.model.JadwalKajianModel;
import com.afn.afnapp.utils.ApiUrl;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApproveListKajianActivity extends AppCompatActivity {

    private JadwalKajianApproveAdapter adapter;
    private List<JadwalKajianModel> listKajian = new ArrayList<>();

    private AppBarLayout appbar;
    private Toolbar toolbar;
    private ExpandableHeightListView svListApprove;

    private RequestQueue queue;
    private ApiUrl apiUrl;

    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unapproved_list_kajian);

        adapter = new JadwalKajianApproveAdapter(this, listKajian);
        adapter.notifyDataSetChanged();
        queue = Volley.newRequestQueue(this);
        apiUrl = new ApiUrl();
        loading = new ProgressDialog(this);

        initView();
        retrieveData();
    }

    private void initView() {
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        svListApprove = (ExpandableHeightListView) findViewById(R.id.svListApprove);
        svListApprove.setExpanded(true);
    }


    void retrieveData() {
        String url = apiUrl.getMainUrl() + "get_data.php?mode=02";
        //Log.d("isiResponse", url);
        // Request a string response from the provided URL.
        loading.setMessage("Mengambil data...");
        loading.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("isiResponse", response);
                        loading.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            String statusApi = obj.getString("afn_status");
                            listKajian.clear();
                            if (statusApi.equalsIgnoreCase("success")) {
                                JSONArray jsonArray = obj.getJSONArray("value");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    JadwalKajianModel jkModel = new JadwalKajianModel();
                                    jkModel.setIdKajian(object.getInt("idKajian"));
                                    jkModel.setTema(object.getString("tema"));
                                    jkModel.setTempat(object.getString("tempat"));
                                    jkModel.setAlamat(object.getString("alamat"));
                                    jkModel.setIsKhusus(object.getInt("isKhusus"));
                                    jkModel.setWaktu(object.getString("waktu"));
                                    listKajian.add(jkModel);
                                }

                                svListApprove.setAdapter(adapter);
                            } else {
                                showSnackbar("Kami belum mempunyai jadwal");
                            }
                        } catch (JSONException ex) {
                            showSnackbar("Gagal mengambil data");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.d("isiResponse", error.getMessage());
                //error.printStackTrace();
                loading.dismiss();
                showSnackbar("Tidak terhubung ke internet");
                //Toast.makeText(JadwalKajianMainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void showSnackbar(String isiValue) {
        Snackbar.make(svListApprove, isiValue, Snackbar.LENGTH_SHORT).show();
    }
}
