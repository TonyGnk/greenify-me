package com.example.greenifyme.data.account

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.greenifyme.data.Account
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(account: Account)

    @Update
    suspend fun update(account: Account)

    @Query("DELETE FROM accounts_table")
    suspend fun deleteAll()

    @Query("SELECT * from accounts_table ORDER BY id ASC")
    fun getAll(): Flow<List<Account>>

    @Query("SELECT * FROM accounts_table WHERE id = :id")
    fun get(id: Int): Flow<Account>

    @Delete
    suspend fun delete(account: Account)
}

class AccountRepository(private val dao: AccountDao) {

    fun init(scope: CoroutineScope) {
        initialAccounts.forEach {
            insert(it, scope)
        }
    }

    fun insert(account: Account, scope: CoroutineScope) =
        scope.launch {
            dao.insert(account)
        }

    fun update(account: Account, scope: CoroutineScope) = scope.launch {
        dao.update(account)
    }

    fun delete(account: Account, scope: CoroutineScope) = scope.launch {
        dao.delete(account)
    }

    fun get(id: Int): Flow<Account?> = dao.get(id)
    fun getAll(): Flow<List<Account>> = dao.getAll()
}

private val initialAccounts = listOf(
    Account(1, "John Doe", "doe@ex.com", hashPassword("myNameIsBond"), false),
    Account(2, "Jane Smith", "smith@ex.com", hashPassword("password123"), false),
    Account(3, "Michael Johnson", "michaelj@ex.com", hashPassword("abc123"), false),
    Account(4, "Emily Brown", "emilyb@ex.com", hashPassword("securepassword"), false),
    Account(5, "David Wilson", "davidw@ex.com", hashPassword("password456"), false),
    Account(6, "Sarah Taylor", "saraht@ex.com", hashPassword("letmein"), false),
    Account(7, "James Martinez", "jamesm@ex.com", hashPassword("qwerty"), false),
    Account(8, "Jennifer Anderson", "janderson@ex.com", hashPassword("password"), false),
    Account(9, "Matthew Thomas", "mthomas@ex.com", hashPassword("matthew123"), false),
    Account(10, "Jessica Garcia", "jessicag@ex.com", hashPassword("hello123"), false),
    Account(11, "Christopher Lee", "chrisl@ex.com", hashPassword("iloveyou"), false),
    Account(12, "Amanda Lewis", "amandal@ex.com", hashPassword("password789"), false),
    Account(13, "Daniel Harris", "danielh@ex.com", hashPassword("daniel123"), false),
    Account(14, "Maria Martinez", "mariam@ex.com", hashPassword("welcome"), false),
    Account(15, "Robert Jackson", "robertj@ex.com", hashPassword("password"), false),
    Account(16, "Linda Clark", "lindac@ex.com", hashPassword("linda123"), false),
    Account(17, "Kevin Young", "keviny@ex.com", hashPassword("kevin123"), false),
    Account(18, "Patricia Hernandez", "patriciah@ex.com", hashPassword("pass123"), false),
    Account(19, "Richard Allen", "richarda@ex.com", hashPassword("richard123"), false),
    Account(20, "Susan King", "susank@ex.com", hashPassword("susan123"), false),
    Account(21, "Mark Davis", "markd@ex.com", hashPassword("mark123"), false),
    Account(22, "Karen White", "karenw@ex.com", hashPassword("karen123"), false),
    Account(23, "Steven Thompson", "stevent@ex.com", hashPassword("steven123"), false),
    Account(24, "Laura Martinez", "lauram@ex.com", hashPassword("laura123"), false),
    Account(25, "Jason Robinson", "jasonr@ex.com", hashPassword("jason123"), false),
    Account(26, "Sandra Harris", "sandrah@ex.com", hashPassword("sandra123"), false),
    Account(27, "Timothy Young", "timothyy@ex.com", hashPassword("timothy123"), false),
    Account(28, "Cynthia Hall", "cynthiah@ex.com", hashPassword("cynthia123"), false),
    Account(29, "Gregory Nelson", "gregoryn@ex.com", hashPassword("gregory123"), false),
    Account(30, "Angela King", "angelak@ex.com", hashPassword("angela123"), false),
    Account(31, "Kenneth Martinez", "kennethm@ex.com", hashPassword("kenneth123"), false),
    Account(32, "Tiffany Brown", "tiffanyb@ex.com", hashPassword("tiffany123"), false),
    Account(33, "Bryan Scott", "bryans@ex.com", hashPassword("bryan123"), false),
    Account(34, "Pamela Lopez", "pamelal@ex.com", hashPassword("pamela123"), false),
    Account(35, "Peter Wright", "peterw@ex.com", hashPassword("peter123"), false),
    Account(36, "Deborah Carter", "deborahc@ex.com", hashPassword("deborah123"), false),
    Account(37, "Jeremy Baker", "jeremyb@ex.com", hashPassword("jeremy123"), false),
    Account(38, "Hannah Diaz", "hannahd@ex.com", hashPassword("hannah123"), false),
    Account(39, "Keith Ramirez", "keithr@ex.com", hashPassword("keith123"), false),
    Account(40, "Stephanie Reed", "stephanier@ex.com", hashPassword("stephanie123"), false)
)