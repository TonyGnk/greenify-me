package com.example.greenifyme.data.account

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.greenifyme.data.Account
import com.example.greenifyme.data.relations.AccountWithForm
import com.example.greenifyme.data.relations.WinnerItem
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: Account)

    @Update
    suspend fun update(account: Account)

    @Delete
    suspend fun deleteAccount(account: Account)

    @Query("DELETE FROM accounts_table WHERE accountId = :id")
    suspend fun deleteAccount(id: Int)

    @Query("DELETE FROM accounts_table")
    suspend fun deleteAccounts()

    @Query("SELECT * FROM accounts_table WHERE email = :email")
    fun getAccount(email: String): Account?

    @Query("SELECT * FROM accounts_table WHERE email = :email AND password = :hash LIMIT 1")
    fun getAccount(email: String, hash: String): Account?

    @Query("SELECT * FROM accounts_table WHERE accountId = :id")
    fun getAccountFlow(id: Int): Flow<Account>

    @Query("SELECT * from accounts_table ORDER BY accountId ASC")
    fun getAccounts(): Flow<List<Account>>

    @Query("SELECT * from accounts_table ORDER BY points DESC")
    fun getAccountsOrderByPoints(): Flow<List<Account>>

    @Query("SELECT name, points as totalPoints FROM accounts_table ORDER BY points DESC LIMIT 3")
    fun getTop3Accounts(): Flow<List<WinnerItem>>

    @Transaction
    @Query("SELECT * FROM accounts_table WHERE accountId = :accountId")
    fun getAccountWithForms(accountId: Int): List<AccountWithForm>

    @Query("SELECT SUM(points) FROM accounts_table")
    fun getTotalPoints(): Flow<Int>


}