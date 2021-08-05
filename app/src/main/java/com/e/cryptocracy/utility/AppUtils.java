package com.e.cryptocracy.utility;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.e.cryptocracy.R;
import com.e.cryptocracy.databinding.FragmentFilterListBinding;
import com.e.cryptocracy.modals.FilterModel;
import com.e.cryptocracy.modals.WelcomeModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class AppUtils {
    private static final String TAG = "AppUtils";
    private static final String MY_PREFS_NAME = "my_prefs";

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

    public static String getCurrencyFormat(double num) {
        String COUNTRY = "IN";
        String LANGUAGE = "en";
        return NumberFormat.getCurrencyInstance(new Locale(LANGUAGE, COUNTRY)).format(num);
    }

    public static String getCurrencyFormat(long num) {
        String COUNTRY = "IN";
        String LANGUAGE = "en";
        return NumberFormat.getCurrencyInstance(new Locale(LANGUAGE, COUNTRY)).format(num);
    }

    public static String getCurrencyFormat(String num) {
        Double number = Double.parseDouble(num);
        String COUNTRY = "IN";
        String LANGUAGE = "en";
        return NumberFormat.getCurrencyInstance(new Locale(LANGUAGE, COUNTRY)).format(number);

    }

    public static void showToolbar(Activity activity) {
        Objects.requireNonNull(((AppCompatActivity) activity).getSupportActionBar()).show();
    }

    public static void hideToolbar(Activity activity) {
        Objects.requireNonNull(((AppCompatActivity) activity).getSupportActionBar()).hide();
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null) {
                return info.getState() == NetworkInfo.State.CONNECTED;
            }
        }

        return false;
    }

    @SuppressLint("SimpleDateFormat")
    public static String getTimeFormatFromTimestamp(long currentTimeMillis, String outFormat) {
        Log.d(TAG, "getTimeFormat:currentTimeMillis  " + currentTimeMillis);
        try {
            return new SimpleDateFormat(outFormat).
                    format(new Date(currentTimeMillis));
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "getTimeFormat: " + e.getLocalizedMessage());
            return null;
        }

    }

    public static android.view.animation.Animation fadeIn(Activity activity) {
        return AnimationUtils.loadAnimation(activity, R.anim.fade_in);
    }

    public static android.view.animation.Animation fadeIn(Context activity) {
        return AnimationUtils.loadAnimation(activity, R.anim.fade_in);
    }

    public static Animation fadeOut(Activity activity) {
        return AnimationUtils.loadAnimation(activity, R.anim.fade_out);
    }

    public static void setString(String key, String value, Activity activity) {
        SharedPreferences sharedpreferences = activity.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void setBoolean(String key, boolean value, Activity activity) {
        SharedPreferences sharedpreferences = activity.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void setBoolean(String key, boolean value, Context activity) {
        SharedPreferences sharedpreferences = activity.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static String getString(String key, Activity activity) {
        if (activity != null) {
            SharedPreferences pref = activity.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            return pref.getString(key, "");
        } else return null;

    }

    public static boolean getBoolean(String key, Activity activity) {
        SharedPreferences pref = activity.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        return pref.getBoolean(key, false);
    }

    public static String capitalizeFirstLetter(String whenToUse) {

        try {
            if (!whenToUse.contains("  ")) {
                String[] words = whenToUse.toLowerCase().split(" ");
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < words.length; i++) {
                    String word = words[i];

                    if (i > 0 && word.length() > 0) {
                        builder.append(" ");
                    }

                    String cap = word.substring(0, 1).toUpperCase() + word.substring(1);
                    builder.append(cap);
                }
                return builder.toString();
            } else {
                return whenToUse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return whenToUse;
        }
    }

    public static String prettyCount(Double num) {
        char[] suffix = {' ', 'k', 'M', 'B', 'T', 'P', 'E'};
        int value = (int) Math.floor(Math.log10(num));
        int base = value / 3;
        if (value >= 3 && base < suffix.length) {
            return new DecimalFormat("#0.0").format(num / Math.pow(10, base * 3)) + suffix[base];
        } else {
            return new DecimalFormat("#,##0").format(num);
        }
    }
}