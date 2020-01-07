package com.star.batman.View.Activity;

import android.app.Application;
import android.content.Context;

public class batmanApplication extends Application {

    public static String BASE_URL="http://www.omdbapi.com/";

    public static String API_KEY="3e974fca";

    public static boolean isOnlineMode=true;

    public static Context m_Context;

    @Override
    public void onCreate() {
        super.onCreate();

        m_Context=getApplicationContext();

    }
}
