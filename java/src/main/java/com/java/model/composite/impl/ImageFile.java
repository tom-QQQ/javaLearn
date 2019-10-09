package com.java.model.composite.impl;

import com.java.model.composite.File;

/**
 * @author Ning
 * @date Create in 2019/4/16
 */
public class ImageFile implements File {

    private String name;

    public ImageFile(String name) {
        this.name = name;
    }

    @Override
    public void addFile(File file) {
        System.out.println("该文件不支持添加文件操作");
    }

    @Override
    public void removeFile(File file) {
        System.out.println("该文件不支持添加文件操作");
    }

    @Override
    public File getChild(int i) {
        System.out.println("该文件不支持添加文件操作");
        return null;
    }

    @Override
    public void killVirus() {
        System.out.println("---- 对图像文件" + name + "进行杀毒");
    }
}
