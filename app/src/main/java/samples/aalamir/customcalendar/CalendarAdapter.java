package samples.aalamir.customcalendar;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.Date;

/**
 * Created by bruno on 10/7/2016.
 */
public class CalendarAdapter extends ArrayAdapter<Date>
{

    public CalendarAdapter(Context context, int resource) {
        super(context, resource);
    }
}