package com.safi.dicegame.util.diceRoller;

import static com.safi.dicegame.Constants.MAX_DICE_NUMBER;

import java.util.Random;

import javax.inject.Inject;

public class DiceRollerImpl implements DiceRoller {
    private final Random random = new Random();

    @Inject
    public DiceRollerImpl() {
    }

    @Override
    public int rollDice() {
        return random.nextInt(MAX_DICE_NUMBER) + 1;
    }
}
