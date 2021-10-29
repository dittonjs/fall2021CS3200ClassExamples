package com.usu.timers;

import android.os.Handler;

public class Debouncer {
    private int delay;
    private boolean isBeingDebounced = false;
    private Runnable internalRunnable;
    private Handler handler;
    public Debouncer(Runnable runnable, int delay) {
        this.delay = delay;
        internalRunnable = () -> {
            runnable.run();
            isBeingDebounced = false;
        };

        handler = new Handler();
    }

    public void execute() {
        if (isBeingDebounced) {
            // cancel current timer
            handler.removeCallbacks(this.internalRunnable);
            // restart it
        }
        isBeingDebounced = true;
        handler.postDelayed(this.internalRunnable, this.delay);
    }
}
