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
import androidx.recyclerview.widget.RecyclerView;

import com.e.cryptocracy.databinding.FilterViewBinding;
import com.e.cryptocracy.databinding.FragmentFilterListBinding;
import com.e.cryptocracy.modals.CoinCategoryModal;
import com.e.cryptocracy.modals.FilterModel;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.viewModal.AppViewModal;
import com.e.cryptocracy.viewModal.ViewModelProviderFactory;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

import static com.e.cryptocracy.utility.AppUtils.getFilterList;


public class FilterListFragment extends BottomSheetDialogFragment {


    FragmentFilterListBinding binding;

    AppViewModal appViewModal;
    @Inject
    ViewModelProviderFactory providerFactory;

    FilterAdapter filterAdapter;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilterListBinding.inflate(getLayoutInflater());
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_Demo_BottomSheetDialog);

        appViewModal = ViewModelProviders.of(this, providerFactory).get(AppViewModal.class);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (null == getArguments())
            dismiss();

      /*  binding.recFilter.addItemDecoration(new
                DividerItemDecoration(requireActivity(),
                DividerItemDecoration.VERTICAL));*/
        binding.recFilter.setItemAnimator(new DefaultItemAnimator());
        binding.recFilter.setHasFixedSize(true);
        filterAdapter = new FilterAdapter(new ArrayList<>());
        binding.recFilter.setAdapter(filterAdapter);

        if (getArguments().getString(AppConstant.KEY_FILTER).equalsIgnoreCase(getString(R.string.category))) {
            binding.progressBar.setVisibility(View.VISIBLE);
            appViewModal.getAllCategory().observe(getViewLifecycleOwner(), coinCategoryModals -> {
                List<FilterModel> list = new ArrayList<>();
                for (CoinCategoryModal modal : coinCategoryModals)
                    list.add(new FilterModel(modal.getCategory_id(), modal.getName()));
                filterAdapter.addItem(list);
                filterAdapter.notifyDataSetChanged();
                binding.progressBar.setVisibility(View.GONE);
            });
        } else {
            filterAdapter.addItem(getFilterList(getArguments().getString(AppConstant.KEY_FILTER), binding));
            filterAdapter.notifyDataSetChanged();
        }

    }


    @Override
    public void onStart() {
        super.onStart();
        updateThought();
    }

    private void updateThought() {
        String[] thoughts = requireActivity().getResources().getStringArray(R.array.tips);
        final int position = new Random().nextInt((thoughts.length - 1) + 1);
        binding.tvThought.setText(thoughts[position]);
    }

    private class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.DemoVH> {
        List<FilterModel> modelList;

        public FilterAdapter(List<FilterModel> modelList) {
            this.modelList = modelList;
        }

        @NonNull
        @Override
        public FilterAdapter.DemoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            FilterViewBinding coinViewBinding = FilterViewBinding.inflate(layoutInflater, parent, false);
            return new FilterAdapter.DemoVH(coinViewBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull FilterAdapter.DemoVH holder, int position) {
            holder.coinViewBinding.setFilterModel(modelList.get(position));
            holder.coinViewBinding.getRoot().setOnClickListener(v -> dismiss());
        }

        @Override
        public int getItemCount() {
            return modelList.size();
        }

        public class DemoVH extends RecyclerView.ViewHolder {
            FilterViewBinding coinViewBinding;

            public DemoVH(@NonNull FilterViewBinding coinViewBinding) {
                super(coinViewBinding.getRoot());
                this.coinViewBinding = coinViewBinding;
            }
        }

        public void addItem(List<FilterModel> filterModels) {
            if (null == modelList) {
                modelList = new ArrayList<>();
            }
            modelList.addAll(filterModels);
        }
    }
}