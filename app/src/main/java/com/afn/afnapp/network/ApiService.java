package com.afn.afnapp.network;

import com.afn.afnapp.model.JadwalSholat.Jadwal;
import com.afn.afnapp.model.JadwalSholat.ResponseJadwalSholat;
import com.afn.afnapp.model.KodeKota.ResponseKodeKota;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("sholat/format/json/jadwal/kota/{kota}/tanggal/{tgl}")
    Flowable<ResponseJadwalSholat> getJadwalSholat(@Path("kota") String kota, @Path("tgl") String tgl);

    @GET("sholat/format/json/kota/nama/{kota}")
    Flowable<ResponseKodeKota> getKodeKota(@Path("kota") String kota);
}
