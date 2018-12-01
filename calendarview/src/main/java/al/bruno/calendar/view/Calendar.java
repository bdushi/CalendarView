package al.bruno.calendar.view;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import al.bruno.calendar.view.databinding.ControlCalendarDayBinding;
import al.bruno.customadapter.CustomAdapter;

public class Calendar {
    // how many days to show, defaults to six weeks, 42 days
    private final int DAYS_COUNT = 42;
    private java.util.Calendar currentDate = java.util.Calendar.getInstance();
    private List<LocalDateTime> localDateTime;
    private String month;
    private String year;
    private String date;

    public Calendar() {
        DateTime minDate = new DateTime();
        localDateTime = new ArrayList<>();
        for(int i = -3; i <= 3; i++) {
            localDateTime.add(new LocalDateTime(minDate.plusDays(i)));
        }
    }

    public CustomAdapter adapter () {
        return new CustomAdapter<LocalDateTime, ControlCalendarDayBinding>(localDateTime, R.layout.control_calendar_day, ControlCalendarDayBinding::setDateTime);
    }
}
