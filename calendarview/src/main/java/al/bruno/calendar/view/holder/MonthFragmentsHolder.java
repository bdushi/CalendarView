package al.bruno.calendar.view.holder;

import android.content.Context;
import android.util.AttributeSet;

import al.bruno.calendar.view.adapter.MonthPagerAdapter;
import al.bruno.calendar.view.model.LocalDateTime;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MonthFragmentsHolder extends ViewPager {
    public MonthFragmentsHolder(@NonNull Context context,@NonNull LocalDateTime[] localDateTime) {
        super(context);
        initialize(context, localDateTime, null);
    }

    public MonthFragmentsHolder(@NonNull Context context,@NonNull LocalDateTime[] localDateTime, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context, localDateTime, attrs);
    }

    private void initialize(Context context, LocalDateTime[] localDateTime, AttributeSet attrs) {

    }
}
