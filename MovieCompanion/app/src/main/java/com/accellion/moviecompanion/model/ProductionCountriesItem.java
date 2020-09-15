package com.accellion.moviecompanion.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProductionCountriesItem implements Parcelable {

	@SerializedName("iso31661")
	private String iso31661;

	@SerializedName("name")
	private String name;

	protected ProductionCountriesItem(Parcel in) {
		iso31661 = in.readString();
		name = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(iso31661);
		dest.writeString(name);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<ProductionCountriesItem> CREATOR = new Creator<ProductionCountriesItem>() {
		@Override
		public ProductionCountriesItem createFromParcel(Parcel in) {
			return new ProductionCountriesItem(in);
		}

		@Override
		public ProductionCountriesItem[] newArray(int size) {
			return new ProductionCountriesItem[size];
		}
	};

	public void setIso31661(String iso31661){
		this.iso31661 = iso31661;
	}

	public String getIso31661(){
		return iso31661;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}
