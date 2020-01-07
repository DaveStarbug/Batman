package com.star.batman.Model.Service;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by davood on 17/6/19.
 */

public class NetworkLayer
{

    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(200, TimeUnit.SECONDS)
            .readTimeout(200,TimeUnit.SECONDS)
            .callTimeout(200,TimeUnit.SECONDS)
            .writeTimeout(200,TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build();


    public void getRequest(String url,Map<String, String> header, Callback callback)
    {
            try {
                if (header != null) {
                    Headers headerbuild = Headers.of(header);

                    Request request = new Request.Builder()
                            .url(url)
                            .headers(headerbuild)
                            .build();

                    client.newCall(request).enqueue(callback);
                } else {
                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    client.newCall(request).enqueue(callback);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    public void postRequest( String url, Map<String, String> header, String json, Callback callback)
    {

        //if (util.isNetworkAvailable(AppContext))
        {
            try {
                RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

                Headers headerbuild = Headers.of(header);

                Request request = new Request.Builder()
                        .url(url)
                        .headers(headerbuild)
                        .post(body)

                        .build();

                client.newCall(request).enqueue(callback);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
       // else
        {
            String Message="you are disconnected";
            //util.messageToUser(AppContext,Message);
        }

    }

    public void uploadRequest(String url,Map<String, String> header, File file, Callback callback)
    {

       // if (util.isNetworkAvailable(AppContext))
        {
        try {
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("upload", file.getName(),
                            RequestBody.create(MediaType.parse("audio/wav"), file))
                    .build();

            Headers headerbuild = Headers.of(header);

            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .headers(headerbuild)
                    .build();

            client.newCall(request).enqueue(callback);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        }
        //else
        {
            String Message="you are disconnected";
            //util.messageToUser(AppContext,Message);
        }
    }
}
