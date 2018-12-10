package al.bruno.calendar.view.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.joda.time.LocalDate;

import al.bruno.calendar.view.CalendarView;
import al.bruno.calendar.view.demo.databinding.ActivityMainBinding;
import al.bruno.calendar.view.listener.OnDateClickListener;
import al.bruno.calendar.view.model.LocalDateTime;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
		activityMainBinding.setOnDateClickListener((view, localDateTime) -> Toast.makeText(MainActivity.this, localDateTime.date().toString(), Toast.LENGTH_SHORT).show());
		activityMainBinding.setEvent(new LocalDate[]{
				new LocalDate("2018-12-05"),
				new LocalDate("2018-12-07"),
				new LocalDate("2018-12-09"),
				new LocalDate("2018-12-19"),
				new LocalDate("2018-12-29"),
				new LocalDate("2018-12-20"),
				new LocalDate("2018-12-15"),
				new LocalDate("2018-12-10")
		});
		/*CalendarView mCalendarView = findViewById(R.id.calendar_view);
		setContentView(R.layout.activity_main);
		mCalendarView.setOnDateClickListener((view, localDateTime) -> {
			Toast.makeText(MainActivity.this, localDateTime.date().toString(), Toast.LENGTH_LONG).show();
		});
		mCalendarView.addEvent(new LocalDate[]{
				new LocalDate("2018-12-05"),
				new LocalDate("2018-12-07"),
				new LocalDate("2018-12-09"),
				new LocalDate("2018-12-19"),
				new LocalDate("2018-12-29"),
				new LocalDate("2018-12-20"),
				new LocalDate("2018-12-15"),
				new LocalDate("2018-12-10")
		});*/
	}
}
