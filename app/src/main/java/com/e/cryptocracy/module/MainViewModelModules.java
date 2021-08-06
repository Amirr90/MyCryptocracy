package com.e.cryptocracy.module;

import androidx.lifecycle.ViewModel;

import com.e.cryptocracy.viewModal.AppViewModal;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModules {
    @Binds
    @IntoMap
    @ViewModelKey(AppViewModal.class)
    public abstract ViewModel bindViewModel(AppViewModal appViewModal);

}
