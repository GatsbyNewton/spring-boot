package edu.wzm.controller;

import edu.wzm.entity.City;
import edu.wzm.service.FileService;
import edu.wzm.tool.Tools;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gatsbynewton on 2017/12/24.
 */
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/download/csv", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadCSV(@RequestParam("fileName") String fileName)throws IOException{
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/csv;charset=UTF-8");
        headers.setContentDispositionFormData("attachment;filename", fileName, Charset.forName("UTF-8"));

        List<City> cities = fileService.findAll();
        /** header of BOM_UTF8 */
        byte[] bomUTF8 = {(byte) 0xef, (byte) 0xbb, (byte) 0xbf};
        String content = cities.stream()
                .map(city -> "城市：" + city + "")
                .collect(Collectors.joining("\n"));
        return new ResponseEntity<>(Tools.byteArrayConcat(bomUTF8, content.getBytes("UTF-8")), headers, HttpStatus.OK);

//        Resource resource = new InputStreamResource(new ByteArrayInputStream(Tools.byteArrayConcat(bomUTF8, content.getBytes("UTF-8"))));
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentType(MediaType.parseMediaType("application/x-msdownload"))
//                .body(resource);
    }

    @RequestMapping(value = "/download/excel", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadExcel(@RequestParam("fileName") String fileName){
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream()){

            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet();
            HSSFCellStyle cellStyle = Tools.createExcel(wb);

            List<City> cities = fileService.findAll();
            String[] header = {"id", "country", "name", "state", "map"};
            HSSFRow headerRow = sheet.createRow(0);
            for (int i = 0, len = header.length; i < len; i++){
                HSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(header[i]);
                cell.setCellStyle(cellStyle);
            }

            for(int i = 1, size = cities.size(); i <= size; i++) {
                HSSFRow row = sheet.createRow(i);
                HSSFCell cell0 = row.createCell(0);
                cell0.setCellValue("城市 " + cities.get(i - 1).getId());
                HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(cities.get(i - 1).getCountry());
                HSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(cities.get(i - 1).getName());
                HSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(cities.get(i - 1).getState());
                HSSFCell cell4 = row.createCell(4);
                cell4.setCellValue(cities.get(i - 1).getMap());
            }
            wb.write(bos);
            wb.close();

            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("charset", "UTF-8");
            fileName = URLEncoder.encode(fileName, "UTF-8");
            headers.add("Content-Disposition", "attachment;filename=\"" + fileName + "\"");

            Resource resource = new InputStreamResource(new ByteArrayInputStream(bos.toByteArray()));

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/x-msdownload"))
                    .body(resource);
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "/download/pdf", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadPdf(){
        return null;
    }
}






