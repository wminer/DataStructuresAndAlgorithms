import java.io.*;
import java.util.*;

/*
    Example case:
    List 1: 
    1 -> 5 -> 8 -> 8 -> 12
    
    List 2: 
    
    3 -> 8 -> 14 -> 16
    
  
    Result: 
    1 -> 3 -> 5 -> 8 -> 12 -> 14 -> 16 
*/

public class MergeLinkedLists {

    /*
        Old sad solution before Bradley helped me see the light of a better solution (below).
    
    private static Node mergeLists(Node root1, Node root2) {
        if(root1 == null && root2 == null) {
            return null;
        }
        Node root;
        Node temp = new Node();
        root = temp;
        List <Integer> distinctVals = new ArrayList <Integer>();
        if(root1 == null && root2 != null) {
            while(root2 != null) {
                if(!distinctVals.contains(root2.val)) {
                    distinctVals.add(root2.val);
                    temp.val = root2.val;
                    temp.next = root2.next == null ? null : new Node();
                    temp = temp.next;
                }
                root2 = root2.next;
            }
        } else if(root1 != null && root2 == null) {
            while(root1 != null) {
                if(!distinctVals.contains(root1.val)) {
                    distinctVals.add(root1.val);
                    temp.val = root1.val;
                    temp.next = root1.next == null ? null : new Node(); 
                }
                root1 = root1.next;
            }
        } else { // root1 != null && root2 != null
            while(root1 != null || root2 != null) {
                if(root1 == null) {
                    if(!distinctVals.contains(root2.val)) {
                        distinctVals.add(root2.val);
                    }
                    root2 = root2.next;
                } else if(root2 == null) {
                    if(!distinctVals.contains(root1.val)) {
                        distinctVals.add(root1.val);
                    }
                    root1 = root1.next;
                } else if (root1.val < root2.val) {
                    if(!distinctVals.contains(root1.val)) {
                        distinctVals.add(root1.val);
                    }
                    root1 = root1.next;
                } else if (root1.val > root2.val) {
                    if(!distinctVals.contains(root2.val)) {
                        distinctVals.add(root2.val);
                    }
                    root2 = root2.next;
                } else { // list1Val == list2Val
                    if(!distinctVals.contains(root1.val)) {
                        distinctVals.add(root1.val);
                    }
                    root1 = root1.next;
                    root2 = root2.next;
                }
            }

            // I have a sorted list of the distinct vals
            for (int i=0;i<distinctVals.size()-1;i++) {
                temp.val = distinctVals.get(i);
                temp.next = new Node();
                temp = temp.next;
            }
            temp.val = distinctVals.get(distinctVals.size()-1);
        }
        return root;
    }*/

    private static Node mergeLists(Node root1, Node root2) {
        Node result = new Node();
        Node resultHead = result;
        int lastVal = 0; 
        while (root1 != null || root2 != null)
        {
            if(root1 == null && root2 != null || root1 != null && root1.val > root2.val) { 
                if(root2.val != lastVal) {
                    result.val = root2.val;
                    lastVal = result.val;
                    result.next = root2.next == null ? null : new Node();
                    result = result.next;
                }
                root2 = root2.next;
            }
            else if(root1 != null && root2 == null || root2 != null && root1.val < root2.val) { 
                if(root1.val != lastVal) {
                    result.val = root1.val;
                    lastVal = result.val;
                    result.next = root1.next == null ? null : new Node();
                    result = result.next;
                }
                root1 = root1.next;
            }
            else if(root1 != null && root2 != null && root1.val == root2.val) { // root1.val == root2.val
                if(root1.val != lastVal) {
                    result.val = root1.val;
                    lastVal = result.val;
                    result.next = root1.next == null ? null : new Node();
                    result = result.next;
                }
                root1 = root1.next;
                root2 = root2.next;
            }
        }
        return resultHead;
    }

    /*
        Method to print out linked list in "{ ... }" format
    */
	private static void PrintLinkedList(Node head) {
		Node traverser = head;
		System.out.print("{");
		while (traverser.next != null) {
			System.out.print(traverser.val + ", ");
			traverser = traverser.next;
		}
		System.out.println(traverser.val + "}");
	}

    public static void main(String[] args) {
        System.out.println("Hello Java");
        Node list1 = new Node(1,
                        new Node(5,
                        new Node(8,
                        new Node(8,
                        new Node(12,
                        new Node(12, null))))));
        System.out.println("List 1: ");
        PrintLinkedList(list1);
        Node list2 = new Node(3,
                        new Node(8,
                        new Node(14,
                        new Node(16, null))));
        System.out.println("List 2: ");
        PrintLinkedList(list2);
        Node newList = mergeLists(list1, list2);
        System.out.println("Merged List: ");
        PrintLinkedList(newList);
    }
}

class Node {
    int val;
    Node next;

    public Node() {}

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }
}