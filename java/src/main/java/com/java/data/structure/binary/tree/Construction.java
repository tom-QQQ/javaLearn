package com.java.data.structure.binary.tree;

/**
 * @author Ning
 * @date Create in 2019/4/9
 */
public class Construction {

    /**
     * 构建二叉树
     * @param array 数据
     * @return 构建结果
     */
    static TreeNode constructBinaryTree(int[] array) {

        if (array.length == 0) {
            return null;
        }

        TreeNode rootTree = new TreeNode(array[0]);

        for (int index = 1; index < array.length; index++) {

            TreeNode treeNode = rootTree;
            while (true) {

                int existValue = treeNode.getValue();

                if (array[index] < existValue) {

                    if (treeNode.getLeftTreeNode() == null) {
                        treeNode.setLeftTreeNode(new TreeNode(array[index]));
                        break;
                    }
                    treeNode = treeNode.getLeftTreeNode();

                } else {

                    if (treeNode.getRightTreeNode() == null) {
                        treeNode.setRightTreeNode(new TreeNode(array[index]));
                        break;
                    }
                    treeNode = treeNode.getRightTreeNode();
                }
            }
        }

        return rootTree;
    }
}
