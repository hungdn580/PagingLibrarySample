package com.an.paginglibrary.sample.data.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.an.paginglibrary.sample.data.dao.NotificationDao;
import com.an.paginglibrary.sample.data.db.NotificationRoomDatabase;
import com.an.paginglibrary.sample.notification.Notification;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NotificationRepository {

  private NotificationDao mNotificationDao;
  private LiveData<List<Notification>> mNotifications;
  private Executor mExecutor;

  public NotificationRoomDatabase mNotificationRoomDatabase;

  public NotificationRepository(Application application) {
    mNotificationRoomDatabase = NotificationRoomDatabase.getDatabase(application);
    mExecutor = Executors.newSingleThreadExecutor();
    mNotificationDao = mNotificationRoomDatabase.mNotificationDao();
//    mNotifications = mNotificationDao.get();
  }

  public LiveData<List<Notification>> getNotifications() {
    return mNotifications;
  }

}
