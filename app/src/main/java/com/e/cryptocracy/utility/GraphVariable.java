package com.e.cryptocracy.utility;

import androidx.lifecycle.LifecycleOwner;

import com.e.cryptocracy.databinding.FragmentCoinDetailBinding;
import com.e.cryptocracy.viewModal.AppViewModal;
import com.highsoft.highcharts.common.hichartsclasses.HIOptions;
import com.highsoft.highcharts.core.HIChartView;

import org.json.JSONObject;

public class GraphVariable {
    String coinId, days;
    FragmentCoinDetailBinding binding;
    JSONObject mJSONObject;
    HIOptions options;
    LifecycleOwner lifecycleOwner;

    public LifecycleOwner getLifecycleOwner() {
        return lifecycleOwner;
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
    }

    HIChartView chartView;
    AppViewModal appViewModal;

    public FragmentCoinDetailBinding getBinding() {
        return binding;
    }

    public void setBinding(FragmentCoinDetailBinding binding) {
        this.binding = binding;
    }

    public JSONObject getmJSONObject() {
        return mJSONObject;
    }

    public void setmJSONObject(JSONObject mJSONObject) {
        this.mJSONObject = mJSONObject;
    }

    public HIOptions getOptions() {
        return options;
    }

    public void setOptions(HIOptions options) {
        this.options = options;
    }

    public HIChartView getChartView() {
        return chartView;
    }

    public void setChartView(HIChartView chartView) {
        this.chartView = chartView;
    }

    public AppViewModal getAppViewModal() {
        return appViewModal;
    }

    public void setAppViewModal(AppViewModal appViewModal) {
        this.appViewModal = appViewModal;
    }

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
