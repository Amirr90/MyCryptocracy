package com.e.cryptocracy.utility;

import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class AppConstant {
    public static final String KEY_FILTER = "filterKey";
    public static final String ASCENDING = "Ascending";
    public static final String DESCENDING = "Descending";
    public static final String MARKET_CAP = "Market Cap Descending";
    public static final String NAME = "Name";
    public static final String MARKET_CAP_STRICT = "Market Cap Strict";
    public static final String MARKET_CAP_BY_TOTAL_SUPPLY_STRICT = "Market Cap BY Total Supply Strict";
    public static final String SYMBOL = "Symbol";
    public static final String DATE_ADDED = "Date Added";
    public static final String CIRCULATION_SUPPLY = "Circulating Supply";
    public static final String TOTAL_SUPPLY = "Total Supply";
    public static final String MAX_SUPPLY = "Max Supply";
    public static final String NUM_MARKET_PAIR = "Max Market Pair";
    public static final String VOLUME_24H = "Volume 24H";
    public static final String VOLUME_7D = "Volume 7D";
    public static final String VOLUME_30D = "Volume 30D";
    public static final String PRICE_CHANGE_1H = "Price Change 1H";
    public static final String ALL = "All";
    public static final String COINS = "Coins";
    public static final String TOKENS = "Tokens";
    public static final String DEFI = "Defi";
    public static final String FILE_SHARING = "FileSharing";
    public static final String MARKET_CAP_RANK = "Market Cap Rank";
    public static final String TRADING_VOLUME = "Trading Volume";
    public static final String HIGH_24 = "High 24H";
    public static final String LOW_24 = "Low 24H";
    public static final String ALL_TIME_HIGH = "All Time High";
    public static final String ALL_TIME_LOW = "All Time Low";
    public static final String ALL_TIME_HIGH_DATE = "All Time High Date";
    public static final String CURRENCY = "currency";
    public static final String COIN_LIST_FILTER_KEY = "coinListFilterKey";
    public static final String CATEGORY = "category";
    public static final String MARKET_CAP_DESC = "Market Cap Descending";
    public static final String MARKET_CAP_ACS = "Market Cap Ascending";
    public static final String GECKO_DESC = "Gecko Descending";
    public static final String GECKO_ASC = "Gecko Ascending";
    public static final String VOLUME_ASC = "Volume Ascending";
    public static final String VOLUME_DESC = "Volume Descending";
    public static final String ORDER_BY = "orderBy";
    public static final String SORT_1H = "1 Hour";
    public static final String SORT_24H = "24 Hour";
    public static final String SORT_7D = "7 Days";
    public static final String SORT_14D = "14 days";
    public static final String SORT_30D = "30 days";
    public static final String SORT_200D = "200 Days";
    public static final String SORT_1Y = "1 Year";
    public static final String CURRENCY_PRICE_IN_SELECTED = "selectedCurrencyPrice";
    public static final String COIN_ID = "coinId";
    public static final String ALL_TIME_LOW_DATE = "All Time Low Date";
    public static final String MARKETCAP = "Market Cap";
    public static final String ALL_HIGH_CHANGE_PERCENTAGE = "All Time High Change";
    public static final String ALL_LOW_CHANGE_PERCENTAGE = "All Time Low Change";
    public static final String SORT_6MONTH = "6 MONTHS";
    public static final String SORT_ALL_TIME = "ALL TIME";
    public static final String SORT_1D = "";
    public static final String IMAGE = "image";
    public static final String EMAIL = "email";
    public static final String TIMESTAMP = "timestamp";
    @NotNull
    public static final String USERS = "users";
    public static final String TOKEN = "token";
    public static final String FAVOURITE = "Favourite";


    public static final String NOTIFICATION_TITLE = "title";
    public static final String NOTIFICATION_BODY = "body";
    public static final String NOTIFICATION_ID_ = "id";
    public static final String NOTIFICATION_IMAGE = "image";
    public static final String NOTIFICATION_TYPE = "notificationType";
    public static final String TOPIC_PRICE_ALERT = "priceAlert";
    public static final String TRADE_URL = "tradeUrl";
    public static final String FAVOURITE_COINS = "favCoins";

    public static void showToast(String no_internet) {
        Toast.makeText(App.context, no_internet, Toast.LENGTH_SHORT).show();
    }
}
