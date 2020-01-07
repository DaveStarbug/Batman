package com.star.batman.Model.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FilmDetail {

    @Expose
    @SerializedName("Title")
    private String Title;

    @Expose
    @SerializedName("Year")
    private String Year;

    @Expose
    @SerializedName("Rated")
    private String Rated;

    @Expose
    @SerializedName("Released")
    private String Released;

    @Expose
    @SerializedName("Runtime")
    private String Runtime;

    @Expose
    @SerializedName("Genre")
    private String Genre;

    @Expose
    @SerializedName("Director")
    private String Director;

    @Expose
    @SerializedName("Writer")
    private String Writer;

    @Expose
    @SerializedName("Actors")
    private String Actors;

    @Expose
    @SerializedName("Plot")
    private String Plot;

    @Expose
    @SerializedName("Language")
    private String Language;

    @Expose
    @SerializedName("Country")
    private String Country;

    @Expose
    @SerializedName("Awards")
    private String Awards;

    @Expose
    @SerializedName("Poster")
    private String Poster;

    @SerializedName("Ratings")
    @Expose
    private List<Rating> Ratings;

    public class Rating
    {

        @SerializedName("Source")
        @Expose
        private String Source;

        @SerializedName("Value")
        @Expose
        private String Value;

        public String getSource() {
            return Source;
        }

        public void setSource(String source) {
            Source = source;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String value) {
            Value = value;
        }
    }

    @SerializedName("Metascore")
    @Expose
    private String Metascore;

    @SerializedName("imdbRating")
    @Expose
    private String imdbRating;

    @SerializedName("imdbVotes")
    @Expose
    private String imdbVotes;

    @SerializedName("imdbID")
    @Expose
    private String imdbID;

    @SerializedName("Type")
    @Expose
    private String Type;

    @SerializedName("DVD")
    @Expose
    private String DVD;

    @SerializedName("BoxOffice")
    @Expose
    private String BoxOffice;

    @SerializedName("Production")
    @Expose
    private String Production;

    @SerializedName("Website")
    @Expose
    private String Website;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getRated() {
        return Rated;
    }

    public void setRated(String rated) {
        Rated = rated;
    }

    public String getReleased() {
        return Released;
    }

    public void setReleased(String released) {
        Released = released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getAwards() {
        return Awards;
    }

    public void setAwards(String awards) {
        Awards = awards;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public List<Rating> getRatings() {
        return Ratings;
    }

    public void setRatings(List<Rating> ratings) {
        Ratings = ratings;
    }

    public String getMetascore() {
        return Metascore;
    }

    public void setMetascore(String metascore) {
        Metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDVD() {
        return DVD;
    }

    public void setDVD(String DVD) {
        this.DVD = DVD;
    }

    public String getBoxOffice() {
        return BoxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        BoxOffice = boxOffice;
    }

    public String getProduction() {
        return Production;
    }

    public void setProduction(String production) {
        Production = production;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }
}
