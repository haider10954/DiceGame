package com.safi.dicegame.ui.setupGame.targetChooser;

import androidx.lifecycle.ViewModelProvider;

import com.safi.base.BaseFragment;
import com.safi.base.BaseViewModel;
import com.safi.dicegame.R;
import com.safi.dicegame.databinding.FragmentTargetChooserBinding;
import com.safi.dicegame.ui.setupGame.SetupGameViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TargetChooserFragment extends BaseFragment<FragmentTargetChooserBinding> {
    private TargetChooserViewModel viewModel;
    private SetupGameViewModel sharedViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_target_chooser;
    }

    @Override
    public BaseViewModel getViewModel() {
        viewModel = new ViewModelProvider(this).get(TargetChooserViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SetupGameViewModel.class);
        return viewModel;
    }

    @Override
    public void onViewCreated(FragmentTargetChooserBinding binding) {
        super.onViewCreated(binding);
        viewModel.target.observe(getViewLifecycleOwner(), target -> sharedViewModel.setTarget(target));
    }
}
