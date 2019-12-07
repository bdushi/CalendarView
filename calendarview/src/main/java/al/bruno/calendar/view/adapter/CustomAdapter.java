package al.bruno.calendar.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter<T, VM extends ViewDataBinding> extends RecyclerView.Adapter<CustomViewHolder<T, VM>> {
    private final List<T> t;
    private final int r;
    private final BindingData<T, VM> bindingData;
    public CustomAdapter(List<T> t, int r, BindingData<T, VM> bindingData) {
        this.t = t;
        this.r = r;
        this.bindingData = bindingData;
    }
    @NonNull
    @Override
    public CustomViewHolder<T, VM> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder<>(LayoutInflater.from(parent.getContext()).inflate(r, parent, false), bindingData);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder<T, VM> holder, int position) {
        holder.bindData(t.get(position));
    }

    @Override
    public int getItemCount() {
        return t.size();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return t.equals(obj);
    }
}
