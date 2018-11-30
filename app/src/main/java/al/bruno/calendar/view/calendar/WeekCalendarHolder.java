package al.bruno.calendar.view.calendar;

import android.view.View;

import al.bruno.calendar.view.R;
import al.bruno.calendar.view.listener.OnItemClickListener;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by 1sd on 12/19/17.
 */

public class WeekCalendarHolder extends RecyclerView.ViewHolder {

    public AppCompatTextView date;

    public WeekCalendarHolder(View view, OnItemClickListener onItemClickListener) {
        super(view);
        date = view.findViewById(R.id.date);
        view.setOnClickListener(view1 -> onItemClickListener.onItemClick(view1, getAdapterPosition()));
    }
}
