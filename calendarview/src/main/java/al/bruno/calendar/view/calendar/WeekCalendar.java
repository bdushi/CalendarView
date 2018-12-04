package al.bruno.calendar.view.calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import org.joda.time.DateTime;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import al.bruno.calendar.view.R;
import al.bruno.calendar.view.Utilities;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * Created by bruno on 17/03/2017.
 */

public class WeekCalendar extends LinearLayout
{
    private OnDateClickListener listener;
    private TypedArray typedArray;
    private GridView daysName;
    private DayDecorator dayDecorator;
    private OnWeekChangeListener onWeekChangeListener;
    private TextView month;

    public WeekCalendar(Context context)
    {
        super(context);
        init(null);
    }

    public WeekCalendar(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        init(attrs);
    }

    public WeekCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs)
    {
        if(attrs != null)
        {
            typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CalendarView);
            int selectedDateColor = typedArray.getColor(R.styleable.CalendarView_selectedBgColor, ContextCompat.getColor(getContext(), R.color.greyed_out));
            int todayDateColor = typedArray.getColor(R.styleable.CalendarView_todaysDateBgColor, ContextCompat.getColor(getContext(), R.color.greyed_out));
            int daysTextColor = typedArray.getColor(R.styleable.CalendarView_daysTextColor, Color.WHITE);
            float daysTextSize = typedArray.getColor(R.styleable.CalendarView_daysTextColor, -1);
            int todayDateTextColor = typedArray.getColor(R.styleable.CalendarView_todaysDateTextColor, ContextCompat.getColor(getContext(), R.color.design_default_color_primary));
            setDayDecorator(new DefaultDayDecorator(getContext(), selectedDateColor, todayDateColor, todayDateTextColor, daysTextColor, daysTextSize));
        }
        setOrientation(VERTICAL);
        month = new TextView(getContext());
        month.setBackgroundColor(typedArray.getColor(R.styleable.CalendarView_monthBackgroundColor, ContextCompat.getColor(getContext(), R.color.greyed_out)));
        month.setTextSize(typedArray.getDimension(R.styleable.CalendarView_monthTextSize, 20));
        month.setTextColor(typedArray.getColor(R.styleable.CalendarView_monthTextColor, Color.WHITE));

        month.setText(Utilities.month()[new DateTime().getMonthOfYear()]);
        month.setGravity(Gravity.CENTER_HORIZONTAL);
        //, new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        //addView(month, 0);
        if (!typedArray.getBoolean(R.styleable.CalendarView_hideNames, false))
        {
            daysName = getDaysNames();
            addView(daysName, 0);
        }
        WeekPager weekPager = new WeekPager(getContext(), attrs);
        addView(weekPager, 1);
    }

    @Subscribe
    public void onDateClick(Event.OnDateClickEvent event) {
        if (listener != null) {
            listener.onDateClick(event.getDateTime());
            month.setText(Utilities.month()[event.getDateTime().getMonthOfYear()]);
        }
    }

    @Subscribe
    public void onDayDecorate(Event.OnDayDecorateEvent event) {
        if (dayDecorator != null) {
            dayDecorator.decorate(event.getView(), event.getDayTextView(), event.getDateTime(), event.getFirstDay(), event.getSelectedDateTime());
        }
    }

    @Subscribe
    public void onWeekChange(Event.OnWeekChange event)
    {
        if (onWeekChangeListener != null) {
            onWeekChangeListener.onWeekChange(event.getFirstDayOfTheWeek(), event.isForward());
        }
    }

    public void setOnDateClickListener(OnDateClickListener listener)
    {
        this.listener = listener;
    }

    public void setDayDecorator(DayDecorator decorator) {
        this.dayDecorator = decorator;
    }

    public void setOnWeekChangeListener(OnWeekChangeListener onWeekChangeListener)
    {
        this.onWeekChangeListener = onWeekChangeListener;
    }

    GridView getDaysNames()
    {
        daysName = new GridView(getContext());
        daysName.setSelector(new StateListDrawable());
        daysName.setNumColumns(7);

        daysName.setAdapter(new BaseAdapter()
        {
            private String[] days = getWeekDayNames();
            @Override
            public int getCount()
            {
                return days.length;
            }

            @Override
            public String getItem(int i)
            {
                return days[i];
            }

            @Override
            public long getItemId(int i)
            {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup)
            {
                if(view == null)
                    view = LayoutInflater.from(getContext()).inflate(R.layout.week_single_item, viewGroup, false);
                TextView day = view.findViewById(R.id.date);
                day.setText(days[i]);
                if (typedArray != null) {
                    day.setTextColor(typedArray.getColor(R.styleable.CalendarView_weekTextColor, Color.WHITE));
                    day.setTextSize(TypedValue.COMPLEX_UNIT_PX, typedArray.getDimension(R.styleable.CalendarView_weekTextSize, day.getTextSize()));
                }
                return view;
            }

            private String[] getWeekDayNames()
            {
                String[] names = DateFormatSymbols.getInstance().getWeekdays();
                List<String> daysName = new ArrayList<>(Arrays.asList(names));
                daysName.remove(0);
                daysName.add(daysName.remove(0));
                if (typedArray.getInt(R.styleable.CalendarView_dayNameLength, 0) == 0)
                    for (int i = 0; i < daysName.size(); i++)
                        daysName.set(i, daysName.get(i).substring(0, 1));
                names = new String[daysName.size()];
                daysName.toArray(names);
                return names;
            }
        });
        if (typedArray != null)
            daysName.setBackgroundColor(typedArray.getColor(R.styleable
                    .CalendarView_weekBackgroundColor, ContextCompat.getColor(getContext(), R
                    .color.greyed_out)));
        return daysName;
    }

    public void updateUi()
    {
        BusProvider.getInstance().post(new Event.OnUpdateUi());
    }

    public void moveToPrevious()
    {
        BusProvider.getInstance().post(new Event.UpdateSelectedDateEvent(-1));
    }

    public void moveToNext()
    {
        BusProvider.getInstance().post(new Event.UpdateSelectedDateEvent(1));
    }

    public void reset()
    {
        BusProvider.getInstance().post(new Event.ResetEvent());
    }

    public void setSelectedDate(DateTime selectedDate)
    {
        BusProvider.getInstance().post(new Event.SetSelectedDateEvent(selectedDate));
    }

    public void setStartDate(DateTime startDate)
    {
        BusProvider.getInstance().post(new Event.SetStartDateEvent(startDate));
    }

    @Override
    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        BusProvider.getInstance().unregister(this);
        BusProvider.disposeInstance();
    }
}
