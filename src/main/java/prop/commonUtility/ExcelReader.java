package prop.commonUtility;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ExcelReader {


    public static boolean isStarted = false;
    private static Sheet sheet;
    private static Iterator<Row> rowIterator;
    public static Object[][] array = null;
    public static Workbook workbook = null;
    public static FileOutputStream fileOut;


    @DataProvider(name = "testData")
    public static Object[][] getTestData() throws IOException {
        HashMap<String, String[]> data = new HashMap<String, String[]>();
        if (!isStarted) {
            // File file = new File(System.getProperty("user.dir") + "/TestData/Dataprovider.xlsx");
            workbook = new XSSFWorkbook(new FileInputStream(System.getProperty("user.dir") + "/TestData/Dataprovider.xlsx"));
            //workbook = WorkbookFactory.create(file);
            sheet = workbook.getSheet("data");
            isStarted = true;
        }
        rowIterator = sheet.rowIterator();

        int temp = 0;

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            int tempVal=0;
            if (row.getCell(0).getStringCellValue().equals("")&&temp!=0) {
                break;
            } else {
                tempVal++;
                if (row.getCell(1).getStringCellValue().equalsIgnoreCase("Yes")) {
                    String[] temp2 = new String[2];
                    temp2[0] = row.getCell(3).getStringCellValue();
                    temp2[1] = row.getCell(4).getStringCellValue();
                    data.put(row.getCell(0).getStringCellValue(), temp2);
                }
            }
        }

        array = new Object[data.size()][2];
        int count = 0;
        for (Map.Entry<String, String[]> entry : data.entrySet()) {
            array[count][0] = entry.getKey();
            array[count][1] = entry.getValue();
            count++;
        }
        return array;
    }

    public static void rightResult(HashMap<String, String[]> result) throws IOException {
        String keyVal = null;
        for (String key : result.keySet()) {
            keyVal = key;
        }

        //  File file = new File(System.getProperty("user.dir") + "/TestData/Dataprovider.xlsx");
        sheet = workbook.getSheet("data");
        rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getCell(0).getStringCellValue().equals(keyVal)) {

                Cell cell = row.getCell(5);
                row.createCell(5);
                cell.setCellValue(result.get(keyVal)[0]);

                Cell cell1 = row.getCell(6);
                row.createCell(6);
                cell1.setCellValue(result.get(keyVal)[1]);
                fileOut = fileOut = new FileOutputStream(System.getProperty("user.dir") + "/TestData/Dataprovider.xlsx");
                workbook.write(fileOut);
                fileOut.close();
            }
        }
    }
    @AfterClass
    public void closeWorkBook() throws IOException {
        workbook.close();
    }
}
