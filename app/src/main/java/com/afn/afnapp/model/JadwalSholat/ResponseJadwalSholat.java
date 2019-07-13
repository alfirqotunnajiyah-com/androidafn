package com.afn.afnapp.model.JadwalSholat;

import com.google.gson.annotations.SerializedName;

public class ResponseJadwalSholat{

	@SerializedName("jadwal")
	private Jadwal jadwal;

	@SerializedName("query")
	private Query query;

	@SerializedName("status")
	private String status;

	public void setJadwal(Jadwal jadwal){
		this.jadwal = jadwal;
	}

	public Jadwal getJadwal(){
		return jadwal;
	}

	public void setQuery(Query query){
		this.query = query;
	}

	public Query getQuery(){
		return query;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseJadwalSholat{" + 
			"jadwal = '" + jadwal + '\'' + 
			",query = '" + query + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}