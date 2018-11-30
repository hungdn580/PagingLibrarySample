package com.an.paginglibrary.sample.notification;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * The Notification
 */

@Entity(tableName = "notification_table")
public class Notification {

  @PrimaryKey
  @ColumnInfo(name = "id")
  @SerializedName("id")
  private int mNotiId;

  @ColumnInfo(name = "type")
  @SerializedName("type")
  private int mNotificationType;

  @ColumnInfo(name = "user_id")
  @SerializedName("user_id")
  private int mUserId;

  @ColumnInfo(name = "subject")
  @SerializedName("subject")
  private String mSubject;

  @ColumnInfo(name = "content")
  @SerializedName("content")
  private String mContent;

//  @ColumnInfo(name = "addition")
  @Ignore
  @SerializedName("addition")
  private JSONObject mAddition;

  @ColumnInfo(name = "created")
  @SerializedName("created")
  private String mCreated;

  public String getCreated() {
    return mCreated;
  }

  public void setCreated(String created) {
    mCreated = created;
  }

  public int getNotiId() {
    return mNotiId;
  }

  public void setNotiId(int notiId) {
    mNotiId = notiId;
  }

  public int getNotificationType() {
    return mNotificationType;
  }

  public void setNotificationType(int notificationType) {
    mNotificationType = notificationType;
  }

  public int getUserId() {
    return mUserId;
  }

  public void setUserId(int userId) {
    mUserId = userId;
  }

  public String getSubject() {
    return mSubject;
  }

  public void setSubject(String subject) {
    mSubject = subject;
  }

  public String getContent() {
    return mContent;
  }

  public void setContent(String content) {
    mContent = content;
  }

  public JSONObject getAddition() {
    return mAddition;
  }

  public void setAddition(JSONObject addition) {
    mAddition = addition;
  }
}
