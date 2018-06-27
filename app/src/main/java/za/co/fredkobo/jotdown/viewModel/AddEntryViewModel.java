package za.co.fredkobo.jotdown.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import za.co.fredkobo.jotdown.db.JotDownDatabase;
import za.co.fredkobo.jotdown.model.JournalEntry;

/**
 * Created by F5094712 on 2018/06/26.
 */

public class AddEntryViewModel extends ViewModel {

    private LiveData<JournalEntry> journalEntry;

    public AddEntryViewModel(JotDownDatabase database, int entryId) {
        journalEntry = database.journalEntryDao().loadJournalEntryById(entryId);
    }
    public LiveData<JournalEntry> getJournalEntry() {
        return journalEntry;
    }
}
