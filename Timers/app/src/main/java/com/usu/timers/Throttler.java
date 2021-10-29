package com.usu.timers;

import android.os.Handler;

public class Throttler {
    private int delay;
    private boolean isBeingThrottled = false;
    private Runnable internalRunnable;
    private Runnable externalRunnable;
    private Handler handler;
    public Throttler(Runnable runnable, int delay) {
        this.delay = delay;
        externalRunnable = runnable;
        internalRunnable = () -> {
            isBeingThrottled = false;
        };

        handler = new Handler();
    }

    public void execute() {
        if (!isBeingThrottled) {
            isBeingThrottled = true;
            externalRunnable.run();
            handler.postDelayed(this.internalRunnable, this.delay);
            // cancel current timer
            // restart it
        }
    }
}
