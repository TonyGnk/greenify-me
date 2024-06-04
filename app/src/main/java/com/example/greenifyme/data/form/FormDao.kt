package com.example.greenifyme.data.form

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.greenifyme.data.Form
import com.example.greenifyme.data.relations.FormWithAccountName
import com.example.greenifyme.data.relations.FormWithTracks
import kotlinx.coroutines.flow.Flow

@Dao
interface FormDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForm(form: Form)

    @Update
    suspend fun update(form: Form)

    @Delete
    suspend fun deleteForm(form: Form)

    @Query("DELETE FROM forms_table WHERE formId = :id")
    suspend fun deleteForm(id: Int)

    @Query("DELETE FROM forms_table")
    suspend fun deleteForms()

    @Query("UPDATE forms_table SET hasAdminViewed = :hasAdminViewed WHERE formId = :formId")
    suspend fun updateFormHasAdminViewed(formId: Int, hasAdminViewed: Boolean)

    @Query("SELECT * FROM forms_table WHERE formId = :id")
    fun getForm(id: Int): Flow<Form?>

    @Query("SELECT * from forms_table ORDER BY formId ASC")
    fun getForms(): Flow<List<Form>>

    @Transaction
    @Query("SELECT * FROM forms_table WHERE formId = :formId")
    fun getFormWithTracks(formId: Int): Flow<FormWithTracks>

    @Transaction
    @Query("SELECT * FROM forms_table")
    fun getFormsWithTracks(): List<FormWithTracks>

    @Transaction
    @Query(
        """
        SELECT forms_table.formId, forms_table.accountId, forms_table.hasAdminViewed, 
               forms_table.createdAt, accounts_table.name AS accountName
        FROM forms_table
        INNER JOIN accounts_table ON forms_table.accountId = accounts_table.accountId
    """
    )
    fun getFormsWithAccountName(): Flow<List<FormWithAccountName>>

    @Query("SELECT MAX(formId) FROM forms_table")
    fun getFormLatestIndex(): Flow<Int?>
}