package com.e.cryptocracy.views.utility;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationListener implements BottomNavigationView.OnNavigationItemSelectedListener {
    NavController navController;

    public BottomNavigationListener(NavController navController) {
        this.navController = navController;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController);
    }
}
