package com.example.greenifyme.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.greenifyme.data.Account
import com.example.greenifyme.data.Form

data class AccountWithForm(
    @Embedded val account: Account,
    @Relation(
        parentColumn = "accountId",
        entityColumn = "accountId"
    )
    val forms: List<Form>
)