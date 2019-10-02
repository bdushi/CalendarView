package al.bruno.calendar.view.adapter;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
/**
 * Created by bruno on 6/23/2017.
 */

public class CustomViewHolder<T, VM extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private VM binding;
    private BindingData<T, VM> bindingData;

    public CustomViewHolder(View view, BindingData<T, VM> bindingData) {
        super(view);
        binding = DataBindingUtil.bind(view);
        this.bindingData = bindingData;
    }
    public void bindData(T model) {
        bindingData.bindData(model, binding);
        binding.executePendingBindings();
    }
}
