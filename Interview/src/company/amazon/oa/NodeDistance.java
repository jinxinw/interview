
package company.amazon.oa;

import java.util.LinkedList;
import java.util.Queue;

public class NodeDistance {
    
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    public int bstDistance(int[] values, int n, int node1, int node2) {
        TreeNode root = build(values);
        TreeNode parent = lowestCommonAncestor(root, node1, node2);
        int level1 = -1;
        int level2 = -1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(parent);
        int level = 0;
        while(!queue.isEmpty()) {
            if (level1 > 0 && level2 > 0) {
                break;
            }
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.val == node1) {
                    level1 = level;
                }
                if (cur.val == node2) {
                    level2 = level;
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            level++;
        }
        return level1 + level2;
    }
    
    private TreeNode build(int[] values) {
        TreeNode root = new TreeNode(values[0]);
        for (int i = 1; i < values.length; i++) {
            insert(root, values[i]);
        }
        return root;
    }
    
    private void insert(TreeNode root, int value) {
        TreeNode parent = null;
        while(root != null) {
            parent = root;
            if (value < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        if (value < parent.val) {
            parent.left = new TreeNode(value);
        } else {
            parent.right = new TreeNode(value);
        }
    }
    
    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.println(root.val);
        inorder(root.right);
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        if (root == null) {
            return root;
        }
        while((root.val - p) * (root.val - q) > 0) {
            if (root.val > p) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return root;
    }
    
    public static void main(String[] args) {
        NodeDistance nd = new NodeDistance();
        int[] values = {5,6,3,1,2,4};
        int res = nd.bstDistance(values, 6, 5, 2);
        System.out.println("res: " + res);
    }
    
}
