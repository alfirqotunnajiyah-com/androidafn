package com.afn.afnapp.model.JadwalSholat;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("imsak")
	private String imsak;

	@SerializedName("isya")
	private String isya;

	@SerializedName("dzuhur")
	private String dzuhur;

	@SerializedName("dhuha")
	private String dhuha;

	@SerializedName("subuh")
	private String subuh;

	@SerializedName("terbit")
	private String terbit;

	@SerializedName("ashar")
	private String ashar;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("maghrib")
	private String maghrib;

	public void setImsak(String imsak){
		this.imsak = imsak;
	}

	public String getImsak(){
		return imsak;
	}

	public void setIsya(String isya){
		this.isya = isya;
	}

	public String getIsya(){
		return isya;
	}

	public void setDzuhur(String dzuhur){
		this.dzuhur = dzuhur;
	}

	public String getDzuhur(){
		return dzuhur;
	}

	public void setDhuha(String dhuha){
		this.dhuha = dhuha;
	}

	public String getDhuha(){
		return dhuha;
	}

	public void setSubuh(String subuh){
		this.subuh = subuh;
	}

	public String getSubuh(){
		return subuh;
	}

	public void setTerbit(String terbit){
		this.terbit = terbit;
	}

	public String getTerbit(){
		return terbit;
	}

	public void setAshar(String ashar){
		this.ashar = ashar;
	}

	public String getAshar(){
		return ashar;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	public void setMaghrib(String maghrib){
		this.maghrib = maghrib;
	}

	public String getMaghrib(){
		return maghrib;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"imsak = '" + imsak + '\'' + 
			",isya = '" + isya + '\'' + 
			",dzuhur = '" + dzuhur + '\'' + 
			",dhuha = '" + dhuha + '\'' + 
			",subuh = '" + subuh + '\'' + 
			",terbit = '" + terbit + '\'' + 
			",ashar = '" + ashar + '\'' + 
			",tanggal = '" + tanggal + '\'' + 
			",maghrib = '" + maghrib + '\'' + 
			"}";
		}
}