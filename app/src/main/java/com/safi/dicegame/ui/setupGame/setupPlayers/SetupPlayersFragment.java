package com.safi.dicegame.ui.setupGame.setupPlayers;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.safi.base.BaseFragment;
import com.safi.base.BaseViewModel;
import com.safi.base.BindingAdapter;
import com.safi.dicegame.BR;
import com.safi.dicegame.R;
import com.safi.dicegame.databinding.FragmentSetupPlayersBinding;
import com.safi.dicegame.ui.model.PlayerUIModel;
import com.safi.dicegame.ui.setupGame.SetupGameViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SetupPlayersFragment extends BaseFragment<FragmentSetupPlayersBinding> {
    private SetupPlayersViewModel viewModel;
    private SetupGameViewModel sharedViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_setup_players;
    }

    @Override
    public BaseViewModel getViewModel() {
        viewModel = new ViewModelProvider(this).get(SetupPlayersViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SetupGameViewModel.class);
        return viewModel;
    }

    @Override
    public void onViewCreated(FragmentSetupPlayersBinding binding) {
        super.onViewCreated(binding);
        int numberOfPlayers = SetupPlayersFragmentArgs.fromBundle(getArguments()).getNumberOfPlayers();
        viewModel.init(numberOfPlayers);
        observePlayersUiModel(binding.rvPlayers);
        observePlayers();
    }

    private void observePlayersUiModel(RecyclerView rvPlayers) {
        viewModel.playerUiModelList.observe(getViewLifecycleOwner(), list -> {
            BindingAdapter<PlayerUIModel> adapter = new BindingAdapter<>(R.layout.item_player, list, BR.playerUIModel);
            rvPlayers.setAdapter(adapter);
        });
    }

    private void observePlayers() {
        viewModel.playersList.observe(getViewLifecycleOwner(), list -> {
            sharedViewModel.setPlayerList(list);
        });
    }
}
