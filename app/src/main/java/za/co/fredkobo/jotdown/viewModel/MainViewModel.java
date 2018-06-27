package za.co.fredkobo.jotdown.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

import za.co.fredkobo.jotdown.db.JotDownDatabase;
import za.co.fredkobo.jotdown.model.JournalEntry;

/**
 * Created by F5094712 on 2018/06/26.
 */

public class MainViewModel extends AndroidViewModel {

    // Constant for logging
    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<JournalEntry>> journalEntries;

    public MainViewModel(Application application) {
        super(application);
        JotDownDatabase database = JotDownDatabase.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving the entries from the Databasae");
        journalEntries = database.journalEntryDao().getAllEntries();
    }

    public LiveData<List<JournalEntry>> getTasks() {
        return journalEntries;
    }
}
