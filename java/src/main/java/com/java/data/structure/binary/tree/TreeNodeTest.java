package com.java.data.structure.binary.tree;


/**
 * @author Ning
 * @date Create in 2019/4/9
 */
public class TreeNodeTest {

    public static void main(String[] args) {

        TreeNode rootTreeNode = Construction.constructBinaryTree(new int[]{17, 9, 20, 15, 35});
//
        System.out.println("二叉树广度优先遍历, 按照层级, 从根节点一层层到叶子节点层");
        Recursive.layerTraver(rootTreeNode);
//
        System.out.println("前序递归遍历二叉树, 根-左-右");
        Recursive.preOrderTraversal(rootTreeNode);
//        System.out.println("循环前序遍历二叉树");
//        Loop.preOrderTraversal(rootTreeNode);
//        System.out.println("");
//
//        System.out.println("中序递归遍历二叉树, 左-根-右");
//        Recursive.inOrderTraversal(rootTreeNode);
//        System.out.println("循环中序遍历二叉树");
//        Loop.inOrderTraversal(rootTreeNode);
//        System.out.println("");
//
//        System.out.println("后序递归遍历二叉树, 左-右-根");
//        Recursive.postOrderTraversal(rootTreeNode);
//        System.out.println("循环后序遍历二叉树");
//        Loop.postOrderTraversal(rootTreeNode);
//        System.out.println("");
//
//        System.out.println("二叉树深度为: " + Recursive.calculateDepth(rootTreeNode));
//        System.out.println("二叉树节点数量: " + Recursive.count(rootTreeNode));
//        System.out.println("二叉树叶子节点数量: " + Recursive.countLeave(rootTreeNode));
//        System.out.println("第2层数节点数量为: " + Recursive.countCountsOfLayer(rootTreeNode, 2));
//
//        System.out.println("比较两个二叉树结构是否相同: " + Recursive.compareStructure(rootTreeNode, rootTreeNode));
//        System.out.println("生成镜像二叉树");
//        TreeNode mirrorTreeNode = Recursive.mirrorTreeNode(rootTreeNode);
//        Recursive.layerTraver(mirrorTreeNode);
//
//        System.out.print("查询两个节点的最低公共祖先节点: ");
//        TreeNode ancestor = Recursive.findLCA(rootTreeNode, new TreeNode(35), new TreeNode(15));
//        if (ancestor != null) {
//            System.out.println(ancestor.getValue());
//        }
//
//        System.out.println("祖先节点和子节点之间的距离为: " + Recursive.findDistanceOfTwoNodes(rootTreeNode, new TreeNode(35)));
//        System.out.println("两个子节点之间的距离为: " + Recursive.findDistanceOfTwoNodes(rootTreeNode, new TreeNode(9), new TreeNode(35)));
//        System.out.print("查找全部祖先节点 : ");
//        Recursive.findAllAncestors(rootTreeNode, new TreeNode(35));
//
//
//        int newValue = 35;
//        System.out.println("二叉树新增值: " + newValue);
//        TreeNode treeNode = Recursive.addOrFindValue(rootTreeNode, newValue);
//        if (treeNode != null) {
//            System.out.println("查询到已存在的数据");
//        } else {
//            System.out.println("新二叉树");
//            Recursive.layerTraver(rootTreeNode);
//        }


//        TreeNode rootTreeNode = Construction.constructBinaryTree(new int[]{8, 4, 2, 1, 3, 6, 5, 7, 12, 10, 9, 11, 14, 13, 15});
//        Recursive.layerTraver(rootTreeNode);

//        Loop.postOrderTraversal(rootTreeNode);


    }
}
