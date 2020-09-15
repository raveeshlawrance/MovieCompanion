package com.accellion.moviecompanion.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MovieListResponse implements Parcelable {

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<ResultsItem> results;

	@SerializedName("total_results")
	private int totalResults;

	protected MovieListResponse(Parcel in) {
		page = in.readInt();
		totalPages = in.readInt();
		totalResults = in.readInt();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(page);
		dest.writeInt(totalPages);
		dest.writeInt(totalResults);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<MovieListResponse> CREATOR = new Creator<MovieListResponse>() {
		@Override
		public MovieListResponse createFromParcel(Parcel in) {
			return new MovieListResponse(in);
		}

		@Override
		public MovieListResponse[] newArray(int size) {
			return new MovieListResponse[size];
		}
	};

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setResults(List<ResultsItem> results){
		this.results = results;
	}

	public List<ResultsItem> getResults(){
		return results;
	}

	public void setTotalResults(int totalResults){
		this.totalResults = totalResults;
	}

	public int getTotalResults(){
		return totalResults;
	}
}