package com.an.paginglibrary.sample.service.callback;

import android.content.Context;

import com.an.paginglibrary.sample.service.dto.UserDTO;
import com.an.paginglibrary.sample.utils.DialogUtils;

/**
 * Common callback for APIs
 * Created by hungdn on 11/7/2016.
 */

public class CommonCallback<T> extends BaseCallback<T> {
  private Context mContext;

  public CommonCallback(Context context) {
    mContext = context;
  }

  @Override
  public void onSuccess(UserDTO user) {

  }

  @Override
  public void onError(String errorCode, String errorMessage) {
    DialogUtils.showAlert(mContext, errorMessage);
  }

  @Override
  public String getServerMsg() {
    return "";
  }
}
