package com.java.model.command.log;

import java.io.Serializable;

/**
 * @author Ning
 * @date Create in 2019/4/23
 */
public class ConfigOperator implements Serializable {

    public void insertNode(String args) {
        System.out.println("增加新节点: " + args);
    }

    public void modifyNode(String args) {
        System.out.println("修改节点: " + args);
    }

    public void deleteNode(String args) {
        System.out.println("删除节点: " + args);
    }
}
