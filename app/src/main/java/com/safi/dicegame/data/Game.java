package com.safi.dicegame.data;

import java.io.Serializable;
import java.util.List;

public class Game implements Serializable {
    private final int target;
    private final List<Player> playerList;
    public int penalty;

    public Game(int target, List<Player> playerList, int penalty) {
        this.target = target;
        this.playerList = playerList;
        this.penalty = penalty;
    }

    public int getTarget() {
        return target;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public int getPenalty() {
        return penalty;
    }

    public void reset() {
        for (Player player : playerList) {
            player.score = 0;
        }
    }
}
