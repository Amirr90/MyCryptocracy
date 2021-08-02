package com.e.cryptocracy.views.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.e.cryptocracy.R;
import com.e.cryptocracy.databinding.ActivityAppHomeScreenBinding;
import com.e.cryptocracy.views.utility.BottomNavigationListener;

import java.util.Objects;

public class AppHomeScreen extends AppCompatActivity {

    ActivityAppHomeScreenBinding binding;
    NavController navController;
    MenuItem itemCoinMarket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_app_home_screen);
        navController = Navigation.findNavController(this, R.id.nav_home);
        NavigationUI.setupActionBarWithNavController(this, navController);


        itemCoinMarket = binding.bottomNavigation.getMenu().getItem(0);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (null != controller.getCurrentDestination()) {
                if (controller.getCurrentDestination().getId() == R.id.coinListFragment
                        || controller.getCurrentDestination().getId() == R.id.favouriteFragment
                        || controller.getCurrentDestination().getId() == R.id.trendingCoinFragment) {
                    binding.bottomNavigation.setVisibility(View.VISIBLE);
                } else binding.bottomNavigation.setVisibility(View.GONE);

            }
        });


    }


    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Objects.requireNonNull(getSupportActionBar()).hide();
        binding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationListener(navController));
    }

}