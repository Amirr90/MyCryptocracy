package com.e.cryptocracy.utility;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.e.cryptocracy.R;
import com.e.cryptocracy.databinding.FragmentFilterListBinding;
import com.e.cryptocracy.modals.FilterModel;
import com.e.cryptocracy.modals.MarketDataModel;
import com.e.cryptocracy.modals.WelcomeModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class AppUtils {
    private static final String TAG = "AppUtils";
    private static final String MY_PREFS_NAME = "my_prefs";
    static ProgressDialog progressDialog;

    public static List<WelcomeModel> getWelcomeList() {
        List<WelcomeModel> models = new ArrayList<>();
        models.add(new WelcomeModel("Welcome to", App.context.getString(R.string.app_name), "Cryptocracy is the best app to track Bitcoin, Ethereum, Ripple  other CryptoCurrency"));
        models.add(new WelcomeModel("Tracks", App.context.getString(R.string.app_name), "every seconds crypto around the world !!"));
        models.add(new WelcomeModel("50+", App.context.getString(R.string.app_name), "Currency support to track coins !!"));
        return models;
    }

    public static List<FilterModel> getCoinDetailsData(JSONObject market_data, MarketDataModel marketDataModel) throws JSONException {
        List<FilterModel> filterModels = new ArrayList<>();
        String outPattern = "EEE, d MMM yyyy hh:mm aaa";
        String inPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String currency = AppUtils.getString(AppConstant.CURRENCY, App.context).toLowerCase();
        filterModels.add(new FilterModel(AppConstant.MARKET_CAP_RANK, "" + (Double) market_data.get("market_cap_rank")));
        filterModels.add(new FilterModel(AppConstant.TRADING_VOLUME, "" + numberFormat(Double.parseDouble("" + market_data.getJSONObject("total_volume").get(currency)))));
        filterModels.add(new FilterModel(AppConstant.MARKETCAP, "" + numberFormat(Double.parseDouble("" + market_data.getJSONObject("market_cap").get(currency)))));


        filterModels.add(new FilterModel(AppConstant.TOTAL_SUPPLY, marketDataModel.getTotal_supply() == null ? "--" : numberFormat(marketDataModel.getTotal_supply())));
        filterModels.add(new FilterModel(AppConstant.MAX_SUPPLY, marketDataModel.getMax_supply() == null ? "--" : numberFormat(marketDataModel.getMax_supply())));
        filterModels.add(new FilterModel(AppConstant.CIRCULATION_SUPPLY, marketDataModel.getCirculating_supply() == null ? "--" : numberFormat(marketDataModel.getCirculating_supply())));


        filterModels.add(new FilterModel(AppConstant.HIGH_24, AppUtils.getCurrencyFormat((Double) market_data.getJSONObject("high_24h").get(currency))));
        filterModels.add(new FilterModel(AppConstant.LOW_24, AppUtils.getCurrencyFormat((Double) market_data.getJSONObject("low_24h").get(currency))));


        filterModels.add(new FilterModel(AppConstant.ALL_TIME_HIGH, AppUtils.getCurrencyFormat((Double) market_data.getJSONObject("ath").get(currency))));
        filterModels.add(new FilterModel(AppConstant.ALL_TIME_HIGH_DATE, (parseDate((String) market_data.getJSONObject("ath_date").get(currency), outPattern, inPattern))));
        filterModels.add(new FilterModel(AppConstant.ALL_HIGH_CHANGE_PERCENTAGE, AppUtils.getCurrencyFormat((Double) market_data.getJSONObject("ath_change_percentage").get(currency)) + "%"));


        filterModels.add(new FilterModel(AppConstant.ALL_TIME_LOW, AppUtils.getCurrencyFormat((Double) market_data.getJSONObject("atl").get(currency))));
        filterModels.add(new FilterModel(AppConstant.ALL_TIME_LOW_DATE, (parseDate((String) market_data.getJSONObject("atl_date").get(currency), outPattern, inPattern))));
        filterModels.add(new FilterModel(AppConstant.ALL_LOW_CHANGE_PERCENTAGE, AppUtils.getCurrencyFormat((Double) market_data.getJSONObject("atl_change_percentage").get(currency)) + "%"));

        // filterModels.add(new FilterModel(AppConstant.ALL_LOW_CHANGE_PERCENTAGE, AppUtils.parseDate((String) market_data.getJSONObject("atl_change_percentage").get(currency), outPattern, inPattern) + "%"));

        return filterModels;
    }

    public static String numberFormat(Double number) {
        return new DecimalFormat("###,###,###,###").format(number);
    }

    public static String parseDate(String inDate, String outPattern, String inputPattern) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outPattern);

        Date date;
        String str = null;

        try {
            date = inputFormat.parse(inDate);
            str = outputFormat.format(date);
            return str;
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d(TAG, "parseDate: " + e.getLocalizedMessage()
            );
            return inDate;
        }


    }

    public static LayoutInflater getInflater(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext());
    }


    public static void showRequestDialog(Activity activity) {

        //Log.d("Token-Number", AppSettings.getString(AppSettings.token));

        try {
            if (!((Activity) activity).isFinishing()) {
                progressDialog = ProgressDialog.show(activity, null, null, false, true);
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(android.R.color.transparent)));
                progressDialog.setContentView(R.layout.progress_bar);
                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showRequestDialog(Context activity) {

        try {
            if (!((Activity) activity).isFinishing()) {
                progressDialog = ProgressDialog.show(activity, null, null, false, true);
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(android.R.color.transparent)));
                progressDialog.setContentView(R.layout.progress_bar);
                progressDialog.show();

            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    public static void hideDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
                progressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<FilterModel> getFilterList(String type, FragmentFilterListBinding binding) {

        Log.d(TAG, "getFilterList: " + type);
        List<FilterModel> filterModels = new ArrayList<>();
        if (type.equalsIgnoreCase(App.context.getString(R.string.name)) || type.equalsIgnoreCase(App.context.getString(R.string.price))) {
            filterModels.add(new FilterModel("id_asc", AppConstant.ASCENDING));
            filterModels.add(new FilterModel("id_desc", AppConstant.DESCENDING));
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
            filterModels.add(new FilterModel("all", AppConstant.ALL));
            filterModels.add(new FilterModel("defi", AppConstant.DEFI));
            filterModels.add(new FilterModel("filesharing", AppConstant.FILE_SHARING));
            binding.recFilter.setLayoutManager(new LinearLayoutManager(App.context, LinearLayoutManager.HORIZONTAL, false));
        } else if (type.equalsIgnoreCase(App.context.getString(R.string.sort_type))) {
            binding.textView14.setText("Order By");
            filterModels.add(new FilterModel("market_cap_desc", AppConstant.MARKET_CAP_DESC));
            filterModels.add(new FilterModel("market_cap_asc", AppConstant.MARKET_CAP_ACS));
            filterModels.add(new FilterModel("gecko_desc", AppConstant.GECKO_DESC));
            filterModels.add(new FilterModel("gecko_asc", AppConstant.GECKO_ASC));
            filterModels.add(new FilterModel("volume_asc", AppConstant.VOLUME_ASC));
            filterModels.add(new FilterModel("volume_desc", AppConstant.VOLUME_DESC));

        } else if (type.equalsIgnoreCase(App.context.getString(R.string.hour_sort))) {
            binding.textView14.setText("Order By");
            filterModels.add(new FilterModel("1h", AppConstant.SORT_1H));
            filterModels.add(new FilterModel("24h", AppConstant.SORT_24H));
            filterModels.add(new FilterModel("7d", AppConstant.SORT_7D));
            filterModels.add(new FilterModel("14d", AppConstant.SORT_14D));
            filterModels.add(new FilterModel("30d", AppConstant.SORT_30D));
            filterModels.add(new FilterModel("200d", AppConstant.SORT_200D));
            filterModels.add(new FilterModel("1y", AppConstant.SORT_1Y));


        }
        return filterModels;
    }

    public static List<String> getGraphFilterKeysList() {
        List<String> strings = new ArrayList<>();
        strings.add(AppConstant.SORT_24H);
        strings.add(AppConstant.SORT_7D);
        strings.add(AppConstant.SORT_14D);
        strings.add(AppConstant.SORT_30D);
        strings.add(AppConstant.SORT_6MONTH);
        strings.add(AppConstant.SORT_1Y);
        strings.add(AppConstant.SORT_ALL_TIME);
        return strings;
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

    public static String getString(String key, Context activity) {
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

    public static List<String> coinFilterKeys() {
        String currency = getString(AppConstant.CURRENCY, App.context);
        List<String> list = new ArrayList<>();
        list.add(App.context.getString(R.string.currency) + "(" + currency + ")");
        //list.add(App.context.getString(R.string.price));
        //list.add(App.context.getString(R.string.h_1));
        list.add(App.context.getString(R.string.sort_type));
        list.add(App.context.getString(R.string.category));
        return list;

    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity != null) {
            try {
                @SuppressLint("WrongConstant") InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
                View view = activity.getCurrentFocus();
                if (view != null) {
                    IBinder binder = view.getWindowToken();
                    if (binder != null) {
                        inputMethodManager.hideSoftInputFromWindow(binder, 0);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Objects.requireNonNull(activity).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }


    public static String getCurrencyFormat(double num) {

        String currency = getString(AppConstant.CURRENCY, App.context).toUpperCase();
        String COUNTRY = currency.substring(0, 2);
        String LANGUAGE = "en";
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale(LANGUAGE, COUNTRY));
        numberFormat.setMaximumFractionDigits(9);
        numberFormat.format(num);
        return numberFormat.format(num);



       /* return NumberFormat.getCurrencyInstance(new Locale(LANGUAGE, COUNTRY))
                .setMaximumFractionDigits(5)
                .format(num);*/
    }

    public static String getCurrencyFormat(long num) {
        String currency = getString(AppConstant.CURRENCY, App.context).toUpperCase();
        String COUNTRY = currency.substring(0, 2);
        String LANGUAGE = "en";
        return NumberFormat.getCurrencyInstance(new Locale(LANGUAGE, COUNTRY)).format(num);
    }

    public static String getCurrencyFormat(String num) {
        Log.d(TAG, "getCurrencyFormat: " + num);
        String currency = getString(AppConstant.CURRENCY, App.context).toUpperCase();
        String COUNTRY = currency.substring(0, 2);
        String LANGUAGE = "en";
        return NumberFormat.getCurrencyInstance(new Locale(LANGUAGE, COUNTRY)).format(num);
    }

    public static void updateToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                        return;
                    }
                    String token = task.getResult();
                    Log.d(TAG, token);
                    updateToServer(token);
                });
    }

    public static void updateToServer(String token) {
        getFireStoreReference().collection(AppConstant.USERS).document(getUid()).update(AppConstant.TOKEN, token);
        Log.d(TAG, "TokenUpdatedToServer: " + token);
    }


    public static void updateUserInfo(@Nullable FirebaseUser user) {
        if (null != user) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put(AppConstant.EMAIL, user.getEmail() == null ? "" : user.getEmail());
            userMap.put(AppConstant.CURRENCY, getString(AppConstant.CURRENCY, App.context));
            userMap.put(AppConstant.TIMESTAMP, System.currentTimeMillis());
            userMap.put(AppConstant.NAME, user.getDisplayName() == null ? "" : user.getDisplayName());
            getFireStoreReference().collection(AppConstant.USERS).document(getUid()).update(userMap)
                    .addOnSuccessListener(aVoid -> updateToken())
                    .addOnFailureListener(e -> getFireStoreReference().collection(AppConstant.USERS).document(getUid()).set(userMap).addOnSuccessListener(aVoid -> updateToken()));
        }
    }

    public final String getDisplayCountry() {
        String[] locales = Locale.getISOCountries();

        for (String countryCode : locales) {

            Locale obj = new Locale("", countryCode);

            System.out.println("Country Code = " + obj.getCountry()
                    + ", Country Name = " + obj.getDisplayCountry());

        }
        return "";
    }

    public static FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    @NotNull
    public static FirebaseFirestore getFireStoreReference() {
        return FirebaseFirestore.getInstance();
    }

    @NotNull
    public static String getUid() {
        return getCurrentUser().getUid();
    }

    public static void updateFavCoins(String id, boolean checked, @NotNull UpdateFavouriteCoinsListener updateFavouriteCoinsListener) {
        if (!isNetworkConnected(App.context)) {
            AppConstant.showToast("No Internet");
            return;
        }
        Log.d(TAG, "getUid: " + getUid());
        Log.d(TAG, "checked: " + checked);
        Log.d(TAG, "id: " + id);

        Map<String, Object> map = new HashMap<>();
        map.put("coinId", id);
        if (checked)
            getFireStoreReference().collection(AppConstant.USERS).document(getUid()).collection(AppConstant.FAVOURITE).document(id).set(map).addOnSuccessListener(obj -> updateFavouriteCoinsListener.onSuccess("Added !!")).addOnFailureListener(e -> updateFavouriteCoinsListener.onFailed(e.getLocalizedMessage()));
        else
            getFireStoreReference().collection(AppConstant.USERS).document(getUid()).collection(AppConstant.FAVOURITE).document(id).delete().addOnSuccessListener(obj -> updateFavouriteCoinsListener.onSuccess("Removed !!")).addOnFailureListener(e -> updateFavouriteCoinsListener.onFailed(e.getLocalizedMessage()));
    }
}