package com.e.cryptocracy.views.utility;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.e.cryptocracy.R;
import com.e.cryptocracy.databinding.FragmentFilterListBinding;
import com.e.cryptocracy.modals.FilterModel;
import com.e.cryptocracy.modals.WelcomeModel;

import java.util.ArrayList;
import java.util.List;

public class AppUtils {
    public static List<WelcomeModel> getWelcomeList() {
        List<WelcomeModel> models = new ArrayList<>();
        models.add(new WelcomeModel("Welcome to", App.context.getString(R.string.app_name), "Cryptocracy is the best app to track Bitcoin, Ethereum, Ripple  other CryptoCurrency"));
        models.add(new WelcomeModel("text 2", App.context.getString(R.string.app_name), "subText2"));
        models.add(new WelcomeModel("text 3", App.context.getString(R.string.app_name), "subText3"));
        return models;
    }

    public static List<FilterModel> getCoinDetailsData() {
        List<FilterModel> filterModels = new ArrayList<>();
        filterModels.add(new FilterModel(AppConstant.MARKET_CAP_RANK, "1"));
        filterModels.add(new FilterModel(AppConstant.MARKET_CAP, "1"));
        filterModels.add(new FilterModel(AppConstant.TRADING_VOLUME, "1"));
        filterModels.add(new FilterModel(AppConstant.HIGH_24, "1"));
        filterModels.add(new FilterModel(AppConstant.LOW_24, "1"));
        filterModels.add(new FilterModel(AppConstant.MARKET_CAP_RANK, "1"));
        filterModels.add(new FilterModel(AppConstant.MARKET_CAP_RANK, "1"));
        filterModels.add(new FilterModel(AppConstant.ALL_TIME_HIGH, "1"));
        filterModels.add(new FilterModel(AppConstant.ALL_TIME_HIGH_DATE, "1"));
        return filterModels;
    }

    public static LayoutInflater getInflater(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext());
    }

    public static List<FilterModel> getFilterList(String type, FragmentFilterListBinding binding) {

        List<FilterModel> filterModels = new ArrayList<>();
        if (type.equalsIgnoreCase(App.context.getString(R.string.name)) || type.equalsIgnoreCase(App.context.getString(R.string.price))) {
            filterModels.add(new FilterModel(AppConstant.ASCENDING, "asc"));
            filterModels.add(new FilterModel(AppConstant.DESCENDING, "desc"));
            binding.textView14.setText(String.format("Sort %s", type));
            binding.recFilter.setLayoutManager(new LinearLayoutManager(App.context, LinearLayoutManager.HORIZONTAL, false));
        } else if (type.equalsIgnoreCase(App.context.getString(R.string.all))) {
            filterModels.add(new FilterModel(AppConstant.MARKET_CAP, "market_cap"));
            filterModels.add(new FilterModel(AppConstant.NAME, "name"));
            filterModels.add(new FilterModel(AppConstant.MARKET_CAP_STRICT, "market_cap_strict"));
            filterModels.add(new FilterModel(AppConstant.MARKET_CAP_BY_TOTAL_SUPPLY_STRICT, "market_cap_by_total_supply_strict"));
            filterModels.add(new FilterModel(AppConstant.SYMBOL, "symbol"));
            filterModels.add(new FilterModel(AppConstant.DATE_ADDED, "date_added"));
            filterModels.add(new FilterModel(AppConstant.SYMBOL, "symbol"));
            filterModels.add(new FilterModel(AppConstant.CIRCULATION_SUPPLY, "circulating_supply"));
            filterModels.add(new FilterModel(AppConstant.TOTAL_SUPPLY, "total_supply"));
            filterModels.add(new FilterModel(AppConstant.MAX_SUPPLY, "max_supply"));
            filterModels.add(new FilterModel(AppConstant.NUM_MARKET_PAIR, "num_market_pairs"));
            filterModels.add(new FilterModel(AppConstant.VOLUME_24H, "volume_24h"));
            filterModels.add(new FilterModel(AppConstant.VOLUME_7D, "volume_7d"));
            filterModels.add(new FilterModel(AppConstant.VOLUME_30D, "volume_30d"));
            filterModels.add(new FilterModel(AppConstant.PRICE_CHANGE_1H, "percent_change_1h"));
            binding.recFilter.setLayoutManager(new LinearLayoutManager(App.context));
        } else if (type.equalsIgnoreCase(App.context.getString(R.string.type))) {
            binding.textView14.setText("Type Filter");
            filterModels.add(new FilterModel(AppConstant.ALL, "all"));
            filterModels.add(new FilterModel(AppConstant.COINS, "coins"));
            filterModels.add(new FilterModel(AppConstant.TOKENS, "tokens"));
            binding.recFilter.setLayoutManager(new LinearLayoutManager(App.context, LinearLayoutManager.HORIZONTAL, false));
        } else if (type.equalsIgnoreCase(App.context.getString(R.string.tag))) {
            binding.textView14.setText("Tag Filter");
            filterModels.add(new FilterModel(AppConstant.ALL, "all"));
            filterModels.add(new FilterModel(AppConstant.DEFI, "defi"));
            filterModels.add(new FilterModel(AppConstant.FILESHARING, "filesharing"));
            binding.recFilter.setLayoutManager(new LinearLayoutManager(App.context, LinearLayoutManager.HORIZONTAL, false));
        }
        return filterModels;
    }

    public static List<String> getGraphFilterKeysList() {
        List<String> strings = new ArrayList<>();
        strings.add("1 Hour");
        strings.add("7 Day");
        strings.add("14 Days");
        strings.add("1 MONTH");
        strings.add("6 MONTH");
        strings.add("1 YEAR");
        strings.add("ALL TIME");
        return strings;
    }
}