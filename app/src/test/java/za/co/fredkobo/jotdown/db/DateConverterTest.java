package za.co.fredkobo.jotdown.db;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by F5094712 on 2018/06/27.
 */
public class DateConverterTest {
    @Test
    public void toDate_returnsDate() throws Exception {
        Long dateAsLong = 1220227200L * 1000;
        Date date = DateConverter.toDate(dateAsLong);
        assertNotNull(date);
    }

    @Test
    public void toTimestamp_returnsLong() throws Exception {
        Long timestamp = DateConverter.toTimestamp(new Date());
        assertNotNull(timestamp);
    }

}