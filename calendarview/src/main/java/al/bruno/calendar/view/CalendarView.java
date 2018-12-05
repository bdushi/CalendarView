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

	private final int PREFILLED_MONTHS = 251;
	private DateTime[] dateTimes;
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
		dateTimes = months();
		CalendarViewBinding calendarViewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.calendar_view, this, true);
		Calendar calendar = new Calendar(context, dateTimes[PREFILLED_MONTHS / 2]).setOnDateClickListener(this);
		calendarViewBinding.setCalendar(calendar);
		calendarViewBinding.monthPager.setCurrentItem(PREFILLED_MONTHS / 2);
		calendarViewBinding.monthPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}

			@Override
			public void onPageSelected(int position) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {
                calendar.setDateTime(dateTimes[state]);
			}
		});

	}
	//@BindingAdapter(value = {"app:onDateClickListener", "app:onClickListener"}, requireAll = false)
	public void setOnDateClickListener(OnDateClickListener onDateClickListener) {
		this.onDateClickListener = onDateClickListener;
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
	public void setOnDateClickListener(View view, LocalDateTime localDateTime) {
		onDateClickListener.setOnDateClickListener(view, localDateTime);
	}
}
