package al.bruno.calendar.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import al.bruno.calendar.view.R;
import al.bruno.calendar.view.databinding.ControlCalendarDayBinding;
import al.bruno.calendar.view.databinding.FragmentMonthBinding;
import al.bruno.calendar.view.model.LocalDateTime;
import al.bruno.customadapter.CustomArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class MonthFragment extends Fragment {
    private static final String LOCAL_DATE_TIME = "LOCAL_DATE_TIME";
    public static class Builder {
        private LocalDateTime[] localDateTimes;
        public MonthFragment.Builder setLocalDateTimes(LocalDateTime[] localDateTimes) {
            this.localDateTimes = localDateTimes;
            return this;
        }
        public MonthFragment build() {
            return newInstance(localDateTimes);
        }
    }

    private static MonthFragment newInstance(LocalDateTime[] localDateTimes) {
        Bundle args = new Bundle();
        args.putParcelableArray(LOCAL_DATE_TIME, localDateTimes);
        MonthFragment fragment = new MonthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentMonthBinding fragmentMonthBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_month, container, false);
        if(getArguments() != null)
            fragmentMonthBinding.setAdapter(new CustomArrayAdapter<LocalDateTime, ControlCalendarDayBinding>((LocalDateTime[]) getArguments().getParcelableArray(LOCAL_DATE_TIME), R.layout.control_calendar_day, (localDateTime, controlCalendarDayBinding) -> controlCalendarDayBinding.setLocalDateTime(localDateTime)));
        else
            throw new IllegalArgumentException("argument is null");
        return fragmentMonthBinding.getRoot();
    }
}
