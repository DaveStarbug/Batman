package com.star.batman.Model.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.star.batman.Model.CallBackInterface.ICallBackGetMovieDetail;
import com.star.batman.Model.Pojo.Film;
import com.star.batman.Model.Pojo.FilmDetail;
import com.star.batman.Model.Pojo.FilmWrapper;
import com.star.batman.Model.Service.GetMovieDetailService;
import com.star.batman.View.Activity.batmanApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Util {

    public static final String MyPREFERENCESNAME = "MY_SHARED_PREFERENCES";

    public static SharedPreferences MY_PREFERENCES;

    public static boolean isJSONValid(String response) {
        try {
            new JSONObject(response);
        } catch (JSONException ex) {
            try {
                new JSONArray(response);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }



    public static SharedPreferences InitializeSharedPreferences(Context _context)
    {
        MY_PREFERENCES = _context.getSharedPreferences(MyPREFERENCESNAME, Context.MODE_PRIVATE);
        return MY_PREFERENCES;
    }

    public static void saveListFilmsLocally(FilmWrapper filmWrapper)
    {

        Gson gson=new Gson();
        SharedPreferences.Editor editor = MY_PREFERENCES.edit();
        String convertedJsonObject=gson.toJson(filmWrapper);
        editor.putString("ListFilms",convertedJsonObject);
        editor.commit();


    }

    public static void saveFilmDetailLocally(FilmDetail filmDetail)
    {
        Gson gson=new Gson();
        SharedPreferences.Editor editor = MY_PREFERENCES.edit();
        String convertedJsonObject=gson.toJson(filmDetail);
        editor.putString(filmDetail.getImdbID(),convertedJsonObject);
        editor.commit();

    }

    public static FilmWrapper getFilmListLocally()
    {

            Gson gson=new Gson();
            FilmWrapper filmWrapper=null;
            if (!MY_PREFERENCES.getString("ListFilms","").isEmpty()) {
                filmWrapper = gson.fromJson(MY_PREFERENCES.getString("ListFilms", ""), FilmWrapper.class);
            }
            return filmWrapper;

    }

    public static FilmDetail getFilmDetailLocally(String imdbID)
    {
            Gson gson=new Gson();
            FilmDetail filmDetail=null;
            if (!MY_PREFERENCES.getString(imdbID,"").isEmpty()) {
                filmDetail = gson.fromJson(MY_PREFERENCES.getString(imdbID, ""), FilmDetail.class);
            }
            return filmDetail;
    }

    public static void SaveResultsLocally(FilmWrapper filmWrapper)
    {
        InitializeSharedPreferences(batmanApplication.m_Context);

        saveListFilmsLocally(filmWrapper);

        Film film;

        for (int i=0;i<filmWrapper.getmFilms().size();i++)
        {

            film = filmWrapper.getmFilms().get(i);

            new GetMovieDetailService(film.getImdbID(), new ICallBackGetMovieDetail() {
                @Override
                public void GetMovieDetailOnTrueResponse(FilmDetail filmDetail) {
                    saveFilmDetailLocally(filmDetail);
                }

                @Override
                public void GetMovieDetailOnFalseResponse(String error) {

                }

                @Override
                public void GetMovieDetailOnFailure(String error) {

                }
            });
        }
    }


}
