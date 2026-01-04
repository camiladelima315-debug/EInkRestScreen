package com.eink.restscreen;

import android.service.dreams.DreamService;
import android.os.Handler;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RestDreamService extends DreamService {

    private Handler handler = new Handler();
    private Runnable updateClock;

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setInteractive(false);
        setFullscreen(true);
        setContentView(R.layout.dream_layout);
    }

    @Override
    public void onDreamingStarted() {
        super.onDreamingStarted();

        TextView clock = findViewById(R.id.clockText);
        TextView date = findViewById(R.id.dateText);

        updateClock = () -> {
            clock.setText(
                new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date())
            );
            date.setText(
                new SimpleDateFormat("EEEE, dd MMMM", Locale.getDefault()).format(new Date())
            );
            handler.postDelayed(updateClock, 60000);
        };

        updateClock.run();
    }

    @Override
    public void onDreamingStopped() {
        super.onDreamingStopped();
        handler.removeCallbacks(updateClock);
    }
}
