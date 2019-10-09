package com.java.model.facade.encrypt.subclass;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * @author Ning
 * @date Create in 2019/4/17
 */
public class FileWrite {

    public void writeFile(String content) {


        String systemContent = System.getProperty("user.dir");
        File file = new File(systemContent + "/java/src/" + "b.txt");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {

            bufferedWriter.write(content);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        FileWrite fileWrite = new FileWrite();
        fileWrite.writeFile("5555");

    }
}
