package com.e.cryptocracy.appDao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.e.cryptocracy.modals.CoinCategoryModal;
import com.e.cryptocracy.modals.CoinModal;
import com.e.cryptocracy.modals.SearchedCoinModal;

import java.util.List;

@Dao
public interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCoins(CoinModal user);

    @Query("select * from coin_table")
    LiveData<List<CoinModal>> getCoins();

    @Query("delete from coin_table")
    void deleteCoins();


    @Update
    void updateCoins(CoinModal user);

    @Delete
    void deleteCoins(CoinModal user);


    //for category
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCategory(CoinCategoryModal user);

    @Query("select * from category_table")
    LiveData<List<CoinCategoryModal>> getAllCategory();

    @Query("delete from category_table")
    void deleteAllCategory();


    //for All Coins to Searching...
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAllCoins(SearchedCoinModal user);

    @Query("select * from  SEARCHED_COIN_TABLE")
    LiveData<List<SearchedCoinModal>> getAllCoins();

    @Query("delete from SEARCHED_COIN_TABLE")
    void deleteAllCoins();


}
