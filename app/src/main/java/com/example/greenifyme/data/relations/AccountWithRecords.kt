package com.example.greenifyme.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.greenifyme.data.Account
import com.example.greenifyme.data.Record

data class AccountWithRecords(
    @Embedded val account: Account,
    @Relation(
        parentColumn = "id",
        entityColumn = "accountId"
    )
    val students: List<Record>
)