package com.afn.afnapp.model.KodeKota;

import com.google.gson.annotations.SerializedName;

public class Query{

	@SerializedName("nama")
	private String nama;

	@SerializedName("format")
	private String format;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setFormat(String format){
		this.format = format;
	}

	public String getFormat(){
		return format;
	}

	@Override
 	public String toString(){
		return 
			"Query{" + 
			"nama = '" + nama + '\'' + 
			",format = '" + format + '\'' + 
			"}";
		}
}