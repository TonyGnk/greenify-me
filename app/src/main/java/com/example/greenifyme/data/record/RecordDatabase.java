package com.example.greenifyme.data.record;

import static com.example.greenifyme.ApplicationSetupKt.getScope;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.greenifyme.data.Record;
import com.example.greenifyme.data.account.AccountDao;
import com.example.greenifyme.data.account.AccountRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Record.class}, version = 1, exportSchema = false)
public abstract class RecordDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile RecordDatabase INSTANCE;
    private static final Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                RecordDao dao = INSTANCE.recordDao();
                RecordRepository repository = new RecordRepository(dao);
                repository.init(getScope());
            });
        }
    };

    public static com.example.greenifyme.data.record.RecordDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (com.example.greenifyme.data.record.RecordDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    com.example.greenifyme.data.record.RecordDatabase.class, "record_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract RecordDao recordDao();
}


