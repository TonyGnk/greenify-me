package com.example.greenifyme.data;

import static com.example.greenifyme.ApplicationSetupKt.getScope;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.greenifyme.data.material.Converters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {
        Account.class, Form.class, Track.class, Material.class
}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class NormalDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile NormalDatabase INSTANCE;

    private static final Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                GreenDao dao = INSTANCE.dao();
                GreenRepository repository = new GreenRepository(dao);
                repository.init(DataObjectType.MATERIAL, getScope());
                Log.d("GreenDatabase", "Normal Database initialized");
            });
        }
    };

    public static NormalDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NormalDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(), NormalDatabase.class, "greenifyme_normal_db"
                            )
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract GreenDao dao();
}