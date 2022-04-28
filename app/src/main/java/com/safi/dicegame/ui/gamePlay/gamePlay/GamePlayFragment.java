package com.safi.dicegame.ui.gamePlay.gamePlay;


import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.safi.base.BaseFragment;
import com.safi.base.BaseViewModel;
import com.safi.dicegame.R;
import com.safi.dicegame.data.Game;
import com.safi.dicegame.data.GameResult;
import com.safi.dicegame.databinding.FragmentGamePlayBinding;
import com.safi.dicegame.ui.gamePlay.GamePlayActivity;
import com.safi.dicegame.ui.gamePlay.GamePlayViewModel;
import com.safi.dicegame.ui.setupGame.SetupGameActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GamePlayFragment extends BaseFragment<FragmentGamePlayBinding> {
    private GamePlayViewModel sharedViewModel;
    private com.safi.dicegame.ui.gamePlay.gamePlay.GamePlayViewModel viewModel;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_game_play;
    }

    @Override
    public BaseViewModel getViewModel() {
        sharedViewModel = new ViewModelProvider(requireActivity()).get(GamePlayViewModel.class);
        viewModel = new ViewModelProvider(this).get(com.safi.dicegame.ui.gamePlay.gamePlay.GamePlayViewModel.class);
        return viewModel;
    }

    @Override
    public void onViewCreated(FragmentGamePlayBinding binding) {
        super.onViewCreated(binding);
        sharedViewModel.game.observe(getViewLifecycleOwner(), game -> {
            viewModel.init(game);
        });
        viewModel.adapter.observe(getViewLifecycleOwner(), binding.rvPlayers::setAdapter);
        viewModel.gameResult.observe(getViewLifecycleOwner(), this::onGameResult);
    }

    private void onGameResult(GameResult gameResult) {
        Game game = sharedViewModel.game.getValue();
        if (game == null) {
            return;
        }
        if (game.getPenalty() == 0) {
            game.penalty = -1;
            game.reset();
            GamePlayActivity.startActivity(requireActivity(), game);
            requireActivity().finish();
            return;
        }
        new MaterialAlertDialogBuilder(requireActivity())
                .setTitle("Game Ended!")
                .setMessage(gameResult.getWinner().getName() + " has won!")
                .setOnDismissListener(dialogInterface -> {
                    requireActivity().finish();
                    SetupGameActivity.startActivity(requireActivity());
                })
                .create()
                .show();
    }
}
