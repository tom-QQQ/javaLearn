package com.java.utils;

import com.java.model.command.log.Command;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ning
 * @date Create in 2019/4/23
 */
public class FileUtil {

    public static void writeCommands(List<Command> commands) {

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(getSystemContent() + "config.log")))) {

            objectOutputStream.writeObject(commands);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Command> readCommands() {

        try (ObjectInputStream objectOutputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(getSystemContent() + "config.log")))) {

            return (ArrayList<Command>) objectOutputStream.readObject();

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static String getSystemContent() {

        return System.getProperty("user.dir") + "/java/src/";
    }
}
