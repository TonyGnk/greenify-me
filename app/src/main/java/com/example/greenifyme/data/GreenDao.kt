package com.example.greenifyme.data

import androidx.room.*
import com.example.greenifyme.data.account.*
import com.example.greenifyme.data.form.*
import com.example.greenifyme.data.material.*
import com.example.greenifyme.data.relations.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@Dao
interface GreenDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAccount(account : Account)



	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertForm(form : Form)



	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertEntry(material : Material)



	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertFormMaterialCrossRef(crossRef : FormMaterialCrossRef)

	//______________________________________________

	@Update
	suspend fun update(account : Account)



	@Update
	suspend fun update(form : Form)



	@Update
	suspend fun update(material : Material)

	//______________________________________________

	@Query("SELECT * FROM accounts_table WHERE email = :email")
	fun accountExists(email : String) : Boolean



	@Query("SELECT * FROM accounts_table WHERE email = :email AND password = :hash")
	fun accountExists(email : String, hash : String) : Boolean

	//______________________________________________

	@Query("SELECT * FROM accounts_table WHERE id = :id")
	fun getAccount(id : Int) : Flow<Account>



	@Query("SELECT * from accounts_table ORDER BY id ASC")
	fun getAccounts() : Flow<List<Account>>



	@Query("SELECT * FROM forms_table WHERE formId = :id")
	fun getForm(id : Int) : Flow<Form>



	@Query("SELECT * from forms_table ORDER BY formId ASC")
	fun getForms() : Flow<List<Form>>



	// getFormLatestIndex
	@Query("SELECT MAX(formId) FROM forms_table")
	fun getFormLatestIndex() : Flow<Int>



	@Query("SELECT * FROM materials_table WHERE materialId = :id")
	fun getMaterial(id : Int) : Flow<Material>



	@Query("SELECT * from materials_table ORDER BY materialId ASC")
	fun getMaterials() : Flow<List<Material>>



	@Query("SELECT * FROM materials_table WHERE category = :category ORDER BY materialId ASC")
	fun getMaterialsWith(category : RecyclingCategory) : Flow<List<Material>>



	//
	@Transaction
	@Query(
		"""
    SELECT m.name AS materialName
    FROM materials_table AS m
    INNER JOIN form_material_cross_ref AS c ON m.materialId = c.materialId
    GROUP BY m.materialId
"""
	)
	fun getMaterialNames() : Flow<List<String>>



	@Transaction
	@Query("SELECT * FROM accounts_table WHERE id = :accountId")
	fun getAccountWithForms(accountId : Int) : List<AccountWithForm>



	@Transaction
	@Query("SELECT SUM(quantity) FROM form_material_cross_ref WHERE materialId = :materialId ORDER BY materialId ASC")
	fun getQuantityForMaterial(materialId : Int) : Flow<Int>



	@Query("SELECT SUM(quantity) AS quantity FROM form_material_cross_ref GROUP BY materialId")
	fun getQuantityForAllMaterials() : Flow<List<Int>>

	//______________________________________________

	@Delete
	suspend fun deleteAccount(account : Account)



	@Query("DELETE FROM accounts_table WHERE id = :id")
	suspend fun deleteAccount(id : Int)



	@Query("DELETE FROM accounts_table")
	suspend fun deleteAccounts()



	@Delete
	suspend fun deleteForm(form : Form)



	@Query("DELETE FROM forms_table WHERE formId = :id")
	suspend fun deleteForm(id : Int)



	@Query("DELETE FROM forms_table")
	suspend fun deleteForms()



	@Delete
	suspend fun deleteMaterial(material : Material)



	@Query("DELETE FROM materials_table WHERE materialId = :id")
	suspend fun deleteMaterial(id : Int)



	@Query("DELETE FROM materials_table")
	suspend fun deleteMaterials()



	@Query("DELETE FROM forms_table WHERE accountId = :accountId")
	suspend fun deleteFormsByAccountId(accountId : Int)
}

class GreenRepository(private val dao : GreenDao) {
	fun init(type : DataObjectType, scope : CoroutineScope) {
		when (type) {
			DataObjectType.ACCOUNT -> initialAccounts
			DataObjectType.FORM -> initialForms
			DataObjectType.MATERIAL -> initialMaterials
		}.forEach { insert(it, scope) }

		// Add relationships cross
		val crossRefs = listOf(
			FormMaterialCrossRef(1, 1, 1),
			FormMaterialCrossRef(1, 2, 5),
			FormMaterialCrossRef(1, 3, 30),
			FormMaterialCrossRef(1, 4, 1),
			FormMaterialCrossRef(5, 5, 11),
			FormMaterialCrossRef(6, 3, 1),
			FormMaterialCrossRef(7, 4, 4),
			FormMaterialCrossRef(8, 2, 6),
			FormMaterialCrossRef(9, 1, 2),
			FormMaterialCrossRef(10, 5, 1),
			FormMaterialCrossRef(11, 6, 22),
			FormMaterialCrossRef(12, 9, 2),
			FormMaterialCrossRef(13, 8, 1),
			FormMaterialCrossRef(14, 7, 22),
		)
		crossRefs.forEach { insertCrossRef(it, scope) }
	}

	fun insert(item : DataObject, scope : CoroutineScope) =
		scope.launch {
			when (item) {
				is Account -> dao.insertAccount(item)
				is Form -> dao.insertForm(item)
				is Material -> dao.insertEntry(item)
			}
		}

	fun insertCrossRef(crossRef : FormMaterialCrossRef, scope : CoroutineScope) =
		scope.launch {
			dao.insertFormMaterialCrossRef(crossRef)
		}

	fun update(item : DataObject, scope : CoroutineScope) = scope.launch {
		when (item) {
			is Account -> dao.update(item)
			is Form -> dao.update(item)
			is Material -> dao.update(item)
		}
	}

	fun accountExists(email : String) : Boolean = dao.accountExists(email)
	fun accountExists(email : String, password : String) : Boolean =
		dao.accountExists(email, hashPassword(password))

	fun getAccount(id : Int) : Flow<Account?> = dao.getAccount(id)
	fun getAccounts() : Flow<List<Account>> = dao.getAccounts()
	fun getForm(id : Int) : Flow<Form?> = dao.getForm(id)
	fun getForms() : Flow<List<Form>> = dao.getForms()
	fun getFormLatestIndex() : Flow<Int> = dao.getFormLatestIndex()
	fun getMaterial(id : Int) : Flow<Material?> = dao.getMaterial(id)
	fun getMaterials() : Flow<List<Material>> = dao.getMaterials()
	fun getMaterialsWith(category : RecyclingCategory) : Flow<List<Material>> =
		dao.getMaterialsWith(category)

	fun getMaterialNames() : Flow<List<String>> = dao.getMaterialNames()
	fun getSumsForMaterial(materialId : Int) : Flow<Int> = dao.getQuantityForMaterial(materialId)
	fun getQuantityForAllMaterials() : Flow<List<Int>> = dao.getQuantityForAllMaterials()
	fun deleteWithId(type : DataObjectType, id : Int, scope : CoroutineScope) =
		scope.launch {
			when (type) {
				DataObjectType.ACCOUNT -> dao.deleteAccount(id)
				DataObjectType.FORM -> dao.deleteForm(id)
				DataObjectType.MATERIAL -> dao.deleteMaterial(id)
			}
		}

	fun delete(item : DataObject, scope : CoroutineScope) = scope.launch {
		when (item) {
			is Account -> {
				dao.deleteFormsByAccountId(item.id)
				dao.deleteAccount(item)
			}

			is Form -> dao.deleteForm(item)
			is Material -> dao.deleteMaterial(item)
		}
	}

	fun deleteAll(type : DataObjectType, scope : CoroutineScope, alsoPopulate : Boolean = false) =
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

				DataObjectType.MATERIAL -> {
					dao.deleteMaterials()
					if (alsoPopulate) init(type, scope)
				}
			}
		}
}