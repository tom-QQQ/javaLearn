package com.java.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * @author Ning
 * @date Create in 2019/7/29
 */
public class ExcelHelper {

    /**
     * 一个sheet的最大数据数量
     */
    private static final int MAX_DATA_COUNT_PER_SHEET = 6000;

    public static <T> String writeExcelToQiNiu(Class<T> clazz, List<T> dataModels, String[] filedNames, String[] titles, String fileName) throws Exception {

        HSSFWorkbook workbook;
        workbook = new HSSFWorkbook();
        int dataIndex = 0;
        int resetTimes = 0;
        boolean isNewPage = true;
        for (int sheetIndex = 1;; sheetIndex++) {

            HSSFSheet sheet = workbook.createSheet("sheet-" + sheetIndex);
            HSSFRow headRow = sheet.createRow(0);
            // 添加表格标题
            for (int i = 0; i < titles.length; i++) {
                HSSFCell cell = headRow.createCell(i);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(titles[i]);
                // 设置字体加粗
                HSSFCellStyle cellStyle = workbook.createCellStyle();
                HSSFFont font = workbook.createFont();
                font.setBoldweight(Font.BOLDWEIGHT_BOLD);
                cellStyle.setFont(font);
                // 设置自动换行
                cellStyle.setWrapText(true);
                cell.setCellStyle(cellStyle);
                // 设置单元格宽度
                sheet.setColumnWidth(i, titles[i].length() * 1000);
            }

            // 添加表w格内容
            for (; dataIndex < dataModels.size(); dataIndex++) {
                HSSFRow row = sheet.createRow(dataIndex - resetTimes * MAX_DATA_COUNT_PER_SHEET + 1);

                if (dataIndex != 0 && dataIndex % MAX_DATA_COUNT_PER_SHEET == 0 && isNewPage) {
                    resetTimes++;
                    isNewPage = false;
                    break;
                }

                isNewPage = true;

                // 遍历属性列表
                for (int j = 0; j < filedNames.length; j++) {
                    // 通过反射获取属性的值域
                    String fieldName = filedNames[j];
                    if (fieldName == null) {
                        continue;
                    }
                    Object result = ReflectUtil.invokeGetter(dataModels.get(dataIndex),
                            fieldName);
                    HSSFCell cell = row.createCell(j);
                    cell.setCellValue(StringUtil.toString(result));
                    // 如果是日期类型则进行格式化处理
                    if (isDateType(clazz, fieldName)) {
                        cell.setCellValue(DateUtils.dateToString((Date) result, DateUtils.DATE_TIME_FORMAT));
                    }
                }
            }

            if (dataIndex == dataModels.size()) {
                break;
            }
        }

        return uploadAndGetUrl(workbook, fileName);
    }

    private static String uploadAndGetUrl(HSSFWorkbook workbook, String fileName) {

        InputStream excelStream = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            workbook.write(out);
            excelStream = new ByteArrayInputStream(out.toByteArray());

            return QiNiuFileUtil.upload(excelStream, "", fileName);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            if (excelStream != null) {

                try {
                    excelStream.close();

                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }

        return "";
    }

    /**
     * 判断属性是否为日期类型
     *
     * @param clazz     数据类型
     * @param fieldName 属性名
     * @return 如果为日期类型返回true，否则返回false
     */
    private static <T> boolean isDateType(Class<T> clazz, String fieldName) {

        boolean flag = false;
        try {
            Field field = clazz.getDeclaredField(fieldName);
            Object typeObj = field.getType().newInstance();
            flag = typeObj instanceof Date;

        } catch (Exception e) {
            // 把异常吞掉直接返回false
        }

        return flag;
    }
}
