package com.e.cryptocracy.modals;

public class CurrencyModel {

    String id;
    String name;
    String sign;
    String symbol;
    Boolean selectd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Boolean getSelected() {
        return selectd;
    }

    public void setSelected(Boolean selected) {
        selectd = selected;
    }

    public CurrencyModel(String id, String name, String sign, String symbol, Boolean selectd) {
        this.id = id;
        this.name = name;
        this.sign = sign;
        this.symbol = symbol;
        this.selectd = selectd;
    }

}
