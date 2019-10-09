package com.java.data.structure.binary.tree;

import java.util.Objects;

/**
 * @author Ning
 * @date Create in 2019/4/9
 */
public class TreeNode {

    private Integer value;

    private TreeNode leftTreeNode;
    private TreeNode rightTreeNode;

    /**
     * 叶子节点构造器
     * @param value 叶子节点的值
     */
    TreeNode(Integer value){
        this(value, null, null);
    }

    TreeNode(Integer value, TreeNode leftTreeNode, TreeNode rightTreeNode) {

        this.value = value;
        this.leftTreeNode = leftTreeNode;
        this.rightTreeNode = rightTreeNode;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeftTreeNode() {
        return leftTreeNode;
    }

    public void setLeftTreeNode(TreeNode leftTreeNode) {
        this.leftTreeNode = leftTreeNode;
    }

    public TreeNode getRightTreeNode() {
        return rightTreeNode;
    }

    public void setRightTreeNode(TreeNode rightTreeNode) {
        this.rightTreeNode = rightTreeNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TreeNode)) {
            return false;
        }
        TreeNode treeNode = (TreeNode) o;
        return Objects.equals(value, treeNode.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }
}
