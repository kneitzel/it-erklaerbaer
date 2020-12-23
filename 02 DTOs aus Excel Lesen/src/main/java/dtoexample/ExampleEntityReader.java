package dtoexample;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ExampleEntityReader {
    private Workbook workbook;
    private Sheet sheet;
    private Iterator<Row> rowIterator;

    public ExampleEntityReader(InputStream stream) throws IOException {
        workbook = WorkbookFactory.create(stream);
        if (workbook.getNumberOfSheets() == 0) throw new IOException("Workbook has no sheets!");

        sheet = workbook.getSheetAt(0);

        rowIterator = sheet.rowIterator();
        if (rowIterator.hasNext()) rowIterator.next(); // Erste Zeile Überspringen
    }

    public boolean hasNext() {
        return rowIterator.hasNext();
    }

    public ExampleEntity next() {
        if (!hasNext()) return null;

        Row row = rowIterator.next();
        return new ExampleEntity(
                (int) row.getCell(0).getNumericCellValue(),
                row.getCell(1).getStringCellValue(),
                row.getCell(2).getStringCellValue(),
                row.getCell(3).getDateCellValue(),
                row.getCell(4).getDateCellValue());
    }
}
