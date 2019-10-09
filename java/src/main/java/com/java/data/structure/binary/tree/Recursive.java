package com.java.data.structure.binary.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ning
 * @date Create in 2019/4/9
 */
public class Recursive {

    /**
     * 前序遍历, 根-左-右
     * @param treeNode 二叉树对象
     */
    public static void preOrderTraversal(TreeNode treeNode) {

        if (treeNode != null) {
            System.out.print(treeNode.getValue() + " ");

            preOrderTraversal(treeNode.getLeftTreeNode());

            preOrderTraversal(treeNode.getRightTreeNode());
        }
    }

    /**
     * 中序遍历, 左-根-右
     * @param treeNode 二叉树对象
     */
    public static void inOrderTraversal(TreeNode treeNode) {

        if (treeNode != null) {

            inOrderTraversal(treeNode.getLeftTreeNode());

            System.out.print(treeNode.getValue() + " ");

            inOrderTraversal(treeNode.getRightTreeNode());
        }
    }

    /**
     * 后序遍历二叉树, 左-右-根
     * @param treeNode 二叉树对象
     */
    public static void postOrderTraversal(TreeNode treeNode) {

        if (treeNode != null) {

            postOrderTraversal(treeNode.getLeftTreeNode());

            postOrderTraversal(treeNode.getRightTreeNode());

            System.out.print(treeNode.getValue() + " ");
        }
    }

    /**
     * 计算二叉树深度
     * @param treeNode 二叉树对象
     * @return 深度
     */
    public static int calculateDepth(TreeNode treeNode) {

        if (treeNode == null) {
            return 0;
        }

        int leftDepth = calculateDepth(treeNode.getLeftTreeNode());

        int rightDepth = calculateDepth(treeNode.getRightTreeNode());

        return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
    }

    /**
     * 计算节点数
     * @param treeNode 二叉树
     * @return 节点数
     */
    public static int count(TreeNode treeNode) {

        if (treeNode != null) {

            // 当前层一个
            int num = 1;

            // 加上左右子节点的节点数量
            num += count(treeNode.getLeftTreeNode());
            num += count(treeNode.getRightTreeNode());

            return num;
        }

        return 0;
    }

    /**
     * 统计叶子节点数量
     * @param treeNode 二叉树对象
     * @return 叶子节点数量
     */
    public static int countLeave(TreeNode treeNode) {

        if (treeNode != null) {

            int num = 0;

            // 当前节点下没有子节点时, 当前节点为叶子节点
            if (treeNode.getRightTreeNode() == null && treeNode.getLeftTreeNode() == null) {
                num+=1;
            }

            num += countLeave(treeNode.getLeftTreeNode());
            num += countLeave(treeNode.getRightTreeNode());
            return num;
        }

        return 0;
    }

    /**
     * 统计指定层数节点数量
     * @param treeNode 二叉树
     * @param layer 层数
     * @return 对应层数的节点数量
     */
    public static int countCountsOfLayer(TreeNode treeNode, int layer) {

        if (layer == 1) {
            return 1;
        }

        if (treeNode == null || layer < 1) {
            return 0;
        }

        return countCountsOfLayer(treeNode.getLeftTreeNode(), layer - 1) + countCountsOfLayer(treeNode.getRightTreeNode(), layer - 1);
    }

    /**
     * 比较两个二叉树结构是否相同
     * @param treeNode1 二叉树
     * @param treeNode2 二叉树
     * @return 是否相同
     */
    public static boolean compareStructure(TreeNode treeNode1, TreeNode treeNode2) {


        if (treeNode1 == null && treeNode2 == null) {
            return true;

        } else if (treeNode1 == null || treeNode2 == null) {
            return false;
        }

        return compareStructure(treeNode1.getLeftTreeNode(), treeNode2.getLeftTreeNode()) && compareStructure(treeNode1.getRightTreeNode(), treeNode2.getRightTreeNode());

    }

    /**
     * 创建一个二叉树对象的镜像对象
     * @param treeNode 原始二叉树对象
     * @return 镜像对象
     */
    public static TreeNode mirrorTreeNode(TreeNode treeNode) {

        if (treeNode != null) {

            TreeNode treeNode1 = new TreeNode(treeNode.getValue());

            treeNode1.setLeftTreeNode(mirrorTreeNode(treeNode.getRightTreeNode()));

            treeNode1.setRightTreeNode(mirrorTreeNode(treeNode.getLeftTreeNode()));

            return treeNode1;
        }

        return null;
    }

    /**
     * 查询2个节点的最低祖先节点
     * @param node 二叉树
     * @param treeNode1 查询节点1
     * @param treeNode2 查询节点2
     * @return 最低祖先节点
     */
    public static TreeNode findLCA(TreeNode node, TreeNode treeNode1, TreeNode treeNode2) {

        if (node == null) {
            return null;
        }

        if (node.equals(treeNode1) || node.equals(treeNode2)) {
            return node;
        }

        TreeNode leftNode = findLCA(node.getLeftTreeNode(), treeNode1, treeNode2);
        TreeNode rightNode = findLCA(node.getRightTreeNode(), treeNode1, treeNode2);

        // 如果左右节点分别不为null, 即要查找的两个节点分别在当前的左右子节点下, 返回当前节点
        if (leftNode != null && rightNode != null) {
            return node;
        }

        // 否则返回不为null的节点
        return leftNode != null ? leftNode : rightNode;
    }

    /**
     * 查询两个子节点在指定二叉树中的距离
     * @param rootNode 二叉树
     * @param treeNode1 节点1
     * @param treeNode2 节点2
     * @return 距离, 即两个子节点到所在二叉树的最低公共祖先的距离和
     */
    public static int findDistanceOfTwoNodes(TreeNode rootNode, TreeNode treeNode1, TreeNode treeNode2) {

        TreeNode lcaNode = findLCA(rootNode, treeNode1, treeNode2);

        if (lcaNode != null) {

            int distance1 = findDistanceOfTwoNodes(lcaNode, treeNode1);
            int distance2 = findDistanceOfTwoNodes(lcaNode, treeNode2);

            if (distance1 != -1 && distance2 != -1) {
                return distance1 + distance2;
            }

            return -1;
        }

        return -1;
    }

    /**
     * 查询祖先节点和子节点之间的距离
     * @param rootNode node节点的祖先节点
     * @param node 子节点
     * @return 距离, 子节点不是祖先节点的子节点时返回-1
     */
    public static int findDistanceOfTwoNodes(TreeNode rootNode, TreeNode node) {

        // 没有时返回-1
        if (rootNode == null) {
            return -1;
        }

        // 找到时返回0
        if (rootNode.equals(node)) {
            return 0;
        }

        // 先去左子树查找
        int distance = findDistanceOfTwoNodes(rootNode.getLeftTreeNode(), node);

        // 没有就去右子树查找
        if (distance == -1) {
            distance = findDistanceOfTwoNodes(rootNode.getRightTreeNode(), node);
        }

        // 找到返回距离+1
        if (distance != -1) {
            return distance + 1;
        }

        // 否则返回-1
        return -1;
    }


    /**
     * 打印指定节点的全部先祖节点的值
     * @param rootNode 二叉树
     * @param treeNode 指定节点
     * @return 当前二叉树下是否存在指定节点
     */
    public static boolean findAllAncestors(TreeNode rootNode, TreeNode treeNode) {

        if (rootNode == null) {
            return false;
        }

        if (rootNode.equals(treeNode)) {
            return true;
        }

        if (findAllAncestors(rootNode.getLeftTreeNode(), treeNode) || findAllAncestors(rootNode.getRightTreeNode(), treeNode)) {
            System.out.print(rootNode.getValue() + " ");
            return true;
        }

        return false;
    }

    /**
     * 广度优先遍历, 按照层输出二叉树的值
     * @param treeNode 二叉树对象
     */
    public static void layerTraver(TreeNode treeNode) {

        List<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.add(treeNode);
        printLayerValues(treeNodes);
    }

    private static void printLayerValues(List<TreeNode> treeNodes) {

        if (treeNodes.size() != 0) {

            boolean over = true;
            List<TreeNode> nextLayerNodes = new ArrayList<>();

            for (TreeNode treeNode : treeNodes) {

                // 打印当前层所有的值
                System.out.print(treeNode.getValue() + " ");

                // 添加下一层可能存在的左右节点的值
                if (treeNode.getLeftTreeNode() != null)  {

                    // 有值时标记需要继续迭代
                    over = false;
                    nextLayerNodes.add(treeNode.getLeftTreeNode());

                    // 没有时添加值为null的叶子节点
                } else {
                    nextLayerNodes.add(new TreeNode(null));
                }

                if (treeNode.getRightTreeNode() != null)  {
                    over = false;
                    nextLayerNodes.add(treeNode.getRightTreeNode());

                } else {
                    nextLayerNodes.add(new TreeNode(null));
                }

            }
            System.out.println("");
            // 如果下一层没有值, 迭代完成
            if (over) {
                return;
            }
            // 否则继续迭代
            printLayerValues(nextLayerNodes);
        }
    }

    /**
     * 添加值或查找值
     * @param treeNode 二叉树对象
     * @param value 要添加或查找的值, 没有时添加, 有时查找
     */
    public static TreeNode addOrFindValue(TreeNode treeNode, int value) {

        if (treeNode != null) {

            if (treeNode.getValue() > value) {


                if (treeNode.getLeftTreeNode() == null) {
                    TreeNode newNode = new TreeNode(value);
                    treeNode.setLeftTreeNode(newNode);
                    return null;
                }
                return addOrFindValue(treeNode.getLeftTreeNode(), value);

            } else if (treeNode.getValue() < value) {

                if (treeNode.getRightTreeNode() == null) {
                    TreeNode newNode = new TreeNode(value);
                    treeNode.setRightTreeNode(newNode);
                    return null;
                }
                return addOrFindValue(treeNode.getRightTreeNode(), value);

            } else {
                return treeNode;
            }
        }
        return null;
    }
}
