package com.afn.afnapp.model;

public class AyahModel {

    private int id, noAyah, surahId;
    private String ayah;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoAyah() {
        return noAyah;
    }

    public void setNoAyah(int noAyah) {
        this.noAyah = noAyah;
    }

    public int getSurahId() {
        return surahId;
    }

    public void setSurahId(int surahId) {
        this.surahId = surahId;
    }

    public String getAyah() {
        return ayah;
    }

    public void setAyah(String ayah) {
        this.ayah = ayah;
    }

    public String getAyahTranslate() {
        return ayahTranslate;
    }

    public void setAyahTranslate(String ayahTranslate) {
        this.ayahTranslate = ayahTranslate;
    }

    private String ayahTranslate;

}
