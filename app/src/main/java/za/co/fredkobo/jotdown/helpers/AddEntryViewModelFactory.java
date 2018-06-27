package za.co.fredkobo.jotdown.helpers;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import za.co.fredkobo.jotdown.db.JotDownDatabase;
import za.co.fredkobo.jotdown.viewModel.AddEntryViewModel;

/**
 * Created by F5094712 on 2018/06/26.
 */

public class AddEntryViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final JotDownDatabase jtDb;
    private final int mTaskId;

    public AddEntryViewModelFactory(JotDownDatabase database, int entryId) {
        jtDb = database;
        mTaskId = entryId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new AddEntryViewModel(jtDb, mTaskId);
    }
}