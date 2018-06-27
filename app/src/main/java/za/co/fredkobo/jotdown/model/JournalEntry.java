package za.co.fredkobo.jotdown.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by F5094712 on 2018/06/26.
 */

@Entity(tableName = "journal_entry")
public class JournalEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    @ColumnInfo(name = "content_text")
    private String contentText;
    @ColumnInfo(name = "last_edited_at")
    private Date lastEditDateTime;

    public JournalEntry(String title, String contentText, Date lastEditDateTime){
        this.title = title;
        this.contentText = contentText;
        this.lastEditDateTime = lastEditDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public Date getLastEditDateTime() {
        return lastEditDateTime;
    }

    public void setLastEditDateTime(Date lastEditDateTime) {
        this.lastEditDateTime = lastEditDateTime;
    }

}
