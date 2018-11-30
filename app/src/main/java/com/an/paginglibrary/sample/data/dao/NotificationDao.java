package com.an.paginglibrary.sample.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.an.paginglibrary.sample.notification.Notification;

@Dao
public interface NotificationDao {
  @Insert
  void insert(Notification notification);

  @Update
  void update(Notification notification);

  @Delete
  void delete(Notification notification);
}
