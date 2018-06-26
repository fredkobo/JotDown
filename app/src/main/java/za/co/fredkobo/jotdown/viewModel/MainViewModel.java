package za.co.fredkobo.jotdown.viewModel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import za.co.fredkobo.jotdown.MainViewInterface;
import za.co.fredkobo.jotdown.model.JournalEntry;

/**
 * Created by F5094712 on 2018/06/26.
 */

public class MainViewModel implements MainViewModelInterface {

    private MainViewInterface mainViewInterface;
    public MainViewModel(MainViewInterface viewInterface){
        this.mainViewInterface = viewInterface;
    }
    @Override
    public List<JournalEntry> getAllJornalEntries() {
        JournalEntry entry = new JournalEntry();
        entry.setId(1);
        entry.setTitle("The tragedy of the commons");
        entry.setContentText("The tragedy of the commons is a term that explains a phenomenon in which the is an abuse of common resources among a particular group of people.");
        entry.setLastEditDateTime(new Date(1220227200L * 1000));
        List<JournalEntry> entries = new ArrayList<>();
        entries.add(entry);
        mainViewInterface.onGetAllEntriesSuccess(entries);
        return entries;
    }
}
