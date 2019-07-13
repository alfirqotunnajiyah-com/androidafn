package com.afn.afnapp.ui.jadwalsholat;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.afn.afnapp.model.JadwalSholat.Jadwal;
import com.afn.afnapp.model.JadwalSholat.ResponseJadwalSholat;
import com.afn.afnapp.model.KodeKota.ResponseKodeKota;
import com.afn.afnapp.repo.Repository;

public class JadwalSholatViewModel extends ViewModel {
    private Repository repository = new Repository();

    public LiveData<ResponseJadwalSholat> JadwalSholatData(String kota, String tgl){
        return repository.getInstance().getjadwalsholat(kota, tgl);
    }

    public LiveData<ResponseKodeKota> KodeKotaData(String kota){
        return repository.getInstance().getkodekota(kota);
    }

    public void onDestroy(){
        repository.getInstance().getNetwork();
    }
}
