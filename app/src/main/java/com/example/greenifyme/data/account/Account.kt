package com.example.greenifyme.data.account

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts_table")
data class Account(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val lastName: String,
    val email: String,
    val password: ByteArray, //Ignore this type for now
    val isAdmin: Boolean
)

fun populateAccount(): List<Account> = listOf(
    Account(
        name = "John",
        lastName = "Doe",
        email = "doe@gmail.com",
        password = byteArrayOf(1, 2, 3),
        isAdmin = true
    ),
    Account(
        name = "John",
        lastName = "Doe",
        email = "doe@gmail.com",
        password = byteArrayOf(1, 2, 3),
        isAdmin = false
    ),
)