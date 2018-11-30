package com.an.paginglibrary.sample.notification;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.an.paginglibrary.sample.service.dto.ResponseDTO;
import com.an.paginglibrary.sample.service.rest.ServiceBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The NotificationDataSource
 */

public class NotificationDataSource extends PageKeyedDataSource<Long, NotificationDTO> {

  private static final String TAG = NotificationDataSource.class.getSimpleName();

  private static final long FIRST_PAGE = 1;
  private static long loadCount = 0;

  NotificationDataSource() {

  }

  @Override
  public void loadInitial(@NonNull LoadInitialParams<Long> params,
                          @NonNull LoadInitialCallback<Long, NotificationDTO> callback) {

    ServiceBuilder.getInstance().getService().getNotification((int) FIRST_PAGE).enqueue(new Callback<ResponseDTO<List<NotificationDTO>>>() {
      @Override
      public void onResponse(Call<ResponseDTO<List<NotificationDTO>>> call, Response<ResponseDTO<List<NotificationDTO>>> response) {
        if (response.isSuccessful()) {
          if (response.body() != null && response.body().getData() != null) {
            callback.onResult(response.body().getData(), null, FIRST_PAGE + 1);
            loadCount += response.body().getData().size();
          }

        }
      }

      @Override
      public void onFailure(Call<ResponseDTO<List<NotificationDTO>>> call, Throwable t) {
        Log.e("@@@@@", "onFailure");
      }
    });
  }

  @Override
  public void loadBefore(@NonNull LoadParams<Long> params,
                         @NonNull LoadCallback<Long, NotificationDTO> callback) {
    ServiceBuilder.getInstance().getService().getNotification(params.key).enqueue(new Callback<ResponseDTO<List<NotificationDTO>>>() {
      @Override
      public void onResponse(Call<ResponseDTO<List<NotificationDTO>>> call, Response<ResponseDTO<List<NotificationDTO>>> response) {
        if(response.isSuccessful()) {
          Long adjacentKey = (params.key > 1) ? params.key - 1 : null;
          if (response.body() != null && response.body().getData() != null) {
            callback.onResult(response.body().getData(), adjacentKey);
            loadCount += response.body().getData().size();
          }

        }
      }

      @Override
      public void onFailure(Call<ResponseDTO<List<NotificationDTO>>> call, Throwable t) {

      }
    });
  }

  @Override
  public void loadAfter(@NonNull LoadParams<Long> params,
                        @NonNull LoadCallback<Long, NotificationDTO> callback) {

    Log.i(TAG, "Loading Rang " + params.key + " Count " + params.requestedLoadSize);

    ServiceBuilder.getInstance().getService().getNotification(params.key).enqueue(new Callback<ResponseDTO<List<NotificationDTO>>>() {
      @Override
      public void onResponse(Call<ResponseDTO<List<NotificationDTO>>> call, Response<ResponseDTO<List<NotificationDTO>>> response) {
        if(response.isSuccessful()) {
          if (response.body() != null && response.body().getData() != null) {
            Long nextKey;
            if (loadCount <= (response.body() != null ? response.body().getCount() : 0)) nextKey = params.key + 1;
            else nextKey = null;
            callback.onResult(response.body().getData(), nextKey);
            loadCount += response.body().getData().size();
          }
        }
      }

      @Override
      public void onFailure(Call<ResponseDTO<List<NotificationDTO>>> call, Throwable t) {

      }
    });
//    appController.getRestApi().fetchFeed(QUERY, API_KEY, params.key, params.requestedLoadSize).enqueue(new Callback<Feed>() {
//      @Override
//      public void onResponse(Call<Feed> call, Response<Feed> response) {
//        if(response.isSuccessful()) {
//          long nextKey = (params.key == response.body().getTotalResults()) ? null : params.key+1;
//          callback.onResult(response.body().getArticles(), nextKey);
//          networkState.postValue(NetworkState.LOADED);
//
//        } else networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
//      }
//
//      @Override
//      public void onFailure(Call<Feed> call, Throwable t) {
//        String errorMessage = t == null ? "unknown error" : t.getMessage();
//        networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
//      }
//    });
  }
}
