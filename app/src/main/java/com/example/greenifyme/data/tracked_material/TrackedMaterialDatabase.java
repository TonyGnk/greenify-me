package com.example.greenifyme.data.tracked_material;


import static com.example.greenifyme.data.tracked_material.TrackedMaterialKt.populateTrackedMaterial;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.greenifyme.data.TrackedMaterial;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {TrackedMaterial.class}, version = 1, exportSchema = false)
public abstract class TrackedMaterialDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile TrackedMaterialDatabase INSTANCE;
    private static final Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                TrackedMaterialDao dao = INSTANCE.trackedMaterialDao();
                TrackedMaterialJavaHandler myAsyncModule = new TrackedMaterialJavaHandler(dao);
                myAsyncModule.deleteAll();

                //Populate the database in the background.
                for (TrackedMaterial object : populateTrackedMaterial()) {
                    myAsyncModule.insert(object);
                }
            });
        }
    };

    public static TrackedMaterialDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TrackedMaterialDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TrackedMaterialDatabase.class, "tracked_material_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract TrackedMaterialDao trackedMaterialDao();
}

