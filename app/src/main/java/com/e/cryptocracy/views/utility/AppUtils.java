package com.e.cryptocracy.views.utility;

import com.e.cryptocracy.R;
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
}
