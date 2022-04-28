package com.safi.dicegame.util.waiter;

import android.os.Handler;

import javax.inject.Inject;

public class WaiterImpl implements Waiter {
    @Inject
    public WaiterImpl() {
    }

    @Override
    public void waitFor(long delayMillis, Runnable runnable) {
        Handler handler = new Handler();
        handler.postDelayed(runnable, delayMillis);
    }
}
