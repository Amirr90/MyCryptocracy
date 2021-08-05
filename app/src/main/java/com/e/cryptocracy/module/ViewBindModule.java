package com.e.cryptocracy.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.e.cryptocracy.viewModal.AppViewModal;
import com.e.cryptocracy.viewModal.ViewModelProviderFactory;

import dagger.Module;

@Module
public abstract class ViewBindModule {
    abstract ViewModelProvider.Factory bindViewModel(ViewModelProviderFactory factory);

    abstract ViewModel bindAppViewModel(AppViewModal factory);
}
