package com.java.model.composite.impl;

import com.java.model.composite.File;

import java.util.ArrayList;

/**
 * @author Ning
 * @date Create in 2019/4/16
 */
public class Folder implements File {

    private ArrayList<File> files = new ArrayList<>();
    private String name;

    public Folder(String name) {
        this.name = name;
    }

    @Override
    public void addFile(File file) {
        files.add(file);
    }

    @Override
    public void removeFile(File file) {

        // 删除的判断标准是equals()方法, 如有必要, 需要重写该方法
        files.remove(file);
    }

    @Override
    public File getChild(int i) {
        return files.get(i);
    }

    @Override
    public void killVirus() {

        System.out.println("******开始扫描" + name + "文件夹");

        for (File file : files) {
            file.killVirus();
        }
    }
}
