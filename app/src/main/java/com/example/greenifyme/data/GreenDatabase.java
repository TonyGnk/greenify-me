package com.example.greenifyme.data;

import static com.example.greenifyme.ApplicationSetupKt.getScope;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.greenifyme.data.relations.RecordMaterialCrossRef;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Account.class, Record.class, Material.class, RecordMaterialCrossRef.class}, version = 1, exportSchema = false)
public abstract class GreenDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile GreenDatabase INSTANCE;
    private static final Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                GreenDao dao = INSTANCE.dao();
                GreenRepository repository = new GreenRepository(dao);
                for (DataObjectType type : DataObjectType.getEntries()) {
                    repository.init(type, getScope());
                }
            });
        }
    };

    public static GreenDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GreenDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    GreenDatabase.class, "greenifyme_db")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract GreenDao dao();
}

