package com.safi.dicegame.data;

public class GameResult {
    private final Player winner;

    public GameResult(Player winner) {
        this.winner = winner;
    }

    public Player getWinner() {
        return winner;
    }
}

