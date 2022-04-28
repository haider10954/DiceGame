package com.safi.dicegame.data;

import java.io.Serializable;

public class Player implements Serializable {
    private final String name;
    private final PlayerType playerType;
    private final int number;
    public int score = 0;

    public Player(String name, PlayerType playerType, int number) {
        this.name = name;
        this.playerType = playerType;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public int getNumber() {
        return number;
    }
}
