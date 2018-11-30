package al.bruno.calendar.view.calendar;

import org.joda.time.DateTime;

/**
 * Created by bruno on 18/03/2017.
 */

public interface OnWeekChangeListener {
    void onWeekChange(DateTime firstDayOfTheWeek, boolean forward);
}
