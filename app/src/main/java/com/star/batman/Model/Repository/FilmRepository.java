package com.star.batman.Model.Repository;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.MutableLiveData;
import com.star.batman.Model.Pojo.FilmWrapper;
import com.star.batman.Model.Service.GetMoviesService;
import com.star.batman.Model.CallBackInterface.ICallBackGetMovies;
import com.star.batman.Model.Utilities.Util;
import com.star.batman.View.Activity.batmanApplication;


public class FilmRepository implements ICallBackGetMovies {

    private MutableLiveData<FilmWrapper> mLDListFilm=new MutableLiveData<>();

    public FilmRepository() { }

    public MutableLiveData<FilmWrapper> getmLDListFilm(String searchValue)
    {

        GetMovies(searchValue);

        return mLDListFilm;

    }

    private void GetMovies(String searchValue)
    {
        if (batmanApplication.isOnlineMode) {
            new GetMoviesService(searchValue, this);
        }
        else
        {
            mLDListFilm.setValue(Util.getFilmListLocally());
        }
    }


    @Override
    public void GetMoviesOnTrueResponse(final FilmWrapper filmWrapper) {

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                //this method is for running in UI Thread
                if (filmWrapper!=null)
                {
                    //return list film
                    mLDListFilm.setValue(filmWrapper);
                    //save locally
                    Util.SaveResultsLocally(filmWrapper);
                }
            }
        });

    }

    @Override
    public void GetMoviesOnFalseResponse(String error) {

    }

    @Override
    public void GetMoviesOnFailure(String error) {

    }
}
