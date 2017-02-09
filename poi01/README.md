[Apache POI \- the Java API for Microsoft Documents](https://poi.apache.org/index.html)

```java
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
```

```
$ java -cp Main 

通所介護（介護予防通所介護）計画書
事業所名
○○通所サービス
計画作成氏名
東京　花子
....
```
