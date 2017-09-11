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
public class TreeToList {
    public static TreeNode treeToList(TreeNode root) {
        if (root == null) {
            return root;
        }
        if (root.left != null) {
            TreeNode left = treeToList(root.left);
            while(left.right != null) {
                left = left.right;
            }
            root.left = left;
            left.right = root;
        }
        if (root.right != null) {
            TreeNode right = treeToList(root.right);
            while(right.left != null) {
                right = right.left;
            }
            root.right = right;
            right.left = root;
        }
        return root;
    }
    
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        String[] s = {"1", "2", "3", "4"};
        TreeNode root = bt.deserializeFromArray(s);
        TreeNode node = treeToList(root);
        while(node.left != null) {
            node = node.left;
        }
        while(node != null) {
            System.out.println(node.val);
            node = node.right;
        }
        
    }
}
