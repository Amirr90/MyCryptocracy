package com.e.cryptocracy.modals;

public class FilterModel {
    String sortKeys;
    String sortText;
    Boolean selected;

    public FilterModel(String sortText, Boolean selected) {
        this.sortText = sortText;
        this.selected = selected;
    }

    public FilterModel(String sortKeys, String sortText, Boolean selected) {
        this.sortKeys = sortKeys;
        this.sortText = sortText;
        this.selected = selected;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public FilterModel(String sortKeys, String sortText) {
        this.sortKeys = sortKeys;
        this.sortText = sortText;
    }

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
}
