package al.bruno.calendar.view.holder;

import android.content.Context;
import android.util.AttributeSet;

import al.bruno.calendar.view.adapter.MonthPagerAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MonthFragmentsHolder extends ViewPager {
    public MonthFragmentsHolder(@NonNull Context context) {
        super(context);
        initialize(context, null);
    }

    public MonthFragmentsHolder(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    private void initialize(Context context, AttributeSet attrs) {
        new MonthPagerAdapter(((AppCompatActivity) context).getSupportFragmentManager());
    }
}
