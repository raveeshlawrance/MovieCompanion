package com.accellion.moviecompanion.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GenresItem implements Parcelable {
	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	protected GenresItem(Parcel in) {
		name = in.readString();
		id = in.readInt();
	}

	public static final Creator<GenresItem> CREATOR = new Creator<GenresItem>() {
		@Override
		public GenresItem createFromParcel(Parcel in) {
			return new GenresItem(in);
		}

		@Override
		public GenresItem[] newArray(int size) {
			return new GenresItem[size];
		}
	};

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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(name);
		parcel.writeInt(id);
	}
}
