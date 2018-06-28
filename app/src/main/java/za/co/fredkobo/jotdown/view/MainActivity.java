package za.co.fredkobo.jotdown.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import za.co.fredkobo.jotdown.helpers.AppExecutors;
import za.co.fredkobo.jotdown.R;
import za.co.fredkobo.jotdown.db.JotDownDatabase;
import za.co.fredkobo.jotdown.model.JournalEntry;
import za.co.fredkobo.jotdown.viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity implements EntryRecyclerViewAdapter.ItemClickListener {

    private String TAG = MainActivity.class.getSimpleName();
    private RecyclerView entryRecyclerView;
    private EntryRecyclerViewAdapter entryAdapter;
    private JotDownDatabase jotDownDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Intent editEntryIntent = new Intent(this, AddEntryActivity.class);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(editEntryIntent);
            }
        });

        entryRecyclerView = (RecyclerView) findViewById(R.id.entry_recyclerview);
        entryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        entryAdapter = new EntryRecyclerViewAdapter(this, this);
        entryRecyclerView.setAdapter(entryAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<JournalEntry> tasks = entryAdapter.getJournalEntries();
                        jotDownDb.journalEntryDao().deleteEntry(tasks.get(position));
                    }
                });
            }
        }).attachToRecyclerView(entryRecyclerView);

        jotDownDb = JotDownDatabase.getInstance(getApplicationContext());
        setupViewModel();
    }

    private void setupViewModel() {
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getTasks().observe(this, new Observer<List<JournalEntry>>() {
            @Override
            public void onChanged(@Nullable List<JournalEntry> journalEntries) {
                Log.d(TAG, "Updating list of tasks from LiveData in ViewModel");
                entryAdapter.setJournalEntries(journalEntries);
            }
        });
    }


   @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClickListener(int itemId) {
        Intent intent = new Intent(MainActivity.this, AddEntryActivity.class);
        intent.putExtra(AddEntryActivity.EXTRA_ENTRY_ID, itemId);
        startActivity(intent);
    }
}
