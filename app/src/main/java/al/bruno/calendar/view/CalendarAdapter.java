package al.bruno.calendar.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by bruno on 10/7/2016.
 */
class CalendarAdapter extends ArrayAdapter<Date>
{
    // days with events
    private HashSet<Date> eventDays;

    // for view inflation
    private LayoutInflater inflater;
    Context context;

    CalendarAdapter(Context context, ArrayList<Date> days, HashSet<Date> eventDays)
    {
        super(context, R.layout.control_calendar_day, days);
        this.eventDays = eventDays;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent)
    {
        // day in question
        Date date = getItem(position);
        int day = date.getDate();
        int month = date.getMonth();
        int year = date.getYear();

        // today
        Date today = new Date();

        // inflate item if it does not exist yet
        if (view == null) {
            view = inflater.inflate(R.layout.control_calendar_day, parent, false);
        }
        // if this day has an event, specify event image
        view.setBackgroundResource(0);
        if (eventDays != null)
        {
            for (Date eventDate : eventDays)
            {
                if (eventDate.getDate() == day &&
                        eventDate.getMonth() == month &&
                        eventDate.getYear() == year)
                {
                    // mark this day for event
                    //view.setBackgroundResource(R.drawable.reminder);
                    break;
                }
            }
        }

        // clear styling
        ((TextView)view).setTypeface(null, Typeface.NORMAL);
        ((TextView)view).setTextColor(Color.BLACK);

        if (month != today.getMonth() || year != today.getYear())
        {
            // if this day is outside current month, grey it out
            ((TextView)view).setTextColor(context.getResources().getColor(R.color.greyed_out));
        }
        else if (day == today.getDate())
        {
            // if it is today, set it to blue/bold
            ((TextView)view).setTypeface(null, Typeface.BOLD);
            ((TextView)view).setTextColor(context.getResources().getColor(R.color.today));
        }

        // set text
        ((TextView)view).setText(String.valueOf(date.getDate()));

        return view;
    }
}