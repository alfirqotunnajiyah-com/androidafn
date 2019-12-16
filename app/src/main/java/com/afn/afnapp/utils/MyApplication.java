package com.afn.afnapp.utils;
import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;

public class MyApplication extends Application {
    private String mainUrl;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(MyApplication.this);

        //mainUrl = "http://webforimage.000webhostapp.com/";

    }
}

