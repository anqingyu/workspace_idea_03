package com.xf.foundation.poi_ooxml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


/**
 * @Description: todo(Excel导入导出)
 * @Author: xiefu
 * @Date: 2021/2/10 23:22
 */
public class ExcelUtil {
    public static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 创建xls文件 2003前版本
     * @param name
     */
    public static void createExcel(String name) {
        try {
            FileOutputStream output = new FileOutputStream(name);
            HSSFWorkbook wkb = new HSSFWorkbook();
            HSSFSheet sheet = wkb.createSheet("节点列表");

            // 设置样式
            HSSFCellStyle style = wkb.createCellStyle();
            // 水平居中
            style.setAlignment(HorizontalAlignment.CENTER);
            // 垂直居中
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            // 设置填充模式，模式为全部前景色
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            // 设置前景色为黄色
            style.setFillForegroundColor(HSSFColorPredefined.YELLOW.getColor().getIndex());

            //创建行
            HSSFRow row2 = sheet.createRow(0);

            //创建单元格
            HSSFCell cell = row2.createCell(0);
            cell.setCellValue("ip");
            cell.setCellStyle(style);

            cell = row2.createCell(1);
            cell.setCellValue("端口");
            cell.setCellStyle(style);

            cell = row2.createCell(2);
            cell.setCellValue("用户名");
            cell.setCellStyle(style);

            cell = row2.createCell(3);
            cell.setCellValue("密码");
            cell.setCellStyle(style);

            cell = row2.createCell(4);
            cell.setCellValue("说明");
            cell.setCellStyle(style);

            wkb.write(output);
            output.flush();
            output.close();
            wkb.close();
        } catch (FileNotFoundException e) {
            log.error("未发现文件" + e);
        } catch (IOException e) {
            log.error("创建excel读取异常" + e);
        }
    }

    /**
     * 创建xlsx文件 2007后版本
     * @param name
     */
    public static void createExcelXlsx(String name) {
        try {
            FileOutputStream output = new FileOutputStream(name);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet  sheet = workbook.createSheet("节点列表");

            // 设置样式
            XSSFCellStyle style = workbook.createCellStyle();
            // 水平居中
            style.setAlignment(HorizontalAlignment.CENTER);
            //垂直居中
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            // 设置填充模式，模式为全部前景色
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            // 设置前景色为黄色
            style.setFillForegroundColor(HSSFColorPredefined.YELLOW.getColor().getIndex());

            //创建行
            XSSFRow  row2 = sheet.createRow(0);

            //创建单元格
            XSSFCell  cell = row2.createCell(0);
            cell.setCellValue("ip");
            cell.setCellStyle(style);

            cell = row2.createCell(1);
            cell.setCellValue("端口");
            cell.setCellStyle(style);

            cell = row2.createCell(2);
            cell.setCellValue("用户名");
            cell.setCellStyle(style);

            cell = row2.createCell(3);
            cell.setCellValue("密码");
            cell.setCellStyle(style);

            cell = row2.createCell(4);
            cell.setCellValue("说明");
            cell.setCellStyle(style);

            workbook.write(output);
            output.flush();
            output.close();
            workbook.close();
        } catch (FileNotFoundException e) {
            log.error("未发现文件" + e);
        } catch (IOException e) {
            log.error("创建excel读取异常" + e);
        }
    }

    public static boolean checkVersion(String file) {
        if(file.matches("^.+\\.(?i)(xlsx)$")) {
            return true;
        }
        return false;
    }

    public static List<String> readExcelSecond(String xlsPath) {
        try {
            List<String> temp = new ArrayList<>();

            FileInputStream fileIn = new FileInputStream(xlsPath);
            Workbook wb = null;
            if (checkVersion(xlsPath)) {
                // xlsx
                wb = new XSSFWorkbook(fileIn);
            }else {
                // xls
                wb = new HSSFWorkbook(fileIn);
            }

            Sheet sht0 = wb.getSheetAt(0);

            for (Row r : sht0) {
                if (r.getRowNum() >= 1) {
                    //String 数据
                    if ((r.getCell(0) != null) && (r.getCell(0).toString() != "")) {
                        System.out.println(r.getCell(0).getStringCellValue().trim());
                        temp.add(r.getCell(0).getStringCellValue().trim());
                    }

                    //number数据
                    if ((r.getCell(1) != null) && (r.getCell(1).toString() != "")) {
                        System.out.println((int) r.getCell(1).getNumericCellValue());
                    }

                    //未知数据类型数据
                    if ((r.getCell(1) != null) && (r.getCell(1).toString() != "")) {
                        System.out.println(r.getCell(1).toString());
                    }
                }
            }
            fileIn.close();
            wb.close();

            return temp;
        } catch (Exception e) {
            log.error(e.toString(), e);
        }
        return null;
    }

    /**
     * Excel 导出
     * @param outputFileName 输出文件名称
     * @param header 表头
     * @param keys map的key值
     * @param content 内容数据
     * @param title 表格名字
     * @param sheetName sheet名
     */
    public static void exportExcel(String outputFileName, String[] header, String[] keys, List<Map<String, Object>> content, String title, String sheetName) throws Exception{
        FileOutputStream output = new FileOutputStream(outputFileName);
        title = title + ".xlsx";
        Workbook wb = new SXSSFWorkbook(1000);
        Sheet sheet = wb.createSheet(sheetName);
        Row row = sheet.createRow( 0);
        // 行高
        row.setHeight((short) 700);
        // 列宽
        for (int i = 0; i < header.length; i++) {
            sheet.setColumnWidth(i, 20 * 256);
        }
        for (int i = 0; i < header.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(header[i]);
            cell.setCellStyle(HeaderStyle(wb));
        }
        for (int i = 0; i < content.size(); i++) {
            Map<String, Object> map = content.get(i);
            row = sheet.createRow((int) i + 1);
            row.setHeight((short) 500);
            for (int j = 0; j < keys.length; j++){
                Cell cell = row.createCell(j);
                cell.setCellValue(map.get(keys[j]) == null ? "" : map.get(keys[j]).toString());
                cell.setCellStyle(contentStyle(wb));
            }
        }
        wb.write(output);
        output.close();
        wb.close();
        wb.close();
    }

    /**
     * Excel 导出
     * @param response  HttpServletResponse
     * @param header 表头
     * @param keys map的key值
     * @param content 内容数据
     * @param title 表格名字
     * @param sheetName sheet名
     */
    public static void exportExcel(HttpServletResponse response, String[] header, String[] keys, List<Map<String, Object>> content, String title, String sheetName) throws Exception{
        title = title + ".xlsx";
        Workbook wb = new SXSSFWorkbook(1000);
        Sheet sheet = wb.createSheet(sheetName);
        Row row = sheet.createRow( 0);
        // 行高
        row.setHeight((short) 700);
        // 列宽
        for (int i = 0; i < header.length; i++) {
            sheet.setColumnWidth(i, 20 * 256);
        }
        for (int i = 0; i < header.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(header[i]);
            cell.setCellStyle(HeaderStyle(wb));
        }
        for (int i = 0; i < content.size(); i++) {
            Map<String, Object> map = content.get(i);
            row = sheet.createRow((int) i + 1);
            row.setHeight((short) 500);
            for (int j = 0; j < keys.length; j++){
                Cell cell = row.createCell(j);
                cell.setCellValue(map.get(keys[j]) == null ? "" : map.get(keys[j]).toString());
                cell.setCellStyle(contentStyle(wb));
            }
        }
//        if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
//            title = new String(title.getBytes("UTF-8"), "ISO8859-1"); // firefox浏览器
//        } else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
//            title = URLEncoder.encode(title, "UTF-8");// IE浏览器
//        } else if (request.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0) {
//            title = new String(title.getBytes("UTF-8"), "ISO8859-1");// 谷歌
//        }
        title = new String(title.getBytes("UTF-8"), "ISO8859-1");
        response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Content-Disposition", "attachment; filename=" + title);
        wb.write(response.getOutputStream());
        response.getOutputStream().close();
    }

    /**
     * Excel 导入
     * @param file 文件
     * @param keys 数据顺序
     */
    public static  List<Map<String, Object>>  importExcel(MultipartFile file, String[] keys) throws Exception{
        Workbook wb = null;
        String fileName = file.getOriginalFilename();
        if (fileName.endsWith("xls")) {
            POIFSFileSystem pois = new POIFSFileSystem(file.getInputStream());
            wb = new HSSFWorkbook(pois);
        } else if (fileName.endsWith("xlsx")) {
            wb = new XSSFWorkbook(file.getInputStream());
        }
        Sheet sheet = wb.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();
        if (sheet.getRow( 1).getPhysicalNumberOfCells() != keys.length){
            throw new RuntimeException("导入的Excel和模板的列不匹配");
        }
        List<Map<String,Object>> result = new ArrayList<>();
        for (int i = 0; i < rowCount - 1; i++) {
            Row row = sheet.getRow(i + 1);
            Map<String,Object> tmp = new HashMap<>();
            for (int j = 0;j < keys.length; j++){
                Cell cell = row.getCell(j);
                // 把类型转行Spring
                // cell.setCellType(CellType.STRING);
                tmp.put(keys[j], cell.getStringCellValue());
            }
            result.add(tmp);
        }
        return result;
    }

    /**
     * 表头样式
     */
    private static CellStyle HeaderStyle(Workbook wb){
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 11);
        CellStyle cellStyle = commonStyle(wb);
        cellStyle.setFont(font);
        return cellStyle;
    }

    /**
     * 内容样式
     */
    private static CellStyle contentStyle(Workbook wb){
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        CellStyle cellStyle = commonStyle(wb);
        cellStyle.setFont(font);
        return cellStyle;
    }

    /**
     * 公共样式
     */
    private static CellStyle commonStyle(Workbook wb){
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        // 自动换行
        style.setWrapText(true);
        return style;
    }
}
