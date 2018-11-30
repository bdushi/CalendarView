package al.bruno.customadapter;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
/**
 * Created by bruno on 6/23/2017.
 */

public class CustomViewHolder<T, VM extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private VM binding;
    private BindingInterface<VM, T> bindingInterface;

    public CustomViewHolder(View view, BindingInterface<VM, T> bindingInterface) {
        super(view);
        binding = DataBindingUtil.bind(view);
        this.bindingInterface = bindingInterface;
    }
    public void bindData(T model) {
        bindingInterface.bindData(binding, model);
        binding.executePendingBindings();
    }
}
