package al.bruno.calendar.view.calendar;

import android.view.View;
import android.widget.TextView;

import org.joda.time.DateTime;

/**
 * Created by bruno on 18/03/2017.
 */

public interface DayDecorator {
    void decorate(View view, TextView dayTextView, DateTime dateTime, DateTime firstDayOfTheWeek, DateTime selectedDateTime);
}
