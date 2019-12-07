package al.bruno.calendar.view.model;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import al.bruno.calendar.view.R;
import al.bruno.calendar.view.adapter.BindingData;
import al.bruno.calendar.view.adapter.CustomAdapter;
import al.bruno.calendar.view.adapter.CustomListAdapter;
import al.bruno.calendar.view.databinding.ControlCalendarDayBinding;
import al.bruno.calendar.view.databinding.FragmentMonthBinding;
import al.bruno.calendar.view.listener.OnDateClickListener;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.DiffUtil;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import static al.bruno.calendar.view.util.Constants.DAYS_COUNT;
import static al.bruno.calendar.view.util.Constants.PREFILLED_MONTHS;

public class Calendar implements Observable {
    private String month;
    private String year;

    private DateTime dateTime;
    private DateTime[] dateTimes;
    private PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();
    public CustomListAdapter<Month, FragmentMonthBinding> adapter =
            new CustomListAdapter<>(R.layout.fragment_month, (customAdapter, fragmentMonthBinding) -> fragmentMonthBinding.setMonth(customAdapter), new DiffUtil.ItemCallback<Month>() {
                @Override
                public boolean areItemsTheSame(@NonNull Month oldItem, @NonNull Month newItem) {
                    return oldItem.equals(newItem);
                }
                @Override
                public boolean areContentsTheSame(@NonNull Month oldItem, @NonNull Month newItem) {
                    return oldItem.equals(newItem);
                }
            });

    /**
     * Display dates correctly in grid
     */

    public Calendar(DateTime dateTime, OnDateClickListener onDateClickListener) {
        this.dateTime = dateTime;
        setMonth(dateTime);
        setYear(dateTime);
        adapter.submitList(monthListAdapter(dateTime, onDateClickListener));
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Bindable
    public String getMonth() {
        return month;
    }

    public void setMonth(DateTime dateTime) {
        this.month = DateTimeFormat.forPattern("MMM").print(dateTime);
        propertyChangeRegistry.notifyChange(this, BR.month);
    }

    @Bindable
    public String getYear() {
        return year;
    }

    public void setYear(DateTime dateTime) {
        this.year = DateTimeFormat.forPattern("YYYY").print(dateTime);
        propertyChangeRegistry.notifyChange(this, BR.year);
    }

    private List<LocalDateTime> dateTimeList(DateTime dateTime) {
        List<LocalDateTime> localDateTime = new ArrayList<>();
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
            localDateTime.add(new LocalDateTime(curDay.withDayOfMonth(value)));
            value++;
        }
        return localDateTime;
    }
    private List<Month> monthListAdapter(DateTime dateTime, OnDateClickListener onDateClickListener) {
        List<Month> dateTimes = new ArrayList<>();
        int ii = 0;
        for (int i = -PREFILLED_MONTHS / 2; i < PREFILLED_MONTHS / 2; i++) {
            DateTime month = dateTime.plusMonths(i);
            dateTimes.add(
                    ii,
                    new Month(
                            new CustomAdapter<>(dateTimeList(month), R.layout.control_calendar_day, (BindingData<LocalDateTime, ControlCalendarDayBinding>) (localDateTime, controlCalendarDayBinding) -> {
                                controlCalendarDayBinding.setLocalDateTime(localDateTime);
                                controlCalendarDayBinding.setDateClickListener(onDateClickListener);
                            }),
                            month
                    )
            );
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

    public ViewPager2.OnPageChangeCallback onPageChangeListener = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            dateTime = adapter.getCurrentList().get(position).month;
            setMonth(dateTime);
            setYear(dateTime);

        }
    };

    public void setEvent(LocalDate[] dateTimeEvent) {

    }
}
