package com.star.batman.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.star.batman.Model.Pojo.FilmDetail;
import com.star.batman.Model.Pojo.FilmWrapper;
import com.star.batman.Model.Repository.FilmDetailRepository;
import com.star.batman.Model.Repository.FilmRepository;

public class FilmViewModel extends AndroidViewModel {

    private FilmRepository filmRepository;
    private FilmDetailRepository filmDetailRepository;

    public FilmViewModel(Application application)
    {

        super(application);
        filmRepository=new FilmRepository();

    }

    public LiveData<FilmWrapper> getFilms(String serachValue)
    {
        return filmRepository.getmLDListFilm(serachValue);
    }

    public LiveData<FilmDetail> getFilmDetail(String imdbID)
    {
        filmDetailRepository=new FilmDetailRepository();

        return filmDetailRepository.getmLDFilmDetail(imdbID);

    }

}
