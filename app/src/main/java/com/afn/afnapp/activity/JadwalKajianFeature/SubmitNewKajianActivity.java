package com.afn.afnapp.activity.JadwalKajianFeature;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import com.afn.afnapp.adapter.MySimpleAdapter;
import com.afn.afnapp.adapter.WilayahAdapter;
import com.afn.afnapp.model.SimpleModel;
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
import com.shuhart.stepview.StepView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private List<SimpleModel> listTipe = new ArrayList<>();
    private List<SimpleModel> listUntuk = new ArrayList<>();

    private WilayahAdapter adapter;
    private MySimpleAdapter adapterTipe;
    private MySimpleAdapter adapterUntuk;

    private DialogPlus dialogTipe;
    private DialogPlus dialogUntuk;
    private DialogPlus dialogProvinsi;

    private String
            idProvinsi,
            idKabupaten,
            idKecamatan;

    private int
            idTipeKajian,
            idKajianUntuk;

    private String android_id;
    private StepView stepView;
    private LinearLayout ll1;
    private LinearLayout ll2;
    private LinearLayout ll3;
    private int currentPos = 1;

    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_new_kajian);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        loading = new ProgressDialog(this);
        apiUrl = new ApiUrl();
        queue = Volley.newRequestQueue(this);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        adapter = new WilayahAdapter(this, listWilayah);
        adapter.notifyDataSetChanged();
        adapterTipe = new MySimpleAdapter(this, listTipe);
        adapterTipe.notifyDataSetChanged();
        adapterUntuk = new MySimpleAdapter(this, listUntuk);
        adapterUntuk.notifyDataSetChanged();

        initView();
        initDialog();
        initClick();
        initStep();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.help_kajian, menu);
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
            startActivity(new Intent(SubmitNewKajianActivity.this,FloatingHelpActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initStep() {
        stepView.getState()
                .animationType(StepView.ANIMATION_CIRCLE)
                .selectedCircleColor(ContextCompat.getColor(this, R.color.colorAccent))
                .selectedStepNumberColor(ContextCompat.getColor(this, R.color.white))
                .stepsNumber(3)
                .doneTextColor(ContextCompat.getColor(this, R.color.white))
                .doneCircleColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .doneStepMarkColor(ContextCompat.getColor(this, R.color.white))
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .typeface(ResourcesCompat.getFont(SubmitNewKajianActivity.this, R.font.robothin))
                // other state methods are equal to the corresponding xml attributes
                .commit();
    }

    private void initView() {
        appbar = (AppBarLayout) findViewById(R.id.appbar);
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
        stepView = (StepView) findViewById(R.id.step_view);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        ll3 = (LinearLayout) findViewById(R.id.ll3);
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
                if (tvProvinsi.getText().length() == 0) {
                    Toast.makeText(SubmitNewKajianActivity.this, "Pilih Provisi terlebih dahulu", Toast.LENGTH_SHORT).show();
                } else {
                    getWilayah(tvKabupaten, 2);
                }
            }
        });

        tvKecamatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvKabupaten.getText().length() == 0) {
                    Toast.makeText(SubmitNewKajianActivity.this, "Pilih Kota/Kabupaten terlebih dahulu", Toast.LENGTH_SHORT).show();
                } else {
                    getWilayah(tvKecamatan, 3);
                }
            }
        });

        tvKajianUntuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogUntuk.show();
            }
        });

        tvTipeKajian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogTipe.show();
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
        btnAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPos == 1) {
                    if (isValid(etTema, 5)) {
                        showSnackbar("Tema minimal 5 Karakter", etTema);
                    } else if (isValid(etDetailTema, 5)) {
                        showSnackbar("Detail Tema minimal 5 Karakter", etDetailTema);
                    } else if (isValid(etPemateri, 5)) {
                        showSnackbar("Pemateri minimal 5 Karakter", etPemateri);
                    } else if (isValid(etDetailPemateri, 5)) {
                        showSnackbar("Detail Pemateri minimal  Karakter", etDetailPemateri);
                    } else {
                        btnAjukan.setText("Selanjutnya");
                        ll1.setVisibility(View.GONE);
                        ll2.setVisibility(View.VISIBLE);
                        currentPos = 2;
                        stepView.go(currentPos - 1, true);
                    }
                } else if (currentPos == 2) {
                    if (isValid(tvTanggal, 0)) {
                        showSnackbar("Tanggal harap diisi");
                    } else if (isValid(tvWaktu, 0)) {
                        showSnackbar("Waktu harap diisi");
                    } else if (isValid(tvProvinsi, 0)) {
                        showSnackbar("Anda belum memilih Provinsi");
                    } else if (isValid(tvKabupaten, 0)) {
                        showSnackbar("Anda belum memilih Kabupaten");
                    } else if (isValid(tvKecamatan, 0)) {
                        showSnackbar("Anda belum memilih Kecamatan");
                    } else if (isValid(etAlamat, 10)) {
                        showSnackbar("Alamat minimal 10 Karakter", etAlamat);
                    } else {
                        btnAjukan.setText("Ajukan Kajian");
                        ll2.setVisibility(View.GONE);
                        ll3.setVisibility(View.VISIBLE);
                        currentPos = 3;
                        stepView.go(currentPos - 1, true);
                    }
                } else {
                    if (isValid(tvTipeKajian, 0)) {
                        showSnackbar("Anda belum memilih tipe kajian");
                    } else if (isValid(tvKajianUntuk, 0)) {
                        showSnackbar("Anda belum memilih kajian untuk");
                    } else {
                        if (llIfRutin.getVisibility() == View.VISIBLE) {
                            if (isValid(etPekanKe, 0)) {
                                showSnackbar("Anda belum mengisi pekan ke", etPekanKe);
                            } else {
                                ajukanKajian();
                            }
                        } else {
                            ajukanKajian();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (currentPos == 1) {
            finish();
        } else if (currentPos == 2) {
            btnAjukan.setText("Selanjutnya");
            ll1.setVisibility(View.VISIBLE);
            ll2.setVisibility(View.GONE);
            currentPos = 1;
            stepView.go(currentPos - 1, true);
        } else {
            btnAjukan.setText("Selanjutnya");
            ll2.setVisibility(View.VISIBLE);
            ll3.setVisibility(View.GONE);
            currentPos = 2;
            stepView.go(currentPos - 1, true);
        }
    }

    private void initDialog() {
        String[] tipeKajian = {"Rutin", "Tematik", "Tabligh Akbar", "Daurah"};
        String[] kajianUntuk = {"Umum", "Ikhwan", "Akhwat"};

        for (int i = 0; i < tipeKajian.length; i++) {
            SimpleModel sp = new SimpleModel();
            sp.setIdSimple(i + 1);
            sp.setNameSimple(tipeKajian[i]);
            listTipe.add(sp);
        }

        for (int i = 0; i < kajianUntuk.length; i++) {
            SimpleModel sp = new SimpleModel();
            sp.setIdSimple(i + 1);
            sp.setNameSimple(kajianUntuk[i]);
            listUntuk.add(sp);
        }

        dialogUntuk = DialogPlus.newDialog(SubmitNewKajianActivity.this).setAdapter(adapterUntuk)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                        SimpleModel pp = listUntuk.get(position);
                        tvKajianUntuk.setText(pp.getNameSimple());
                        idKajianUntuk = pp.getIdSimple();
                        dialog.dismiss();
                    }
                })
                .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                .create();

        dialogTipe = DialogPlus.newDialog(SubmitNewKajianActivity.this).setAdapter(adapterTipe)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                        SimpleModel pp = listTipe.get(position);
                        tvTipeKajian.setText(pp.getNameSimple());
                        idTipeKajian = pp.getIdSimple();
                        if (idTipeKajian == 1) {
                            llIfRutin.setVisibility(View.VISIBLE);
                        } else {
                            llIfRutin.setVisibility(View.GONE);
                        }
                        dialog.dismiss();
                    }
                })
                .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                .create();
    }

    private void getWilayah(final TextView targetTv, final int level) {
        String url = apiUrl.getMainUrl() + "get_data_wilayah.php?mode=1";
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
                            if (statusApi.equalsIgnoreCase("success")) {
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
                                                WilayahModel wilModel = listWilayah.get(position);
                                                if (level == 1) {
                                                    idProvinsi = wilModel.getKodeWilayah();
                                                    idKabupaten = "";
                                                    idKecamatan = "";

                                                    tvKabupaten.setText("");
                                                    tvKecamatan.setText("");
                                                } else if (level == 2) {
                                                    idKabupaten = wilModel.getKodeWilayah();
                                                    idKecamatan = "";

                                                    tvKecamatan.setText("");
                                                } else {
                                                    idKecamatan = wilModel.getKodeWilayah();
                                                }
                                                targetTv.setText(wilModel.getNama());
                                                dialog.dismiss();
                                            }
                                        })
                                        .setHeader(R.layout.header_wilayah_provinsi)
                                        .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                                        .create();
                                dialogProvinsi.show();
                            }
                        } catch (JSONException ex) {
                            //ex.printStackTrace();
                            showSnackbar("Gagal mengambil data");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                //Log.d("isiResponse", error.getMessage());
                //error.printStackTrace();
                //Toast.makeText(SubmitNewKajianActivity.this, "Error", Toast.LENGTH_SHORT).show();
                showSnackbar("Tidak terhubung ke internet");
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("level", level + "");
                if (level == 2) {
                    params.put("mstWilayah", idProvinsi + "");
                } else if (level == 3) {
                    params.put("mstWilayah", idKabupaten + "");
                }
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void ajukanKajian() {
        String url = apiUrl.getMainUrl() + "insert_data.php?mode=01";
        //Log.d("isiResponse", url);
        // Request a string response from the provided URL.
        loading.setMessage("Menambah data...");
        loading.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        //Log.d("isiResponse", response);
                        try {
                            JSONObject obj = new JSONObject(response);
                            String statusApi = obj.getString("afn_status");
                            if (statusApi.equalsIgnoreCase("success")) {
                                final int idKajian = obj.getInt("value");
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(SubmitNewKajianActivity.this);
                                alertDialog
                                        .setCancelable(false)
                                        .setTitle("Berhasil")
                                        .setMessage("Pengajuan kajian berhasil diajukan, silakan tunggu konfirmasi dari kami")
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent = new Intent(SubmitNewKajianActivity.this, JadwalKajianDetailActivity.class);
                                                intent.putExtra("idKajian", idKajian);
                                                startActivity(intent);
                                                finish();
                                            }
                                        });
                                alertDialog.show();
                            } else {
                                showSnackbar("Data gagal disimpan");
                            }
                        } catch (JSONException ex) {
                            //ex.printStackTrace();
                            showSnackbar("Data gagal disimpan");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                //Log.d("isiResponse", error.getMessage());
                //error.printStackTrace();
                showSnackbar("Tidak terhubung ke internet");
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("p_tema", getText(etTema) + "");
                params.put("p_infoTema", getText(etDetailTema) + "");
                params.put("p_tanggal", getText(tvTanggal) + "");
                params.put("p_waktu", getText(tvWaktu) + "");
                params.put("p_pemateri", getText(etPemateri) + "");
                params.put("p_infoPemateri", getText(etDetailPemateri) + "");
                params.put("p_tempat", getText(etTempat) + "");
                params.put("p_alamat", getText(etAlamat) + "");
                params.put("p_linkMaps", getText(etLinkGmap) + "");
                params.put("p_pekanKe", getText(etPekanKe) + "");
                params.put("p_isKhusus", idKajianUntuk + "");
                params.put("p_isApprove", 0 + "");
                params.put("p_kajianTypeId", idTipeKajian + "");
                params.put("p_deviceId", android_id + "");
                params.put("p_namaProvinsi", getText(tvProvinsi) + "");
                params.put("p_namaKabupaten", getText(tvKabupaten) + "");
                params.put("p_namaKecamatan", getText(tvKecamatan) + "");
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private String getText(EditText et) {
        return et.getText().toString();
    }

    private String getText(TextView et) {
        return et.getText().toString();
    }

    private boolean isValid(EditText et, int minLength) {
        if (et.getText().length() > minLength) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isValid(TextView et, int minLength) {
        if (et.getText().length() > minLength) {
            return false;
        } else {
            return true;
        }
    }

    private void showSnackbar(String isiValue, EditText et) {
        Snackbar.make(et, isiValue, Snackbar.LENGTH_SHORT).setAction("Oke", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.requestFocus();
            }
        }).show();
    }

    private void showSnackbar(String isiValue) {
        Snackbar.make(tvKajianUntuk, isiValue, Snackbar.LENGTH_SHORT).show();
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
        }, 07, 00, true);

        /**
         * Tampilkan DatePicker dialog
         */
        timePickerDialog.show();
    }
}
