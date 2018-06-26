package za.co.fredkobo.jotdown;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import za.co.fredkobo.jotdown.model.JournalEntry;

/**
 * Created by F5094712 on 2018/06/26.
 */

public class EntryRecyclerViewAdapter extends RecyclerView.Adapter<EntryRecyclerViewAdapter.EntryViewHolder> {
    // Constant for date format
    private static final String DATE_FORMAT = "dd/MM/yyy";

    private final ItemClickListener itemClickListener;
    private Context context;
    private List<JournalEntry> journalEntries;
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    public EntryRecyclerViewAdapter(Context context, ItemClickListener listener) {
        this.context = context;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.entry_item, parent, false);
        return new EntryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EntryViewHolder holder, int position) {
        JournalEntry entry = journalEntries.get(position);
        holder.title.setText(entry.getTitle());
        holder.content.setText(entry.getContentText());
        String lastUpdatedAt = dateFormat.format(entry.getLastEditDateTime());
        holder.lastEdit.setText(lastUpdatedAt);
    }

    @Override
    public int getItemCount() {
        if (journalEntries == null) {
            return 0;
        }
        return journalEntries.size();
    }

    public List<JournalEntry> getJournalEntries() {
        return journalEntries;
    }

    public void setJournalEntries(List<JournalEntry> journalEntries) {
        journalEntries = journalEntries;
        notifyDataSetChanged();
    }

    public class EntryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title;
        public TextView content;
        public TextView lastEdit;

        public EntryViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_textview);
            content = (TextView) itemView.findViewById(R.id.content_textview);
            lastEdit = (TextView) itemView.findViewById(R.id.last_edit_date_textview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int elementId = journalEntries.get(getAdapterPosition()).getId();
            itemClickListener.onItemClickListener(elementId);
        }
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }
}
