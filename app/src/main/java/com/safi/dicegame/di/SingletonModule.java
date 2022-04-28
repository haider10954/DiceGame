package com.safi.dicegame.di;

import com.safi.dicegame.util.diceRoller.DiceRoller;
import com.safi.dicegame.util.diceRoller.DiceRollerImpl;
import com.safi.dicegame.util.waiter.Waiter;
import com.safi.dicegame.util.waiter.WaiterImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
abstract public class SingletonModule {

    @Binds
    public abstract DiceRoller bindDiceRoller(DiceRollerImpl diceRoller);

    @Binds
    public abstract Waiter bindWaiter(WaiterImpl waiter);
}
