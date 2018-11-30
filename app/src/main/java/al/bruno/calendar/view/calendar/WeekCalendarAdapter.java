package al.bruno.calendar.view.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.joda.time.DateTime;

import java.util.List;
import al.bruno.customadapter.OnItemClickListener;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by 1sd on 12/19/17.
 */

public class WeekCalendarAdapter extends RecyclerView.Adapter<WeekCalendarHolder> {
    private List<DateTime> days;
    private Context context;
    private int resource;
    private OnItemClickListener onItemClickListener;
    private View view;

    public WeekCalendarAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<DateTime> days, OnItemClickListener onItemClickListener) {
        this.days = days;
        this.context = context;
        this.resource = resource;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public WeekCalendarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(resource, parent, false);
        return new WeekCalendarHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekCalendarHolder holder, int position) {
        DateTime dateTime = days.get(position);
        holder.date.setText(String.valueOf(dateTime.getDayOfMonth()));
        BusProvider.getInstance().post(new Event.OnDayDecorateEvent(view, holder.date, dateTime, getItem(0), WeekFragment.selectedDateTime));
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    DateTime getItem(int i)
    {
        return days.get(i);
    }
}
