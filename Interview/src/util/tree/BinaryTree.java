/*
 * xinwei.penn@gmail.com
 */
package util.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {

    public BinaryTree() {

    }

    public String serialize(TreeNode root) {
        return "";
    }

    public List<String> serializeToList(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            res.add("#");
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur != null) {
                    res.add(String.valueOf(cur.val));
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                } else {
                    res.add("#");
                }
            }
        }
        return res;
    }
    
    public String[] serializeToArray(TreeNode root) {
        List<String> list = serializeToList(root);
        String[] res = new String[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public TreeNode deserializeFromList(List<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.get(0).equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(list.get(0)));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int index = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (index >= list.size() || list.get(index).equals("#")) {
                    cur.left = null;
                } else {
                    TreeNode left = new TreeNode(Integer.parseInt(list.get(index)));
                    cur.left = left;
                    queue.offer(left);
                }
                index++;
                if (index >= list.size() || list.get(index).equals("#")) {
                    cur.right = null;
                } else {
                    TreeNode right = new TreeNode(Integer.parseInt(list.get(index)));
                    cur.right = right;
                    queue.offer(right);
                }
                index++;
            }
        }
        return root;
    }
    
    public TreeNode deserializeFromArray(String[] array) {
        List<String> list = new ArrayList<>();
        for (String s : array) {
            list.add(s);
        }
        return deserializeFromList(list);
    }
    
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        String[] s = {"1", "2", "3", "4"};
        TreeNode root = bt.deserializeFromArray(s);
        System.out.println(bt.serializeToList(root));
    }
}
