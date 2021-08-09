package com.e.cryptocracy.modals;

import java.util.List;

public class Investor {
    List<Companies> companies;
    double total_holdings;
    double total_value_usd;
    double market_cap_dominance;

    public List<Companies> getCompanies() {
        return companies;
    }

    public double getTotal_holdings() {
        return total_holdings;
    }

    public double getTotal_value_usd() {
        return total_value_usd;
    }

    public double getMarket_cap_dominance() {
        return market_cap_dominance;
    }
}
