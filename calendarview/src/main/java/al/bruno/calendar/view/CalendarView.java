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
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

public class CalendarView extends LinearLayout implements OnDateClickListener{
	//event handling
	private DateTime dateTime = new DateTime();
	private OnDateClickListener onDateClickListener;

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
		calendarViewBinding.setCalendar(new Calendar(dateTime).setOnDateClickListener(this));

	}
	//@BindingAdapter(value = {"app:onDateClickListener", "app:onClickListener"}, requireAll = false)
	public void setOnDateClickListener(OnDateClickListener onDateClickListener) {
		this.onDateClickListener = onDateClickListener;
	}

	@Override
	public void setOnDateClickListener(View view, LocalDateTime localDateTime) {
		onDateClickListener.setOnDateClickListener(view, localDateTime);
	}
}
