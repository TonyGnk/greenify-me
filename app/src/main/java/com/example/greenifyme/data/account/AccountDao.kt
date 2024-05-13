package com.example.greenifyme.data.account

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.greenifyme.data.Account
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(account: Account)

    @Update
    suspend fun update(account: Account)

    @Query("SELECT * from accounts_table ORDER BY id ASC")
    fun getAll(): Flow<List<Account>>

    @Query("SELECT * FROM accounts_table WHERE id = :id")
    fun get(id: Int): Flow<Account>

    @Delete
    suspend fun delete(account: Account)

    @Query("DELETE FROM accounts_table")
    suspend fun deleteAll()
}

class AccountRepository(private val dao: AccountDao) {

    fun init(scope: CoroutineScope) {
        initialAccounts.forEach {
            insert(it, scope)
        }
    }

    fun insert(account: Account, scope: CoroutineScope) =
        scope.launch {
            dao.insert(account)
        }

    fun update(account: Account, scope: CoroutineScope) = scope.launch {
        dao.update(account)
    }

    fun get(id: Int): Flow<Account?> = dao.get(id)
    fun getAll(): Flow<List<Account>> = dao.getAll()

    fun delete(account: Account, scope: CoroutineScope) = scope.launch {
        dao.delete(account)
    }

    fun deleteAll(scope: CoroutineScope) = scope.launch {
        dao.deleteAll()
    }
}

