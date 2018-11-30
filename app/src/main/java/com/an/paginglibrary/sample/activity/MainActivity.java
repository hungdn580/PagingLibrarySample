package com.an.paginglibrary.sample.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.an.paginglibrary.sample.R;
import com.an.paginglibrary.sample.notification.NotificationAdapter;
import com.an.paginglibrary.sample.notification.NotificationViewModel;

public class MainActivity extends AppCompatActivity {

    private NotificationAdapter mNotificationAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        /*
         * Step 2: Initialize the ViewModel
         *
         * */
        NotificationViewModel notificationViewModel = new NotificationViewModel();

        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        mNotificationAdapter = new NotificationAdapter();
        RecyclerView notificationRv = findViewById(R.id.notification_rv);

        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            notificationViewModel.refreshData();
            mSwipeRefreshLayout.setRefreshing(false);
        });

        notificationRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        notificationRv.setAdapter(mNotificationAdapter);

        /*
         * Step 4: When a new page is available, we call submitList() method
         * of the PagedListAdapter class
         *
         * */
        notificationViewModel.getArticleLiveData().observe(this, pagedList -> mNotificationAdapter.submitList(pagedList));

//        /*
//         * Step 5: When a new page is available, we call submitList() method
//         * of the PagedListAdapter class
//         *
//         * */
//        mNotificationAdapter.getNetworkState().observe(this, networkState -> {
//            adapter.setNetworkState(networkState);
//        });
    }
}
