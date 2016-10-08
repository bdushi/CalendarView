package samples.aalamir.customcalendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by bruno on 10/8/2016.
 */

public class CalendarFragment extends DialogFragment {
    CalendarView mCalendarView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        mCalendarView = ((CalendarView) view.findViewById(R.id.calendar_view));
        HashSet<Date> events = new HashSet<>();
        events.add(new Date());
        mCalendarView.updateCalendar(events);

        // assign event handler
        mCalendarView.setEventHandler(new CalendarView.EventHandler()
        {
            @Override
            public void onDateClickListener(Date date)
            {
                // show returned day
                DateFormat mDateFormat = SimpleDateFormat.getDateInstance();
                Toast.makeText(getActivity(), mDateFormat.format(date), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
