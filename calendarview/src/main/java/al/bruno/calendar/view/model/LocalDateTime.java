package al.bruno.calendar.view.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import org.joda.time.DateTime;

import java.util.Date;

import al.bruno.calendar.view.listener.OnDateClickListener;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;

public class LocalDateTime implements OnDateClickListener, Observable, Parcelable {
    private DateTime dateTime;
    private DateTime currentDateTime = new DateTime();
    private PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();

    private OnDateClickListener onDateClickListener;

    LocalDateTime(DateTime dateTime, OnDateClickListener onDateClickListener) {
        this.dateTime = dateTime;
        this.onDateClickListener = onDateClickListener;
    }

    @Bindable
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
        propertyChangeRegistry.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        propertyChangeRegistry.remove(callback);
    }

    protected LocalDateTime(Parcel in) {
        in.writeLong(dateTime.getMillis());
        in.writeLong(currentDateTime.getMillis());
    }

    public static final Creator<LocalDateTime> CREATOR = new Creator<LocalDateTime>() {
        @Override
        public LocalDateTime createFromParcel(Parcel in) {
            return new LocalDateTime(in);
        }

        @Override
        public LocalDateTime[] newArray(int size) {
            return new LocalDateTime[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dateTime = new DateTime(dest.readLong());
        currentDateTime = new DateTime(dest.readLong());
    }
}
