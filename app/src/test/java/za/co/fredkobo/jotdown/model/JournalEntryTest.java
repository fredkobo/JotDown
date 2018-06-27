package za.co.fredkobo.jotdown.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by F5094712 on 2018/06/27.
 */
public class JournalEntryTest {

    JournalEntry entry = new JournalEntry("Example journal entry", "This example journal entry is used for testing", new Date(1220227200L * 1000));

    @Test
    public void getId() throws Exception {
        entry.setId(7);
        assertEquals(7, entry.getId());
    }

    @Test
    public void getTitle() throws Exception {
        assertEquals("Example journal entry", entry.getTitle() );
    }

    @Test
    public void getContentText() throws Exception {
        assertEquals("This example journal entry is used for testing", entry.getContentText());
    }

    @Test
    public void getLastEditDateTime() throws Exception {
        assertNotNull(entry.getLastEditDateTime());
    }

}