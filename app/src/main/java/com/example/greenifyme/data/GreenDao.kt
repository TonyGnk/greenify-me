package com.example.greenifyme.data

import androidx.room.Dao
import androidx.room.Query
import com.example.greenifyme.data.account.AccountDao
import com.example.greenifyme.data.account.initialAccounts
import com.example.greenifyme.data.form.FormDao
import com.example.greenifyme.data.form.initialForms
import com.example.greenifyme.data.material.MaterialDao
import com.example.greenifyme.data.material.initialMaterials
import com.example.greenifyme.data.relations.CategoryQuantitySum
import com.example.greenifyme.data.relations.FormWithAccountName
import com.example.greenifyme.data.relations.FormWithTracks
import com.example.greenifyme.data.relations.TrackWithMaterial
import com.example.greenifyme.data.relations.WinnerItem
import com.example.greenifyme.data.track.TrackDao
import com.example.greenifyme.data.track.initialTracks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


@Dao
interface GreenDao : AccountDao, FormDao, TrackDao, MaterialDao {
    @Query("SELECT MAX(accountId) FROM accounts_table")
    fun getLatestAccountId(): Flow<Int>

    @Query("SELECT MAX(formId) FROM forms_table")
    fun getLatestFormId(): Flow<Int>

    @Query("SELECT MAX(trackId) FROM tracks_table")
    fun getLatestTrackId(): Flow<Int>

    @Query("SELECT MAX(materialId) FROM materials_table")
    fun getLatestMaterialId(): Flow<Int>
}


class GreenRepository(private val dao: GreenDao) {
    fun init(type: DataObjectType, scope: CoroutineScope) {
        when (type) {
            DataObjectType.ACCOUNT -> initialAccounts
            DataObjectType.FORM -> initialForms
            DataObjectType.TRACK -> initialTracks
            DataObjectType.MATERIAL -> initialMaterials
        }.forEach { insert(it, scope) }
    }

    fun insert(item: DataObject, scope: CoroutineScope) = scope.launch {
        when (item) {
            is Account -> dao.insertAccount(item)
            is Form -> dao.insertForm(item)
            is Track -> dao.insertTrack(item)
            is Material -> dao.insertEntry(item)
        }
    }


    fun update(item: DataObject, scope: CoroutineScope) = scope.launch {
        when (item) {
            is Account -> dao.update(item)
            is Form -> dao.update(item)
            is Track -> dao.update(item)
            is Material -> dao.update(item)
        }
    }

    fun updateFormHasAdminViewed(
        formId: Int, hasAdminViewed: Boolean, scope: CoroutineScope
    ) = scope.launch {
        dao.updateFormHasAdminViewed(formId, hasAdminViewed)
    }

    fun getAccount(accountId: Int): Flow<Account?> = dao.getAccountFlow(accountId)

    fun getAccount(email: String): Account? = dao.getAccount(email)

    fun getAccounts(): Flow<List<Account>> = dao.getAccounts()

    fun getAccountsOrderByPoints(): Flow<List<Account>> = dao.getAccountsOrderByPoints()

    fun getTop3Accounts(): Flow<List<WinnerItem>> = dao.getTop3Accounts()
    fun getForms(): Flow<List<Form>> = dao.getForms()

    fun getFormsWithAccountName(): Flow<List<FormWithAccountName>> = dao.getFormsWithAccountName()

    fun getTracksWithMaterial(formId: Int): Flow<List<TrackWithMaterial>> =
        dao.getTracksWithMaterial(formId)

    fun getAccountLatestIndex() = dao.getLatestAccountId()

    fun getFormLatestIndex(): Flow<Int?> = dao.getFormLatestIndex()

    fun getFormWithTracks(formId: Int): Flow<FormWithTracks> = dao.getFormWithTracks(formId)

    fun getTracks(formId: Int? = null): Flow<List<Track>> {
        return if (formId == null) dao.getTracks()
        else dao.getTracks(formId)
    }

    fun getMaterials(): Flow<List<Material>> = dao.getMaterials()
    fun getMaterialsOrderByCategory(): Flow<List<Material>> = dao.getMaterialsOrderByCategory()


    fun getMaterialsWith(category: RecyclingCategory): Flow<List<Material>> =
        dao.getMaterialsWith(category)

    fun getSumQuantityPerCategory(): Flow<List<CategoryQuantitySum>> =
        dao.getSumQuantityPerCategory()


    fun getSumQuantityPerMaterialInCategory(category: RecyclingCategory) =
        dao.getSumQuantityPerMaterialInCategory(category)

    fun getTotalPoints(): Flow<Int> = dao.getTotalPoints()

    fun delete(item: DataObject, scope: CoroutineScope) = scope.launch {
        when (item) {
            is Account -> dao.deleteAccount(item)
            is Form -> dao.deleteForm(item)
            is Track -> dao.deleteTrack(item)
            is Material -> dao.deleteMaterial(item)
        }
    }

    fun deleteAll(scope: CoroutineScope) = scope.launch {
        dao.deleteTracks()
        dao.deleteForms()
        dao.deleteAccounts()
        dao.deleteMaterials()
    }
}