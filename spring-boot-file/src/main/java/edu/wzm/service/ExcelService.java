package edu.wzm.service;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Service
public class ExcelService {

    public void download(ExcelWriter writer, String sheetName) {
        writer.renameSheet(sheetName);
        int colIndex = 0;
        int rowIndex = 0;
        for (int i = 1; i <= 100; i++) {
            writer.writeCellValue(colIndex, rowIndex++, i);
            if (i % 10 == 0) {
                colIndex++;
                rowIndex = 0;
            }
        }
    }

    public void test(Workbook workbook ) {
        Sheet sheet = workbook.createSheet("safeTitle");
        Row head = sheet.createRow(0);
        for (int i = 0; i < 4; i++) {
            head.createCell(i).setCellValue("test" + 1);
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        ExcelWriter writer = ExcelUtil.getBigWriter();
        ExcelService service = new ExcelService();
        service.download(writer, "test");
        writer.flush(new FileOutputStream("/Users/wangzhiming/Documents/code/java/data/test111.xlsx"));
        writer.close();
    }
}