package al.bruno.calendar.view;

import org.joda.time.DateTime;

public class LocalDateTime {
    private DateTime dateTime;
    LocalDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }
    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDate() {
        return String.valueOf(dateTime.getDayOfMonth());
    }
}
