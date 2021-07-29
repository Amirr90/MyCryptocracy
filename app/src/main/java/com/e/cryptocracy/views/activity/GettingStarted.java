package com.e.cryptocracy.views.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.e.cryptocracy.R;
import com.e.cryptocracy.databinding.ActivityGettingStartedBinding;

public class GettingStarted extends AppCompatActivity {


    ActivityGettingStartedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_getting_started);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
    }

    private void initView() {
    }
}