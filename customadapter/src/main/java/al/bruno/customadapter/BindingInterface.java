package al.bruno.customadapter;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

/**
 * Created by bruno on 6/23/2017.
 */

public interface BindingInterface<VM extends ViewDataBinding, T> {
    void bindData(@NonNull VM vm, @NonNull T t);
}