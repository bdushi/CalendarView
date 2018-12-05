package al.bruno.calendar.view.adapter;

import al.bruno.calendar.view.MonthFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MonthPagerAdapter extends FragmentStatePagerAdapter {
    public MonthPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new MonthFragment();
    }

    @Override
    public int getCount() {
        return 0;
    }
}
