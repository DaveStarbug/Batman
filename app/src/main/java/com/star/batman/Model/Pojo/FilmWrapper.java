package com.star.batman.Model.Pojo;

import java.util.ArrayList;
import java.util.List;

public class FilmWrapper {

    private List<Film> mFilms;

    private int totalResults;

    public FilmWrapper()
    {
        mFilms=new ArrayList<>();
    }

    public List<Film> getmFilms() {
        return mFilms;
    }

    public void setmFilms(List<Film> mFilms) {
        this.mFilms = mFilms;
    }

    public void addFilm(Film film)
    {
        mFilms.add(film);
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
