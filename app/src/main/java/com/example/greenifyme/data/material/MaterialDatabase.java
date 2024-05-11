package com.example.greenifyme.data.material;


import static com.example.greenifyme.data.material.MaterialKt.populateMaterial;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.greenifyme.data.Material;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Material.class}, version = 1, exportSchema = false)
public abstract class MaterialDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile MaterialDatabase INSTANCE;
    private static final Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                MaterialDao dao = INSTANCE.materialDao();
                MaterialJavaHandler myAsyncModule = new MaterialJavaHandler(dao);
                myAsyncModule.deleteAll();

                // Populate the database in the background.
                for (Material object : populateMaterial()) {
                    myAsyncModule.insert(object);
                }
            });
        }
    };

    public static MaterialDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MaterialDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MaterialDatabase.class, "material_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract MaterialDao materialDao();
}

