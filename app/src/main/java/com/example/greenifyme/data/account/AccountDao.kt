package com.example.greenifyme.data.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(account: Account)

    @Update
    suspend fun update(account: Account)

    @Query("DELETE FROM accounts_table")
    suspend fun deleteAll()

    @Query("SELECT * from accounts_table ORDER BY id ASC")
    fun getAccounts(): Flow<List<Account>>

    @Query("SELECT * FROM accounts_table WHERE id = :id")
    fun getAccount(id: Int): Flow<Account>

    @Delete
    suspend fun delete(account: Account)
}

// Async running is done in the background thread instead of the main thread
// This class is used to handle the async operations for the database for the AccountDatabase.java
// With that, we can call async java code from everywhere in the app
class AccountJavaHandler(private val accountDao: AccountDao) : ViewModel() {
    fun insert(account: Account) {
        viewModelScope.launch {
            accountDao.insert(account)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            accountDao.deleteAll()
        }
    }
}