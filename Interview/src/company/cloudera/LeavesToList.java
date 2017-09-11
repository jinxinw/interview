/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.cloudera;

import util.tree.BinaryTree;
import util.tree.TreeNode;

/**
 *
 * @author Victor
 */
public class LeavesToList {

    public static TreeNode head = null;
    public static TreeNode pre = null;

    public static TreeNode leavesToList(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null || root.right == null) {
            if (pre == null) {
                head = root;
            } else {
                pre.right = root;
                root.left = pre;
            }
            pre = root;
            return null;
        }
        root.left = leavesToList(root.left);
        root.right = leavesToList(root.right);
        return root;
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        String[] s = {"1", "2", "3", "4", "5"};
        TreeNode root = bt.deserializeFromArray(s);
        leavesToList(root);
        while (head != null) {
            System.out.println(head.val);
            head = head.right;
        }
    }
}
