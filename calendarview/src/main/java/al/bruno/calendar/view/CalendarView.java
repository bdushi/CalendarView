package al.bruno.calendar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import org.joda.time.DateTime;

import al.bruno.calendar.view.databinding.CalendarViewBinding;
import al.bruno.calendar.view.listener.OnDateClickListener;
import al.bruno.calendar.view.model.Calendar;
import al.bruno.calendar.view.model.LocalDateTime;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

public class CalendarView extends LinearLayout implements OnDateClickListener{
	//event handling
	private OnDateClickListener onDateClickListener;
	private DateTime[] dateTimeEvent;

	public CalendarView(Context context) {
		super(context);
		initControl(context);
	}

	public CalendarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initControl(context);
	}

	public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initControl(context);
	}

	private void initControl(Context context) {
		CalendarViewBinding calendarViewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.calendar_view, this, true);
		calendarViewBinding.setCalendar(new Calendar(context, DateTime.now()).setOnDateClickListener(this));

	}

	@BindingAdapter(value = {"app:onDateClickListener"}, requireAll = false)
    public void setOnDateClickListener(CalendarView calendarView, OnDateClickListener onDateClickListener) {
	    calendarView.setOnDateClickListener(onDateClickListener);
    }
	public void setOnDateClickListener(OnDateClickListener onDateClickListener) {
		this.onDateClickListener = onDateClickListener;
	}

	public void setEvent(DateTime[] dateTimeEvent) {
	    this.dateTimeEvent = dateTimeEvent;
    }

	@Override
	public void setOnDateClickListener(View view, LocalDateTime localDateTime) {
		onDateClickListener.setOnDateClickListener(view, localDateTime);
	}
}
