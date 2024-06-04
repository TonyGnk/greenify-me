package com.example.greenifyme.data.track

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.greenifyme.data.RecyclingCategory
import com.example.greenifyme.data.Track
import com.example.greenifyme.data.relations.CategoryQuantitySum
import com.example.greenifyme.data.relations.MaterialQuantitySum
import com.example.greenifyme.data.relations.TrackWithMaterial
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrack(track: Track)

    @Update
    suspend fun update(track: Track)

    @Delete
    suspend fun deleteTrack(track: Track)

    @Query("DELETE FROM tracks_table WHERE trackId = :id")
    suspend fun deleteTrack(id: Int)

    @Query("DELETE FROM tracks_table")
    suspend fun deleteTracks()

    @Query("SELECT * FROM tracks_table WHERE formId = :formId")
    fun getTrack(formId: Int): Flow<Track?>

    @Query("SELECT * FROM tracks_table")
    fun getTracks(): Flow<List<Track>>

    @Query("SELECT * FROM tracks_table WHERE formId = :formId")
    fun getTracks(formId: Int): Flow<List<Track>>

    @Transaction
    @Query(
        """
        SELECT t.trackId, t.formId, t.materialId, t.quantity, m.category, m.name, m.type
        FROM tracks_table t
        INNER JOIN materials_table m ON t.materialId = m.materialId
    """
    )
    fun getTracksWithMaterial(): Flow<List<TrackWithMaterial>>

    @Transaction
    @Query(
        """
        SELECT t.trackId, t.formId, t.materialId, t.quantity, m.category, m.name, m.type
        FROM tracks_table t
        INNER JOIN materials_table m ON t.materialId = m.materialId
        WHERE t.formId = :formId
    """
    )
    fun getTracksWithMaterial(formId: Int): Flow<List<TrackWithMaterial>>

    @Query(
        """
        SELECT m.category, SUM(t.quantity) as totalQuantity 
        FROM materials_table m
        JOIN tracks_table t ON m.materialId = t.materialId
        GROUP BY m.category
        """
    )
    fun getSumQuantityPerCategory(): Flow<List<CategoryQuantitySum>>

    @Query(
        """
        SELECT m.materialId, m.name, m.category, SUM(t.quantity) as totalQuantity
        FROM materials_table m
        JOIN tracks_table t ON m.materialId = t.materialId
        WHERE m.category = :category
        GROUP BY m.materialId, m.name, m.category
        """
    )
    fun getSumQuantityPerMaterialInCategory(category: RecyclingCategory): Flow<List<MaterialQuantitySum>>

}