package com.afn.afnapp.ui.jadwalsholat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.afn.afnapp.R;
import com.afn.afnapp.model.JadwalKajianModel;
import com.afn.afnapp.model.JadwalSholat.ResponseJadwalSholat;
import com.afn.afnapp.model.KodeKota.ResponseKodeKota;
import com.afn.afnapp.utils.ApiUrl;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class JadwalSholatActivity extends AppCompatActivity {

    double lat, lng;

    private String cityName, fullCityName, address, kode_kota;
    private TextView tgl, lokasi, lokasilengkap, subuh, terbit, dhuha, dzuhur, ashar, maghrib, isya;
    private ProgressDialog progressDialog;

    private RequestQueue queue;
    private ApiUrl apiUrl;
    private ProgressDialog loading;
    private boolean isKota;

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jadwal_sholat_activity);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        queue = Volley.newRequestQueue(this);
        apiUrl = new ApiUrl();
        loading = new ProgressDialog(this);

        loading.setMessage("Mengambil data..");
        loading.setCancelable(true);
        loading.show();

        //Cek Permission Get Location
        if (ActivityCompat.checkSelfPermission(JadwalSholatActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(JadwalSholatActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(JadwalSholatActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        } else {
            //Convert Lat Lng to Text
            final Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                lat = location.getLatitude();
                                lng = location.getLongitude();
                                //Log.d("Lat Lng", String.valueOf(location.getLatitude()+","+String.valueOf(location.getLongitude())));

                                List<Address> addresses = null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                } catch (IOException e) {
                                    //e.printStackTrace();
                                }
                                fullCityName = addresses.get(0).getSubAdminArea();
                                cityName = addresses.get(0).getSubAdminArea();
                                address = addresses.get(0).getAddressLine(0);

                                String mystring = cityName;
                                String arr[] = mystring.split(" ", 2);
                                String firstWord = arr[0];
                                isKota = false;

                                Log.d("KabKota1", cityName);

                                if (firstWord.trim().toLowerCase().equalsIgnoreCase("kota")) {
                                    cityName = arr[1];
                                    isKota = true;
                                } else if (firstWord.trim().toLowerCase().equalsIgnoreCase("kabupaten")) {
                                    cityName = arr[1];
                                    isKota = false;
                                } else {
                                    cityName = arr[0];
                                    isKota = false;
                                }

                                Log.d("KabKota2", cityName);

                                retrieveData();
                            }
                        }
                    });

            setDataToText();
        }
    }

    private void setDataToText() {
        tgl = findViewById(R.id.txtTanggal);
        subuh = findViewById(R.id.txtSubuh);
        terbit = findViewById(R.id.txtTerbit);
        dhuha = findViewById(R.id.txtDhuha);
        dzuhur = findViewById(R.id.txtDzuhur);
        ashar = findViewById(R.id.txtAshar);
        maghrib = findViewById(R.id.txtMaghrib);
        isya = findViewById(R.id.txtIsya);
        lokasi = findViewById(R.id.txtLokasi);
        lokasilengkap = findViewById(R.id.txtLokasiLengkap);

    }

    void retrieveData() {
        String url = apiUrl.getUrlKota() + cityName;
        //Log.d("isiResponse", url);
        // Request a string response from the provided URL.
        loading.setMessage("Mengambil data...");
        loading.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        //Log.d("isiResponse", response);
                        try {
                            JSONObject obj = new JSONObject(response);
                            String statusApi = obj.getString("status");
                            if (statusApi.equalsIgnoreCase("ok")) {
                                JSONArray jsonArray = obj.getJSONArray("kota");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jObj = jsonArray.getJSONObject(i);
                                    if (isKota) {
                                        if (jObj.getString("nama").equalsIgnoreCase("Kota " + cityName)) {
                                            kode_kota = jObj.getString("id");
                                            break;
                                        } else {
                                            Log.d("namaKota", jObj.toString());
                                        }
                                    } else {
                                        if (jObj.getString("nama").equalsIgnoreCase(cityName)) {
                                            kode_kota = jObj.getString("id");
                                            break;
                                        } else {
                                            Log.d("namaKota", jObj.toString());
                                        }
                                    }
                                }
                                Log.d("kode_kota", kode_kota + "");
                                retrieveData2();
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
                loading.dismiss();
                //Log.d("isiResponse", error.getMessage());
                //error.printStackTrace();
                showSnackbar("Tidak terhubung ke internet");
                //Toast.makeText(JadwalKajianMainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    void retrieveData2() {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strdate = simpleDateFormat.format(calendar.getTime());

        String url = apiUrl.getUrlJadwal() + "/" + kode_kota + "/tanggal/" + strdate;
        //Log.d("isiResponse", url);
        // Request a string response from the provided URL.
        loading.setMessage("Mengambil data...");
        loading.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        //Log.d("isiResponse", response);
                        try {
                            JSONObject obj = new JSONObject(response);
                            String statusApi = obj.getString("status");
                            if (statusApi.equalsIgnoreCase("ok")) {
                                JSONObject jbo = obj.getJSONObject("jadwal");
                                JSONObject data = jbo.getJSONObject("data");

                                String[] ahad = data.getString("tanggal").split(",");
                                if (ahad[0].equals("Minggu")) {
                                    tgl.setText("Ahad," + ahad[1]);
                                } else {
                                    tgl.setText(ahad[0] + "," + ahad[1]);
                                }

                                subuh.setText(data.getString("subuh"));
                                terbit.setText(data.getString("terbit"));
                                dhuha.setText(data.getString("dhuha"));
                                dzuhur.setText(data.getString("dzuhur"));
                                ashar.setText(data.getString("ashar"));
                                maghrib.setText(data.getString("maghrib"));
                                isya.setText(data.getString("isya"));
                                lokasi.setText(fullCityName);
                                lokasilengkap.setText(address);

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
                loading.dismiss();
                //Log.d("isiResponse", error.getMessage());
                //error.printStackTrace();
                showSnackbar("Tidak terhubung ke internet");
                //Toast.makeText(JadwalKajianMainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void showSnackbar(String isiValue) {
        Snackbar.make(tgl, isiValue, Snackbar.LENGTH_SHORT).show();
    }

    /*private void getViewModel() {
        mViewModel.KodeKotaData(cityName).observe(JadwalSholatActivity.this, new Observer<ResponseKodeKota>() {
            @Override
            public void onChanged(@Nullable ResponseKodeKota responseKodeKota) {
                kode_kota = responseKodeKota.getKota().get(0).getId();
                //Log.d("Kode Kota", responseKodeKota.getKota().get(0).getId());

                //Get date system today
                Calendar calendar = Calendar.getInstance();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strdate = simpleDateFormat.format(calendar.getTime());
                //Log.d("Tgl",strdate);

                mViewModel.JadwalSholatData(kode_kota, strdate).observe(JadwalSholatActivity.this, new Observer<ResponseJadwalSholat>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onChanged(@Nullable ResponseJadwalSholat responseJadwalSholat) {
                        //Log.d("Jadwal Sholat", responseJadwalSholat.getJadwal().toString());

                        String[] ahad = responseJadwalSholat.getJadwal().getData().getTanggal().split(",");
                        if (ahad[0].equals("Minggu")) {
                            tgl.setText("Ahad," + ahad[1]);
                        } else {
                            tgl.setText(ahad[0] + "," + ahad[1]);
                        }

                        subuh.setText(responseJadwalSholat.getJadwal().getData().getSubuh());
                        terbit.setText(responseJadwalSholat.getJadwal().getData().getTerbit());
                        dhuha.setText(responseJadwalSholat.getJadwal().getData().getDhuha());
                        dzuhur.setText(responseJadwalSholat.getJadwal().getData().getDzuhur());
                        ashar.setText(responseJadwalSholat.getJadwal().getData().getAshar());
                        maghrib.setText(responseJadwalSholat.getJadwal().getData().getMaghrib());
                        isya.setText(responseJadwalSholat.getJadwal().getData().getIsya());
                        lokasi.setText(cityName);
                        lokasilengkap.setText(address);

                        progressDialog.dismiss();
                    }
                });
            }
        });
    }*/
}
