package com.example.rhg.outsourcing.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by whiskeyfei on 15-7-24.
 */
public class AnimationUtils {
    public static void alphaAnimation(final View view, float start, float end, long time) {
        view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        ObjectAnimator oa = ObjectAnimator.ofFloat(view, "alpha", start, end);
        oa.setDuration(time);
        oa.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setLayerType(View.LAYER_TYPE_NONE, null);
            }
        });
        oa.start();
    }
}
