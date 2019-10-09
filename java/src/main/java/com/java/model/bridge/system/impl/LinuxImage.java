package com.java.model.bridge.system.impl;

import com.java.model.bridge.Matrix;
import com.java.model.bridge.system.SystemImage;

/**
 * @author Ning
 * @date Create in 2019/4/16
 */
public class LinuxImage implements SystemImage {

    @Override
    public void doPaint(Matrix m) {
        System.out.println("在Linux系统中显示图像");
    }
}
