package com.example.greenifyme.data.material

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


    @Query("SELECT * from materials_table ORDER BY materialId ASC")
    fun getAll(): Flow<List<Material>>

    @Query("SELECT * FROM materials_table WHERE materialId = :id")
    fun get(id: Int): Flow<Material>

    @Delete
    suspend fun delete(material: Material)

    @Query("DELETE FROM materials_table")
    suspend fun deleteAll()
}

class MaterialRepository(private val dao: MaterialDao) {
    fun init(scope: CoroutineScope) {
        initialMaterials.forEach {
            insert(it, scope)
        }
    }

    fun insert(item: Material, scope: CoroutineScope) =
        scope.launch {
            dao.insert(item)
        }

    fun update(item: Material, scope: CoroutineScope) = scope.launch {
        dao.update(item)
    }

    fun get(id: Int): Flow<Material?> = dao.get(id)
    fun getAll(): Flow<List<Material>> = dao.getAll()

    fun delete(item: Material, scope: CoroutineScope) = scope.launch {
        dao.delete(item)
    }

    fun deleteAll(scope: CoroutineScope) = scope.launch {
        dao.deleteAll()
    }
}

