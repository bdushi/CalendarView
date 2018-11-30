package al.bruno.calendar.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import al.bruno.calendar.view.databinding.ControlCalendarBinding;
import al.bruno.calendar.view.databinding.ControlCalendarDayBinding;
import al.bruno.calendar.view.databinding.WeekSingleItemBinding;
import al.bruno.customadapter.BindingInterface;
import al.bruno.customadapter.CustomAdapter;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by a7med on 28/06/2015.
 */
public class CalendarView extends LinearLayout {
	private ControlCalendarBinding controlCalendarBinding;
	// for logging
	private static final String LOGTAG = "Calendar View";

	// how many days to show, defaults to six weeks, 42 days
	private static final int DAYS_COUNT = 42;

	// default date format
	private static final String DATE_FORMAT = "MMM yyyy";

	// date format
	private String dateFormat;

	// current displayed month
	private Calendar currentDate = Calendar.getInstance();

	//event handling
	private EventHandler eventHandler;

	// seasons' rainbow
	int[] rainbow = new int[] {
			R.color.summer,
			R.color.fall,
			R.color.winter,
			R.color.spring
	};

	// month-season association (northern hemisphere, sorry australia :)
	int[] monthSeason = new int[] {2, 2, 3, 3, 3, 0, 0, 0, 1, 1, 1, 2};

	public CalendarView(Context context)
	{
		super(context);
	}

	public CalendarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initControl(context, attrs);
	}

	public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initControl(context, attrs);
	}

	private void initControl(Context context, AttributeSet attrs) {
		//LayoutInflater.from(context).inflate(R.layout.control_calendar, this);

		/*LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.control_calendar, this);*/
		controlCalendarBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.control_calendar, this, true);

		//grid.setAdapter(new CalendarAdapter(getContext(), cells, events));
		loadDateFormat(attrs);
		assignClickHandlers();

		updateCalendar();
	}

	private void loadDateFormat(AttributeSet attrs) {
		TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CalendarView);
		try {
			// try to load provided date format, and fallback to default otherwise
			dateFormat = ta.getString(R.styleable.CalendarView_dateFormat);
			if (dateFormat == null)
				dateFormat = DATE_FORMAT;
		} finally {
			ta.recycle();
		}
	}

	private void assignClickHandlers()
	{
		// add one month and refresh UI
		/*btnNext.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				currentDate.add(Calendar.MONTH, 1);
				updateCalendar();
			}
		});*/

		// subtract one month and refresh UI
		/*btnPrev.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				currentDate.add(Calendar.MONTH, -1);
				updateCalendar();
			}
		});*/

		/*grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				eventHandler.onDateClickListener((Date) adapterView.getItemAtPosition(i));
			}
		});*/
	}

	/**
	 * Display dates correctly in grid
	 */
	public void updateCalendar() {
		List<LocalDateTime> dateTimes = new ArrayList<>();
		Calendar calendar = (Calendar)currentDate.clone();

		// determine the cell for current month's beginning
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;

		// move calendar backwards to the beginning of the week
		calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

		// fill cells
		while (dateTimes.size() < DAYS_COUNT) {
			dateTimes.add(new LocalDateTime(new DateTime(calendar.getTimeInMillis())));
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}

		// update grid
		//grid.setAdapter(new CalendarAdapter(getContext(), cells, events));
		controlCalendarBinding.setAdapter(new CustomAdapter<LocalDateTime, ControlCalendarDayBinding>(dateTimes, R.layout.control_calendar_day, ControlCalendarDayBinding::setDateTime));

		// update title
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		//txtDate.setText(sdf.format(currentDate.getTime()));

		// set header color according to current season
		int month = currentDate.get(Calendar.MONTH);
		int season = monthSeason[month];
		int color = rainbow[season];
	}
	/**
	 * Assign event handler to be passed needed events
	 */
	public void setEventHandler(EventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}

	/**
	 * This interface defines what events to be reported to
	 * the outside world
	 */
	public interface EventHandler
	{
		void onDateClickListener(Date date);
	}
}
