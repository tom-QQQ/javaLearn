package com.java.model.composite;

/**
 * 由于叶子实现类不支持addFile(), removeFile(), getChild()方法, 这些方法可以在当前类实现或在叶子类实现, 各有利弊, 需根据实际情况做选择
 * @author Ning
 * @date Create in 2019/4/16
 */
public interface File {

    /**
     * 添加一个文件或文件夹, 主体只能是文件夹
     * @param file 文件或文件夹
     */
    void addFile(File file);

    /**
     * 从文件夹里删除一个文件或文件夹
     * @param file 文件或文件夹
     */
    void removeFile(File file);

    /**
     * 从文件夹中获取一个文件或文件夹
     * @param i 索引
     * @return 文件或文件夹
     */
    File getChild(int i);

    /**
     * 对文件或文件夹进行杀毒
     */
    void killVirus();
}
