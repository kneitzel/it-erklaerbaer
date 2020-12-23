package dtoexample;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ExampleEntityReaderTest {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private Date createDate(String date) throws ParseException {
        return format.parse(date);
    }

    @Test
    public void testReadingOfExcelSheet() throws IOException, ParseException {
        ExampleEntityReader reader = new ExampleEntityReader(getClass().getResourceAsStream("/Kundenliste.xlsx"));

        assertTrue(reader.hasNext());
        ExampleEntity entity = reader.next();
        assertNotNull(entity);
        assertEquals(1, entity.getId());
        assertEquals("Müller", entity.getName());
        assertEquals("Weimar", entity.getAddress());
        assertEquals(createDate("2020-06-04"), entity.getFromDate());
        assertEquals(createDate("2020-06-09"), entity.getToDate());

        assertTrue(reader.hasNext());
        entity = reader.next();
        assertNotNull(entity);
        assertEquals(2, entity.getId());
        assertEquals("Meier", entity.getName());
        assertEquals("München", entity.getAddress());
        assertEquals(createDate("2020-03-05"), entity.getFromDate());
        assertEquals(createDate("2020-03-10"), entity.getToDate());

        assertTrue(reader.hasNext());
        entity = reader.next();
        assertNotNull(entity);
        assertEquals(3, entity.getId());
        assertEquals("Bergmann", entity.getName());
        assertEquals("Frankfurt", entity.getAddress());
        assertEquals(createDate("2020-06-04"), entity.getFromDate());
        assertEquals(createDate("2020-06-05"), entity.getToDate());

        assertTrue(reader.hasNext());
        entity = reader.next();
        assertNotNull(entity);
        assertEquals(2, entity.getId());
        assertEquals("Meier", entity.getName());
        assertEquals("München", entity.getAddress());
        assertEquals(createDate("2020-05-07"), entity.getFromDate());
        assertEquals(createDate("2020-05-09"), entity.getToDate());

        assertTrue(reader.hasNext());
        entity = reader.next();
        assertNotNull(entity);
        assertEquals(4, entity.getId());
        assertEquals("Hochkamp", entity.getName());
        assertEquals("Berlin", entity.getAddress());
        assertEquals(createDate("2020-02-01"), entity.getFromDate());
        assertEquals(createDate("2020-02-03"), entity.getToDate());

        assertFalse(reader.hasNext());
        entity = reader.next();
        assertNull(entity);
    }
}