import java.util.*;

public class TreeTraversal {

    private static List<Integer> recursiveInOrder(TreeNode node, List<Integer> treeList) {
        if(node == null) {
            return treeList;
        }
        treeList = recursiveInOrder(node.left, treeList);
        treeList.add(node.val);
        treeList = recursiveInOrder(node.right, treeList);
        return treeList;
    }

    private static List<Integer> iterativeInOrder(TreeNode node, List<Integer> treeList) {
        Stack<TreeNode> treeStack = new Stack<TreeNode>();
        while(!treeStack.empty() || node != null) {
            if(node != null) {
                treeStack.push(node);
                node = node.left;
            } else {
                node = treeStack.pop();
                treeList.add(node.val);
                node = node.right;
            }
        }
        return treeList;
    }

    private static List<Integer> recursivePreOrder(TreeNode node, List<Integer> treeList) {
        if(node == null) {
            return treeList;
        }
        treeList.add(node.val);
        recursivePreOrder(node.left, treeList);
        recursivePreOrder(node.right, treeList);
        return treeList;
    }

    private static List<Integer> iterativePreOrder(TreeNode node, List<Integer> treeList) {
        Stack<TreeNode> treeStack = new Stack<TreeNode>();
        while(!treeStack.empty() || node != null) {
            if(node != null) {
                treeList.add(node.val);
                treeStack.push(node);
                node = node.left; 
            } else {
                node = treeStack.pop();
                node = node.right;
            }
        }
        return treeList;
    }

    private static List<Integer> recursivePostOrder(TreeNode node, List<Integer> traversalList) {
        if(node == null) {
            return traversalList;
        }
        traversalList = recursivePostOrder(node.right, traversalList);
        traversalList.add(node.val);
        traversalList = recursivePostOrder(node.left, traversalList);
        return traversalList;
    }

    private static List<Integer> iterativePostOrder(TreeNode node, List<Integer> traversalList) {
        Stack<TreeNode> treeStack = new Stack<TreeNode>();
        while(!treeStack.empty() || node != null) {
            if(node != null) {
                treeStack.push(node);
                node = node.right;
            } else {
                node = treeStack.pop();
                traversalList.add(node.val);
                node = node.left;
            }
        }
        return traversalList;
    }

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1, 
                                    new TreeNode(4), 
                                    new TreeNode(2, 
                                        new TreeNode(3), 
                                        null));
        
        List<Integer> treeList = new ArrayList<Integer>();
        System.out.println("Recursive Pre-Order Traversal: ");
        treeList = recursivePreOrder(tree, treeList);
        System.out.println(Arrays.toString(treeList.toArray())+"\n");
        
        treeList = new ArrayList<Integer>();
        System.out.println("Iterative Pre-Order Traversal: ");
        treeList = iterativePreOrder(tree, treeList);
        System.out.println(Arrays.toString(treeList.toArray())+"\n");

        treeList = new ArrayList<Integer>();
        System.out.println("Recursive In-Order Traversal: ");
        treeList = recursiveInOrder(tree, treeList);
        System.out.println(Arrays.toString(treeList.toArray())+"\n");
        
        treeList = new ArrayList<Integer>();
        System.out.println("Iterative In-Order Traversal: ");
        treeList = iterativeInOrder(tree, treeList);
        System.out.println(Arrays.toString(treeList.toArray())+"\n");

        treeList = new ArrayList<Integer>();
        System.out.println("Recursive Post-Order Traversal: ");
        treeList = recursivePostOrder(tree, treeList);
        System.out.println(Arrays.toString(treeList.toArray())+"\n");

        treeList = new ArrayList<Integer>();
        System.out.println("Iterative Post-Order Traversal: ");
        treeList = iterativePostOrder(tree, treeList);
        System.out.println(Arrays.toString(treeList.toArray())+"\n");
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return this.val+"";
    }
}
