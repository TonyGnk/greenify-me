package com.example.greenifyme.data.account

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts_table")
data class Account(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    val password: String,
    val isAdmin: Boolean
)

fun populateAccount() = listOf(
    Account(0, "John Doe", "doe@ex.com", hashPassword("myNameIsBond"), false),
    Account(1, "Jane Smith", "smith@ex.com", hashPassword("password123"), false),
    Account(2, "Michael Johnson", "michaelj@ex.com", hashPassword("abc123"), false),
    Account(3, "Emily Brown", "emilyb@ex.com", hashPassword("securepassword"), false),
    Account(4, "David Wilson", "davidw@ex.com", hashPassword("password456"), false),
    Account(5, "Sarah Taylor", "saraht@ex.com", hashPassword("letmein"), false),
    Account(6, "James Martinez", "jamesm@ex.com", hashPassword("qwerty"), false),
    Account(7, "Jennifer Anderson", "janderson@ex.com", hashPassword("password"), false),
    Account(8, "Matthew Thomas", "mthomas@ex.com", hashPassword("matthew123"), false),
    Account(9, "Jessica Garcia", "jessicag@ex.com", hashPassword("hello123"), false),
    Account(10, "Christopher Lee", "chrisl@ex.com", hashPassword("iloveyou"), false),
    Account(11, "Amanda Lewis", "amandal@ex.com", hashPassword("password789"), false),
    Account(12, "Daniel Harris", "danielh@ex.com", hashPassword("daniel123"), false),
    Account(13, "Maria Martinez", "mariam@ex.com", hashPassword("welcome"), false),
    Account(14, "Robert Jackson", "robertj@ex.com", hashPassword("password"), false),
    Account(15, "Linda Clark", "lindac@ex.com", hashPassword("linda123"), false),
    Account(16, "Kevin Young", "keviny@ex.com", hashPassword("kevin123"), false),
    Account(17, "Patricia Hernandez", "patriciah@ex.com", hashPassword("password123"), false),
    Account(18, "Richard Allen", "richarda@ex.com", hashPassword("richard123"), false),
    Account(19, "Susan King", "susank@ex.com", hashPassword("susan123"), false),
    Account(20, "Mark Davis", "markd@ex.com", hashPassword("mark123"), false),
    Account(21, "Karen White", "karenw@ex.com", hashPassword("karen123"), false),
    Account(22, "Steven Thompson", "stevent@ex.com", hashPassword("steven123"), false),
    Account(23, "Laura Martinez", "lauram@ex.com", hashPassword("laura123"), false),
    Account(24, "Jason Robinson", "jasonr@ex.com", hashPassword("jason123"), false),
    Account(25, "Sandra Harris", "sandrah@ex.com", hashPassword("sandra123"), false),
    Account(26, "Timothy Young", "timothyy@ex.com", hashPassword("timothy123"), false),
    Account(27, "Cynthia Hall", "cynthiah@ex.com", hashPassword("cynthia123"), false),
    Account(28, "Gregory Nelson", "gregoryn@ex.com", hashPassword("gregory123"), false),
    Account(29, "Angela King", "angelak@ex.com", hashPassword("angela123"), false),
    Account(30, "Kenneth Martinez", "kennethm@ex.com", hashPassword("kenneth123"), false),
    Account(31, "Tiffany Brown", "tiffanyb@ex.com", hashPassword("tiffany123"), false),
    Account(32, "Bryan Scott", "bryans@ex.com", hashPassword("bryan123"), false),
    Account(33, "Pamela Lopez", "pamelal@ex.com", hashPassword("pamela123"), false),
    Account(34, "Peter Wright", "peterw@ex.com", hashPassword("peter123"), false),
    Account(35, "Deborah Carter", "deborahc@ex.com", hashPassword("deborah123"), false),
    Account(36, "Jeremy Baker", "jeremyb@ex.com", hashPassword("jeremy123"), false),
    Account(37, "Hannah Diaz", "hannahd@ex.com", hashPassword("hannah123"), false),
    Account(38, "Keith Ramirez", "keithr@ex.com", hashPassword("keith123"), false),
    Account(39, "Stephanie Reed", "stephanier@ex.com", hashPassword("stephanie123"), false)
)

