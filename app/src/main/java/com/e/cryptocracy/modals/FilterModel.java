package com.e.cryptocracy.modals;

public class FilterModel {
    String sortKeys;
    String sortText;

    public String getSortText() {
        return sortText;
    }

    public void setSortText(String sortText) {
        this.sortText = sortText;
    }

    public String getSortKeys() {
        return sortKeys;
    }

    public void setSortKeys(String sortKeys) {
        this.sortKeys = sortKeys;
    }

    public FilterModel(String sortKeys, String sortText) {
        this.sortKeys = sortKeys;
        this.sortText = sortText;
    }
}
