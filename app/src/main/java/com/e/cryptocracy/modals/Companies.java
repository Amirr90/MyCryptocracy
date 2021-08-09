package com.e.cryptocracy.modals;

public class Companies {
    public String name;
    public String symbol;
    String country;
    Double total_holdings;
    Double total_entry_value_usd;
    Double total_current_value_usd;
    Double percentage_of_total_supply;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getTotal_holdings() {
        return total_holdings;
    }

    public void setTotal_holdings(Double total_holdings) {
        this.total_holdings = total_holdings;
    }

    public Double getTotal_entry_value_usd() {
        return total_entry_value_usd;
    }

    public void setTotal_entry_value_usd(Double total_entry_value_usd) {
        this.total_entry_value_usd = total_entry_value_usd;
    }

    public Double getTotal_current_value_usd() {
        return total_current_value_usd;
    }

    public void setTotal_current_value_usd(Double total_current_value_usd) {
        this.total_current_value_usd = total_current_value_usd;
    }

    public Double getPercentage_of_total_supply() {
        return percentage_of_total_supply;
    }

    public void setPercentage_of_total_supply(Double percentage_of_total_supply) {
        this.percentage_of_total_supply = percentage_of_total_supply;
    }
}
