package com.example.dragon.main.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelHelper {
    private static Integer SHEET_PAGE_NUM = 0;// 读取sheet页第一页

    public static ArrayList<ArrayList<String[]>> explanExcelToAllList(String fileName) {
        ArrayList<ArrayList<String[]>> list = new ArrayList();
        Workbook wb = null;
        try {
            wb = get2003Workbook(new FileInputStream(fileName));
            if (wb == null) {
                wb = get2007Workbook(new FileInputStream(fileName));
                if (wb == null) {
                    throw new RuntimeException("无法识别的格式，Unexpected Excel type (" + fileName + ")");
                }
            }
            list = explanExcelToAllList(wb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<String[]> explanExcelToList(String fileName) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        Workbook wb = null;
        try {
            wb = get2003Workbook(new FileInputStream(fileName));
            if (wb == null) {
                wb = get2007Workbook(new FileInputStream(fileName));
                if (wb == null) {
                    throw new RuntimeException("无法识别的格式，Unexpected Excel type (" + fileName + ")");
                }
            }
            list = explanExcelToList(wb);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Excel所有Sheet转换
     *
     * @param wb
     * @return
     */
    public static ArrayList<ArrayList<String[]>> explanExcelToAllList(Workbook wb) {
        ArrayList<ArrayList<String[]>> list = new ArrayList<>();
        if (wb == null) {
            return list;
        }
        try {
            wb.setMissingCellPolicy(Row.RETURN_BLANK_AS_NULL);// 空白设置为null
            // 读取所有表格内容
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                ArrayList<String[]> list1 = new ArrayList<>();
                Sheet sheet = wb.getSheetAt(i);
                if (sheet != null) {
                    list1 = explantSheet(sheet);
                }
                list.add(list1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<String[]> explanExcelToList(Workbook wb) {
        ArrayList<String[]> resList = new ArrayList<String[]>();
        if (wb == null) {
            return resList;
        }
        try {
            wb.setMissingCellPolicy(Row.RETURN_BLANK_AS_NULL);// 空白设置为null
            // 读取第一章表格内容
            Sheet sheet = wb.getSheetAt(SHEET_PAGE_NUM);
            if (sheet == null) {
                return resList;
            }
            // 循环输出表格中的内容
            resList = explantSheet(sheet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resList;
    }

    public static ArrayList<String[]> explantSheet(Sheet sheet) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        if (sheet == null) {
            return list;
        }
        int rowNum = sheet.getRow(0) != null ? sheet.getRow(0).getPhysicalNumberOfCells() : 0;// 通过表头定义数组的位数，确定每行固定大小
        if (rowNum == 0) {
            rowNum = sheet.getRow(1) != null ? sheet.getRow(1).getPhysicalNumberOfCells() : 0;// 防止不写表头
        }
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {// 循环行
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            String[] contentArr = new String[rowNum];
            for (int j = 0; j < rowNum; j++) {// 循环列
                Cell cell = row.getCell(j);
                String text = "";
                if (cell != null) {
                    text = formatCell(cell);
                }
                contentArr[j] = text;
            }
            list.add(contentArr);
        }
        return list;
    }

    public static String formatCell(Cell cell) {
        DataFormatter _formatter = new DataFormatter();
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING: // 2016年4月27日11:19:20 在excel中视为字符串形式
                return cell.getRichStringCellValue().getString().replace("\u200E", "").replace("\u200F", "");
            case Cell.CELL_TYPE_NUMERIC:// 数值型
                if (DateUtil.isCellDateFormatted(cell)) {// 日期 poi
                    return dateToString(cell.getDateCellValue());
                } else {
                    return _formatter.formatCellValue(cell);
                }
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue() == true ? "true" : "false";// boolean
            // 转成String
            case Cell.CELL_TYPE_ERROR:
                return ErrorEval.getText(cell.getErrorCellValue());// 返回错误码
            case Cell.CELL_TYPE_FORMULA:// 公式
                switch (cell.getCachedFormulaResultType()) {
                    case Cell.CELL_TYPE_STRING:
                        RichTextString str = cell.getRichStringCellValue();
                        if (str != null && str.length() > 0) {
                            return str.toString();
                        }
                    case Cell.CELL_TYPE_NUMERIC:
                        CellStyle style = cell.getCellStyle();
                        if (style == null) {
                            return cell.getNumericCellValue() + "";// double转成String
                        } else {
                            return _formatter.formatRawCellContents(cell.getNumericCellValue(), style.getDataFormat(), style.getDataFormatString());
                        }
                    case Cell.CELL_TYPE_BOOLEAN:
                        return cell.getBooleanCellValue() ? "true" : "false";// boolean
                    // 转成String
                    case Cell.CELL_TYPE_ERROR:
                        return ErrorEval.getText(cell.getErrorCellValue());

                }
            default:
                throw new RuntimeException("Unexpected cell type (" + cell.getCellType() + ")");
        }
    }

    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }


    public static Workbook get2003Workbook(InputStream is) {
        Workbook wb = null;
        try {
            wb = new HSSFWorkbook(is);
        } catch (Exception e) {
            return wb;
        }
        return wb;
    }

    public static Workbook get2007Workbook(InputStream is) {
        Workbook wb = null;
        try {
            wb = new XSSFWorkbook(is);
        } catch (Exception e) {
            return wb;
        }
        return wb;
    }

    public static boolean isExcel2003(InputStream is) {
        try {
            new HSSFWorkbook(is);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isExcel2007(InputStream is) {
        try {
            new XSSFWorkbook(is);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 判断行的内容是否为空
     *
     * @param sheetList
     * @return
     */
    public static List<String[]> isRowEmpty(List<String[]> sheetList) {
        List<String[]> list = new ArrayList<>();
        Boolean info = null;

        for (int i = 1; i < sheetList.size(); i++) {
            String[] arr = sheetList.get(i);
            if (arr[1] != null && !"".equals(arr[1])) {
                list.add(arr);
            }
        }


        return list;
    }

}