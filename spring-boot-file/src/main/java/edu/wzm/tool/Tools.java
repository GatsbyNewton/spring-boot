package edu.wzm.tool;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gatsbynewton on 2017/12/24.
 */
public class Tools {

    public static HSSFCellStyle createExcel(HSSFWorkbook wb){
        HSSFCellStyle cellStyle = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        cellStyle.setFont(font);

        return cellStyle;
    }

    public static <T> T[] arrayConcat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    public static byte[] byteArrayConcat(byte[] first, byte[] second) {
        byte[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    public static void main(String[] args){
        String[] arr1 = {"1", "2", "3"};
        String[] arr2 = {"one", "two"};
        System.out.println(Arrays.toString(arrayConcat(arr1, arr2)));
    }
}
