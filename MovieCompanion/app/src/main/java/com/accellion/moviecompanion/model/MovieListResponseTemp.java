package com.accellion.moviecompanion.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieListResponseTemp implements Parcelable {

	@SerializedName("originalLanguage")
	private String originalLanguage;

	@SerializedName("imdbId")
	private String imdbId;

	@SerializedName("video")
	private boolean video;

	@SerializedName("title")
	private String title;

	@SerializedName("backdropPath")
	private String backdropPath;

	@SerializedName("revenue")
	private int revenue;

	@SerializedName("genres")
	private List<GenresItem> genres;

	@SerializedName("popularity")
	private double popularity;

	@SerializedName("productionCountries")
	private List<ProductionCountriesItem> productionCountries;

	@SerializedName("id")
	private int id;

	@SerializedName("voteCount")
	private int voteCount;

	@SerializedName("budget")
	private int budget;

	@SerializedName("overview")
	private String overview;

	@SerializedName("originalTitle")
	private String originalTitle;

	@SerializedName("runtime")
	private int runtime;

	@SerializedName("posterPath")
	private String posterPath;

	@SerializedName("spokenLanguages")
	private List<SpokenLanguagesItem> spokenLanguages;

	@SerializedName("productionCompanies")
	private List<ProductionCompaniesItem> productionCompanies;

	@SerializedName("releaseDate")
	private String releaseDate;

	@SerializedName("voteAverage")
	private double voteAverage;

	@SerializedName("belongsToCollection")
	private Object belongsToCollection;

	@SerializedName("tagline")
	private String tagline;

	@SerializedName("adult")
	private boolean adult;

	@SerializedName("homepage")
	private String homepage;

	@SerializedName("status")
	private String status;

	protected MovieListResponseTemp(Parcel in) {
		originalLanguage = in.readString();
		imdbId = in.readString();
		video = in.readByte() != 0;
		title = in.readString();
		backdropPath = in.readString();
		revenue = in.readInt();
		popularity = in.readDouble();
		id = in.readInt();
		voteCount = in.readInt();
		budget = in.readInt();
		overview = in.readString();
		originalTitle = in.readString();
		runtime = in.readInt();
		posterPath = in.readString();
		releaseDate = in.readString();
		voteAverage = in.readDouble();
		tagline = in.readString();
		adult = in.readByte() != 0;
		homepage = in.readString();
		status = in.readString();
	}

	public static final Creator<MovieListResponseTemp> CREATOR = new Creator<MovieListResponseTemp>() {
		@Override
		public MovieListResponseTemp createFromParcel(Parcel in) {
			return new MovieListResponseTemp(in);
		}

		@Override
		public MovieListResponseTemp[] newArray(int size) {
			return new MovieListResponseTemp[size];
		}
	};

	public void setOriginalLanguage(String originalLanguage){
		this.originalLanguage = originalLanguage;
	}

	public String getOriginalLanguage(){
		return originalLanguage;
	}

	public void setImdbId(String imdbId){
		this.imdbId = imdbId;
	}

	public String getImdbId(){
		return imdbId;
	}

	public void setVideo(boolean video){
		this.video = video;
	}

	public boolean isVideo(){
		return video;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setBackdropPath(String backdropPath){
		this.backdropPath = backdropPath;
	}

	public String getBackdropPath(){
		return backdropPath;
	}

	public void setRevenue(int revenue){
		this.revenue = revenue;
	}

	public int getRevenue(){
		return revenue;
	}

	public void setGenres(List<GenresItem> genres){
		this.genres = genres;
	}

	public List<GenresItem> getGenres(){
		return genres;
	}

	public void setPopularity(double popularity){
		this.popularity = popularity;
	}

	public double getPopularity(){
		return popularity;
	}

	public void setProductionCountries(List<ProductionCountriesItem> productionCountries){
		this.productionCountries = productionCountries;
	}

	public List<ProductionCountriesItem> getProductionCountries(){
		return productionCountries;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setVoteCount(int voteCount){
		this.voteCount = voteCount;
	}

	public int getVoteCount(){
		return voteCount;
	}

	public void setBudget(int budget){
		this.budget = budget;
	}

	public int getBudget(){
		return budget;
	}

	public void setOverview(String overview){
		this.overview = overview;
	}

	public String getOverview(){
		return overview;
	}

	public void setOriginalTitle(String originalTitle){
		this.originalTitle = originalTitle;
	}

	public String getOriginalTitle(){
		return originalTitle;
	}

	public void setRuntime(int runtime){
		this.runtime = runtime;
	}

	public int getRuntime(){
		return runtime;
	}

	public void setPosterPath(String posterPath){
		this.posterPath = posterPath;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public void setSpokenLanguages(List<SpokenLanguagesItem> spokenLanguages){
		this.spokenLanguages = spokenLanguages;
	}

	public List<SpokenLanguagesItem> getSpokenLanguages(){
		return spokenLanguages;
	}

	public void setProductionCompanies(List<ProductionCompaniesItem> productionCompanies){
		this.productionCompanies = productionCompanies;
	}

	public List<ProductionCompaniesItem> getProductionCompanies(){
		return productionCompanies;
	}

	public void setReleaseDate(String releaseDate){
		this.releaseDate = releaseDate;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public void setVoteAverage(double voteAverage){
		this.voteAverage = voteAverage;
	}

	public double getVoteAverage(){
		return voteAverage;
	}

	public void setBelongsToCollection(Object belongsToCollection){
		this.belongsToCollection = belongsToCollection;
	}

	public Object getBelongsToCollection(){
		return belongsToCollection;
	}

	public void setTagline(String tagline){
		this.tagline = tagline;
	}

	public String getTagline(){
		return tagline;
	}

	public void setAdult(boolean adult){
		this.adult = adult;
	}

	public boolean isAdult(){
		return adult;
	}

	public void setHomepage(String homepage){
		this.homepage = homepage;
	}

	public String getHomepage(){
		return homepage;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(originalLanguage);
		parcel.writeString(imdbId);
		parcel.writeByte((byte) (video ? 1 : 0));
		parcel.writeString(title);
		parcel.writeString(backdropPath);
		parcel.writeInt(revenue);
		parcel.writeDouble(popularity);
		parcel.writeInt(id);
		parcel.writeInt(voteCount);
		parcel.writeInt(budget);
		parcel.writeString(overview);
		parcel.writeString(originalTitle);
		parcel.writeInt(runtime);
		parcel.writeString(posterPath);
		parcel.writeString(releaseDate);
		parcel.writeDouble(voteAverage);
		parcel.writeString(tagline);
		parcel.writeByte((byte) (adult ? 1 : 0));
		parcel.writeString(homepage);
		parcel.writeString(status);
	}
}