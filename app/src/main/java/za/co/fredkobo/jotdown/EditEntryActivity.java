package za.co.fredkobo.jotdown;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import za.co.fredkobo.jotdown.db.JotDownDatabase;
import za.co.fredkobo.jotdown.model.JournalEntry;
import za.co.fredkobo.jotdown.viewModel.AddEntryViewModel;

public class EditEntryActivity extends AppCompatActivity {

    // Extra for the task ID to be received in the intent
    public static final String EXTRA_TASK_ID = "extraTaskId";
    // Extra for the task ID to be received after rotation
    public static final String INSTANCE_TASK_ID = "instanceTaskId";
    // Constant for default task id to be used when not in update mode
    private static final int DEFAULT_TASK_ID = -1;

    private int mTaskId = DEFAULT_TASK_ID;

    // Member variable for the Database
    private JotDownDatabase mDb;

    private EditText titleEditText;
    private EditText contentEditText;
    private Button editEntryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);

        titleEditText = (EditText) findViewById(R.id.title_edittext);
        contentEditText = (EditText) findViewById(R.id.content_edittext);
        editEntryButton = (Button) findViewById(R.id.edit_entry_button);

        mDb = JotDownDatabase.getInstance(getApplicationContext());

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_TASK_ID)) {
            mTaskId = savedInstanceState.getInt(INSTANCE_TASK_ID, DEFAULT_TASK_ID);
        }

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_TASK_ID)) {
            editEntryButton.setText(R.string.update_button);
            if (mTaskId == DEFAULT_TASK_ID) {
                // populate the UI
                mTaskId = intent.getIntExtra(EXTRA_TASK_ID, DEFAULT_TASK_ID);

                // COMPLETED (9) Remove the logging and the call to loadTaskById, this is done in the ViewModel now
                // COMPLETED (10) Declare a AddTaskViewModelFactory using mDb and mTaskId
                AddEntryViewModelFactory factory = new AddEntryViewModelFactory(mDb, mTaskId);
                // COMPLETED (11) Declare a AddTaskViewModel variable and initialize it by calling ViewModelProviders.of
                // for that use the factory created above AddTaskViewModel
                final AddEntryViewModel viewModel
                        = ViewModelProviders.of(this, factory).get(AddEntryViewModel.class);

                // COMPLETED (12) Observe the LiveData object in the ViewModel. Use it also when removing the observer
                viewModel.getJournalEntry().observe(this, new Observer<JournalEntry>() {
                    @Override
                    public void onChanged(@Nullable JournalEntry taskEntry) {
                        viewModel.getJournalEntry().removeObserver(this);
                        populateUI(taskEntry);
                    }
                });
            }
        }
    }

    private void populateUI(JournalEntry entry) {
        if (entry == null) {
            return;
        }

        titleEditText.setText(entry.getTitle());
        contentEditText.setText(entry.getContentText());
    }
}
