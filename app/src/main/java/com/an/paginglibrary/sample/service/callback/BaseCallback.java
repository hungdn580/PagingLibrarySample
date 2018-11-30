package com.an.paginglibrary.sample.service.callback;

import android.util.Log;

import com.an.paginglibrary.sample.service.dto.ResponseDTO;
import com.an.paginglibrary.sample.service.dto.UserDTO;
import com.google.gson.JsonSyntaxException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Base API callback1
 * Created by hungdn on 11/7/2016.
 */

public abstract class BaseCallback<T> implements Callback<ResponseDTO<T>> {

  public static final String NETWORK_ERROR = "9999";
  //  private Paging mPaging;
  private ResponseDTO<T> mBody;
  private ResponseBody mErrorBody;

  @Override
  public void onResponse(Call<ResponseDTO<T>> call, Response<ResponseDTO<T>> response) {
    // Get body of request
    mBody = null;
    mErrorBody = null;
    String responseCode = NETWORK_ERROR;
    String message = "";
    mErrorBody = response.errorBody();
    if (response.isSuccessful() && response.code() == 200) {
      mBody = response.body();
      Log.e("@@@@@", mBody.toString());
    } else {
      mErrorBody = response.errorBody();
      Log.e("@@@@@", mErrorBody.toString());
    }

    if (mBody == null && mErrorBody == null) {
      try {
        onError(NETWORK_ERROR, getServerMsg());
      } catch (IllegalStateException | NullPointerException ex) {
        ex.printStackTrace();
      }
      return;
    }

    if (mErrorBody != null && mBody == null) {
      try {
//        ResponseDTO error = new Gson().fromJson(mErrorBody.string(), ResponseDTO.class);
//        if (error.getResponseMsg() == null || error.getResponseMsg() == null) {
//          onError(NETWORK_ERROR, getServerMsg());
//          return;
//        }
        onError(String.valueOf(response.code()), String.valueOf(response.code()));
      } catch ( IllegalStateException | JsonSyntaxException e) {
        e.printStackTrace();
        onError(NETWORK_ERROR, getServerMsg());
      }
      return;
    }

    // If not success
    if (mBody != null && !mBody.getSuccess()) {
      try {
        onError(mBody.getResponseMsg(), mBody.getResponseMsg());
      } catch (IllegalStateException | NullPointerException ex) {
        ex.printStackTrace();
        onError(NETWORK_ERROR, getServerMsg());
      }
      return;
    }

    // Request success
    try {
//      mPaging = mBody.getPaging();
      onSuccess(mBody.getUser());
    } catch (IllegalStateException | NullPointerException ex) {
      ex.printStackTrace();
      onError(NETWORK_ERROR, getServerMsg());
    }
  }

  public abstract void onSuccess(UserDTO user);

  public ResponseDTO<T> getBody() {
    return mBody;
  }

  @Override
  public void onFailure(Call<ResponseDTO<T>> call, Throwable t) {
    try {
      onError(NETWORK_ERROR, getServerMsg());
    } catch (IllegalStateException | NullPointerException ex) {
      ex.printStackTrace();
    }
  }

  public abstract String getServerMsg();

  public abstract void onError(String errorCode, String errorMessage);

}