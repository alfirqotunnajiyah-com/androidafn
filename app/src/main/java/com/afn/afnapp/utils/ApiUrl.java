package com.afn.afnapp.utils;


public class ApiUrl {
    //String mainUrl = "http://192.168.43.82/afn/public_html/";
    String mainUrl = "http://webforimage.000webhostapp.com/";
    String urlKota = "https://api.banghasan.com/sholat/format/json/kota/nama/";
    String urlJadwal = "https://api.banghasan.com/sholat/format/json/jadwal/kota";


    public String getUrlJadwal() {
        return urlJadwal;
    }

    public void setUrlJadwal(String urlJadwal) {
        this.urlJadwal = urlJadwal;
    }


    public String getUrlKota() {
        return urlKota;
    }

    public void setUrlKota(String urlKota) {
        this.urlKota = urlKota;
    }


    public String getMainUrl() {
        return mainUrl;
    }

    public void setMainUrl(String mainUrl) {
        this.mainUrl = mainUrl;
    }
}
