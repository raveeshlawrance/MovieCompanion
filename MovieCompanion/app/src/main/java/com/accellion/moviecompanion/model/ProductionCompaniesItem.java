package com.accellion.moviecompanion.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProductionCompaniesItem implements Parcelable {
	@SerializedName("logoPath")
	private String logoPath;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("originCountry")
	private String originCountry;

	protected ProductionCompaniesItem(Parcel in) {
		logoPath = in.readString();
		name = in.readString();
		id = in.readInt();
		originCountry = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(logoPath);
		dest.writeString(name);
		dest.writeInt(id);
		dest.writeString(originCountry);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<ProductionCompaniesItem> CREATOR = new Creator<ProductionCompaniesItem>() {
		@Override
		public ProductionCompaniesItem createFromParcel(Parcel in) {
			return new ProductionCompaniesItem(in);
		}

		@Override
		public ProductionCompaniesItem[] newArray(int size) {
			return new ProductionCompaniesItem[size];
		}
	};

	public void setLogoPath(String logoPath){
		this.logoPath = logoPath;
	}

	public String getLogoPath(){
		return logoPath;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setOriginCountry(String originCountry){
		this.originCountry = originCountry;
	}

	public String getOriginCountry(){
		return originCountry;
	}
}
