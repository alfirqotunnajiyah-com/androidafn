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
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.afn.afnapp.R;
import com.afn.afnapp.model.JadwalSholat.ResponseJadwalSholat;
import com.afn.afnapp.model.KodeKota.ResponseKodeKota;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class JadwalSholat extends AppCompatActivity {

    private JadwalSholatViewModel mViewModel;
    double lat,lng;

    String cityName, address, kode_kota;
    TextView tgl, lokasi, lokasilengkap, subuh, terbit, dhuha, dzuhur, ashar, maghrib, isya;
    ProgressDialog progressDialog;

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jadwal_sholat_activity);

        mViewModel = ViewModelProviders.of(this).get(JadwalSholatViewModel.class);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mengambil data..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        //Cek Permission Get Location
        if (ActivityCompat.checkSelfPermission(JadwalSholat.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(JadwalSholat.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(JadwalSholat.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }else{
            // Write you code here if permission already given.
        }

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
                            cityName = addresses.get(0).getSubAdminArea();
                            address = addresses.get(0).getAddressLine(0);
                            //Log.d("Kota", cityName);

                            getViewModel();

                        }
                    }
                });

        setDataToText();
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

    private void getViewModel(){
        mViewModel.KodeKotaData(cityName).observe(JadwalSholat.this, new Observer<ResponseKodeKota>() {
            @Override
            public void onChanged(@Nullable ResponseKodeKota responseKodeKota) {
                kode_kota = responseKodeKota.getKota().get(0).getId();
                //Log.d("Kode Kota", responseKodeKota.getKota().get(0).getId());

                //Get date system today
                Calendar calendar = Calendar.getInstance();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strdate = simpleDateFormat.format(calendar.getTime());
                //Log.d("Tgl",strdate);

                mViewModel.JadwalSholatData(kode_kota, strdate).observe(JadwalSholat.this, new Observer<ResponseJadwalSholat>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onChanged(@Nullable ResponseJadwalSholat responseJadwalSholat) {
                        //Log.d("Jadwal Sholat", responseJadwalSholat.getJadwal().toString());

                        String[] ahad = responseJadwalSholat.getJadwal().getData().getTanggal().split(",");
                        if (ahad[0].equals("Minggu")){
                            tgl.setText("Ahad,"+ahad[1]);
                        } else {
                            tgl.setText(ahad[0]+","+ahad[1]);
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
    }
}
