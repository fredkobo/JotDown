package za.co.fredkobo.jotdown;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import za.co.fredkobo.jotdown.db.JotDownDatabase;
import za.co.fredkobo.jotdown.viewModel.AddEntryViewModel;

/**
 * Created by F5094712 on 2018/06/26.
 */

public class AddEntryViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    // COMPLETED (2) Add two member variables. One for the database and one for the taskId
    private final JotDownDatabase jtDb;
    private final int mTaskId;

    // COMPLETED (3) Initialize the member variables in the constructor with the parameters received
    public AddEntryViewModelFactory(JotDownDatabase database, int entryId) {
        jtDb = database;
        mTaskId = entryId;
    }

    // COMPLETED (4) Uncomment the following method
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new AddEntryViewModel(jtDb, mTaskId);
    }
}