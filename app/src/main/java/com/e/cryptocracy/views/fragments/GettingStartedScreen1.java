package com.e.cryptocracy.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.e.cryptocracy.adapters.PagerAdapterGettingStarted;
import com.e.cryptocracy.databinding.FragmentGettingStartedScreen1Binding;


public class GettingStartedScreen1 extends Fragment {
    FragmentGettingStartedScreen1Binding binding;
    PagerAdapterGettingStarted adapterViewPager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGettingStartedScreen1Binding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapterViewPager = new PagerAdapterGettingStarted(requireActivity().getSupportFragmentManager());
        binding.vpPager.setAdapter(adapterViewPager);
        binding.vpPager.setCurrentItem(0);
        binding.tabLayout.setupWithViewPager(binding.vpPager, true);
    }
}