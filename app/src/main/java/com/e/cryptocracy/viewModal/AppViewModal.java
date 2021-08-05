package com.e.cryptocracy.viewModal;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.e.cryptocracy.modals.CoinModal;
import com.e.cryptocracy.repositories.ApiRepository;

import java.util.List;

import javax.inject.Inject;

public class AppViewModal extends ViewModel {
    private static final String TAG = "AppViewModal";

    @Inject
    ApiRepository apiRepository;

    @Inject
    public AppViewModal(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
        Log.d(TAG, "AppViewModal: implemented Successfully!!");
    }

    public LiveData<List<CoinModal>> getAllCoins(String page) {
        return apiRepository.getAllCoins(page);
    }

}
