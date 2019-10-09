package com.java.model.facade.encrypt.subclass;


import org.springframework.util.StringUtils;

import java.io.*;

/**
 * @author Ning
 * @date Create in 2019/4/17
 */
public class FileRead {

    public String read(String fileName) {

        String systemContent = System.getProperty("user.dir");
        File file = new File(systemContent + "/java/src/" + fileName);
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {

            String line;
            while (!StringUtils.isEmpty(line = bufferedReader.readLine())) {
                stringBuilder.append(line);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();

    }

    public static void main(String[] args) {
        FileRead fileRead = new FileRead();
        System.out.println(fileRead.read("a.txt"));
    }
}
