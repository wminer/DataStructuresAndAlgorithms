import java.util.*;

public class BinarySearchTree {
    TreeNode root;

    public BinarySearchTree() {}

    public BinarySearchTree(int val) {
        this.root = new TreeNode(val);
    }

    public BinarySearchTree(TreeNode node) {
        this.root = node;
    }

    public void Insert(int val) {
        System.out.println("Inserting " + val + " into Tree.");
        TreeNode node = new TreeNode(val);
        if(this.root == null) {
            this.root = node;
            return;
        }
        TreeNode runner = this.root;
        while((runner.left != null && runner.val >= val) || (runner.right != null && runner.val < val)) {
            if(runner.val >= val) {
                runner = runner.left;
            } else {
                runner = runner.right;
            }
        }
        if(runner.val >= val) {
            runner.left = node;
            return;
        } else {
            runner.right = node;
            return;
        }
    }

    public void Delete(int val) {
        System.out.println("Deleting " + val + " from Tree.");
        if(root == null) return;
        TreeNode successor, successorParent;
        successorParent = new TreeNode();
        // CASE 1: Val is root
        if(root.val == val) {
            successor = root.right;
            while(successor != null && successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            if(successor == null) { // No in-order successor
                if(root.left == null) {
                    root = null;
                    return;
                }
                successor = root.left;
                while(successor != null && successor.right != null) {
                    successorParent = successor;
                    successor = successor.right;
                }
                successorParent.right = successor.left;
                if(successor != root.left) {
                    successor.left = root.left;
                }
            } else { // in-order successor exists
                successor.left = root.left;
                successorParent.left = successor.right;
                if(successor != root.right) {
                    successor.right = root.right; 
                }
            }
            root = successor;
            return;
        }
        // CASE 2: Val is root's child
        TreeNode parent = root;
        TreeNode child = root.val > val ? root.left : root.right;
        if(child == null) return;
        if(child.val == val) {
            if(parent.right == child) {
                if(child.left == null && child.right == null) {
                    parent.right = null;
                } else if(child.left == null && child.right != null) {
                    parent.right = child.right;
                } else if(child.left != null && child.right == null) {
                    parent.right = child.left;
                } else { // child.left != null && child.right != null
                    successor = child.right;
                    while(successor.left != null) {
                        successorParent = successor;
                        successor = successor.left;
                    }
                    successorParent.left = successor.right;
                    successor.left = child.left;
                    if(successor != child.right) successor.right = child.right;
                    parent.right = successor;
                }
            } else if(parent.left == child) {
                if(child.left == null && child.right == null) {
                    parent.left = null;
                } else if(child.left == null && child.right != null) {
                    parent.left = child.right;
                } else if(child.left != null && child.right == null) {
                    parent.left = child.left;
                } else { // child.left != null && child.right != null
                    successor = child.right;
                    while(successor.left != null) {
                        successorParent = successor;
                        successor = successor.left;
                    }
                    successorParent.left = successor.right;
                    successor.left = child.left;
                    if(successor != child.right) successor.right = child.right;
                    parent.left = successor;
                }
            }
            return;
        }
        
        // CASE 3: Val is not root and not root's child 
        if(child != null) {
            while(child != null && child.val != val) {
                parent = child;
                child = child.val > val ? child.left: child.right;
            }
            if(child == null) return;
            if(parent.right == child) {
                if(child.left == null && child.right == null) {
                    parent.right = null;
                } else if(child.left == null && child.right != null) {
                    parent.right = child.right;
                } else if(child.left != null && child.right == null) {
                    parent.right = child.left;
                } else { // child.left != null && child.right != null
                    successor = child.right;
                    while(successor.left != null) {
                        successorParent = successor;
                        successor = successor.left;
                    }
                    successorParent.left = successor.right;
                    successor.left = child.left;
                    if(successor != child.right) successor.right = child.right;
                    parent.right = successor;
                }
            } else if(parent.left == child) {
                if(child.left == null && child.right == null) {
                    parent.left = null;
                } else if(child.left == null && child.right != null) {
                    parent.left = child.right;
                } else if(child.left != null && child.right == null) {
                    parent.left = child.left;
                } else { // child.left != null && child.right != null
                    successor = child.right;
                    while(successor.left != null) {
                        successorParent = successor;
                        successor = successor.left;
                    }
                    successorParent.left = successor.right;
                    successor.left = child.left;
                    if(successor != child.right) successor.right = child.right;
                    parent.left = successor;
                }
            }
        }
        return;
    }

    private static List<Integer> recursiveInOrder(TreeNode node, List<Integer> treeList) {
        if(node == null) {
            //treeList.add(null);
            return treeList;
        }
        treeList = recursiveInOrder(node.left, treeList);
        treeList.add(node.val);
        treeList = recursiveInOrder(node.right, treeList);
        return treeList;
    }

    private static List<Integer> recursivePreOrder(TreeNode node, List<Integer> treeList) {
        if(node == null) {
            treeList.add(null);
            return treeList;
        }
        // System.out.println("node.val is: " + node.val);
        treeList.add(node.val);
        recursivePreOrder(node.left, treeList);
        recursivePreOrder(node.right, treeList);
        return treeList;
    }

    public String toString() {
        List<Integer> treeList = new ArrayList<Integer>();
        //treeList = recursiveInOrder(root, treeList);
        treeList = recursivePreOrder(root,treeList);
        return Arrays.toString(treeList.toArray());
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        /*  
            tree.Insert(2);
            tree.Insert(4);
            tree.Insert(3);
            tree.Insert(1);
            tree.Insert(7);

            tree.Insert(5);
            tree.Insert(21);
            tree.Insert(25);
            tree.Insert(19);
            tree.Insert(20);

            tree.Insert(6);
            tree.Insert(10);
            tree.Insert(3);
            tree.Insert(4);
            tree.Insert(12);
            tree.Insert(8);
            tree.Insert(9);
        */
        /*  
            tree.Insert(5);
            tree.Insert(6);
            tree.Insert(3);
            tree.Insert(4);
            tree.Insert(2);
            tree.Insert(7);
        */
        /*
            tree.Insert(6);
            tree.Insert(1);
            tree.Insert(5);
            tree.Insert(4);
        */
        tree.Insert(0);
        System.out.println("Tree before deletion: " + tree.toString()+"\n");
        tree.Delete(0);
        System.out.println("Tree after deletion: " + tree.toString()+"\n");
        System.out.println("Hello World! from BinarySearchTree.java");
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