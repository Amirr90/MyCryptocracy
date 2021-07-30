package com.e.cryptocracy.views.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.e.cryptocracy.R;
import com.e.cryptocracy.databinding.ActivityAppHomeScreenBinding;
import com.e.cryptocracy.views.utility.BottomNavigationListener;

import java.util.Objects;

public class AppHomeScreen extends AppCompatActivity {

    ActivityAppHomeScreenBinding binding;
    NavController navController;
    BottomNavigationListener bottomNavigationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_app_home_screen);
        navController = Navigation.findNavController(this, R.id.nav_home);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Objects.requireNonNull(getSupportActionBar()).hide();
        binding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationListener(navController));


    }
}