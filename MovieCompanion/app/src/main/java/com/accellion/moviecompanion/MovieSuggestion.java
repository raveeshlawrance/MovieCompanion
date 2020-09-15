package com.accellion.moviecompanion;

import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

public class MovieSuggestion implements SearchSuggestion {
    private String mColorName;
    private boolean mIsHistory = false;

    public MovieSuggestion(String suggestion) {
        this.mColorName = suggestion.toLowerCase();
    }

    public MovieSuggestion(Parcel source) {
        this.mColorName = source.readString();
        this.mIsHistory = source.readInt() != 0;
    }

    @Override
    public String getBody() {
        return mColorName;
    }

    public void setIsHistory(boolean isHistory) {
        this.mIsHistory = isHistory;
    }

    public boolean getIsHistory() {
        return this.mIsHistory;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieSuggestion> CREATOR = new Creator<MovieSuggestion>() {
        @Override
        public MovieSuggestion createFromParcel(Parcel in) {
            return new MovieSuggestion(in);
        }

        @Override
        public MovieSuggestion[] newArray(int size) {
            return new MovieSuggestion[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mColorName);
        parcel.writeInt(mIsHistory ? 1 : 0);
    }
}
