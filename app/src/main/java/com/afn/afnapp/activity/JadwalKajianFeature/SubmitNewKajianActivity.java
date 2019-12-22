package com.afn.afnapp.activity.JadwalKajianFeature;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.afn.afnapp.R;
import com.afn.afnapp.adapter.WilayahAdapter;
import com.afn.afnapp.model.JadwalKajianModel;
import com.afn.afnapp.model.WilayahModel;
import com.afn.afnapp.utils.ApiUrl;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SubmitNewKajianActivity extends AppCompatActivity {

    private AppBarLayout appbar;
    private Toolbar toolbar;
    private ScrollView svMain;
    private EditText etTema;
    private EditText etDetailTema;
    private EditText etPemateri;
    private EditText etDetailPemateri;
    private TextView tvTanggal;
    private TextView tvWaktu;
    private EditText etTempat;
    private EditText etAlamat;
    private EditText etLinkGmap;
    private TextView tvTipeKajian;
    private LinearLayout llIfRutin;
    private EditText etPekanKe;
    private TextView tvKajianUntuk;
    private Button btnAjukan;
    private LinearLayout llProvinsi;
    private TextView tvProvinsi;
    private LinearLayout llKabupaten;
    private TextView tvKabupaten;
    private LinearLayout llKecamatan;
    private TextView tvKecamatan;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private SimpleDateFormat dateFormatter;

    private ApiUrl apiUrl;
    private RequestQueue queue;

    private List<WilayahModel> listWilayah = new ArrayList<>();
    private WilayahAdapter adapter;
    private DialogPlus dialogProvinsi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_new_kajian);

        apiUrl = new ApiUrl();
        queue = Volley.newRequestQueue(this);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        adapter = new WilayahAdapter(this, listWilayah);
        adapter.notifyDataSetChanged();

        initView();
        initClick();
    }

    private void initView() {
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        svMain = (ScrollView) findViewById(R.id.svMain);
        etTema = (EditText) findViewById(R.id.etTema);
        etDetailTema = (EditText) findViewById(R.id.etDetailTema);
        etPemateri = (EditText) findViewById(R.id.etPemateri);
        etDetailPemateri = (EditText) findViewById(R.id.etDetailPemateri);
        tvTanggal = (TextView) findViewById(R.id.tvTanggal);
        tvWaktu = (TextView) findViewById(R.id.tvWaktu);
        etTempat = (EditText) findViewById(R.id.etTempat);
        etAlamat = (EditText) findViewById(R.id.etAlamat);
        etLinkGmap = (EditText) findViewById(R.id.etLinkGmap);
        tvTipeKajian = (TextView) findViewById(R.id.tvTipeKajian);
        llIfRutin = (LinearLayout) findViewById(R.id.llIfRutin);
        etPekanKe = (EditText) findViewById(R.id.etPekanKe);
        tvKajianUntuk = (TextView) findViewById(R.id.tvKajianUntuk);
        btnAjukan = (Button) findViewById(R.id.btnAjukan);
        llProvinsi = (LinearLayout) findViewById(R.id.llProvinsi);
        tvProvinsi = (TextView) findViewById(R.id.tvProvinsi);
        llKabupaten = (LinearLayout) findViewById(R.id.llKabupaten);
        tvKabupaten = (TextView) findViewById(R.id.tvKabupaten);
        llKecamatan = (LinearLayout) findViewById(R.id.llKecamatan);
        tvKecamatan = (TextView) findViewById(R.id.tvKecamatan);
    }

    private void initClick() {
        tvProvinsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWilayah(tvProvinsi, 1);
            }
        });
        tvKabupaten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tvKajianUntuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tvWaktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeDialog();
            }
        });

        tvTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
    }

    private void getWilayah(final TextView targetTv, final int level) {
        String url = apiUrl.getMainUrl() + "get_data_wilayah.php?mode=1";
        Log.d("isiResponse", url);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("isiResponse", response);
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray jsonArray = obj.getJSONArray("value");
                            listWilayah.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                WilayahModel wilayahModel = new WilayahModel();
                                wilayahModel.setIdWilayah(object.getInt("idWilayah"));
                                wilayahModel.setKodeWilayah(object.getString("kodeWilayah"));
                                wilayahModel.setMstWilayah(object.getString("mstWilayah"));
                                wilayahModel.setNama(object.getString("nama"));
                                wilayahModel.setLevel(object.getInt("level"));
                                listWilayah.add(wilayahModel);
                            }
                            dialogProvinsi = DialogPlus.newDialog(SubmitNewKajianActivity.this).setAdapter(adapter)
                                    .setOnItemClickListener(new OnItemClickListener() {
                                        @Override
                                        public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                                            WilayahModel wilModel = listWilayah.get(position);;
                                            targetTv.setText(wilModel.getNama());
                                            dialog.dismiss();
                                        }
                                    })
                                    .setHeader(R.layout.header_wilayah_provinsi)
                                    .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                                    .create();
                            dialogProvinsi.show();
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                            Toast.makeText(SubmitNewKajianActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("isiResponse", error.getMessage());
                error.printStackTrace();
                Toast.makeText(SubmitNewKajianActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("level", level + "");
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void showDateDialog() {

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                tvTanggal.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

    private void showTimeDialog() {

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                String hours = i + "", minutes = i1 + "";
                if (i < 10) hours = "0" + i;
                if (i1 < 10) minutes = "0" + i1;
                tvWaktu.setText(hours + ":" + minutes);
            }
        }, newCalendar.get(Calendar.HOUR_OF_DAY), newCalendar.get(Calendar.MINUTE), true);

        /**
         * Tampilkan DatePicker dialog
         */
        timePickerDialog.show();
    }
}
