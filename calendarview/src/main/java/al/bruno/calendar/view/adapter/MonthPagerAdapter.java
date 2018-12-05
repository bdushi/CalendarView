package al.bruno.calendar.view.adapter;

import al.bruno.calendar.view.MonthFragment;
import al.bruno.calendar.view.model.LocalDateTime;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MonthPagerAdapter extends FragmentStatePagerAdapter {
    private LocalDateTime[] localDateTime;
    public MonthPagerAdapter(FragmentManager fm, LocalDateTime[] localDateTime) {
        super(fm);
        this.localDateTime = localDateTime;
    }

    @Override
    public Fragment getItem(int position) {
        return new MonthFragment.Builder().setLocalDateTimes(localDateTime).build();
    }

    @Override
    public int getCount() {
        return 251;
    }
}
