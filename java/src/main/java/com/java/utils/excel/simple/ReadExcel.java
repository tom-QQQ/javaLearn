package com.java.utils.excel.simple;

import com.java.utils.excel.ExcelClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class ReadExcel {

    public static void main(String[] args)  {

        File file = new File("D:\\learnBySelf\\项目学习笔记\\32.加西贝拉\\生产迁移\\库存量报表1.xls");


        String[] filedsName = {"storerkey","sku","descr","lot","loc","id","qty","qtyallocated","qtypicked",
                "qtyexpected","qtypickinprocess","lottable01","lottable02","lottable03","lottable04","lottable05",
                "lottable06","lottable07","company","lottable08","lottable09","lottable10","pendingmovein",
                "available"};
        try (InputStream in = new FileInputStream(file)) {

            ExcelClass excelClass = new ExcelClass();
            List<MesData> list =  excelClass.readExcel(in, MesData.class, filedsName, 0, true);
            System.out.println("status");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
