package com.an.paginglibrary.sample.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.an.paginglibrary.sample.data.dao.NotificationDao;
import com.an.paginglibrary.sample.notification.Notification;

@Database(entities = {Notification.class}, version = 1)
public abstract class NotificationRoomDatabase extends RoomDatabase {
  public abstract NotificationDao mNotificationDao();
  private static NotificationRoomDatabase INSTANCE;

  public static NotificationRoomDatabase getDatabase(final Context context) {
    if (INSTANCE == null) {
      synchronized (NotificationRoomDatabase.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context,
                  NotificationRoomDatabase.class, "notification_database")
                  // Wipes and rebuilds instead of migrating
                  // if no Migration object.
                  // Migration is not part of this practical.
                  .fallbackToDestructiveMigration()
                  .build();
        }
      }
    }
    return INSTANCE;
  }
}
