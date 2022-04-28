package com.safi.dicegame.ui.gamePlay.gamePlay;

import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.safi.base.BaseViewModel;
import com.safi.base.BindingAdapter;
import com.safi.dicegame.util.diceRoller.DiceRoller;
import com.safi.dicegame.R;
import com.safi.dicegame.data.Game;
import com.safi.dicegame.data.GameResult;
import com.safi.dicegame.data.Player;
import com.safi.dicegame.data.PlayerType;
import com.safi.dicegame.util.waiter.Waiter;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class GamePlayViewModel extends BaseViewModel {
    private Game game;
    private final DiceRoller diceRoller;
    private final Waiter waiter;

    private final MutableLiveData<BindingAdapter<Player>> _adapter = new MutableLiveData<>();
    public final LiveData<BindingAdapter<Player>> adapter = _adapter;

    private final MutableLiveData<Player> currentTurn = new MutableLiveData<>();
    public final LiveData<String> title = Transformations.map(currentTurn, player -> "Current Turn: " + player.getName());

    public final LiveData<Boolean> isCurrentPlayerHuman = Transformations.map(currentTurn, player -> player.getPlayerType() == PlayerType.HUMAN);
    private final MutableLiveData<Integer> _diceResult = new MutableLiveData<>();
    public final LiveData<String> diceResult = Transformations.map(_diceResult, result -> "Dice Result: " + result);

    private final MutableLiveData<GameResult> _gameResult = new MutableLiveData<>();
    public final LiveData<GameResult> gameResult = _gameResult;

    public final LiveData<String> rollDiceText = Transformations.map(isCurrentPlayerHuman, isHuman -> {
        if (isHuman) {
            return "Roll Dice";
        } else return "Robot is thinking!";
    });

    @Inject
    public GamePlayViewModel(DiceRoller diceRoller, Waiter waiter) {
        this.diceRoller = diceRoller;
        this.waiter = waiter;
    }

    public void init(Game game) {
        this.game = game;
        BindingAdapter<Player> adapter = new BindingAdapter<>(R.layout.item_gameplay_player, game.getPlayerList(), BR.player);
        _adapter.postValue(adapter);
        startGame();
    }

    private void startGame() {
        changeTurn();
    }

    public void onRollDiceClicked() {
        onDiceResult(diceRoller.rollDice());
    }


    private void onDiceResult(int diceResult) {
        _diceResult.postValue(diceResult);
        for (Player player : game.getPlayerList()) {
            if (player.getNumber() == diceResult) {
                updateScore(player);
                return;
            }
        }
        // no one's number matched -> need to apply penalty now
        applyPenalty();
        changeTurn();
    }

    private void applyPenalty() {
        Player player = currentTurn.getValue();
        if (player == null || adapter.getValue() == null) {
            return;
        }
        player.score += game.getPenalty();
        adapter.getValue().notifyItemChanged(game.getPlayerList().indexOf(player));
    }

    private void updateScore(Player player) {
        player.score++;
        if (adapter.getValue() == null) {
            return;
        }
        adapter.getValue().notifyItemChanged(game.getPlayerList().indexOf(player));
        checkGameResult(player);
    }

    private void checkGameResult(Player player) {
        if (player.score >= game.getTarget()) {
            _gameResult.postValue(new GameResult(player));
        } else changeTurn();
    }

    private void changeTurn() {
        Player currentPlayer = currentTurn.getValue();
        if (currentPlayer == null) {
            // the game has just begun
            afterTurnChanged(game.getPlayerList().get(0));
            return;
        }
        int index = game.getPlayerList().indexOf(currentPlayer);
        if (index >= (game.getPlayerList().size() - 1)) {
            index = 0;
        } else index++;
        afterTurnChanged(game.getPlayerList().get(index));
    }

    private void afterTurnChanged(Player player) {
        currentTurn.postValue(player);
        if (player.getPlayerType() == PlayerType.ROBOT) {
            // wait for 1 second
            waiter.waitFor(1000L, this::onRollDiceClicked);
        }
    }
}
