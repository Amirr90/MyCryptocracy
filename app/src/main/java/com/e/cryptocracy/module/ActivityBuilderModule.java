package com.e.cryptocracy.module;


import com.e.cryptocracy.main.MainFragmentBuilderModule;
import com.e.cryptocracy.views.activity.AppHomeScreen;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = {MainFragmentBuilderModule.class, MainViewModelModules.class})
    abstract AppHomeScreen appHomeScreen();
}
