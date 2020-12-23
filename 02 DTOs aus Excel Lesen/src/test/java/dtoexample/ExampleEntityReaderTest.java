package dtoexample;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ExampleEntityReaderTest {

    @Test
    public void testReadingOfExcelSheet() throws IOException {
        ExampleEntityReader reader = new ExampleEntityReader(getClass().getResourceAsStream("/Kundenliste.xlsx"));

        while (reader.hasNext()) {
            System.out.println(reader.next());
        }
    }
}