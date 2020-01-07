package com.star.batman.Model.Repository;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.MutableLiveData;

import com.star.batman.Model.Pojo.FilmDetail;
import com.star.batman.Model.Service.GetMovieDetailService;
import com.star.batman.Model.CallBackInterface.ICallBackGetMovieDetail;
import com.star.batman.Model.Utilities.Util;
import com.star.batman.View.Activity.batmanApplication;

public class FilmDetailRepository implements ICallBackGetMovieDetail {

    private MutableLiveData<FilmDetail> mLDFilmDetail = new MutableLiveData<>();

    public FilmDetailRepository(){};

    public MutableLiveData<FilmDetail> getmLDFilmDetail(String imdbID)
    {

        getFilmDetail(imdbID);

        return mLDFilmDetail;

    }

    private void getFilmDetail(String imdbID)
    {
        if (batmanApplication.isOnlineMode) {
            new GetMovieDetailService(imdbID, this);
        }
        else
        {
            mLDFilmDetail.setValue(Util.getFilmDetailLocally(imdbID));
        }
    }

    @Override
    public void GetMovieDetailOnTrueResponse(final FilmDetail filmDetail) {

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                //this method is for running in UI Thread
                if (filmDetail!=null)
                {
                    mLDFilmDetail.setValue(filmDetail);
                }
            }
        });

    }

    @Override
    public void GetMovieDetailOnFalseResponse(String error) {

    }

    @Override
    public void GetMovieDetailOnFailure(String error) {

    }
}
