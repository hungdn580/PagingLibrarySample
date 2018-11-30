package com.an.paginglibrary.sample.notification;

import com.google.gson.annotations.SerializedName;

public class NotificationDTO {
  @SerializedName("EmployeeNotification")
  private Notification mNotification;

  public Notification getNotification() {
    return mNotification;
  }

  public void setNotification(Notification notification) {
    mNotification = notification;
  }
}
