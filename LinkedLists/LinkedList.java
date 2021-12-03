public class LinkedList {
    Node head;

    public static void main(String[] args) {
        System.out.println("Hello World! from LinkedList.java");
    }
}

/*
	Class for Linked List Node
*/
class Node {

	int data;
	Node next;

	public Node() {}

	public Node(int data, Node next) {
		this.data = data;
		this.next = next;
	}

	public Node(Node next) {
		this.next = next;
	}
}
