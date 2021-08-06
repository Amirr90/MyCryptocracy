package com.e.cryptocracy;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.e.cryptocracy.adapters.CurrencyAdapter;
import com.e.cryptocracy.apiInterface.onAdapterClick;
import com.e.cryptocracy.databinding.FragmentChangeCurrencyBinding;
import com.e.cryptocracy.modals.CurrencyModel;
import com.e.cryptocracy.utility.App;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.utility.AppUtils;
import com.e.cryptocracy.viewModal.AppViewModal;
import com.e.cryptocracy.viewModal.ViewModelProviderFactory;
import com.e.cryptocracy.views.activity.AppHomeScreen;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class ChangeCurrencyFragment extends DaggerFragment implements onAdapterClick {

    FragmentChangeCurrencyBinding binding;

    AppViewModal appViewModal;
    @Inject
    ViewModelProviderFactory providerFactory;
    CurrencyAdapter currencyAdapter;
    List<CurrencyModel> currencyModelList;
    NavController navController;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChangeCurrencyBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        appViewModal = ViewModelProviders.of(this, providerFactory).get(AppViewModal.class);

        currencyAdapter = new CurrencyAdapter(this);
        binding.recCurrency.setAdapter(currencyAdapter);

        binding.imageView5.setOnClickListener(AppHomeScreen.getInstance());


        appViewModal.getAllCurrency().observe(getViewLifecycleOwner(), currencyModels -> {
            currencyModelList = currencyModels;
            addCurrency(currencyModels);

            binding.progressBar3.setVisibility(View.GONE);
        });

        binding.etSearchCurrency.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (null != s) {
                    filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void addCurrency(List<CurrencyModel> currencyModels) {
        List<CurrencyModel> currencyModels1 = new ArrayList<>();
        for (CurrencyModel model : currencyModels) {
            currencyModels1.add(new CurrencyModel(
                    model.getId(),
                    model.getName(),
                    model.getSign(),
                    model.getSymbol(),
                    AppUtils.getString(AppConstant.CURRENCY, App.context).contentEquals(model.getSymbol())));
        }

        currencyAdapter.submitList(currencyModels1);
    }

    private void filter(String toString) {

        if (null != currencyModelList) {
            List<CurrencyModel> filterList = new ArrayList<>();
            for (CurrencyModel model : currencyModelList) {
                if (model.getName().toLowerCase().contains(toString.toLowerCase()) || model.getSymbol().toLowerCase().contains(toString.toLowerCase()))
                    filterList.add(model);
            }

            addCurrency(filterList);

        }

    }

    @Override
    public void onClickItem(Object obj) {
        AppUtils.setString(AppConstant.CURRENCY, (String) obj, requireActivity());
        navController.navigateUp();
    }
}