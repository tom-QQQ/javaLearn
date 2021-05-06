package com.java.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 引入版本
 * @author Ning
 * @date Create in 2019/7/12
 */
public class ExcelClass {

    public static final String UID = "2318716612658281164L";

    /**
     * 获取单元格的内容
     *
     * @param cell 单元格
     * @return 返回单元格内容
     */
    private String getCellContent(HSSFCell cell) {
        StringBuffer buffer = new StringBuffer();
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                buffer.append(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN: // 布尔
                buffer.append(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_FORMULA: // 公式
                buffer.append(cell.getCellFormula());
                break;
            case HSSFCell.CELL_TYPE_STRING: // 字符串
                buffer.append(cell.getStringCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK: // 空值
            case HSSFCell.CELL_TYPE_ERROR: // 故障
            default:
                break;
        }
        return buffer.toString();
    }

    public <T> List<T> readExcel(InputStream fileInputStream, Class<T> clazz, String[] fieldNames, int sheetNo, boolean hasTitle) throws Exception {
        List<T> dataModels = new ArrayList<>();
        // 获取excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
        HSSFSheet sheet = workbook.getSheetAt(sheetNo);
        int start = sheet.getFirstRowNum() + (hasTitle ? 1 : 0); // 如果有标题则从第二行开始
        for (int i = start; i <= sheet.getLastRowNum(); i++) {
            HSSFRow row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            // 生成实例并通过反射调用setter方法
            T target = clazz.newInstance();
            for (int j = 0; j < fieldNames.length; j++) {
                String fieldName = fieldNames[j];
                if (fieldName == null || UID.equals(fieldName)) {
                    continue; // 过滤serialVersionUID属性
                }
                // 获取excel单元格的内容
                HSSFCell cell = row.getCell(j);
                if (cell == null) {
                    continue;
                }
                String content = getCellContent(cell);
                // 如果属性是日期类型则将内容转换成日期对象
                if (isDateType(clazz, fieldName)) {

                    // 如果属性是日期类型则将内容转换成日期对象
                    ReflectUtil.invokeSetter(target, fieldName, cell.getDateCellValue());
                } else {
                    Field field = clazz.getDeclaredField(fieldName);
                    ReflectUtil.invokeSetter(target, fieldName,
                            parseValueWithType(content, field.getType()));
                }
            }
            dataModels.add(target);
        }

        try {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataModels;
    }

    /**
     * 判断属性是否为日期类型
     *
     * @param clazz     数据类型
     * @param fieldName 属性名
     * @return 如果为日期类型返回true，否则返回false
     */
    protected <T> boolean isDateType(Class<T> clazz, String fieldName) {
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


    /**
     * 根据类型将指定参数转换成对应的类型
     *
     * @param value 指定参数
     * @param type  指定类型
     * @return 返回类型转换后的对象
     */
    protected <T> Object parseValueWithType(String value, Class<?> type) {
        Object result = null;
        try { // 根据属性的类型将内容转换成对应的类型
            if (Boolean.TYPE == type) {
                result = Boolean.parseBoolean(value);
            } else if (Byte.TYPE == type||Byte.class==type) {
                result = Byte.parseByte(value);
            } else if (Short.TYPE == type||Short.class==type) {
                result = Short.parseShort(value);
            } else if (Integer.TYPE == type|| Integer.class == type) {
                double doubleVal=Double.parseDouble(value);
                result = (int)doubleVal;
            } else if (Long.TYPE == type||Long.class==type) {
                result = Long.parseLong(value);
            } else if (Float.TYPE == type||Float.class==type) {
                result = Float.parseFloat(value);
            } else if (Double.TYPE == type||Double.class==type) {
                result = Double.parseDouble(value);
            } else {
                result = (Object) value;
            }
        } catch (Exception e) {
            // 把异常吞掉直接返回null
        }
        return result;
    }
}
