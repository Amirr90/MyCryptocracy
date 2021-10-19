package com.e.cryptocracy.viewModal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.e.cryptocracy.modals.CoinModal;

public class ItemDataSourceFactory extends DataSource.Factory {

    private final MutableLiveData<PageKeyedDataSource<Integer, CoinModal>> itemLiveDataSource = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loadingState = new MutableLiveData<>();


    @Override
    public DataSource create() {
        ItemDataSource itemDataSource = new ItemDataSource();
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, CoinModal>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }

    public LiveData<Boolean> getLoadingState() {
        return loadingState;
    }
}
