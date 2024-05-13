package com.example.greenifyme.data.material

import androidx.lifecycle.ViewModel
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.greenifyme.data.Material
import kotlinx.coroutines.CoroutineScope
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
    fun getAll(): Flow<List<Material>>

    @Query("SELECT * FROM materials_table WHERE id = :id")
    fun get(id: Int): Flow<Material>

    @Delete
    suspend fun delete(material: Material)
}

class MaterialRepository(private val dao: MaterialDao) : ViewModel() {
    fun init(scope: CoroutineScope) {
        initialMaterials.forEach {
            insert(it, scope)
        }
    }

    fun insert(material: Material, scope: CoroutineScope) =
        scope.launch {
            dao.insert(material)
        }

    fun update(material: Material, scope: CoroutineScope) = scope.launch {
        dao.update(material)
    }

    fun delete(material: Material, scope: CoroutineScope) = scope.launch {
        dao.delete(material)
    }

    fun get(id: Int): Flow<Material?> = dao.get(id)
    fun getAll(): Flow<List<Material>> = dao.getAll()
}

