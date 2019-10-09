package com.java.model.bridge;

import com.java.model.bridge.system.SystemImage;
import com.java.model.bridge.system.impl.WindowsImage;
import com.java.model.bridge.type.BaseImageType;
import com.java.model.bridge.type.impl.GIFImgae;

/**
 * 桥接模式用于解除两个存在调用关系的对象之间的耦合
 * @author Ning
 * @date Create in 2019/4/16
 */
public class BridgeTest {

    public static void main(String[] args) {

        SystemImage windowsImage = new WindowsImage();
        BaseImageType gifImage = new GIFImgae();
        gifImage.setSystemImage(windowsImage);
        gifImage.parseFile("否认三连");
    }
}
