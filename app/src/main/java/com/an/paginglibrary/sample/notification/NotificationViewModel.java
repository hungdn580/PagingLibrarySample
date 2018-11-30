package com.an.paginglibrary.sample.notification;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * The NotificationViewModel
 */

public class NotificationViewModel extends ViewModel {

  private LiveData<PagedList<NotificationDTO>> articleLiveData;
  private NotificationDataSourceFactory notificationDataSourceFactory;

  public NotificationViewModel() {
    init();
  }

  private void init() {
    Executor executor = Executors.newFixedThreadPool(5);

    notificationDataSourceFactory = new NotificationDataSourceFactory();

    PagedList.Config pagedListConfig =
            (new PagedList.Config.Builder())
                    .setEnablePlaceholders(true)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5).build();

    articleLiveData = (new LivePagedListBuilder(notificationDataSourceFactory, pagedListConfig))
            .setFetchExecutor(executor)
            .build();
  }

  public LiveData<PagedList<NotificationDTO>> getArticleLiveData() {
    return articleLiveData;
  }

  public void refreshData() {
    Objects.requireNonNull(notificationDataSourceFactory.getItemLiveDataSource().getValue()).invalidate();
  }
}
