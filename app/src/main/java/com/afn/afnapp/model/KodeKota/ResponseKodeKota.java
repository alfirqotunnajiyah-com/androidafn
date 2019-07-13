package com.afn.afnapp.model.KodeKota;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseKodeKota{

	@SerializedName("kota")
	private List<KotaItem> kota;

	@SerializedName("query")
	private Query query;

	@SerializedName("status")
	private String status;

	public void setKota(List<KotaItem> kota){
		this.kota = kota;
	}

	public List<KotaItem> getKota(){
		return kota;
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
			"ResponseKodeKota{" + 
			"kota = '" + kota + '\'' + 
			",query = '" + query + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}