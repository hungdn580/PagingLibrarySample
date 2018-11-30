package com.an.paginglibrary.sample.notification;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.arch.paging.PagedListAdapter;
import android.support.v7.util.DiffUtil;
import android.widget.TextView;

import com.an.paginglibrary.sample.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The NotificationAdapter
 */

public class NotificationAdapter extends PagedListAdapter<NotificationDTO, NotificationAdapter.NotificationViewHolder> {
  private static DiffUtil.ItemCallback<NotificationDTO> DIFF_CALLBACK =
          new DiffUtil.ItemCallback<NotificationDTO>() {
            @Override
            public boolean areItemsTheSame(NotificationDTO oldItem, NotificationDTO newItem) {
              return oldItem.getNotification().getNotiId() == oldItem.getNotification().getNotiId();
//              return false;
            }

            @Override
            public boolean areContentsTheSame(NotificationDTO oldItem, NotificationDTO newItem) {
              return oldItem.equals(newItem);
            }
          };

  public NotificationAdapter() {
    super(DIFF_CALLBACK);
  }

  @Override
  public NotificationViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
    View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.notification_item, parent, false);
    return new NotificationViewHolder(v);
  }

  @Override
  public void onBindViewHolder(NotificationViewHolder holder, int position) {
    Notification item = Objects.requireNonNull(getItem(position)).getNotification();
    holder.bind(item, position);
  }

  class NotificationViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.notif_title)
    TextView mNotificationTitleTv;
    @BindView(R.id.notif_content)
    TextView mNotificationContentTv;
    @BindView(R.id.notif_time)
    TextView mNotificationTimeTv;
    @BindView(R.id.textViewDate)
    TextView mDateTv;

    public NotificationViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void bind(Notification item, int position) {
      mNotificationContentTv.setText(item.getContent());
      mNotificationTitleTv.setText(item.getSubject());
      if (item.getCreated() != null) {
        mNotificationTimeTv.setText(item.getCreated().substring(11, item.getCreated().length()));

        mDateTv.setText(getDayString(item.getCreated()));

        if (position == 0){
          mDateTv.setVisibility(View.VISIBLE);
        } else {
          Notification item2 = Objects.requireNonNull(getItem(position - 1)).getNotification();
          if (item2 != null && item2.getCreated() != null) {
            if (!getDayString(item2.getCreated()).equals(getDayString(item.getCreated()))){
              mDateTv.setVisibility(View.VISIBLE);
            }
            else {
              mDateTv.setVisibility(View.GONE);
            }
          }

        }
      }

    }

    String getDayString(String dateString){

      Date selectedDate = dateFromString(dateString);
      Calendar cal = Calendar.getInstance();
      cal.setTime(selectedDate);
      String dayString = cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1);
      if (compareDay(selectedDate,new Date())){
        return "Hôm nay " + dayString;
      }
      else {
        return "Ngày " + dayString;
      }
    }


    private boolean compareDay(Date date1, Date date2) {
      Calendar cal1 = Calendar.getInstance();
      cal1.setTime(date1);
      Calendar cal2 = Calendar.getInstance();
      cal2.setTime(date2);
      return  cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
              cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
              cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
              cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    private Date dateFromString(String dateString){
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date date = null;
      try {
        date = df.parse(dateString);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      return  date;
    }
  }
}
