package al.bruno.calendar.view.model;

import android.view.LayoutInflater;
import org.joda.time.DateTime;

import al.bruno.calendar.view.BR;
import al.bruno.calendar.view.R;
import al.bruno.calendar.view.adapter.MonthAdapter;
import al.bruno.calendar.view.databinding.FragmentMonthBinding;
import al.bruno.calendar.view.util.Utilities;
import al.bruno.calendar.view.listener.OnDateClickListener;
import al.bruno.calendar.view.databinding.ControlCalendarDayBinding;
import al.bruno.customadapter.CustomArrayAdapter;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;

public class Calendar implements Observable {
    private final int DAYS_COUNT = 42;
    private final int PREFILLED_MONTHS = 251;
    private DateTime dateTime = new DateTime();
    private DateTime[] dateTimes = months();
    private OnDateClickListener onDateClickListener;
    private MonthAdapter monthAdapter;
    private PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();
    /**
     * Display dates correctly in grid
     */
    public Calendar() {
        monthAdapter = new MonthAdapter((viewGroup, position) -> {
            setDateTime(dateTimes[position]);
            FragmentMonthBinding fragmentMonth = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.fragment_month, viewGroup, false);
            fragmentMonth.setAdapter(new CustomArrayAdapter<LocalDateTime, ControlCalendarDayBinding>(dateTime(getDateTime()), R.layout.control_calendar_day, (localDateTime, controlCalendarDayBinding) -> controlCalendarDayBinding.setLocalDateTime(localDateTime)));
            return fragmentMonth.getRoot();
        }, dateTimes.length);
    }

    public MonthAdapter getMonthAdapter() {
        return monthAdapter;
    }

    @Bindable
    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
        propertyChangeRegistry.notifyChange(this, BR.dateTime);
    }
    public String getMonth() {
        return Utilities.month()[dateTime.getMonthOfYear()];
    }

    public String getYear() {
        return String.format("%s", dateTime.getYear());
    }


    public Calendar setOnDateClickListener(OnDateClickListener onDateClickListener) {
        this.onDateClickListener = onDateClickListener;
        return this;
    }
    private LocalDateTime[] calendar() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        LocalDateTime[] localDateTime = new LocalDateTime[DAYS_COUNT];
        // determine the cell for current month's beginning
        calendar.set(java.util.Calendar.DAY_OF_MONTH, 1);
        int monthBeginningCell = calendar.get(java.util.Calendar.DAY_OF_WEEK) - 2;
        // move calendar backwards to the beginning of the week
        calendar.add(java.util.Calendar.DAY_OF_MONTH, -monthBeginningCell);
        // how many days to show, defaults to six weeks, 42 days
        for (int i = 0; i < DAYS_COUNT; i++) {
            localDateTime[i] = new LocalDateTime(new DateTime(calendar.getTimeInMillis()), (view, ld) -> onDateClickListener.setOnDateClickListener(view, ld));
            calendar.add(java.util.Calendar.DAY_OF_MONTH, 1);
        }
        return localDateTime;
    }
    private LocalDateTime[] dateTime(DateTime dateTime) {
        LocalDateTime[] localDateTime = new LocalDateTime[DAYS_COUNT];
        int currMonthDays = dateTime.dayOfMonth().getMaximumValue();
        int firstDayIndex = dateTime.withDayOfMonth(1).getDayOfWeek();
        int prevMonthDays = dateTime.minusMonths(1).dayOfMonth().getMaximumValue();
        int value = prevMonthDays - (firstDayIndex -= 1) + 1;
        DateTime curDay = dateTime;
        for (int i = 0; i < DAYS_COUNT; i++) {
            if(i < firstDayIndex) {
                    curDay = dateTime.withDayOfMonth(1).minusMonths(1);
            } else if(i == firstDayIndex) {
                    value = 1;
                    curDay = dateTime;
            } else if( value == currMonthDays + 1) {
                    value = 1;
                    curDay = dateTime.withDayOfMonth(1).plusMonths(1);
            }
            localDateTime[i] = new LocalDateTime(curDay.withDayOfMonth(value), (view, ld) -> onDateClickListener.setOnDateClickListener(view, ld));
            value++;
        }
        return localDateTime;
    }

    private DateTime[] months() {
        DateTime[] dateTimes = new DateTime[251];
        int ii = 0;
        for (int i = -PREFILLED_MONTHS / 2; i < PREFILLED_MONTHS / 2; i++) {
            dateTimes[ii] = dateTime.plusMonths(i);
            ii++;
        }
        return dateTimes;
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        propertyChangeRegistry.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        propertyChangeRegistry.remove(callback);
    }
}
