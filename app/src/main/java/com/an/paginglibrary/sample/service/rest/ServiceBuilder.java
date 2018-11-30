package com.an.paginglibrary.sample.service.rest;

import android.content.Context;

import com.an.paginglibrary.sample.service.dto.ResponseDTO;
import com.an.paginglibrary.sample.service.dto.UserDeserialier;
import com.an.paginglibrary.sample.service.pref.PrefWrapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Web services builder
 * Created by hungdn on 23/8/2017.
 */
public class ServiceBuilder {

  private static ServiceBuilder sInstance;
  private Retrofit mRetrofit;
  private ApiService mApiService;
  private String mBaseUrl;
  private static Context sContext;

  public ServiceBuilder(String endPoint) {
    mBaseUrl = endPoint;
  }

  public static ServiceBuilder getInstance() {
    return sInstance;
  }

  private String getCookie() {
    return "PHPSESSID=" + PrefWrapper.getInstance().getUser().getAccessToken();
  }

  public static void init(String endPoint, Context applicationContext) {
    sInstance = new ServiceBuilder(endPoint);
    sContext = applicationContext;
  }

  private Retrofit getRetrofit(String endPoint) {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    // User agent default
    OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(chain -> {
              // Set original User agent
              Request original = chain.request();

              // Build request with headers
              Request request = original.newBuilder()
                      .header("isMobileApp", "1")
                      .header("Cookie", PrefWrapper.getInstance().getUser() != null ? getCookie() : "")
                      .method(original.method(), original.body())
                      .build();

              return chain.proceed(request);
            })
            .build();

    if (mRetrofit == null) {

      Gson gson = new GsonBuilder()
              .registerTypeAdapter(ResponseDTO.class, new UserDeserialier())
              .create();

      mRetrofit = new Retrofit.Builder()
              .baseUrl(endPoint)
              .client(client)
              .addConverterFactory(GsonConverterFactory.create(gson))
              .build();
    }

    return mRetrofit;
  }

  public ApiService getService() {
    if (mApiService == null) {
      mApiService = getRetrofit(mBaseUrl).create(ApiService.class);
    }

    return mApiService;
  }

  public ApiService getService(String endPoint) {
    if (mApiService == null) {
      mApiService = getRetrofit(endPoint).create(ApiService.class);
    }

    return mApiService;
  }
}
