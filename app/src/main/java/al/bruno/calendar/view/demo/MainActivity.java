package al.bruno.calendar.view.demo;

import android.os.Bundle;
import android.widget.Toast;

import al.bruno.calendar.view.CalendarView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		CalendarView mCalendarView = findViewById(R.id.calendar_view);
		mCalendarView.setOnDateClickListener((view, localDateTime) -> {
			Toast.makeText(MainActivity.this, localDateTime.date().toString(), Toast.LENGTH_LONG).show();
		});
	}
}
