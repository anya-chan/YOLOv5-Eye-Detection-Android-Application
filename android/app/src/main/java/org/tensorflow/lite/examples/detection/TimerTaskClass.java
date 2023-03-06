package org.tensorflow.lite.examples.detection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.util.Timer;

public class TimerTaskClass {
    Timer timer = new Timer();
    public static final int TIME_INTERVAL = 10000;

    public void startTimer(final Context context) {

        Log.d("Constants", "Timer Started");
        timer.scheduleAtFixedRate(new java.util.TimerTask() {

            @SuppressLint("DefaultLocale")
            @Override
            public void run() {

                //Performing my Operations

            }
        }, 0, TIME_INTERVAL);

    }

    public void stopTimer() {
        timer.cancel();
    }
}