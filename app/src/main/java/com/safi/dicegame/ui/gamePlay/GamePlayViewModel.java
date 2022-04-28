package com.safi.dicegame.ui.gamePlay;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.safi.dicegame.data.Game;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class GamePlayViewModel extends ViewModel {
    private final MutableLiveData<Game> _game = new MutableLiveData<>();
    public final LiveData<Game> game = _game;

    @Inject
    public GamePlayViewModel() {
    }

    public void setGame(Game game) {
        _game.postValue(game);
    }
}
