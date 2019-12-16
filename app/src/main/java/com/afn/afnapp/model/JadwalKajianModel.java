package com.afn.afnapp.model;

public class JadwalKajianModel {
    private int idKajian,status,pekanKe,isKhusus,isApprove,kajianTypeId;

    public int getIdKajian() {
        return idKajian;
    }

    public void setIdKajian(int idKajian) {
        this.idKajian = idKajian;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPekanKe() {
        return pekanKe;
    }

    public void setPekanKe(int pekanKe) {
        this.pekanKe = pekanKe;
    }

    public int getIsKhusus() {
        return isKhusus;
    }

    public void setIsKhusus(int isKhusus) {
        this.isKhusus = isKhusus;
    }

    public int getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(int isApprove) {
        this.isApprove = isApprove;
    }

    public int getKajianTypeId() {
        return kajianTypeId;
    }

    public void setKajianTypeId(int kajianTypeId) {
        this.kajianTypeId = kajianTypeId;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getInfoTema() {
        return infoTema;
    }

    public void setInfoTema(String infoTema) {
        this.infoTema = infoTema;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getPemateri() {
        return pemateri;
    }

    public void setPemateri(String pemateri) {
        this.pemateri = pemateri;
    }

    public String getInfoPemateri() {
        return infoPemateri;
    }

    public void setInfoPemateri(String infoPemateri) {
        this.infoPemateri = infoPemateri;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getInfoTempat() {
        return infoTempat;
    }

    public void setInfoTempat(String infoTempat) {
        this.infoTempat = infoTempat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getLinkMaps() {
        return linkMaps;
    }

    public void setLinkMaps(String linkMaps) {
        this.linkMaps = linkMaps;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    private String tema,infoTema,tanggal,waktu,pemateri,infoPemateri,tempat,infoTempat,alamat,linkMaps,deviceId;
}
