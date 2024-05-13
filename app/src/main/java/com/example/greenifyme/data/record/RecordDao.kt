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

    @Query("DELETE FROM records_table")
    suspend fun deleteAll()

    @Query("SELECT * from records_table ORDER BY id ASC")
    fun getAll(): Flow<List<Record>>

    @Query("SELECT * FROM records_table WHERE id = :id")
    fun get(id: Int): Flow<Record>

    @Delete
    suspend fun delete(account: Record)
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

    fun delete(record: Record, scope: CoroutineScope) = scope.launch {
        dao.delete(record)
    }

    fun get(id: Int): Flow<Record?> = dao.get(id)
    fun getAll(): Flow<List<Record>> = dao.getAll()
}

private val initialRecords = listOf(
    Record(0, 0, false, 1620000000),
    Record(1, 0, true, 1620100000),
    Record(2, 1, false, 1620200000),
    Record(3, 1, true, 1620300000),
    Record(4, 1, false, 1620400000),
    Record(5, 2, true, 1620500000),
    Record(6, 2, false, 1620600000),
    Record(7, 3, true, 1620700000),
    Record(8, 3, false, 1620800000),
    Record(9, 4, true, 1620900000),
    Record(10, 4, false, 1621000000),
    Record(11, 5, true, 1621100000),
    Record(12, 5, false, 1621200000),
    Record(13, 6, true, 1621300000),
    Record(14, 6, false, 1621400000),
    Record(15, 7, true, 1621500000),
    Record(16, 7, false, 1621600000),
    Record(17, 8, true, 1621700000),
    Record(18, 8, false, 1621800000),
    Record(19, 9, true, 1621900000),
    Record(20, 9, false, 1622000000),
    Record(21, 10, true, 1622100000),
    Record(22, 10, false, 1622200000),
    Record(23, 11, true, 1622300000),
    Record(24, 11, false, 1622400000),
    Record(25, 12, true, 1622500000),
    Record(26, 12, false, 1622600000),
    Record(27, 13, true, 1622700000),
    Record(28, 13, false, 1622800000),
    Record(29, 14, true, 1622900000),
    Record(30, 14, false, 1623000000),
    Record(31, 15, true, 1623100000),
    Record(32, 15, false, 1623200000),
    Record(33, 16, true, 1623300000),
    Record(34, 16, false, 1623400000),
    Record(35, 17, true, 1623500000),
    Record(36, 17, false, 1623600000),
    Record(37, 18, true, 1623700000),
    Record(38, 18, false, 1623800000),
    Record(39, 19, true, 1623900000),
    Record(40, 19, false, 1624000000),
    Record(41, 20, true, 1624100000),
    Record(42, 20, false, 1624200000),
    Record(43, 21, true, 1624300000),
    Record(44, 22, false, 1624400000),
    Record(45, 22, true, 1624500000),
    Record(46, 23, false, 1624600000),
    Record(47, 23, true, 1624700000),
    Record(48, 24, false, 1624800000),
    Record(49, 24, true, 1624900000),
    Record(50, 25, false, 1625000000),
    Record(51, 25, true, 1625100000),
    Record(52, 26, false, 1625200000),
    Record(53, 26, true, 1625300000),
    Record(54, 27, false, 1625400000),
    Record(55, 27, true, 1625500000),
    Record(56, 28, false, 1625600000),
    Record(57, 28, true, 1625700000),
    Record(58, 29, false, 1625800000),
    Record(59, 29, true, 1625900000),
    Record(60, 30, false, 1626000000),
    Record(61, 30, true, 1626100000),
    Record(62, 31, false, 1626200000),
    Record(63, 31, true, 1626300000),
    Record(64, 32, false, 1626400000),
    Record(65, 32, true, 1626500000),
    Record(66, 33, false, 1626600000),
    Record(67, 33, true, 1626700000),
    Record(68, 34, false, 1626800000),
    Record(69, 34, true, 1626900000),
    Record(70, 35, false, 1627000000),
    Record(71, 35, true, 1627100000),
    Record(72, 36, false, 1627200000),
    Record(73, 36, true, 1627300000),
    Record(74, 37, false, 1627400000),
    Record(75, 37, true, 1627500000),
    Record(76, 38, false, 1627600000),
    Record(77, 38, true, 1627700000),
    Record(78, 39, false, 1627800000),
    Record(79, 39, true, 1627900000),
)