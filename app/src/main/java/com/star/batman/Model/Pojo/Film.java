package com.star.batman.Model.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Film {

    @Expose
    @SerializedName("Title")
    private String Title;

    @Expose
    @SerializedName("Year")
    private String Year;

    @Expose
    @SerializedName("imdbID")
    private String imdbID;

    @Expose
    @SerializedName("Type")
    private String Type;

    @Expose
    @SerializedName("Poster")
    private String Poster;

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

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }
}
