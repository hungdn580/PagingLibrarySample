package com.an.paginglibrary.sample.notification;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

/**
 * The Notification DataSourceFactory
 */

public class NotificationDataSourceFactory extends DataSource.Factory<Long, NotificationDTO> {

  private MutableLiveData<NotificationDataSource> itemLiveDataSource = new MutableLiveData<>();

  @Override
  public DataSource<Long, NotificationDTO> create() {
    NotificationDataSource itemDataSource = new NotificationDataSource();
    itemLiveDataSource.postValue(itemDataSource);
    return itemDataSource;
  }

  public MutableLiveData<NotificationDataSource> getItemLiveDataSource() {
    return itemLiveDataSource;
  }
}
