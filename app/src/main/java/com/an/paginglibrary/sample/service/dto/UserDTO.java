package com.an.paginglibrary.sample.service.dto;

import com.google.gson.annotations.SerializedName;

public class UserDTO {
  @SerializedName("id")
  private int mUserID;
  @SerializedName("username")
  private String mUserName;
  @SerializedName("token")
  private String mAccessToken;
  @SerializedName("fullname")
  private String mFullName;

  public UserDTO(String token, String fullName, String userName) {
    mAccessToken = token;
    mFullName = fullName;
    mUserName = userName;
  }

  public int getUserID() {
    return mUserID;
  }

  public void setUserID(int userID) {
    mUserID = userID;
  }

  public String getUserName() {
    return mUserName;
  }

  public void setUserName(String userName) {
    mUserName = userName;
  }

  public String getAccessToken() {
    return mAccessToken;
  }

  public void setAccessToken(String accessToken) {
    mAccessToken = accessToken;
  }

  public String getFullName() {
    return mFullName;
  }

  public void setFullName(String fullName) {
    mFullName = fullName;
  }
}
