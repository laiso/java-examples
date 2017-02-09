import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        parseExelData("http://reki.jp/tusyokeikakurei2.xls");
    }

    static void parseExelData(String string){
        try {
            URL url = new URL(string);
            InputStream inputStream = url.openStream();

            Workbook workbook = new HSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.rowIterator();
            while(rows.hasNext()){
                Row row = rows.next();
                Iterator<Cell> cells = row.cellIterator();
                while(cells.hasNext()){
                    Cell cell = cells.next();
                    if (cell.getCellTypeEnum() == CellType.STRING) {
                        String value = cell.getStringCellValue();
                        System.out.println(value);
                    }
                }
            }

            inputStream.close();
            workbook.close();
        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
    }
}
