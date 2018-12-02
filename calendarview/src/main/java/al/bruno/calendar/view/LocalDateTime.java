package al.bruno.calendar.view;

import org.joda.time.DateTime;

import java.util.Date;

public class LocalDateTime {
    private DateTime dateTime;
    private DateTime currentDateTime = new DateTime();
    LocalDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
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
}
