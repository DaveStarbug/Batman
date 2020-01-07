package com.star.batman.Model.Service;

import android.util.Log;

import com.google.gson.Gson;
import com.star.batman.Model.Pojo.Film;
import com.star.batman.Model.Pojo.FilmWrapper;
import com.star.batman.Model.Utilities.Util;
import com.star.batman.View.Activity.batmanApplication;
import com.star.batman.Model.CallBackInterface.ICallBackGetMovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GetMoviesService {

    public GetMoviesService(String searchValue, final ICallBackGetMovies listener)
    {
        String url=String.format("%s?apikey=%s&s=%s",batmanApplication.BASE_URL,batmanApplication.API_KEY,searchValue, Locale.US);

        new NetworkLayer().getRequest(url, null, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                String errorMessage=e.getMessage();
                listener.GetMoviesOnFailure(errorMessage);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String resp=response.body().string();

                if (Util.isJSONValid(resp))
                {

                    try {

                        if (new JSONObject(resp).getBoolean("Response"))
                        {

                            Log.i("Response",resp);

                            JSONArray array = new JSONObject(resp).getJSONArray("Search");

                            List<Film> films= Arrays.asList(new Gson().fromJson(array.toString(),Film[].class));

                            FilmWrapper filmWrapper=new FilmWrapper();
                            filmWrapper.setmFilms(films);
                            filmWrapper.setTotalResults(new JSONObject(resp).getInt("totalResults"));

                            listener.GetMoviesOnTrueResponse(filmWrapper);

                        }
                        else
                        {

                            String error=new JSONObject(resp).getString("Error");
                            listener.GetMoviesOnFalseResponse(error);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                else
                {
                    String error="Invalid Film List";
                    listener.GetMoviesOnFailure(error);
                }


            }
        });


    }
}
