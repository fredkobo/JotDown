package za.co.fredkobo.jotdown.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import za.co.fredkobo.jotdown.db.JotDownDatabase;
import za.co.fredkobo.jotdown.model.JournalEntry;

/**
 * Created by F5094712 on 2018/06/26.
 */

public class AddEntryViewModel extends ViewModel {
    // COMPLETED (6) Add a task member variable for the TaskEntry object wrapped in a LiveData
    private LiveData<JournalEntry> journalEntry;

    // COMPLETED (8) Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId
    public AddEntryViewModel(JotDownDatabase database, int entryId) {
        journalEntry = database.journalEntryDao().loadJournalEntryById(entryId);
    }

    // COMPLETED (7) Create a getter for the task variable
    public LiveData<JournalEntry> getJournalEntry() {
        return journalEntry;
    }
}
