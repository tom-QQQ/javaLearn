package com.java.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * 指定时间前的qq图片文件清除
 */
public class QQImage {


    /**
     * 是否删除
     */
    static boolean isDelete = false;

    /**
     * 最晚删除时间
     */
    static Long deleteTime;

    /**
     * 删除文件大小统计, 单位B
     */
    static Long size = 0L;

    static {

        Calendar calendar  = Calendar.getInstance();
        calendar.set(2021, Calendar.JANUARY, 1, 0, 0, 0);
        deleteTime = calendar.getTime().getTime();
    }

    public static void main(String[] args) {
        deleteFiles("D:\\QQ\\506868192\\Image\\Group2");
        System.out.println("删除文件大小" + calculateSize(size));
    }

    /**
     * 递归添加文件名
     * @param filePath 文件根路径
     */
    private static void deleteFiles(String filePath) {

        File file = new File(filePath);

        if (!file.isDirectory()) {
            deleteFile(file);
            return;
        }

        File[] files = file.listFiles();

        if (files == null || files.length == 0) {
            return;
        }

        for (File f : files) {
            if (f.isDirectory()) {
                if (Objects.requireNonNull(f.listFiles()).length != 0) {
                    deleteFiles(f.getAbsolutePath());
                }
            } else {
                deleteFile(f);
            }
        }
    }

    private static void deleteFile(File file) {

        // 图片一般修改时间和创建时间相同, 这里取修改时间
        if (file.lastModified() < deleteTime) {
            if (isDelete) {
                file.delete();
                System.out.println("删除了文件".concat(file.getName()));
            }
            size += file.length();
        }
    }

    /**
     * 获取文件创建时间
     * @param filePath 文件路径
     * @return 创建时间
     */
    private Long getFileCreateTime(String filePath) {

        File file = new File(filePath);
        try {
            Path path= Paths.get(filePath);
            BasicFileAttributeView basicview= Files.getFileAttributeView(path, BasicFileAttributeView.class,
                    LinkOption.NOFOLLOW_LINKS);
            BasicFileAttributes attr = basicview.readAttributes();
            return attr.creationTime().toMillis();
        } catch (Exception e) {
            e.printStackTrace();
            return file.lastModified();
        }
    }

    private static String calculateSize(Long size) {

        int[] arrays = new int[]{1024, 1024, 1024, 1024};
        String[] unit = new String[]{"B", "KB", "MB", "GB"};

        long higherPart = size;
        long currentPart ;
        long temp;
        List<String> result = new ArrayList<>(16);

        for (int index = 0; index < arrays.length; index++) {

            int value = arrays[index];
            temp = higherPart;
            higherPart = higherPart/value;
            currentPart = temp - higherPart*value;

            if (currentPart > 0) {
                result.add(currentPart + unit[index]);
            }

            if (higherPart == 0) {
                return timePartToString(result);
            }
        }

        return timePartToString(result);
    }

    private static String timePartToString(List<String> timeParts) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int index = timeParts.size() - 1; index > -1; index--) {
            stringBuilder.append(timeParts.get(index)).append(" ");
        }
        return stringBuilder.toString();

    }
}
