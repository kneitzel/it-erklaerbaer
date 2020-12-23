package dtoexample;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * ExampleEntityReader
 * Reads ExampleEntity from an Excel Workbook.
 */
public class ExampleEntityReader implements Closeable, AutoCloseable {
    private Workbook workbook;
    private Sheet sheet;
    private Iterator<Row> rowIterator;

    /**
     * Creates a new ExampleEntityReader on an input stream.
     * @param stream InputStream to read.
     * @throws IOException IOException if the Stream cannot be read.
     */
    public ExampleEntityReader(InputStream stream) throws IOException {
        workbook = WorkbookFactory.create(stream);
        if (workbook.getNumberOfSheets() == 0) throw new IOException("Workbook has no sheets!");
        sheet = workbook.getSheetAt(0);
        rowIterator = sheet.rowIterator();
        if (rowIterator.hasNext()) rowIterator.next();
    }

    /**
     * Returns if another record is available.
     * @return true if another record is available.
     */
    public boolean hasNext() {
        return rowIterator.hasNext();
    }

    /**
     * Creates a new ExampleEntity of a row.
     * @param row Row to get the data of the entity from.
     * @return New ExampleEntity row.
     */
    protected ExampleEntity createExampleEntity(Row row) {
        return new ExampleEntity(
                (int) row.getCell(0).getNumericCellValue(),
                row.getCell(1).getStringCellValue(),
                row.getCell(2).getStringCellValue(),
                row.getCell(3).getDateCellValue(),
                row.getCell(4).getDateCellValue());
    }

    /**
     * Gets the next ExampleEntity.
     * @return Next ExampleEntity or null if none available.
     */
    public ExampleEntity next() {
        if (!hasNext()) return null;
        return createExampleEntity(rowIterator.next());
    }

    /**
     * Gets a Stream of ExampleEntity.
     * @return Stream of ExamleEntity.
     */
    public Stream<ExampleEntity> stream() {
        Spliterator<Row> spliterator = Spliterators.spliteratorUnknownSize(rowIterator, Spliterator.NONNULL);
        return StreamSupport.stream(spliterator, false).map(row -> createExampleEntity(row));
    }

    /**
     * Closes the Reader of the ExampleEntities.
     * @throws IOException IOException if there is an IO error while closing the reader.
     */
    @Override
    public void close() throws IOException {
        workbook.close();
    }
}
