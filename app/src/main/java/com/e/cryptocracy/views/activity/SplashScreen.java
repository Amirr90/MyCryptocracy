package com.e.cryptocracy.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.e.cryptocracy.R;
import com.e.cryptocracy.databinding.ActivityMainBinding;
import com.e.cryptocracy.utility.Animation;

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
        runnable = () -> {
            startActivity(new Intent(SplashScreen.this, AppHomeScreen.class));
            finish();
        };
        handler.postDelayed(runnable, 3000);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (null != handler)
            handler.removeCallbacks(runnable);
    }
}