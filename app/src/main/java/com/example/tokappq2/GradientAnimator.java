package com.example.tokappq2;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.view.View;

public class GradientAnimator {

    private ValueAnimator animator;
    private GradientDrawable gradient;
    private int[] setA;

    @SuppressLint("ClickableViewAccessibility")
    public void attach(View view, int[] setA, int[] setB, float cornerRadiusPx) {
        this.setA = setA;
        gradient = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, setA);
        gradient.setCornerRadius(cornerRadiusPx);
        view.setBackground(gradient);

        final ArgbEvaluator evaluator = new ArgbEvaluator();
        animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(150);
        animator.setRepeatCount(1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(a -> {
            float f = a.getAnimatedFraction();
            int c1 = (int) evaluator.evaluate(f, setA[0], setB[0]);
            int c2 = (int) evaluator.evaluate(f, setA[1], setB[1]);
            gradient.setColors(new int[]{c1, c2});
        });

        // One tap = one color change
        view.setOnTouchListener((v, event) -> {
            if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                play();
            }
            return false; // lets normal clicks still work
        });
    }

    private void play() {
        if (animator == null) return;
        animator.cancel();
        gradient.setColors(setA);
        animator.start();
    }

    public void stop() {
        if (animator != null) animator.cancel();
        if (gradient != null) gradient.setColors(setA);
    }
}