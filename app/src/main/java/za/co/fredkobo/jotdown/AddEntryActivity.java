package za.co.fredkobo.jotdown;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import za.co.fredkobo.jotdown.db.JotDownDatabase;
import za.co.fredkobo.jotdown.model.JournalEntry;
import za.co.fredkobo.jotdown.viewModel.AddEntryViewModel;

public class AddEntryActivity extends AppCompatActivity {

    public static final String EXTRA_ENTRY_ID = "extraEntryId";
    public static final String INSTANCE_ENTRY_ID = "instanceEntryId";
    private static final int DEFAULT_ENTRY_ID = -1;

    private int entryId = DEFAULT_ENTRY_ID;

    private JotDownDatabase jtDb;

    private EditText titleEditText;
    private EditText contentEditText;
    private Button editEntryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);

        initialiseViews();

        jtDb = JotDownDatabase.getInstance(getApplicationContext());

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_ENTRY_ID)) {
            entryId = savedInstanceState.getInt(INSTANCE_ENTRY_ID, DEFAULT_ENTRY_ID);
        }

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_ENTRY_ID)) {
            editEntryButton.setText(R.string.update_button);
            if (entryId == DEFAULT_ENTRY_ID) {

                entryId = intent.getIntExtra(EXTRA_ENTRY_ID, DEFAULT_ENTRY_ID);

                AddEntryViewModelFactory factory = new AddEntryViewModelFactory(jtDb, entryId);

                final AddEntryViewModel viewModel = ViewModelProviders.of(this, factory).get(AddEntryViewModel.class);

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

    private void initialiseViews() {
        titleEditText = (EditText) findViewById(R.id.title_edittext);
        contentEditText = (EditText) findViewById(R.id.content_edittext);
        editEntryButton = (Button) findViewById(R.id.edit_entry_button);

        editEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditButtonClicked();
            }
        });
    }

    public void onEditButtonClicked() {
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();
        Date date = new Date();

        if (!title.isEmpty() && !content.isEmpty()) {
            final JournalEntry entry = new JournalEntry(title, content, date);
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    if (entryId == DEFAULT_ENTRY_ID) {
                        // insert new entry
                        jtDb.journalEntryDao().insertEntry(entry);
                    } else {
                        //update entry
                        entry.setId(entryId);
                        jtDb.journalEntryDao().updateEntry(entry);
                    }
                    finish();
                }
            });
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(AddEntryActivity.this).create();
            alertDialog.setTitle("Missing fields");
            alertDialog.setMessage("Please complete all the fields to save the journal entry");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
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
