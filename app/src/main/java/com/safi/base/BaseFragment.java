package com.safi.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.safi.dicegame.BR;

public abstract class BaseFragment<B extends ViewDataBinding> extends Fragment {
    private ViewDataBinding db;
    private BaseViewModel viewModel;

    @NonNull
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        db = DataBindingUtil.inflate(
                inflater, getLayoutId(), container, false);
        db.setLifecycleOwner(this);
        viewModel = getViewModel();
        db.setVariable(BR.viewModel, viewModel);
        return db.getRoot();
    }

    @Override
    public final void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        observeNavDirections(view);
        observeExceptionEvent(view);
        onViewCreated((B) db);
        super.onViewCreated(view, savedInstanceState);
    }

    private void observeExceptionEvent(View view) {
        viewModel.exceptionEvent.observe(getViewLifecycleOwner(), e -> {
            Exception exception = e.getContentIfNotHandled();
            if (exception == null || exception.getMessage() == null) {
                return;
            }
            Snackbar.make(view, exception.getMessage(), Snackbar.LENGTH_SHORT).show();
        });
    }

    private void observeNavDirections(View view) {
        viewModel.navigationEvent.observe(getViewLifecycleOwner(), (it) -> {
            NavDirections navDirections = it.getContentIfNotHandled();
            if (navDirections != null) {
                Navigation.findNavController(view).navigate(navDirections);
            }
        });
    }

    public abstract int getLayoutId();

    public void onViewCreated(B binding) {
    }

    public abstract BaseViewModel getViewModel();
}
