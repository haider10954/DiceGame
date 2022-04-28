package com.safi.base;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BindingAdapter<T> extends RecyclerView.Adapter<BindingAdapter<T>.BindingHolder> {
    @LayoutRes
    private final int layoutId;
    private final List<T> items;
    private final int bindingVariable;

    public BindingAdapter(int layoutId, List<T> items, int bindingVariable) {
        this.layoutId = layoutId;
        this.items = items;
        this.bindingVariable = bindingVariable;
    }

    @NonNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BindingHolder(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BindingHolder holder, int position) {
        T item = items.get(position);
        holder.db.setVariable(bindingVariable, item);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    class BindingHolder extends RecyclerView.ViewHolder {
        public ViewDataBinding db;

        public BindingHolder(@NonNull View itemView) {
            super(itemView);
            db = DataBindingUtil.bind(itemView);
        }
    }
}
