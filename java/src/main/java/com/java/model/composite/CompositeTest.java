package com.java.model.composite;

import com.java.model.composite.impl.Folder;
import com.java.model.composite.impl.ImageFile;
import com.java.model.composite.impl.TextFile;
import com.java.model.composite.impl.VideoFile;

/**
 * 用于处理树形结构的数据, 对叶子节点和中间节点有不同的处理
 * @author Ning
 * @date Create in 2019/4/16
 */
public class CompositeTest {

    public static void main(String[] args) {

        File folder = new Folder("Sunny的资料");
        File imageFolder = new Folder("图像文件");
        File txtFolder = new Folder("文本文件");
        File videoFolder = new Folder("视频文件");

        File imageFile = new ImageFile("小龙女.jpg");
        File imageFile1 = new ImageFile("张无忌.gif");
        File textFile = new TextFile("九阴真经.txt");
        File textFile1 = new TextFile("葵花宝典.doc");
        File videoFile = new VideoFile("笑傲江湖.rmvb");

        imageFolder.addFile(imageFile);
        imageFolder.addFile(imageFile1);
        txtFolder.addFile(textFile);
        txtFolder.addFile(textFile1);
        videoFolder.addFile(videoFile);

        folder.addFile(imageFolder);
        folder.addFile(txtFolder);
        folder.addFile(videoFolder);

        folder.killVirus();

    }

}
