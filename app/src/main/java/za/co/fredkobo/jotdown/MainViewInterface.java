package za.co.fredkobo.jotdown;

import java.util.List;

import za.co.fredkobo.jotdown.model.JournalEntry;

/**
 * Created by F5094712 on 2018/06/26.
 */

public interface MainViewInterface {
    void onGetAllEntriesSuccess(List<JournalEntry> journalEntries);
}
