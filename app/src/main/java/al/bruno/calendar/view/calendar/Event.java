package al.bruno.calendar.view.calendar;

import android.view.View;
import android.widget.TextView;

import org.joda.time.DateTime;

/**
 * Created by bruno on 18/03/2017.
 */

public class Event
{
    static class OnDateClickEvent
    {
        OnDateClickEvent(DateTime dateTime)
        {
            this.dateTime = dateTime;
        }

        private DateTime dateTime;

        public DateTime getDateTime()
        {
            return dateTime;
        }
    }

    static class InvalidateEvent
    {
    }

    static class UpdateSelectedDateEvent
    {
        UpdateSelectedDateEvent(int direction)
        {
            this.direction = direction;
        }

        int getDirection()
        {
            return direction;
        }

        private int direction;
    }

    static class SetCurrentPageEvent
    {
        int getDirection()
        {
            return direction;
        }

        SetCurrentPageEvent(int direction)
        {

            this.direction = direction;
        }

        private int direction;
    }

    static class ResetEvent
    {
    }

    static class SetSelectedDateEvent
    {
        SetSelectedDateEvent(DateTime selectedDate)
        {
            this.selectedDate = selectedDate;
        }

        DateTime getSelectedDate()
        {
            return selectedDate;
        }

        private DateTime selectedDate;
    }

    static class SetStartDateEvent
    {


        SetStartDateEvent(DateTime startDate)
        {
            this.startDate = startDate;
        }

        DateTime getStartDate()
        {
            return startDate;
        }

        private DateTime startDate;
    }

    public static class OnDayDecorateEvent
    {

        private final View view;
        private final TextView dayTextView;
        private final DateTime dateTime;
        private DateTime firstDay;
        private DateTime selectedDateTime;

        public OnDayDecorateEvent(View view, TextView dayTextView, DateTime dateTime, DateTime firstDayOfTheWeek, DateTime selectedDateTime)
        {
            this.view = view;
            this.dayTextView = dayTextView;
            this.dateTime = dateTime;
            this.firstDay = firstDayOfTheWeek;
            this.selectedDateTime = selectedDateTime;
        }

        public View getView()
        {
            return view;
        }

        TextView getDayTextView()
        {
            return dayTextView;
        }

        public DateTime getDateTime()
        {
            return dateTime;
        }

        DateTime getFirstDay()
        {
            return firstDay;
        }

        DateTime getSelectedDateTime()
        {
            return selectedDateTime;
        }
    }

    static class OnWeekChange
    {

        private final DateTime firstDayOfTheWeek;
        private final boolean forward;

        OnWeekChange(DateTime firstDayOfTheWeek, boolean isForward)
        {
            this.firstDayOfTheWeek = firstDayOfTheWeek;
            this.forward = isForward;
        }

        DateTime getFirstDayOfTheWeek()
        {
            return firstDayOfTheWeek;
        }

        boolean isForward()
        {
            return forward;
        }
    }

    public static class OnUpdateUi
    {
    }
}
