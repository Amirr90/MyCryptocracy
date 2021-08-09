package com.e.cryptocracy;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.e.cryptocracy.adapters.CompanyAdapter;
import com.e.cryptocracy.databinding.FragmentCoinInvestorBinding;
import com.e.cryptocracy.modals.Investor;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.viewModal.AppViewModal;
import com.e.cryptocracy.viewModal.ViewModelProviderFactory;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


public class coinInvestorFragment extends BottomSheetDialogFragment {

    private static final String TAG = "coinInvestorFragment";

    FragmentCoinInvestorBinding binding;
    AppViewModal appViewModal;
    @Inject
    ViewModelProviderFactory providerFactory;
    String coinId, name;
    CompanyAdapter adapter;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCoinInvestorBinding.inflate(getLayoutInflater());
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_Demo_BottomSheetDialog);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() == null)
            return;
        coinId = getArguments().getString(AppConstant.COIN_ID);
        name = getArguments().getString(AppConstant.NAME);
        appViewModal = ViewModelProviders.of(this, providerFactory).get(AppViewModal.class);

        adapter = new CompanyAdapter();
        binding.recInvestor.addItemDecoration(new DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL));
        binding.recInvestor.setItemAnimator(new DefaultItemAnimator());
        binding.recInvestor.setAdapter(adapter);

        binding.tvTitle.setText(String.format("Investor of %s", name));
        appViewModal.coinInvestorData(coinId).observe(getViewLifecycleOwner(), o -> {
            Gson gson = new Gson();
            String json = gson.toJson(o);

            //if response in not found !!
            if (json.equals("\"\"")) {
                binding.tvNoInvestor.setVisibility(View.VISIBLE);
                binding.tvNoInvestor.setText(R.string.no_investors);
                binding.setInvestor(new Investor());
                adapter.submitList(new ArrayList<>());
            } else {
                binding.tvNoInvestor.setVisibility(View.GONE);
                Investor investor = gson.fromJson(json, Investor.class);
                binding.setInvestor(investor);
                adapter.submitList(investor.getCompanies());

            }

            binding.progressBar9.setVisibility(View.GONE);


        });

    }
}