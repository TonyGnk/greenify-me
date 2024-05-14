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
import com.example.greenifyme.data.material.initialMaterials
import com.example.greenifyme.data.record.initialRecords
import com.example.greenifyme.data.relations.AccountWithRecords
import com.example.greenifyme.data.relations.RecordMaterialCrossRef
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Dao
interface GreenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: Account)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: Record)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(material: Material)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecordMaterialCrossRef(crossRef: RecordMaterialCrossRef)

    //______________________________________________

    @Update
    suspend fun update(account: Account)

    @Update
    suspend fun update(record: Record)

    @Update
    suspend fun update(material: Material)

    //______________________________________________

    @Query("SELECT * FROM accounts_table WHERE email = :email")
    fun accountExists(email: String): Boolean

    @Query("SELECT * FROM accounts_table WHERE email = :email AND password = :hash")
    fun accountExists(email: String, hash: String): Boolean

    //______________________________________________

    @Query("SELECT * FROM accounts_table WHERE id = :id")
    fun getAccount(id: Int): Flow<Account>

    @Query("SELECT * from accounts_table ORDER BY id ASC")
    fun getAccounts(): Flow<List<Account>>

    @Query("SELECT * FROM records_table WHERE recordId = :id")
    fun getRecord(id: Int): Flow<Record>

    @Query("SELECT * from records_table ORDER BY recordId ASC")
    fun getRecords(): Flow<List<Record>>

    @Query("SELECT * FROM materials_table WHERE materialId = :id")
    fun getMaterial(id: Int): Flow<Material>

    @Query("SELECT * from materials_table ORDER BY materialId ASC")
    fun getMaterials(): Flow<List<Material>>

    @Transaction
    @Query("SELECT * FROM accounts_table WHERE id = :accountId")
    fun getAccountWithRecords(accountId: Int): List<AccountWithRecords>

    //______________________________________________

    @Delete
    suspend fun deleteAccount(account: Account)

    @Query("DELETE FROM accounts_table")
    suspend fun deleteAccounts()

    @Delete
    suspend fun deleteRecord(record: Record)

    @Query("DELETE FROM records_table")
    suspend fun deleteRecords()

    @Delete
    suspend fun deleteMaterial(material: Material)

    @Query("DELETE FROM materials_table")
    suspend fun deleteMaterials()

    @Query("DELETE FROM records_table WHERE accountId = :accountId")
    suspend fun deleteRecordsByAccountId(accountId: Int)
}

class GreenRepository(private val dao: GreenDao) {
    fun init(type: DataObjectType, scope: CoroutineScope) {
        when (type) {
            DataObjectType.ACCOUNT -> initialAccounts
            DataObjectType.RECORD -> initialRecords
            DataObjectType.MATERIAL -> initialMaterials
        }.forEach { insert(it, scope) }

        //Add relationships cross
        val crossRefs = listOf(
            RecordMaterialCrossRef(1, 1, 1),
            RecordMaterialCrossRef(2, 1, 1),
            RecordMaterialCrossRef(3, 1, 1),
            RecordMaterialCrossRef(4, 1, 1),
            RecordMaterialCrossRef(5, 1, 1),
            RecordMaterialCrossRef(6, 1, 1),
            RecordMaterialCrossRef(7, 1, 1),
            RecordMaterialCrossRef(8, 1, 1),
        )
        crossRefs.forEach { insertCrossRef(it, scope) }
    }

    fun insert(item: DataObject, scope: CoroutineScope) =
        scope.launch {
            when (item) {
                is Account -> dao.insertAccount(item)
                is Record -> dao.insertRecord(item)
                is Material -> dao.insertEntry(item)
            }
        }

    fun insertCrossRef(crossRef: RecordMaterialCrossRef, scope: CoroutineScope) =
        scope.launch {
            dao.insertRecordMaterialCrossRef(crossRef)
        }

    fun update(item: DataObject, scope: CoroutineScope) = scope.launch {
        when (item) {
            is Account -> dao.update(item)
            is Record -> dao.update(item)
            is Material -> dao.update(item)
        }
    }

    fun accountExists(email: String): Boolean = dao.accountExists(email)

    fun accountExists(email: String, password: String): Boolean =
        dao.accountExists(email, hashPassword(password))

    fun getAccount(id: Int): Flow<Account?> = dao.getAccount(id)
    fun getAccounts(): Flow<List<Account>> = dao.getAccounts()

    fun getRecord(id: Int): Flow<Record?> = dao.getRecord(id)
    fun getRecords(): Flow<List<Record>> = dao.getRecords()

    fun getMaterial(id: Int): Flow<Material?> = dao.getMaterial(id)
    fun getMaterials(): Flow<List<Material>> = dao.getMaterials()

    fun delete(item: DataObject, scope: CoroutineScope) = scope.launch {
        when (item) {
            is Account -> {
                dao.deleteRecordsByAccountId(item.id)
                dao.deleteAccount(item)
            }

            is Record -> dao.deleteRecord(item)
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

                DataObjectType.RECORD -> {
                    dao.deleteRecords()
                    if (alsoPopulate) init(type, scope)
                }

                DataObjectType.MATERIAL -> {
                    dao.deleteMaterials()
                    if (alsoPopulate) init(type, scope)
                }
            }
        }
}