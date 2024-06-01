package com.example.greenifyme.data;

import static com.example.greenifyme.ApplicationSetupKt.getScope;

import android.content.Context;

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
				repository.init(DataObjectType.ACCOUNT, getScope());
				repository.init(DataObjectType.FORM, getScope());
				repository.init(DataObjectType.MATERIAL, getScope());
				repository.init(DataObjectType.TRACK, getScope());
			});
		}
	};

	public static GreenDatabase getDatabase(final Context context) {
		if (INSTANCE == null) {
			synchronized (GreenDatabase.class) {
				if (INSTANCE == null) {
					INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
					                                GreenDatabase.class,
					                                "greenifyme_db"
							)
							.addCallback(sRoomDatabaseCallback)
							//.allowMainThreadQueries()
							.build() // allowMainThreadQueries() is a
                    // TEMPORARY FIX until i can think of a more lazy umm
                    // efficient way to do it
					;
				}
			}
		}
		return INSTANCE;
	}

	public abstract GreenDao dao();
}