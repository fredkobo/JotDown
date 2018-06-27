package za.co.fredkobo.jotdown.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import za.co.fredkobo.jotdown.model.JournalEntry;

/**
 * Created by F5094712 on 2018/06/26.
 */

@Database(entities = {JournalEntry.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class JotDownDatabase extends RoomDatabase {

    private static final String LOG_TAG = JotDownDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "journal_entry_db";
    private static JotDownDatabase dbInstance;

    public static JotDownDatabase getInstance(Context context) {
        if (dbInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                dbInstance = Room.databaseBuilder(context.getApplicationContext(),
                        JotDownDatabase.class, JotDownDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return dbInstance;
    }

    public abstract JournalEntryDao journalEntryDao();
}
