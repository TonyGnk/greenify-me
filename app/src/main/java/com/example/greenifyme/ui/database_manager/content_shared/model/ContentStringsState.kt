package com.example.greenifyme.ui.database_manager.content_shared.model

import com.example.greenifyme.R

sealed class ContentStringsState {
	abstract val fabText : Int
	abstract val emptyDatabase : Int
	val deleteAll : Int = R.string.db_manager_delete_all
	val reset : Int = R.string.db_manager_reset
	abstract val searchPlaceHolder : Int
	val closeSearch : Int = R.string.db_manager_close_search
	val nameField : Int = R.string.db_manager_name_field
	val accountId : Int = R.string.db_manager_account_id_field

	val hasAdminViewed : Int = R.string.db_manager_has_admin_viewed
	val save : Int = R.string.db_manager_save
}

data class AccountStringsState(
	override val fabText : Int = R.string.db_manager_fab_text_account,
	override val emptyDatabase : Int = R.string.db_manager_empty_account,
	override val searchPlaceHolder : Int = R.string.db_manager_search_placeholder_account
) : ContentStringsState()

data class RecordStringsState(
	override val fabText : Int = R.string.db_manager_fab_text_form,
	override val emptyDatabase : Int = R.string.db_manager_empty_form,
	override val searchPlaceHolder : Int = R.string.db_manager_search_placeholder_form
) : ContentStringsState()

data class MaterialStringsState(
	override val fabText : Int = R.string.db_manager_fab_text_material,
	override val emptyDatabase : Int = R.string.db_manager_empty_material,
	override val searchPlaceHolder : Int = R.string.db_manager_search_placeholder_material
) : ContentStringsState()
