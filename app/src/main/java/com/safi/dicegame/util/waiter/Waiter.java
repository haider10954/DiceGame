package com.safi.dicegame.util.waiter;

public interface Waiter {
    void waitFor(long delayMillis, Runnable runnable);
}
