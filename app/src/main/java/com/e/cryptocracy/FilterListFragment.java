package com.e.cryptocracy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.e.cryptocracy.databinding.FilterViewBinding;
import com.e.cryptocracy.databinding.FragmentFilterListBinding;
import com.e.cryptocracy.modals.FilterModel;
import com.e.cryptocracy.views.utility.AppConstant;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;


public class FilterListFragment extends BottomSheetDialogFragment {


    FragmentFilterListBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilterListBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (null == getArguments())
            dismiss();

        binding.recFilter.addItemDecoration(new
                DividerItemDecoration(requireActivity(),
                DividerItemDecoration.VERTICAL));
        binding.recFilter.setItemAnimator(new DefaultItemAnimator());
        binding.recFilter.setHasFixedSize(true);
        binding.recFilter.setAdapter(new DemoAdapter(getFilterList(getArguments().getString(AppConstant.KEY_FILTER))));
    }

    private List<FilterModel> getFilterList(String type) {

        List<FilterModel> filterModels = new ArrayList<>();
        if (type.equalsIgnoreCase(requireActivity().getString(R.string.name))) {
            filterModels.add(new FilterModel(AppConstant.ASCENDING, "asc"));
            filterModels.add(new FilterModel(AppConstant.DESCENDING, "desc"));
        }
        return filterModels;
    }

    private class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.DemoVH> {
        List<FilterModel> modelList;

        public DemoAdapter(List<FilterModel> modelList) {
            this.modelList = modelList;
        }

        @NonNull
        @Override
        public DemoAdapter.DemoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            FilterViewBinding coinViewBinding = FilterViewBinding.inflate(layoutInflater, parent, false);
            return new DemoAdapter.DemoVH(coinViewBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull DemoAdapter.DemoVH holder, int position) {
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
    }
}