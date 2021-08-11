package com.e.cryptocracy.views.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.e.cryptocracy.OnBackButtonClickListener;
import com.e.cryptocracy.R;
import com.e.cryptocracy.databinding.ActivityAppHomeScreenBinding;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.utility.AppUtils;
import com.e.cryptocracy.utility.BottomNavigationListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

import dagger.android.support.DaggerAppCompatActivity;

public class AppHomeScreen extends DaggerAppCompatActivity implements OnBackButtonClickListener {

    ActivityAppHomeScreenBinding binding;
    NavController navController;
    MenuItem itemCoinMarket;

    public static AppHomeScreen instance;

    public static AppHomeScreen getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_app_home_screen);
        navController = Navigation.findNavController(this, R.id.nav_home);
        NavigationUI.setupActionBarWithNavController(this, navController);

        instance = this;

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


        if (AppUtils.getString(AppConstant.TOPIC_PRICE_ALERT, this).isEmpty())
            FirebaseMessaging.getInstance().subscribeToTopic("priceAlert").addOnSuccessListener(aVoid -> AppUtils.setString(AppConstant.TOPIC_PRICE_ALERT, "yes", AppHomeScreen.this));
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

    @Override
    public void onClick(View v) {
        navController.navigateUp();
        AppUtils.hideSoftKeyboard(this);
    }
}