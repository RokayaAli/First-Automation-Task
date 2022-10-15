package Util;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataExcel {
    public static String [][] getData(String tab) throws IOException {
        // fileInputStream argument

        FileInputStream file = new FileInputStream("Test_data/Book1.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet excelSheet = wb.getSheet(tab);
        int noRow = excelSheet.getPhysicalNumberOfRows()-1;
        int noColumn = excelSheet.getRow(0).getLastCellNum();
        String [][] data = new  String [noRow][noColumn];
        DataFormatter formatter = new DataFormatter();

        for(int i = 0; i < noRow; i ++) {
            for(int x = 0; x < noColumn; x++) {
                data[i][x] = formatter.formatCellValue(excelSheet.getRow(i+1).getCell(x));
            }
        }

        return data;
    }

}
