package com.usu.timers;

import android.os.Handler;

public class Delay {
    private int delay;
    private Runnable internalRunnable;
    private Handler handler;
    public Delay(Runnable runnable, int delay) {
        this.delay = delay;
        this.internalRunnable = runnable;

        handler = new Handler();
    }

    public void execute() {
        handler.postDelayed(this.internalRunnable, this.delay);
    }
}
