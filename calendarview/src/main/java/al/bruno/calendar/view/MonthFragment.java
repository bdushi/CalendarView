package al.bruno.calendar.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import al.bruno.calendar.view.databinding.FragmentMonthBinding;
import al.bruno.calendar.view.listener.OnDateClickListener;
import al.bruno.calendar.view.model.Calendar;
import al.bruno.calendar.view.model.LocalDateTime;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class MonthFragment extends Fragment {
    private Calendar calendar;
    public static class Builder {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calendar = new Calendar().setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void setOnDateClickListener(View view, LocalDateTime localDateTime) {
                //onDateClickListener.setOnDateClickListener(view, localDateTime);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentMonthBinding fragmentMonthBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.calendar_view, container, false);
        fragmentMonthBinding.setAdapter(calendar.adapter());
        return fragmentMonthBinding.getRoot();
    }
}
