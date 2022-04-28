package com.safi.dicegame.ui.setupGame.setupPlayers;

import static com.safi.dicegame.Constants.MAX_PLAYER_CHOSEN_NUMBER;
import static com.safi.dicegame.Constants.MIN_PLAYER_CHOSEN_NUMBER;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.safi.base.BaseViewModel;
import com.safi.dicegame.data.Player;
import com.safi.dicegame.data.PlayerType;
import com.safi.dicegame.ui.model.PlayerUIModel;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SetupPlayersViewModel extends BaseViewModel {
    private final MutableLiveData<List<PlayerUIModel>> _playerUiModelList = new MutableLiveData<>();
    final LiveData<List<PlayerUIModel>> playerUiModelList = _playerUiModelList;

    private final MutableLiveData<List<Player>> _playersList = new MutableLiveData<>();
    LiveData<List<Player>> playersList = _playersList;

    @Inject
    public SetupPlayersViewModel() {
    }

    public void init(int numberOfPlayers) {
        List<PlayerUIModel> playerUIModelList = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            PlayerUIModel playerUIModel = new PlayerUIModel("Player # " + (i + 1), PlayerType.HUMAN, String.valueOf(i + 1));
            playerUIModelList.add(playerUIModel);
        }
        _playerUiModelList.postValue(playerUIModelList);
    }

    public void onNextClicked() {
        List<PlayerUIModel> playerUIModelList = this.playerUiModelList.getValue();
        if (playerUIModelList == null) {
            return;
        }
        try {
            validate(playerUIModelList);
        } catch (Exception e) {
            showException(e);
            return;
        }
        List<Player> playerList = new ArrayList<>();
        for (PlayerUIModel playerUIModel : playerUIModelList) {
            playerList.add(playerUIModel.toPlayer());
        }
        _playersList.postValue(playerList);
        navigate(SetupPlayersFragmentDirections.toTargetChooserFragment());
    }

    private void validate(List<PlayerUIModel> players) throws Exception {
        // every player must choose a unique number
        // the number should be between 1 and 6
        List<Integer> alreadyTakenNumbers = new ArrayList<>();
        for (PlayerUIModel player : players) {
            int number;
            try {
                number = Integer.parseInt(player.number);
            } catch (Exception e) {
                throw new Exception("The number must be between " + MIN_PLAYER_CHOSEN_NUMBER + " and " + MAX_PLAYER_CHOSEN_NUMBER);
            }
            if (number < MIN_PLAYER_CHOSEN_NUMBER || number > MAX_PLAYER_CHOSEN_NUMBER) {
                throw new Exception("The number must be between " + MIN_PLAYER_CHOSEN_NUMBER + " and " + MAX_PLAYER_CHOSEN_NUMBER);
            }
            if (alreadyTakenNumbers.contains(number)) {
                throw new Exception("Every player must choose a unique number.");
            }
            alreadyTakenNumbers.add(number);
        }
    }
}
