package com.example.greenifyme.data.material

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.greenifyme.data.Material
import com.example.greenifyme.data.RecyclingCategory
import com.example.greenifyme.data.relations.MaterialWithTracks
import kotlinx.coroutines.flow.Flow

@Dao
interface MaterialDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(material: Material)

    @Update
    suspend fun update(material: Material)

    @Delete
    suspend fun deleteMaterial(material: Material)

    @Query("DELETE FROM materials_table WHERE materialId = :id")
    suspend fun deleteMaterial(id: Int)

    @Query("DELETE FROM materials_table")
    suspend fun deleteMaterials()

    @Query("SELECT * FROM materials_table WHERE materialId = :id")
    fun getMaterialFlow(id: Int): Flow<Material?>

    @Query("SELECT * FROM materials_table WHERE materialId = :id")
    fun getMaterial(id: Int): Material?

    @Query("SELECT category FROM materials_table WHERE materialId = :id")
    fun getMaterialCategory(id: Int): Flow<RecyclingCategory>

    @Query("SELECT * from materials_table ORDER BY materialId ASC")
    fun getMaterials(): Flow<List<Material>>

    @Query("SELECT * FROM materials_table ORDER BY category")
    fun getMaterialsOrderByCategory(): Flow<List<Material>>

    @Query("SELECT * FROM materials_table WHERE category = :category ORDER BY materialId ASC")
    fun getMaterialsWith(category: RecyclingCategory): Flow<List<Material>>

    @Transaction
    @Query("SELECT * FROM Materials_table")
    fun getMaterialsWithTracks(): List<MaterialWithTracks>

//    @Query(
//        """
//        SELECT m.category, SUM(t.quantity) as totalQuantity
//        FROM materials_table m
//        JOIN tracks_table t ON m.materialId = t.materialId
//        GROUP BY m.category
//    """
//    )
//    fun getSumQuantityPerCategory(): Flow<List<CategoryQuantitySum>>

    @Query("SELECT COUNT(*) FROM materials_table")
    fun getNumberOfMaterials(): Flow<Int>
}