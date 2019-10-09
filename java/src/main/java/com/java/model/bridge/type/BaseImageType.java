package com.java.model.bridge.type;

import com.java.model.bridge.system.SystemImage;

/**
 * 这里的实现方法会调用SystemImage的方法
 * @author Ning
 * @date Create in 2019/4/15
 */
public abstract class BaseImageType {

    protected SystemImage systemImage;

    public void setSystemImage(SystemImage systemImage) {
        this.systemImage = systemImage;
    }

    /**
     * 解析图像, 需要子类实现
     * @param fileName 图片名
     */
    public abstract void parseFile(String fileName);

}
