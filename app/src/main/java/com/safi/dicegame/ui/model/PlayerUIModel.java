package com.safi.dicegame.ui.model;

import com.safi.dicegame.data.Player;
import com.safi.dicegame.data.PlayerType;

public class PlayerUIModel {
    public String name;
    public PlayerType playerType;
    public String number;

    public PlayerUIModel(String name, PlayerType playerType, String number) {
        this.name = name;
        this.playerType = playerType;
        this.number = number;
    }

    public void onHumanClicked(boolean isChecked) {
        if (isChecked) {
            playerType = PlayerType.HUMAN;
        } else playerType = PlayerType.ROBOT;
    }

    public void onRobotClicked(boolean isChecked) {
        if (isChecked) {
            playerType = PlayerType.ROBOT;
        } else playerType = PlayerType.HUMAN;
    }

    public Player toPlayer() {
        return new Player(name, playerType, Integer.parseInt(number));
    }
}
