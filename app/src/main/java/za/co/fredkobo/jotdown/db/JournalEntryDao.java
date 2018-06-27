package za.co.fredkobo.jotdown.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import za.co.fredkobo.jotdown.model.JournalEntry;

/**
 * Created by F5094712 on 2018/06/26.
 */
@Dao
public interface JournalEntryDao {
    @Query("SELECT * FROM journal_entry")
    LiveData<List<JournalEntry>> getAllEntries();

    @Insert
    void insertEntry(JournalEntry journalEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateEntry(JournalEntry journalEntry);

    @Delete
    void deleteEntry(JournalEntry journalEntry);

    @Query("SELECT * FROM journal_entry WHERE id = :id")
    LiveData<JournalEntry> loadJournalEntryById(int id);
}
