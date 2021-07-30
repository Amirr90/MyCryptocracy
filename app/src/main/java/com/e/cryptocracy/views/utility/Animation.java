package com.e.cryptocracy.views.utility;

import android.view.animation.AnimationUtils;

import com.e.cryptocracy.R;

public class Animation {
    public static android.view.animation.Animation bounce() {
        android.view.animation.Animation myAnim = AnimationUtils.loadAnimation(App.context, R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 15);
        myAnim.setInterpolator(interpolator);
        return myAnim;
    }

    public static android.view.animation.Animation getAnimation(int animation) {
        return AnimationUtils.loadAnimation(App.context, animation);

    }
}
