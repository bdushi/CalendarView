package al.bruno.customadapter;

import android.view.View;

import androidx.annotation.NonNull;

public interface OnItemClickListener<T> {
    void onItemClick(@NonNull View view, T t);
    boolean onLongItemClick(@NonNull View view, T t);
}
