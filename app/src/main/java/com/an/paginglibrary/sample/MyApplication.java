package com.an.paginglibrary.sample;

import android.app.Application;

import com.an.paginglibrary.sample.service.pref.PrefWrapper;
import com.an.paginglibrary.sample.service.rest.ServiceBuilder;

public class MyApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();

    PrefWrapper.init(getApplicationContext());
    ServiceBuilder.init("https://admin.giaohangtietkiem.vn", getApplicationContext());
  }
}
