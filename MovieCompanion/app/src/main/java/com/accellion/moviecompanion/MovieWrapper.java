package com.accellion.moviecompanion;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieWrapper implements Parcelable {

    @SerializedName("hex")
    @Expose
    private String hex;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rgb")
    @Expose
    private String rgb;

    private MovieWrapper(Parcel in) {
        hex = in.readString();
        name = in.readString();
        rgb = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hex);
        dest.writeString(name);
        dest.writeString(rgb);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     *
     * @return
     * The hex
     */
    public String getHex() {
        return hex;
    }

    /**
     *
     * @param hex
     * The hex
     */
    public void setHex(String hex) {
        this.hex = hex;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The rgb
     */
    public String getRgb() {
        return rgb;
    }

    /**
     *
     * @param rgb
     * The rgb
     */
    public void setRgb(String rgb) {
        this.rgb = rgb;
    }

    public static final Creator<MovieWrapper> CREATOR = new Creator<MovieWrapper>() {
        @Override
        public MovieWrapper createFromParcel(Parcel in) {
            return new MovieWrapper(in);
        }

        @Override
        public MovieWrapper[] newArray(int size) {
            return new MovieWrapper[size];
        }
    };
}
