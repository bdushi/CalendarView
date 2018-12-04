package al.bruno.calendar.view;

import android.view.View;

import org.joda.time.DateTime;

import java.util.Date;

import androidx.databinding.Observable;

public class LocalDateTime implements OnDateClickListener, Observable {
    private DateTime dateTime;
    private DateTime currentDateTime = new DateTime();
    private OnDateClickListener onDateClickListener;
    LocalDateTime(DateTime dateTime, OnDateClickListener onDateClickListener) {
        this.dateTime = dateTime;
        this.onDateClickListener = onDateClickListener;
    }
    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Date date() {
        return new Date(dateTime.getMillis());
    }

    public String getDate() {
        return String.valueOf(dateTime.getDayOfMonth());
    }

    public boolean isSunday() {
        return dateTime.getDayOfWeek() == 7;
    }

    public boolean isToday() {
        return dateTime.getDayOfMonth() == currentDateTime.getDayOfMonth() && dateTime.getMonthOfYear() == currentDateTime.getMonthOfYear() && dateTime.getYear() == currentDateTime.getYear();
    }

    public boolean isCurrentMonth() {
        return dateTime.getMonthOfYear() == currentDateTime.getMonthOfYear() && dateTime.getYear() == currentDateTime.getYear();
    }

    @Override
    public void setOnDateClickListener(View view, LocalDateTime localDateTime) {
        onDateClickListener.setOnDateClickListener(view, localDateTime);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
}
