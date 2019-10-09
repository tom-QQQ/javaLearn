package com.java.data.structure.binary.tree;

/**
 * @author Ning
 * @date Create in 2019/4/11
 */
public class Loop {

    /**
     * 循环前序遍历输出二叉树
     * @param rootNode 二叉树
     */
    public static void preOrderTraversal(TreeNode rootNode) {

        TreeNode curNode = rootNode;
        TreeNode preNode;

        while (curNode != null) {

            if (curNode.getLeftTreeNode() == null) {

                // 打印偏子节点
                System.out.print(curNode.getValue() + " ");
                // 左节点为null时, 左节点处理完毕, 右节点在之前的循环里存放的是当时循环处理的节点或未处理的右节点, 继续处理
                curNode = curNode.getRightTreeNode();

            } else {

                // 当前节点的前驱节点(小于当前节点值的最大值对应的节点)先设置为当前节点的左节点的引用
                preNode = curNode.getLeftTreeNode();

                // 继续寻找更大的前驱节点, 这里的前驱节点的右节点可能会存放之前未处理完成的节点, 遇到时不继续寻找前驱节点, 认为当前节点就是前驱节点
                while (preNode.getRightTreeNode() != null && preNode.getRightTreeNode() != curNode) {
                    preNode = preNode.getRightTreeNode();
                }

                if (preNode.getRightTreeNode() == null) {

                    // 打印偏根节点
                    System.out.print(curNode.getValue() + " ");
                    // 这里在将前驱节点为null的右节点设置为当前节点时, 由于该前驱节点来自于当前节点, 也就是将当前节点的前驱节点的右节点修改为当前节点
                    preNode.setRightTreeNode(curNode);
                    // 继续处理左节点
                    curNode = curNode.getLeftTreeNode();
                    System.out.print("");

                    // 这里用来判断当前节点的左节点是否处理完成, 这里的右节点会由于之前if的逻辑是curNode, 表明左节点全部处理完成 因此这里恢复之前的前驱节点修改, 开始处理右节点
                } else {
                    preNode.setRightTreeNode(null);
                    curNode = curNode.getRightTreeNode();
                    System.out.print("");
                }
            }
        }
    }

    /**
     * 循环中序遍历输出二叉树
     * @param treeNode 二叉树
     */
    public static void inOrderTraversal(TreeNode treeNode) {

        TreeNode curNode = treeNode;
        TreeNode preNode;

        while (curNode != null) {

            if (curNode.getLeftTreeNode() == null) {

                // 这里打印偏子节点
                System.out.print(curNode.getValue() + " ");
                curNode = curNode.getRightTreeNode();

            } else {

                preNode = curNode.getLeftTreeNode();

                while (preNode.getRightTreeNode() != null && preNode.getRightTreeNode() != curNode) {

                    preNode = preNode.getRightTreeNode();
                }

                if (preNode.getRightTreeNode() == null) {

                    System.out.println("右节点为空时, 当前节点为: " + curNode.getValue() + " ");
                    preNode.setRightTreeNode(curNode);
                    curNode = curNode.getLeftTreeNode();


                } else {
                    // 这里的打印偏根节点
                    System.out.print(curNode.getValue() + " ");
                    preNode.setRightTreeNode(null);
                    curNode = curNode.getRightTreeNode();
                }
            }
        }
    }

    /**
     * 循环后序遍历输出二叉树
     * @param treeNode 二叉树
     */
    public static void postOrderTraversal(TreeNode treeNode) {

        TreeNode curNode = new TreeNode(-1, treeNode, null);
        TreeNode preNode;

        while (curNode != null) {


            if (curNode.getLeftTreeNode() == null) {

                curNode = curNode.getRightTreeNode();

            } else {

                preNode = curNode.getLeftTreeNode();

                while (preNode.getRightTreeNode() != null && preNode.getRightTreeNode() != curNode) {
                    preNode = preNode.getRightTreeNode();
                }

                if (preNode.getRightTreeNode() == null) {

                    preNode.setRightTreeNode(curNode);
                    curNode = curNode.getLeftTreeNode();

                } else {

                    preNode.setRightTreeNode(null);
                    // 打印单独的左节点或反向打印连续右节点
                    // 可以应用这个方法快速写出任意二叉树的后序遍历, 从最左边的叶子节点开始, 如果当前叶子节点是父节点的左节点时直接写出; 如果该叶子节点是父节点的右节点, 逆序从该叶子节点向上写到节点不是父节点的右节点为止, 之后继续下一个叶子节点的处理, 直到写完全部叶子节点
                    reversePrint(curNode.getLeftTreeNode(), preNode);
                    curNode = curNode.getRightTreeNode();
                }
            }
        }
    }

    /**
     * 逆序输出节点, 逆序输出从当前节点到前序节点
     * @param fromNode 当前节点
     * @param toNode 前序节点
     */
    private static void reversePrint(TreeNode fromNode, TreeNode toNode) {

        if (fromNode.equals(toNode)) {

            System.out.print(fromNode.getValue() + " ");
            return;
        }

        reversePrint(fromNode.getRightTreeNode(),toNode);
        System.out.print(fromNode.getValue() + " ");
    }
}
