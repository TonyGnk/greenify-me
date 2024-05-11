package com.example.greenifyme.data.material

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.greenifyme.data.Material
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Dao
interface MaterialDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(material: Material)

    @Update
    suspend fun update(material: Material)

    @Query("DELETE FROM materials_table")
    suspend fun deleteAll()

    @Query("SELECT * from materials_table ORDER BY id ASC")
    fun getMaterials(): Flow<List<Material>>

    @Query("SELECT * FROM materials_table WHERE id = :id")
    fun getMaterial(id: Int): Flow<Material>

    @Delete
    suspend fun delete(material: Material)
}

// Async running is done in the background thread instead of the main thread
// This class is used to handle the async operations for the database for the MaterialDatabase.java
// With that, we can call async java code from everywhere in the app
class MaterialJavaHandler(private val materialDao: MaterialDao) : ViewModel() {
    fun insert(material: Material) {
        viewModelScope.launch {
            materialDao.insert(material)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            materialDao.deleteAll()
        }
    }
}