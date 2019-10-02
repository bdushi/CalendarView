package al.bruno.calendar.view.binding.utils;

import androidx.databinding.BindingAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import al.bruno.calendar.view.adapter.CustomListAdapter;

public class Adapter {
    private static final int PREFILLED_MONTHS = 251;

    @BindingAdapter(value = {"bind:adapter", "bind:listener"}, requireAll = false)
    public static void loadPager(ViewPager2 viewPager, CustomListAdapter adapter, ViewPager2.OnPageChangeCallback callback) {
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(PREFILLED_MONTHS / 2);
        viewPager.registerOnPageChangeCallback(callback);
        //viewPager.addOnPageChangeListener(listener);
    }
}
