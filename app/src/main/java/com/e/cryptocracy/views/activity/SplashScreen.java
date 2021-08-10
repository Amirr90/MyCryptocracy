package com.e.cryptocracy.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.e.cryptocracy.R;
import com.e.cryptocracy.databinding.ActivityMainBinding;
import com.e.cryptocracy.utility.Animation;
import com.e.cryptocracy.utility.AppUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    ActivityMainBinding binding;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.imageView2.setAnimation(Animation.bounce());

        handler = new Handler();
        runnable = this::updateUi;
        handler.postDelayed(runnable, 3000);

    }

    private void updateUi() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (null == user) {
            //user is not logged In
            startActivity(new Intent(SplashScreen.this, GettingStarted.class));
        } else {
            //user Is logged in
            AppUtils.updateToken();
            startActivity(new Intent(SplashScreen.this, AppHomeScreen.class));
        }
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (null != handler)
            handler.removeCallbacks(runnable);
    }
}