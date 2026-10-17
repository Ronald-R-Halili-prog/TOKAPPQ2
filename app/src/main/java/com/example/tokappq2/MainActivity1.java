package com.example.tokappq2;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity1 extends AppCompatActivity {

    private final GradientAnimator animator1 = new GradientAnimator();
    private final GradientAnimator animator2 = new GradientAnimator();
    private final GradientAnimator animator3 = new GradientAnimator();

    private MediaPlayer bgMusic;
    private ImageButton btnMusic;
    private boolean musicOn = true;
    private ValueAnimator bgAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main1);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setupBackgroundGradient(findViewById(R.id.main));
        setupLogo();

        bgMusic = MediaPlayer.create(this, R.raw.bg_music);
        bgMusic.setLooping(true);
        bgMusic.setVolume(0.7f, 0.7f);

        btnMusic = findViewById(R.id.btnMusic);
        updateMusicIcon();
        btnMusic.setOnClickListener(v -> {
            bounce(v);
            musicOn = !musicOn;
            if (musicOn) {
                startMusic();
            } else {
                pauseMusic();
            }
            updateMusicIcon();
        });

        Button assignedBtn = findViewById(R.id.button4);
        Button detailsBtn = findViewById(R.id.button);
        Button completedBtn = findViewById(R.id.button3);

        float radius = 50 * getResources().getDisplayMetrics().density;
        int[] setA = {0xFFFFB6C1, 0xFFB3E5FC};
        int[] setB = {0xFFD1C4E9, 0xFFFFF59D};

        animator1.attach(assignedBtn, setA, setB, radius);
        animator2.attach(detailsBtn, setA, setB, radius);
        animator3.attach(completedBtn, setA, setB, radius);

        assignedBtn.setOnClickListener(this::bounce);
        detailsBtn.setOnClickListener(this::bounce);
        completedBtn.setOnClickListener(this::bounce);
    }

    private void setupLogo() {
        ImageView logo = findViewById(R.id.logo);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            try {
                ImageDecoder.Source source =
                        ImageDecoder.createSource(getResources(), R.drawable.logo_anim);
                Drawable drawable = ImageDecoder.decodeDrawable(source);
                logo.setImageDrawable(drawable);
                if (drawable instanceof AnimatedImageDrawable) {
                    AnimatedImageDrawable animated = (AnimatedImageDrawable) drawable;
                    animated.setRepeatCount(AnimatedImageDrawable.REPEAT_INFINITE);
                    animated.start();
                }
            } catch (IOException e) {
                logo.setImageResource(R.drawable.logo_anim);
            }
        } else {
            logo.setImageResource(R.drawable.logo_anim);
        }
        logo.setAlpha(0f);
        logo.setScaleX(0.3f);
        logo.setScaleY(0.3f);
        logo.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(900)
                .setInterpolator(new OvershootInterpolator(2f))
                .start();
    }

    private void setupBackgroundGradient(View root) {
        final int[] bgA = {0xFF0D47A1, 0xFF004D40};
        final int[] bgB = {0xFF880E4F, 0xFF4A148C};

        final GradientDrawable bg = new GradientDrawable(
                GradientDrawable.Orientation.TL_BR, bgA);
        root.setBackground(bg);

        final ArgbEvaluator evaluator = new ArgbEvaluator();
        bgAnimator = ValueAnimator.ofFloat(0f, 1f);
        bgAnimator.setDuration(4000);
        bgAnimator.setRepeatCount(ValueAnimator.INFINITE);
        bgAnimator.setRepeatMode(ValueAnimator.REVERSE);
        bgAnimator.addUpdateListener(a -> {
            float f = a.getAnimatedFraction();
            int c1 = (int) evaluator.evaluate(f, bgA[0], bgB[0]);
            int c2 = (int) evaluator.evaluate(f, bgA[1], bgB[1]);
            bg.setColors(new int[]{c1, c2});
        });
    }

    private void bounce(View view) {
        view.animate().cancel();
        view.animate()
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(80)
                .withEndAction(() -> view.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(180)
                        .setInterpolator(new OvershootInterpolator(3f))
                        .start())
                .start();
    }

    private void startMusic() {
        if (bgMusic != null && !bgMusic.isPlaying()) {
            bgMusic.start();
            bgMusic.setPlaybackParams(
                    new PlaybackParams().setSpeed(1.25f).setPitch(1.0f));
        }
    }

    private void pauseMusic() {
        if (bgMusic != null && bgMusic.isPlaying()) {
            bgMusic.pause();
        }
    }

    private void updateMusicIcon() {
        btnMusic.setImageResource(musicOn
                ? R.drawable.ic_volume_on
                : R.drawable.ic_volume_off);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (musicOn) {
            startMusic();
        }
        if (bgAnimator != null && !bgAnimator.isRunning()) {
            bgAnimator.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        pauseMusic();
        if (bgAnimator != null) {
            bgAnimator.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        animator1.stop();
        animator2.stop();
        animator3.stop();
        if (bgAnimator != null) {
            bgAnimator.cancel();
        }
        if (bgMusic != null) {
            bgMusic.release();
            bgMusic = null;
        }
    }
}