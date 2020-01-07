package com.star.batman.Model.Service;

import android.util.Log;

import com.google.gson.Gson;
import com.star.batman.Model.Pojo.FilmDetail;
import com.star.batman.Model.Utilities.Util;
import com.star.batman.View.Activity.batmanApplication;
import com.star.batman.Model.CallBackInterface.ICallBackGetMovieDetail;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GetMovieDetailService {

    public GetMovieDetailService(String imdbID, final ICallBackGetMovieDetail listener)
    {

        String url=String.format("%s?apikey=%s&i=%s", batmanApplication.BASE_URL,batmanApplication.API_KEY,imdbID, Locale.US);

        new NetworkLayer().getRequest(url, null, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                String error=e.getMessage();
                listener.GetMovieDetailOnFailure(error);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String resp=response.body().string();

                if (Util.isJSONValid(resp))
                {

                    try
                    {

                        if (new JSONObject(resp).getBoolean("Response"))
                        {

                            Log.i("Response",resp);

                            FilmDetail filmDetail=new Gson().fromJson(resp, FilmDetail.class);

                            listener.GetMovieDetailOnTrueResponse(filmDetail);

                        }
                        else
                        {

                            String error=new JSONObject(resp).getString("Error");
                            listener.GetMovieDetailOnFalseResponse(error);

                        }


                    }
                    catch (JSONException ex)
                    {
                        ex.printStackTrace();
                    }


                }else
                {

                    String error="Invalid Film Detail";
                    listener.GetMovieDetailOnFailure(error);

                }



            }
        });


    }
}
