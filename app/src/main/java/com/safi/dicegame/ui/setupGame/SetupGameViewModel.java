package com.safi.dicegame.ui.setupGame;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.safi.dicegame.data.Game;
import com.safi.dicegame.data.Player;

import java.util.List;

public class SetupGameViewModel extends ViewModel {
    private final MutableLiveData<Game> _game = new MutableLiveData<>();
    final LiveData<Game> game = _game;
    private List<Player> playerList;
    private int target;

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public void setTarget(int target) {
        this.target = target;
    }
    public void setPenalty(int penalty){
        _game.postValue(new Game(target, playerList, penalty));
    }
}
