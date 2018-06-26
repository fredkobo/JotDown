package za.co.fredkobo.jotdown.model;

import java.sql.Date;

/**
 * Created by F5094712 on 2018/06/26.
 */

public class JournalEntry {
    private int id;
    private String title;
    private String contentText;
    private Date lastEditDateTime;

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
