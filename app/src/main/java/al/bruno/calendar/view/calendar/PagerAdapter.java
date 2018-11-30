package al.bruno.calendar.view.calendar;

import android.os.Bundle;



import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import static al.bruno.calendar.view.calendar.WeekFragment.DATE_KEY;
import static al.bruno.calendar.view.calendar.WeekPager.NUM_OF_PAGES;

/**
 * Created by bruno on 18/03/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter
{
    private static final String TAG = "PagerAdapter";
    private int currentPage = NUM_OF_PAGES / 2;
    private DateTime date;

    PagerAdapter(FragmentManager fm, DateTime date)
    {
        super(fm);
        this.date = date;
    }

    @Override
    public Fragment getItem(int position)
    {
        WeekFragment fragment = new WeekFragment();
        Bundle bundle = new Bundle();

        if(position < currentPage)
            bundle.putSerializable(DATE_KEY, getPreviousDate());
        else if(position > currentPage)
            bundle.putSerializable(DATE_KEY, getNextDate());
        else
            bundle.putSerializable(DATE_KEY, getTodayDate());
        fragment.setArguments(bundle);
        return fragment;
    }

    private DateTime getTodayDate() {
        return date;
    }

    private DateTime getPreviousDate()
    {
        return date.plusDays(-7);
    }
    private DateTime getNextDate()
    {
        return date.plusDays(7);
    }

    @Override
    public int getItemPosition(Object object)
    {
        return POSITION_NONE;
    }

    @Override
    public int getCount()
    {
        return NUM_OF_PAGES;
    }

    void swipeBack()
    {
        date = date.plusDays(-7);
        currentPage--;
        currentPage = currentPage <= 1 ? NUM_OF_PAGES / 2 : currentPage;
        BusProvider.getInstance().post(new Event.OnWeekChange(date.withDayOfWeek(DateTimeConstants.MONDAY), false));
    }
    void swipeForward()
    {
        date = date.plusDays(7);
        currentPage++;
        currentPage = currentPage >= NUM_OF_PAGES -1 ? NUM_OF_PAGES / 2 : currentPage;
        BusProvider.getInstance().post(new Event.OnWeekChange(date.withDayOfWeek(DateTimeConstants.MONDAY), true));
    }
}
