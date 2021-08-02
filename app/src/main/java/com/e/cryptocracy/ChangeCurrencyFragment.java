package com.e.cryptocracy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.e.cryptocracy.adapters.CurrencyAdapter;
import com.e.cryptocracy.databinding.FragmentChangeCurrencyBinding;
import com.e.cryptocracy.modals.CurrencyModel;

import java.util.ArrayList;
import java.util.List;


public class ChangeCurrencyFragment extends Fragment {

    FragmentChangeCurrencyBinding binding;
    CurrencyAdapter currencyAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChangeCurrencyBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recCurrency.setAdapter(new CurrencyAdapter(getCurrencyList()));
    }

    private List<CurrencyModel> getCurrencyList() {
        List<CurrencyModel> currencyModels = new ArrayList<>();
        for (int a = 0; a < 100; a++) {
            currencyModels.add(new CurrencyModel("" + a, "United States Dollar", "$", "USD", (a % 5 == 0)));
        }
        return currencyModels;
    }
}