package com.e.cryptocracy;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.e.cryptocracy.adapters.CoinTradingListAdapter;
import com.e.cryptocracy.databinding.FragmentCoinTradingPlateformBinding;
import com.e.cryptocracy.modals.CoinTradingModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;


public class CoinTradingPlateformFragment extends BottomSheetDialogFragment {
    private static final String TAG = "CoinTradingPlateformFra";

    FragmentCoinTradingPlateformBinding binding;
    CoinTradingListAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCoinTradingPlateformBinding.inflate(getLayoutInflater());
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_Demo_BottomSheetDialog);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new CoinTradingListAdapter();
        binding.recCoinTradingMarket.addItemDecoration(new
                DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL));
        binding.recCoinTradingMarket.setItemAnimator(new DefaultItemAnimator());
        binding.recCoinTradingMarket.setHasFixedSize(true);
        binding.recCoinTradingMarket.setAdapter(adapter);
        adapter.submitList(getList());

        Log.d(TAG, "onViewCreated: " + adapter.getItemCount());
    }


    private List<CoinTradingModel> getList() {
        List<CoinTradingModel> tradingModels = new ArrayList<>();
        for (int a = 0; a < 100; a++) {
            tradingModels.add(new CoinTradingModel(String.valueOf(a)));
        }
        return tradingModels;
    }
}