package com.java.model.bridge.system;

import com.java.model.bridge.Matrix;

/**
 * 实现方法会被BaseImageType的实现类调用, 因此作为主体抽象类中的对象
 * @author Ning
 * @date Create in 2019/4/15
 */
public interface SystemImage {

    /**
     * 在不同的系统上绘制图像
     * @param m 矩阵
     */
    void doPaint(Matrix m);
}
