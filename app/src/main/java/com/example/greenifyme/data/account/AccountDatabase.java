package com.example.greenifyme.data.account;


import static com.example.greenifyme.data.account.AccountKt.populateAccount;

import android.content.Context;

import com.example.greenifyme.data.Account;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Account.class}, version = 1, exportSchema = false)
public abstract class AccountDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile AccountDatabase INSTANCE;
    private static final Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                AccountDao dao = INSTANCE.accountDao();
                AccountJavaHandler myAsyncModule = new AccountJavaHandler(dao);
                myAsyncModule.deleteAll();

                // Populate the database in the background.
                for (Account object : populateAccount()) {
                    myAsyncModule.insert(object);
                }
            });
        }
    };

    public static AccountDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AccountDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AccountDatabase.class, "account_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract AccountDao accountDao();
}

