package com.accellion.moviecompanion.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SpokenLanguagesItem implements Parcelable {

	@SerializedName("name")
	private String name;

	@SerializedName("iso6391")
	private String iso6391;

	protected SpokenLanguagesItem(Parcel in) {
		name = in.readString();
		iso6391 = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(iso6391);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<SpokenLanguagesItem> CREATOR = new Creator<SpokenLanguagesItem>() {
		@Override
		public SpokenLanguagesItem createFromParcel(Parcel in) {
			return new SpokenLanguagesItem(in);
		}

		@Override
		public SpokenLanguagesItem[] newArray(int size) {
			return new SpokenLanguagesItem[size];
		}
	};

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setIso6391(String iso6391){
		this.iso6391 = iso6391;
	}

	public String getIso6391(){
		return iso6391;
	}
}
