package com.example.greenifyme.data.record

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
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(record: Record)

    @Update
    suspend fun update(record: Record)

    @Query("DELETE FROM records_table")
    suspend fun deleteAll()

    @Query("SELECT * from records_table ORDER BY id ASC")
    fun getRecords(): Flow<List<Record>>

    @Query("SELECT * FROM records_table WHERE id = :id")
    fun getRecord(id: Int): Flow<Record>

    @Delete
    suspend fun delete(account: Record)
}

class RecordJavaHandler(private val recordDao: RecordDao) : ViewModel() {
    fun insert(record: Record) {
        viewModelScope.launch {
            recordDao.insert(record)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            recordDao.deleteAll()
        }
    }
}