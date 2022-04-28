package com.safi.dicegame.ui.setupGame.playersCountChooser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.safi.base.BaseViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PlayersCountChooserViewModel extends BaseViewModel {
    public final MutableLiveData<String> numberOfPlayers = new MutableLiveData<>();

    public final LiveData<Boolean> isNextButtonEnabled = Transformations.map(numberOfPlayers, input -> {
        try {
            Integer numberOfPlayers = Integer.parseInt(input);
            return numberOfPlayers > 1 && numberOfPlayers <= 6;
        } catch (Exception e) {
            return false;
        }
    });

    @Inject
    public PlayersCountChooserViewModel() {

    }

    public void onNextClicked() {
        String n = numberOfPlayers.getValue();
        if (n == null) {
            return;
        }
        try {
            navigate(PlayersCountChooserFragmentDirections.toPlayerTypesChooser(Integer.parseInt(n)));
        } catch (Exception ignored) {

        }
    }
}
