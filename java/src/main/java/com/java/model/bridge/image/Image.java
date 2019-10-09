package com.java.model.bridge.image;

import com.java.model.bridge.Matrix;
import com.java.model.bridge.system.SystemImage;

/**
 * 使用组合也能达到相同的效果, 但是由于调用者直接调用的是ImageType的parseFile()方法, 这里的实现有多余的操作
 * @author Ning
 * @date Create in 2019/4/16
 */
public class Image {

    private SystemImage systemImage;
    private ImageType imageType;

    public Image(SystemImage systemImage, ImageType imageType) {

        this.imageType = imageType;
        this.systemImage = systemImage;
    }

    public void showImage(String fileName) {

        Matrix matrix = new Matrix();
        systemImage.doPaint(matrix);
        imageType.parseFile(fileName);
    }
}
