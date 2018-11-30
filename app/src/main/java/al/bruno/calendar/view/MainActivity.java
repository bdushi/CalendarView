package al.bruno.calendar.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		CalendarView mCalendarView = findViewById(R.id.calendar_view);
		HashSet<Date> events = new HashSet<>();
		events.add(new Date());
		//mCalendarView.updateCalendar(events);
		// assign event handler
		mCalendarView.setEventHandler(new CalendarView.EventHandler() {
			@Override
			public void onDateClickListener(Date date) {
				// show returned day
				DateFormat mDateFormat = SimpleDateFormat.getDateInstance();
				Toast.makeText(MainActivity.this, mDateFormat.format(date), Toast.LENGTH_SHORT).show();
			}
		});
	}
}
