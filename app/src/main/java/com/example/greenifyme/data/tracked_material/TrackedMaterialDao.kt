package com.example.greenifyme.data.tracked_material

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.greenifyme.data.TrackedMaterial
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Dao
interface TrackedMaterialDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tracked_material: TrackedMaterial)

    @Update
    suspend fun update(tracked_material: TrackedMaterial)

    @Query("DELETE FROM tracked_material_table")
    suspend fun deleteAll()

    @Query("SELECT * from tracked_material_table ORDER BY id ASC")
    fun getAccounts(): Flow<List<TrackedMaterial>>

    @Query("SELECT * FROM tracked_material_table WHERE id = :id")
    fun getAccount(id: Int): Flow<TrackedMaterial>

    @Delete
    suspend fun delete(tracked_material: TrackedMaterial)
}

// Async running is done in the background thread instead of the main thread
// This class is used to handle the async operations for the database for the TrackedMaterialDatabase.java
// With that, we can call async java code from everywhere in the app
class TrackedMaterialJavaHandler(private val trackedMaterialDao: TrackedMaterialDao) : ViewModel() {
    fun insert(account: TrackedMaterial) {
        viewModelScope.launch {
            trackedMaterialDao.insert(account)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            trackedMaterialDao.deleteAll()
        }
    }
}