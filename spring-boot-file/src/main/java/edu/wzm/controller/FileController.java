package edu.wzm.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.opencsv.CSVWriter;
import edu.wzm.service.ExcelService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gatsbynewton on 2017/12/24.
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private ExcelService excelService;

    /**
     * 使用HttpServletResponse下载Excel
     */
    @GetMapping("/excel1")
    public void downloadExcel(@RequestParam("fn") String fileName,
                              @RequestParam("sn") String sheetName,
                              HttpServletResponse response) {
        // 实现下载逻辑
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ServletOutputStream sos = response.getOutputStream();
             ExcelWriter excelWriter = ExcelUtil.getBigWriter()) {

            excelService.download(excelWriter, sheetName);
            excelService.test(excelWriter.getWorkbook());
            String codedFileName = URLEncoder.encode(fileName, "UTF-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=" + codedFileName + ".xlsx");
            response.setHeader("X-Custom-Message", "文件下载成功"); // 自定义提示信息

            excelWriter.flush(baos, true);
            baos.writeTo(sos);
        } catch (Exception e) {
            log.error("下载异常", e);
        }
    }

    /**
     * 使用ResponseEntity下载Excel
     */
    @GetMapping("/excel2")
    public ResponseEntity<byte[]> downloadExcel1(@RequestParam("fn") String fileName,
                                                 @RequestParam("sn") String sheetName) {
        // 实现下载逻辑
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ExcelWriter excelWriter = ExcelUtil.getBigWriter();) {

            excelService.download(excelWriter, sheetName);
            excelService.test(excelWriter.getWorkbook());
            String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            excelWriter.flush(baos, true);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + codedFileName + ".xlsx")
                    .header("X-Custom-Message", "文件下载成功")
                    .body(baos.toByteArray());
        } catch (Exception e) {
            log.error("下载异常", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    /**
     * 下载CSV
     */
    @GetMapping("/export/csv")
    public void exportCSV(HttpServletResponse response) throws Exception {
        String filename = "data.csv";

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             OutputStreamWriter streamWriter = new OutputStreamWriter(baos, StandardCharsets.UTF_8);
             PrintWriter printWriter = response.getWriter()) {
            response.setContentType("text/csv");
            response.setCharacterEncoding("UTF-8");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

            List<String[]> dataList = new ArrayList<String[]>() {{
                add(new String[]{"1", "张三", "销售部", "1000"});
                add(new String[]{"2", "李四", "市场部", "1500"});
                add(new String[]{"3", "王五", "销售部", "1200"});
                add(new String[]{"4", "赵六", "技术部", "1800"});
                add(new String[]{"5", "孙七", "市场部", "1300"});
            }};

            dataList.add(new String[4]);
            dataList.get(5)[1] = "test";
            dataList.get(5)[3] = "测试";

            byte[] bom = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
            streamWriter.write(new String(bom));
            // 使用 OpenCSV 进行 CSV 数据写入
            CSVWriter csvWriter = new CSVWriter(streamWriter);
            csvWriter.writeAll(dataList);
            streamWriter.flush();

            printWriter.write(baos.toString());
            String result = new String(baos.toByteArray(), StandardCharsets.UTF_8);
            System.out.println(result);

            printWriter.flush();
        } catch (Exception e) {
            log.error("ChartDownloadController#downloadBDashboard: 看板下载异常", e);
        }
    }

    /**
     * 下载大文件
     */
    @GetMapping("/big/excel1")
    public ResponseEntity<Resource> download() {
        try {
            Resource resource = new FileSystemResource("/Users/wangzhiming/Documents/code/java/data/test1.xlsx");
            String filename = URLEncoder.encode(resource.getFilename(), "UTF-8");
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e) {
            log.error("下载异常", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    /**
     * 下载大文件
     */
    @SneakyThrows
    @GetMapping("/bigExcel2")
    public void download(HttpServletResponse response) {
        File file = ResourceUtils.getFile("/Users/wangzhiming/Documents/code/java/data/test1.xlsx");
        try (InputStream is = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {
            String fileName = URLEncoder.encode(file.getName(), "UTF-8");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

            // 边读边写，避免OOM
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
        } catch (Exception e) {
            log.error("下载异常", e);
        }
    }
}






