package com.safi.dicegame.ui.setupGame.gameTypeChooser;

import androidx.lifecycle.ViewModelProvider;

import com.safi.base.BaseFragment;
import com.safi.base.BaseViewModel;
import com.safi.dicegame.R;
import com.safi.dicegame.databinding.FragmentGameTypeChooserBinding;
import com.safi.dicegame.ui.setupGame.SetupGameViewModel;

public class GameTypeChooserFragment extends BaseFragment<FragmentGameTypeChooserBinding> {
    private SetupGameViewModel sharedViewModel;
    private GameTypeChooserViewModel viewModel;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_game_type_chooser;
    }

    @Override
    public BaseViewModel getViewModel() {
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SetupGameViewModel.class);
        return viewModel = new ViewModelProvider(this).get(GameTypeChooserViewModel.class);
    }

    @Override
    public void onViewCreated(FragmentGameTypeChooserBinding binding) {
        super.onViewCreated(binding);
        viewModel.penalty.observe(getViewLifecycleOwner(), penalty -> sharedViewModel.setPenalty(penalty));
    }
}
