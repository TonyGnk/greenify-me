package com.example.greenifyme.data.account;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;

import kotlinx.coroutines.flow.Flow;

@Dao
public interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Account account);

    @Query("DELETE FROM accounts_table")
    void deleteAll();

    @Query("SELECT * from accounts_table ORDER BY name ASC")
    Flow<List<Account>> getAccounts();

    @Query("SELECT * FROM accounts_table WHERE id = :id")
    Flow<Account> getAccount(int id);

    @Delete
    void delete(Account account);
}