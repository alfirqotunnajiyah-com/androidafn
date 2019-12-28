package com.afn.afnapp.activity.JadwalKajianFeature;

import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afn.afnapp.R;
import com.afn.afnapp.adapter.JadwalKajianAdapter;
import com.afn.afnapp.model.JadwalKajianModel;
import com.afn.afnapp.utils.ApiUrl;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightGridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JadwalKajianMainActivity extends AppCompatActivity {

    private JadwalKajianAdapter adapter;
    private List<JadwalKajianModel> listKajian = new ArrayList<>();
    private List<JadwalKajianModel> listKajian2 = new ArrayList<>();
    private ExpandableHeightGridView gvList;
    private EditText etSearch;
    private Toolbar toolbar;
    private RequestQueue queue;
    private ApiUrl apiUrl;
    private ImageView btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_kajian_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapter = new JadwalKajianAdapter(this, listKajian);
        adapter.notifyDataSetChanged();
        queue = Volley.newRequestQueue(this);
        apiUrl = new ApiUrl();


        initView();
        initClick();
        retrieveData();

        String android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        //Toast.makeText(this, android_id + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(JadwalKajianMainActivity.this, SubmitNewKajianActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void initView() {
        gvList = findViewById(R.id.gvList);
        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);
        gvList.setExpanded(true);
    }

    void initClick() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        gvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                JadwalKajianModel mm;
                /*if (etSearch.getText().length() > 0) {
                    mm = listKajian2.get(i);
                } else {
                    mm = listKajian.get(i);
                }*/

                mm = listKajian.get(i);

                Intent intent = new Intent(JadwalKajianMainActivity.this, JadwalKajianDetailActivity.class);
                intent.putExtra("idKajian", mm.getIdKajian());
                startActivity(intent);
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /*String isiNa = getTextNa(etSearch);
                listKajian2.clear();
                for (int i = 0; i < listKajian.size(); i++) {
                    JadwalKajianModel ll = listKajian.get(i);
                    if (ll.getTema().trim().toLowerCase().startsWith(isiNa.trim().toLowerCase())) {
                        ll.setTema(ll.getTema());
                        ll.setIdKajian(ll.getIdKajian());
                        listKajian2.add(ll);
                    }
                }
                adapter = new JadwalKajianAdapter(JadwalKajianMainActivity.this, listKajian2);
                adapter.notifyDataSetChanged();
                gvList.setAdapter(adapter);*/
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getTextNa(etSearch).length() > 0) {
                    retrieveData(getTextNa(etSearch));
                }
            }
        });
    }

    String getTextNa(EditText et) {
        return et.getText().toString();
    }

    void retrieveData() {
        String url = apiUrl.getMainUrl() + "get_data.php?mode=11";
        //Log.d("isiResponse", url);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("isiResponse", response);
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

                                gvList.setAdapter(adapter);
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
                showSnackbar("Tidak terhubung ke internet");
                //Toast.makeText(JadwalKajianMainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    void retrieveData(final String keyword) {
        String url = apiUrl.getMainUrl() + "get_data.php?mode=13";
        Log.d("isiResponse", url);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("isiResponse", response);
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

                                gvList.setAdapter(adapter);
                            } else {
                                showSnackbar("Kami belum mempunyai jadwal dengan keyword \"" + keyword + "\"");
                                gvList.setAdapter(adapter);
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
                showSnackbar("Tidak terhubung ke internet");
                //Toast.makeText(JadwalKajianMainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("p_keyword", keyword);
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void showSnackbar(String isiValue) {
        Snackbar.make(etSearch, isiValue, Snackbar.LENGTH_SHORT).show();
    }
}
