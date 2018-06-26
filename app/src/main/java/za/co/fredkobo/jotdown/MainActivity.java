package za.co.fredkobo.jotdown;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import za.co.fredkobo.jotdown.model.JournalEntry;
import za.co.fredkobo.jotdown.viewModel.MainViewModel;
import za.co.fredkobo.jotdown.viewModel.MainViewModelInterface;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    private RecyclerView entryRecyclerView;
    private EntryRecyclerViewAdapter entryAdapter;
    private List<JournalEntry> journalEntryList;
    private MainViewModelInterface viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewModel = new MainViewModel(this);

        final Intent editEntryIntent = new Intent(this, EditEntryActivity.class);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(editEntryIntent);
            }
        });

        entryRecyclerView = (RecyclerView) findViewById(R.id.entry_recyclerview);

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getAllJornalEntries();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetAllEntriesSuccess(List<JournalEntry> journalEntries) {
        entryAdapter = new EntryRecyclerViewAdapter(journalEntries);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        entryRecyclerView.setLayoutManager(mLayoutManager);
        entryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        entryRecyclerView.setAdapter(entryAdapter);
    }
}
