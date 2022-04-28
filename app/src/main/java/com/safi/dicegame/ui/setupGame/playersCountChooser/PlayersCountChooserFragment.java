package com.safi.dicegame.ui.setupGame.playersCountChooser;

import androidx.lifecycle.ViewModelProvider;

import com.safi.base.BaseFragment;
import com.safi.base.BaseViewModel;
import com.safi.dicegame.R;
import com.safi.dicegame.databinding.FragmentPlayersCountChooserBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PlayersCountChooserFragment extends BaseFragment<FragmentPlayersCountChooserBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_players_count_chooser;
    }

    @Override
    public BaseViewModel getViewModel() {
        return new ViewModelProvider(this).get(PlayersCountChooserViewModel.class);
    }
}
