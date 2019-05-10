package com.afn.afnapp.model;

public class FiturModel {
    private int fiturId;
    private String fiturNama;
    private int fiturGambar;

    public void setFiturGambar(int fiturGambar) {
        this.fiturGambar = fiturGambar;
    }

    public int getFiturGambar() {
        return fiturGambar;
    }

    public void setFiturId(int fiturId) {
        this.fiturId = fiturId;
    }

    public void setFiturNama(String fiturNama) {
        this.fiturNama = fiturNama;
    }

    public int getFiturId() {
        return fiturId;
    }

    public String getFiturNama() {
        return fiturNama;
    }
}

