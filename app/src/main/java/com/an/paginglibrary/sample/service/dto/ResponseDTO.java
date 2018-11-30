package com.an.paginglibrary.sample.service.dto;

import com.an.paginglibrary.sample.utils.StringUtils;
import com.google.gson.annotations.SerializedName;

/**
 * Common Response DTO for all API requests
 * Created by hungdn on 26/4/2018.
 */

public class ResponseDTO<T> {

  @SerializedName("message")
  private String message = "";
  @SerializedName("msg")
  private String msg = "";
  @SerializedName("data")
  private T data = null;
  private boolean msuccess;
  @SerializedName("count")
  private int count;
  @SerializedName("token")
  private String mToken;
  @SerializedName("fullname")
  private String mFullName;
  @SerializedName("username")
  private String mUserName;

  public UserDTO getUser() {
    return new UserDTO(mToken, mFullName, mUserName);
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public boolean isSuccess() {
    return msuccess;
  }

  public String getToken() {
    return mToken;
  }

  public void setToken(String token) {
    mToken = token;
  }

  public String getFullName() {
    return mFullName;
  }

  public void setFullName(String fullName) {
    mFullName = fullName;
  }

  public String getUserName() {
    return mUserName;
  }

  public void setUserName(String userName) {
    mUserName = userName;
  }

  /**
   *
   * @return
   * The message
   */
  public String getMessage() {
    return message;
  }

  public String getResponseMsg() {
      return StringUtils.isEmpty(message) ? msg : message;
  }

  /**
   *
   * @param message
   * The message
   */
  public void setMessage(String message) {
    this.message = message;
  }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean getSuccess() {
        return msuccess;
    }

    public void setSuccess(boolean success) {
        this.msuccess = success;
    }

    /**
   *
   * @return
   * The data
   */
  public T getData() {
    return data;
  }

  /**
   *
   * @param data
   * The data
   */
  public void setData(T data) {
    this.data = data;
  }

}
