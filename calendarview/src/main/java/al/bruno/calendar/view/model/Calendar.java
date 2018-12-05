package al.bruno.calendar.view.model;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import al.bruno.calendar.view.R;
import al.bruno.calendar.view.util.Utilities;
import al.bruno.calendar.view.databinding.ControlCalendarDayBinding;
import al.bruno.calendar.view.listener.OnDateClickListener;
import al.bruno.customadapter.BindingInterface;
import al.bruno.customadapter.CustomAdapter;

public class Calendar {
    // how many days to show, defaults to six weeks, 42 days
    private final int DAYS_COUNT = 42;
    private java.util.Calendar calendar = java.util.Calendar.getInstance();
    private List<LocalDateTime> localDateTime;
    private OnDateClickListener onDateClickListener;

    /**
     * Display dates correctly in grid
     */
    public Calendar() {
        // determine the cell for current month's beginning
        calendar.set(java.util.Calendar.DAY_OF_MONTH, 1);
        int monthBeginningCell = calendar.get(java.util.Calendar.DAY_OF_WEEK) - 2;
        // move calendar backwards to the beginning of the week
        calendar.add(java.util.Calendar.DAY_OF_MONTH, -monthBeginningCell);
        localDateTime = new ArrayList<>();
        while (localDateTime.size() < DAYS_COUNT) {
            localDateTime.add(new LocalDateTime(new DateTime(calendar.getTimeInMillis()), (view, localDateTime) -> onDateClickListener.setOnDateClickListener(view, localDateTime)));
            calendar.add(java.util.Calendar.DAY_OF_MONTH, 1);
        }
    }

    public CustomAdapter adapter () {
        return new CustomAdapter<>(localDateTime, R.layout.control_calendar_day, (BindingInterface<ControlCalendarDayBinding, LocalDateTime>) ControlCalendarDayBinding::setLocalDateTime);
    }

    public String getMonth() {
        return Utilities.month()[calendar.get(java.util.Calendar.MONTH)];
    }

    public String getYear() {
        return String.format("%s", calendar.get(java.util.Calendar.YEAR));
    }


    public Calendar setOnDateClickListener(OnDateClickListener onDateClickListener) {
        this.onDateClickListener = onDateClickListener;
        return this;
    }
}
