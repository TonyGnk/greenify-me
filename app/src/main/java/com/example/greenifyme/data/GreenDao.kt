package com.example.greenifyme.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.greenifyme.data.account.hashPassword
import com.example.greenifyme.data.account.initialAccounts
import com.example.greenifyme.data.form.initialForms
import com.example.greenifyme.data.material.initialMaterials
import com.example.greenifyme.data.relations.AccountWithForm
import com.example.greenifyme.data.relations.CategoryQuantitySum
import com.example.greenifyme.data.relations.FormWithAccountName
import com.example.greenifyme.data.relations.FormWithTracks
import com.example.greenifyme.data.relations.MaterialWithTracks
import com.example.greenifyme.data.relations.TrackWithMaterial
import com.example.greenifyme.data.relations.WinnerItem
import com.example.greenifyme.data.track.initialTracks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Dao
interface GreenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: Account)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForm(form: Form)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrack(track: Track)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(material: Material)


    //______________________________________________

    //find the latest id of all
    @Query("SELECT MAX(accountId) FROM accounts_table")
    fun getLatestAccountId(): Flow<Int>

    @Query("SELECT MAX(formId) FROM forms_table")
    fun getLatestFormId(): Flow<Int>

    @Query("SELECT MAX(trackId) FROM tracks_table")
    fun getLatestTrackId(): Flow<Int>

    @Query("SELECT MAX(materialId) FROM materials_table")
    fun getLatestMaterialId(): Flow<Int>

    //______________________________________________

    @Update
    suspend fun update(account: Account)


    @Update
    suspend fun update(form: Form)

    @Query("UPDATE forms_table SET hasAdminViewed = :hasAdminViewed WHERE formId = :formId")
    suspend fun updateFormHasAdminViewed(formId: Int, hasAdminViewed: Boolean)

    @Update
    suspend fun update(track: Track)


    @Update
    suspend fun update(material: Material)

    //______________________________________________

    @Query("SELECT * FROM accounts_table WHERE email = :email")
    fun getAccount(email: String): Account?

    @Query("SELECT * FROM accounts_table WHERE email = :email AND password = :hash LIMIT 1")
    fun getAccount(email: String, hash: String): Account?

    //______________________________________________

    @Query("SELECT * FROM accounts_table WHERE accountId = :id")
    fun getAccountFlow(id: Int): Flow<Account>

    @Query("SELECT * FROM accounts_table WHERE accountId = :id")
    fun getAccount(id: Int): Account?

    @Query("SELECT * FROM accounts_table WHERE email = :email")
    fun getAccountFlow(email: String): Flow<Account?>


    @Query("SELECT * from accounts_table ORDER BY accountId ASC")
    fun getAccounts(): Flow<List<Account>>

    @Query("SELECT * from accounts_table ORDER BY points DESC")
    fun getAccountsOrderByPoints(): Flow<List<Account>>

    @Query("SELECT name, points as totalPoints FROM accounts_table ORDER BY points DESC LIMIT 3")
    fun getTop3Accounts(): Flow<List<WinnerItem>>


    @Query("SELECT * FROM forms_table WHERE formId = :id")
    fun getForm(id: Int): Flow<Form>


    @Query("SELECT * from forms_table ORDER BY formId ASC")
    fun getForms(): Flow<List<Form>>

    @Transaction
    @Query("SELECT * FROM forms_table")
    fun getFormsWithTracks(): List<FormWithTracks>

    @Transaction
    @Query("SELECT * FROM forms_table WHERE formId = :formId")
    fun getFormWithTracks(formId: Int): Flow<FormWithTracks>


    @Query("SELECT MAX(formId) FROM forms_table")
    fun getFormLatestIndex(): Flow<Int>

    @Query("SELECT * FROM tracks_table WHERE formId = :formId")
    fun getTrack(formId: Int): Flow<Track>


    @Query("SELECT * FROM tracks_table")
    fun getTracks(): Flow<List<Track>>


    @Query("SELECT * FROM tracks_table WHERE formId = :formId")
    fun getTracks(formId: Int): Flow<List<Track>>


    @Query("SELECT * FROM materials_table WHERE materialId = :id")
    fun getMaterialFlow(id: Int): Flow<Material>

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
    @Query("SELECT * FROM accounts_table WHERE accountId = :accountId")
    fun getAccountWithForms(accountId: Int): List<AccountWithForm>


    @Query(
        """
        SELECT m.category, SUM(t.quantity) as totalQuantity 
        FROM materials_table m
        JOIN tracks_table t ON m.materialId = t.materialId
        GROUP BY m.category
    """
    )
    fun getSumQuantityPerCategory(): Flow<List<CategoryQuantitySum>>


    @Query("SELECT COUNT(*) FROM materials_table")
    fun getNumberOfMaterials(): Flow<Int>


    @Transaction
    @Query("SELECT * FROM Materials_table")
    fun getMaterialsWithTracks(): List<MaterialWithTracks>


    //getTotalPoints
    @Query("SELECT SUM(points) FROM accounts_table")
    fun getTotalPoints(): Flow<Int>

    @Transaction
    @Query(
        """
        SELECT forms_table.formId, forms_table.accountId, forms_table.hasAdminViewed, 
               forms_table.createdAt,forms_table.points, accounts_table.name AS accountName
        FROM forms_table
        INNER JOIN accounts_table ON forms_table.accountId = accounts_table.accountId
    """
    )
    fun getFormsWithAccountName(): Flow<List<FormWithAccountName>>

    @Transaction
    @Query(
        """
        SELECT t.trackId, t.formId, t.materialId, t.quantity, m.category, m.name, m.hasSubcategories, m.type
        FROM tracks_table t
        INNER JOIN materials_table m ON t.materialId = m.materialId
    """
    )
    fun getTracksWithMaterial(): Flow<List<TrackWithMaterial>>


    @Transaction
    @Query(
        """
        SELECT t.trackId, t.formId, t.materialId, t.quantity, m.category, m.name, m.hasSubcategories, m.type
        FROM tracks_table t
        INNER JOIN materials_table m ON t.materialId = m.materialId
        WHERE t.formId = :formId
    """
    )
    fun getTracksWithMaterial(formId: Int): Flow<List<TrackWithMaterial>>


    //______________________________________________

    @Delete
    suspend fun deleteAccount(account: Account)


    @Query("DELETE FROM accounts_table WHERE accountId = :id")
    suspend fun deleteAccount(id: Int)


    @Query("DELETE FROM accounts_table")
    suspend fun deleteAccounts()


    @Delete
    suspend fun deleteForm(form: Form)


    @Query("DELETE FROM forms_table WHERE formId = :id")
    suspend fun deleteForm(id: Int)


    @Query("DELETE FROM forms_table")
    suspend fun deleteForms()

    @Delete
    suspend fun deleteTrack(track: Track)

    @Query("DELETE FROM tracks_table WHERE trackId = :id")
    suspend fun deleteTrack(id: Int)

    @Query("DELETE FROM tracks_table")
    suspend fun deleteTracks()


    @Delete
    suspend fun deleteMaterial(material: Material)


    @Query("DELETE FROM materials_table WHERE materialId = :id")
    suspend fun deleteMaterial(id: Int)


    @Query("DELETE FROM materials_table")
    suspend fun deleteMaterials()


    @Query("DELETE FROM forms_table WHERE accountId = :accountId")
    suspend fun deleteFormsByAccountId(accountId: Int)
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

    fun getLatestId(type: DataObjectType): Flow<Int> = when (type) {
        DataObjectType.ACCOUNT -> dao.getLatestAccountId()
        DataObjectType.FORM -> dao.getLatestFormId()
        DataObjectType.TRACK -> dao.getLatestTrackId()
        DataObjectType.MATERIAL -> dao.getLatestMaterialId()
    }

    fun update(item: DataObject, scope: CoroutineScope) = scope.launch {
        when (item) {
            is Account -> dao.update(item)
            is Form -> dao.update(item)
            is Track -> dao.update(item)
            is Material -> dao.update(item)
        }
    }

    fun updateFormHasAdminViewed(formId: Int, hasAdminViewed: Boolean, scope: CoroutineScope) =
        scope.launch {
            dao.updateFormHasAdminViewed(formId, hasAdminViewed)
        }

    fun getAccount(email: String): Account? = dao.getAccount(email)
    fun getAccount(email: String, password: String): Account? =
        dao.getAccount(email, hashPassword(password))

    fun getAccountFlow(id: Int): Flow<Account> = dao.getAccountFlow(id)

    fun getAccount(id: Int): Account? = dao.getAccount(id)
    fun getAccounts(): Flow<List<Account>> = dao.getAccounts()

    fun getAccountsOrderByPoints(): Flow<List<Account>> = dao.getAccountsOrderByPoints()

    fun getTop3Accounts(): Flow<List<WinnerItem>> = dao.getTop3Accounts()
    fun getForm(id: Int): Flow<Form?> = dao.getForm(id)
    fun getForms(): Flow<List<Form>> = dao.getForms()

    fun getFormsWithAccountName(): Flow<List<FormWithAccountName>> = dao.getFormsWithAccountName()

    fun getTracksWithMaterial(): Flow<List<TrackWithMaterial>> = dao.getTracksWithMaterial()

    fun getTracksWithMaterial(formId: Int): Flow<List<TrackWithMaterial>> =
        dao.getTracksWithMaterial(formId)

    fun getFormLatestIndex(): Flow<Int> = dao.getFormLatestIndex()

    fun getFormWithTracks(formId: Int): Flow<FormWithTracks> = dao.getFormWithTracks(formId)

    fun getTrack(formId: Int): Flow<Track?> = dao.getTrack(formId)

    fun getTracks(formId: Int? = null): Flow<List<Track>> {
        return if (formId == null) dao.getTracks()
        else dao.getTracks(formId)
    }

    fun getMaterialFlow(id: Int): Flow<Material?> = dao.getMaterialFlow(id)

    fun getMaterial(id: Int): Material? = dao.getMaterial(id)

    fun getMaterialCategory(id: Int): Flow<RecyclingCategory> = dao.getMaterialCategory(id)
    fun getMaterials(): Flow<List<Material>> = dao.getMaterials()
    fun getMaterialsOrderByCategory(): Flow<List<Material>> = dao.getMaterialsOrderByCategory()


    fun getMaterialsWith(category: RecyclingCategory): Flow<List<Material>> =
        dao.getMaterialsWith(category)


    fun getSumQuantityPerCategory(): Flow<List<CategoryQuantitySum>> =
        dao.getSumQuantityPerCategory()

    fun getTotalPoints(): Flow<Int> = dao.getTotalPoints()

    fun deleteWithId(type: DataObjectType, id: Int, scope: CoroutineScope) = scope.launch {
        when (type) {
            DataObjectType.ACCOUNT -> dao.deleteAccount(id)
            DataObjectType.FORM -> dao.deleteForm(id)
            DataObjectType.TRACK -> dao.deleteTrack(id)
            DataObjectType.MATERIAL -> dao.deleteMaterial(id)
        }
    }

    fun delete(item: DataObject, scope: CoroutineScope) = scope.launch {
        when (item) {
            is Account -> dao.deleteAccount(item)
            is Form -> dao.deleteForm(item)
            is Track -> dao.deleteTrack(item)
            is Material -> dao.deleteMaterial(item)
        }
    }

    fun deleteAll(type: DataObjectType, scope: CoroutineScope, alsoPopulate: Boolean = false) =
        scope.launch {
            when (type) {
                DataObjectType.ACCOUNT -> {
                    dao.deleteAccounts()
                    if (alsoPopulate) init(type, scope)
                }

                DataObjectType.FORM -> {
                    dao.deleteForms()
                    if (alsoPopulate) init(type, scope)
                }

                DataObjectType.TRACK -> {
                    dao.deleteTracks()
                    if (alsoPopulate) init(type, scope)
                }

                DataObjectType.MATERIAL -> {
                    dao.deleteMaterials()
                    if (alsoPopulate) init(type, scope)
                }
            }
        }

    fun getNumberOfMaterials(): Flow<Int> = dao.getNumberOfMaterials()
}