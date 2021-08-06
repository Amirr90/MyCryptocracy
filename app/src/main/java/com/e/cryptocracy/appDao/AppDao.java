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

import java.util.List;

@Dao
public interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCoins(CoinModal user);
    @Query("select * from coin_table")
    LiveData<List<CoinModal>> getAllCoins();
    @Query("delete from coin_table")
    void deleteAllCoins();


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


}
