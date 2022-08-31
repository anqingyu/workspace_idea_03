package com.xf.foundation.poi_ooxml;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: todo(描述)
 * @Author: xiefu
 * @Date: 2021/2/9 9:06
 */
public class ExcelTest {
    /**
     * 处理单页xls
     * @param filePath 文件地址
     * @param firstDataRow 数据从第几行开始
     * @return List<List<String>>
     * @throws IOException
     */
    public static List<List<String>> handler(String filePath, int firstDataRow) throws IOException {
        List<List<String>> zongList = new ArrayList<List<String>>();

        // 获取excle文件
        Workbook wb = null;
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        is = new FileInputStream(filePath);
        if (".xls".equals(extString)) {
            wb = new HSSFWorkbook(is);
        } else if (".xlsx".equals(extString)) {
            wb = new XSSFWorkbook(is);
        }

        // 读第一页
        Sheet sheet = wb.getSheetAt(0);
        // 获取最大行数
        int maxrownum = sheet.getPhysicalNumberOfRows();
        // 获取第一行
        Row row = sheet.getRow(firstDataRow - 1); // 从0开始计数
        // 获取最大列数
        int maxcolnum = row.getPhysicalNumberOfCells();

        for (int i = firstDataRow - 1; i < maxrownum; i++) {
            // 定义数据存储集合
            List<String> list = new ArrayList<String>();
            // 获取一行
            row = sheet.getRow(i);
            // 循环列，一个个获取
            for (int j = 0; j < maxcolnum; j++) {
                // 获取一个格内容
                Cell cell = row.getCell(j);
                // 获取数据
                String cellData = getCellValue(cell); // 这里只处理了String类型,其它内容单独处理
                list.add(cellData);
            }
            zongList.add(list);
        }

        return zongList;
    }

    /**
     * 对不同类型处理
     *
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        switch (cell.getCellType()) {
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case STRING:
                return String.valueOf(cell.getStringCellValue());
            case BLANK:
                return "";
            case ERROR:
                return String.valueOf(cell.getErrorCellValue());
            /*
             * case FORMULA: //公式类型
             * System.out.println(evaluator.evaluate(row.getCell(k)).getNumberValue());
             * break;
             */
            default : return  "";
        }
    }

    public static void main(String[] args) throws IOException {
        // 读取excel数据
        List<List<String>> handler = handler("H:/新建文件夹 (2)/1月考勤_艾融 _互联网金融.xlsx", 1);
        System.out.println("读取excel数据完毕");
//        ExcelUtil.createExcel("H:/新建文件夹 (2)/b.xls");
//        ExcelUtil.createExcelXlsx("H:/新建文件夹 (2)/c.xlsx");
    }
}
