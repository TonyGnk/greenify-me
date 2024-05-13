package com.example.greenifyme.data.record

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.greenifyme.data.Record
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(record: Record)

    @Update
    suspend fun update(record: Record)

    @Query("SELECT * from records_table ORDER BY id ASC")
    fun getAll(): Flow<List<Record>>

    @Query("SELECT * FROM records_table WHERE id = :id")
    fun get(id: Int): Flow<Record>

    @Delete
    suspend fun delete(account: Record)

    @Query("DELETE FROM records_table")
    suspend fun deleteAll()
}

class RecordRepository(private val dao: RecordDao) {

    fun init(scope: CoroutineScope) {
        initialRecords.forEach {
            insert(it, scope)
        }
    }

    fun insert(record: Record, scope: CoroutineScope) =
        scope.launch {
            dao.insert(record)
        }

    fun update(record: Record, scope: CoroutineScope) = scope.launch {
        dao.update(record)
    }


    fun get(id: Int): Flow<Record?> = dao.get(id)
    fun getAll(): Flow<List<Record>> = dao.getAll()

    fun delete(record: Record, scope: CoroutineScope) = scope.launch {
        dao.delete(record)
    }

    fun deleteAll(scope: CoroutineScope) = scope.launch {
        dao.deleteAll()
    }
}
