package com.java.model.bridge.type.impl;

import com.java.model.bridge.Matrix;
import com.java.model.bridge.type.BaseImageType;

/**
 * @author Ning
 * @date Create in 2019/4/16
 */
public class BMGImgae extends BaseImageType {

    @Override
    public void parseFile(String fileName) {

        Matrix matrix = new Matrix();
        systemImage.doPaint(matrix);
        System.out.println(fileName + ", 格式为BMP");
    }
}
