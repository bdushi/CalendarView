package al.bruno.calendar.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        CalendarView mCalendarView = view.findViewById(R.id.calendar_view);
        HashSet<Date> events = new HashSet<>();
        events.add(new Date());
        mCalendarView.updateCalendar(events);
        // assign event handler
        mCalendarView.setEventHandler(new CalendarView.EventHandler() {
            @Override
            public void onDateClickListener(Date date) {
                // show returned day
                DateFormat mDateFormat = SimpleDateFormat.getDateInstance();
                Toast.makeText(getActivity(), mDateFormat.format(date), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
