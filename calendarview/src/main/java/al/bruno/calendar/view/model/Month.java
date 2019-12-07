package al.bruno.calendar.view.model;

import org.joda.time.DateTime;

import al.bruno.calendar.view.adapter.CustomAdapter;

public class Month {
    public final CustomAdapter days;
    public final DateTime month;

    public Month(CustomAdapter days, DateTime month) {
        this.days = days;
        this.month = month;
    }
}
