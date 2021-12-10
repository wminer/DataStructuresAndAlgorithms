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
        if(root == null) return; // No values in tree to remove from
        TreeNode successor, successorParent; // Pointers for value we're replacing deleted node with (successor) and its Parent node (successorParent)
        successorParent = new TreeNode();
        // CASE 1: Val is root's vool --> deleting root Node
        if(root.val == val) {
            successor = root.right;
            while(successor != null && successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            if(successor == null) { // No in-order successor
                if(root.left == null) { // No predecessor nodes -- root is last val in tree
                    root = null;
                    return;
                }
                successor = root.left; // Predecessor exists, find largest value in left sub-tree
                while(successor != null && successor.right != null) { // Traverse to largest value, which will become new root
                    successorParent = successor;
                    successor = successor.right;
                }
                successorParent.right = successor.left; // replace reference to new root (successor) with successor's left child (next largest value because we know no successor.right; if successor.left is null, that's ok)
                if(successor != root.left) { // Need this check because of line 57; without it we may create a circular loop of references in the tree
                    successor.left = root.left;
                }
            } else { // in-order successor exists
                successor.left = root.left; // Update new root's reference
                successorParent.left = successor.right; // Update reference to new root to next value (it's ok if it's null)
                if(successor != root.right) { // Need this check because of line 47; without it we may create a circular loop of references in the tree
                    successor.right = root.right; 
                }
            }
            root = successor; // Update root reference
            return;
        }
        // CASE 2: Val is root's child
        TreeNode parent = root; // Pointer for parent to Node to be deleted (parent)
        TreeNode child = root.val > val ? root.left : root.right; // Pointer for Node to be deleted (child)
        if(child == null) return; // Node with value to be deleted doesn't exist
        if(child.val == val) { 
            if(parent.right == child) { // Node to be deleted is the right child
                if(child.left == null && child.right == null) { // No Nodes to replace node
                    parent.right = null;
                } else if(child.left == null && child.right != null) {
                    parent.right = child.right;
                } else if(child.left != null && child.right == null) {
                    parent.right = child.left;
                } else { // child.left != null && child.right != null --> find in-order successor node
                    successor = child.right;
                    while(successor.left != null) {
                        successorParent = successor;
                        successor = successor.left;
                    }
                    successorParent.left = successor.right;
                    successor.left = child.left;
                    if(successor != child.right) { // Need this check because of line 89; without it we may create a circular loop of references in the tree
                        successor.right = child.right;
                    }
                    parent.right = successor;
                }
            } else if(parent.left == child) { // Node to be deleted is the left child
                if(child.left == null && child.right == null) {
                    parent.left = null;
                } else if(child.left == null && child.right != null) {
                    parent.left = child.right;
                } else if(child.left != null && child.right == null) {
                    parent.left = child.left;
                } else { // child.left != null && child.right != null --> find in-order successor node
                    successor = child.right;
                    while(successor.left != null) {
                        successorParent = successor;
                        successor = successor.left;
                    }
                    successorParent.left = successor.right;
                    successor.left = child.left;
                    if(successor != child.right) { // Need this check because of line 109; without it we may create a circular loop of references in the tree
                        successor.right = child.right;
                    }
                    parent.left = successor;
                }
            }
            return;
        }
        
        // CASE 3: Val is not root and not root's child
        while(child != null && child.val != val) { // Search for node to be deleted 
            parent = child;
            child = child.val > val ? child.left: child.right;
        }
        if(child == null) return; // Node to be deleted doesn't exist
        if(parent.right == child) {
            if(child.left == null && child.right == null) {
                parent.right = null;
            } else if(child.left == null && child.right != null) {
                parent.right = child.right;
            } else if(child.left != null && child.right == null) {
                parent.right = child.left;
            } else { // child.left != null && child.right != null --> find in-order successor node
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
            } else { // child.left != null && child.right != null --> find in-order successor node
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