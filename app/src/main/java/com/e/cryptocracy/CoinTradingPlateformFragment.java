package com.e.cryptocracy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.e.cryptocracy.adapters.CoinTradingListAdapter;
import com.e.cryptocracy.databinding.FragmentCoinTradingPlateformBinding;
import com.e.cryptocracy.modals.CoinTradingModel;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.viewModal.AppViewModal;
import com.e.cryptocracy.viewModal.ViewModelProviderFactory;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


public class CoinTradingPlateformFragment extends BottomSheetDialogFragment {
    private static final String TAG = "CoinTradingPlateformFra";

    FragmentCoinTradingPlateformBinding binding;
    CoinTradingListAdapter adapter;
    private String image;

    AppViewModal appViewModal;
    @Inject
    ViewModelProviderFactory providerFactory;
    String page = "1";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
    }

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
        appViewModal = ViewModelProviders.of(this, providerFactory).get(AppViewModal.class);


        adapter = new CoinTradingListAdapter();
        binding.recCoinTradingMarket.addItemDecoration(new
                DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL));
        binding.recCoinTradingMarket.setItemAnimator(new DefaultItemAnimator());
        binding.recCoinTradingMarket.setAdapter(adapter);

        fetchList();

        Log.d(TAG, "onViewCreated: " + adapter.getItemCount());
    }


    @SuppressLint("DefaultLocale")
    private void fetchList() {

        if (getArguments() == null)
            return;
        binding.setImage(getArguments().getString(AppConstant.IMAGE));
        String coinId = getArguments().getString(AppConstant.COIN_ID);
        appViewModal.coinExchangeList(coinId, page).observe(getViewLifecycleOwner(), o -> {
            binding.progressBar8.setVisibility(View.GONE);
            Gson gson = new Gson();
            String json = gson.toJson(o);
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray tickerArray = jsonObject.getJSONArray("tickers");
                binding.textView26.setText(String.format("%d Trading Pairs", tickerArray.length()));
                List<CoinTradingModel> coinTradingModels = new ArrayList<>();
                for (int a = 0; a < tickerArray.length(); a++) {
                    CoinTradingModel coinTradingModel = gson.fromJson(tickerArray.get(a).toString(), CoinTradingModel.class);
                    coinTradingModels.add(coinTradingModel);
                }
                adapter.submitList(coinTradingModels);

            } catch (JSONException e) {
                e.printStackTrace();
                dismiss();
            }


        });

    }
}