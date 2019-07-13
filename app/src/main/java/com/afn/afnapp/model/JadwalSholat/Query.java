package com.afn.afnapp.model.JadwalSholat;

import com.google.gson.annotations.SerializedName;

public class Query{

	@SerializedName("kota")
	private String kota;

	@SerializedName("format")
	private String format;

	@SerializedName("tanggal")
	private String tanggal;

	public void setKota(String kota){
		this.kota = kota;
	}

	public String getKota(){
		return kota;
	}

	public void setFormat(String format){
		this.format = format;
	}

	public String getFormat(){
		return format;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	@Override
 	public String toString(){
		return 
			"Query{" + 
			"kota = '" + kota + '\'' + 
			",format = '" + format + '\'' + 
			",tanggal = '" + tanggal + '\'' + 
			"}";
		}
}