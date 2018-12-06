package al.bruno.calendar.view.binding.utils;

import al.bruno.calendar.view.adapter.MonthAdapter;
import androidx.databinding.BindingAdapter;
import androidx.viewpager.widget.ViewPager;

public class Adapter {
    @BindingAdapter(value = {"bind:adapter"}, requireAll = false)
    public static void loadPager(ViewPager viewPager, MonthAdapter monthAdapter) {
        final int PREFILLED_MONTHS = 251;
        viewPager.setAdapter(monthAdapter);
        viewPager.setCurrentItem(PREFILLED_MONTHS / 2);
    }
}
