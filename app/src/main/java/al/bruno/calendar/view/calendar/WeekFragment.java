package al.bruno.calendar.view.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.otto.Subscribe;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.util.ArrayList;
import java.util.List;

import al.bruno.calendar.view.R;
import al.bruno.calendar.view.listener.OnItemClickListener;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
/**
 * Created by bruno on 18/03/2017.
 */

public class WeekFragment extends Fragment implements OnItemClickListener<Integer>
{
    public static String DATE_KEY = "date_key";
    private WeekCalendarAdapter weekCalendarAdapter;

    private DateTime startDate;
    private DateTime endDate;

    public static DateTime selectedDateTime = new DateTime();
    public static DateTime CalendarStartDate = new DateTime();

    private boolean isVisible;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_week, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView gridView = view.findViewById(R.id.date);
        List<DateTime> days = new ArrayList<>();
        DateTime minDate = (DateTime) getArguments().getSerializable(DATE_KEY);
        if(minDate != null)
            minDate = minDate.withDayOfWeek(DateTimeConstants.THURSDAY);
        for(int i = -3; i <= 3; i++)
            days.add(minDate != null ? minDate.plusDays(i) : null);

        startDate = days.get(0);
        endDate = days.get(days.size() -1);
        weekCalendarAdapter = new WeekCalendarAdapter(getActivity(), R.layout.week_single_item, days,this );
        gridView.setLayoutManager(new GridLayoutManager(getActivity(), 7));
        gridView.setItemAnimator(new DefaultItemAnimator());
        gridView.setAdapter(weekCalendarAdapter);
       /*weekAdapter = new WeekAdapter(getActivity(), R.layout.grid_item, days);
        gridView.setAdapter(weekAdapter);
        gridView.setOnItemClickListener(this);*/
    }

    @Subscribe
    public void updateSelectedDate(Event.UpdateSelectedDateEvent event)
    {
        if (isVisible) {
            selectedDateTime = selectedDateTime.plusDays(event.getDirection());
            if (selectedDateTime.toLocalDate().equals(endDate.plusDays(1).toLocalDate())
                    || selectedDateTime.toLocalDate().equals(startDate.plusDays(-1).toLocalDate())) {
                if (!(selectedDateTime.toLocalDate().equals(startDate.plusDays(-1).toLocalDate()) &&
                        event.getDirection() == 1)
                        && !(selectedDateTime.toLocalDate().equals(endDate.plusDays(1)
                        .toLocalDate()) && event.getDirection() == -1))
                    BusProvider.getInstance().post(new Event.SetCurrentPageEvent(event.getDirection()));
            }
            BusProvider.getInstance().post(new Event.InvalidateEvent());
        }
    }

    @Subscribe
    public void invalidate(Event.InvalidateEvent event) {
        //weekCalendarAdapter.invalidateViews();
        weekCalendarAdapter.notifyDataSetChanged();
    }

    @Subscribe
    public void updateUi(Event.OnUpdateUi event)
    {
        weekCalendarAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart()
    {
        BusProvider.getInstance().register(this);
        super.onStart();
    }

    @Override
    public void onStop()
    {
        BusProvider.getInstance().unregister(this);
        super.onStop();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        isVisible = isVisibleToUser;
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onItemClick(@NonNull View view, Integer position) {
        BusProvider.getInstance().post(new Event.OnDateClickEvent(weekCalendarAdapter.getItem(position)));
        selectedDateTime = weekCalendarAdapter.getItem(position);
        BusProvider.getInstance().post(new Event.InvalidateEvent());
    }

    @Override
    public boolean onLongItemClick(@NonNull View view, Integer integer) {
        return false;
    }

    /*private class WeekAdapter extends BaseAdapter
    {
        private ArrayList<DateTime> days;
        private Context context;
        private DateTime firstDay;

        WeekAdapter(Context context, ArrayList<DateTime> days)
        {
            this.days = days;
            this.context = context;
        }

        @Override
        public int getCount() {
            return days.size();
        }

        @Override
        public DateTime getItem(int position) {
            return days.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @SuppressLint("InflateParams")
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.grid_item, null);
                firstDay = getItem(0);
            }

            DateTime dateTime = getItem(position).withMillisOfDay(0);

            TextView dayTextView = (TextView) convertView.findViewById(R.id.daytext);
            dayTextView.setText(String.valueOf(dateTime.getDayOfMonth()));

            BusProvider.getInstance().post(new Event.OnDayDecorateEvent(convertView, dayTextView,
                    dateTime, firstDay, WeekFragment.selectedDateTime));
            return convertView;
        }
    }*/
}
