package com.example.greenifyme.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.greenifyme.data.Account
import com.example.greenifyme.data.Form
import com.example.greenifyme.data.OptionsType
import com.example.greenifyme.data.Pieces
import com.example.greenifyme.data.RecyclingCategory

data class AccountWithForm(
    @Embedded val account: Account,
    @Relation(
        parentColumn = "accountId",
        entityColumn = "accountId"
    )
    val forms: List<Form>
)

data class FormWithAccountName(
    val formId: Int,
    val accountId: Int,
    val hasAdminViewed: Boolean,
    val createdAt: Long,
    val accountName: String,
)


data class TrackWithMaterial(
    val trackId: Int = 0,
    val formId: Int,
    val materialId: Int,
    val quantity: Int,
    val category: RecyclingCategory = RecyclingCategory.OTHER,
    val name: String = "",
    val type: OptionsType = Pieces(0),
)