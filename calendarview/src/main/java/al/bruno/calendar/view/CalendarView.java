package al.bruno.calendar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import al.bruno.calendar.view.databinding.CalendarViewBinding;
import al.bruno.calendar.view.listener.OnDateClickListener;
import al.bruno.calendar.view.model.Calendar;
import al.bruno.calendar.view.model.LocalDateTime;
import androidx.databinding.DataBindingUtil;

public class CalendarView extends LinearLayout {
	//event handling
	private OnDateClickListener onDateClickListener;

	public CalendarView(Context context) {
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

		CalendarViewBinding calendarViewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.calendar_view, this, true);
		calendarViewBinding.setCalendar(new Calendar().setOnDateClickListener(new OnDateClickListener() {
			@Override
			public void setOnDateClickListener(View view, LocalDateTime localDateTime) {
				onDateClickListener.setOnDateClickListener(view, localDateTime);
			}
		}));

	}
	//@BindingAdapter(value = {"app:onDateClickListener", "app:onClickListener"}, requireAll = false)
	public void setOnDateClickListener(OnDateClickListener onDateClickListener) {
		this.onDateClickListener = onDateClickListener;
	}
}
