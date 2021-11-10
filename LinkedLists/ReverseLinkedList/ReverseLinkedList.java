public class ReverseLinkedList {

	/*
        Internal Class for Linked List Node
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

	/*
        Internal Class for Pointer Node for Head of Linked List
        - same as Node but no data field, just Node reference  
    */
	class Pointer {

		Node reference;

		public Pointer() {}

		public Pointer(Node reference) {
			this.reference = reference;
		}
	}

	/*
        Method to reverse Linked List
    */
	private static Pointer reverseFunction(Pointer head) {
		Node curr, prev, temp;
		prev = head.reference;
		curr = prev.next;
		while (curr != null) {
			temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = temp;
		}
		temp = head.reference;
		temp.next = null;
		head.reference = prev;
		return head;
	}

	/*
        Method to print out linked list in "{ ... }" format
    */
	private static void PrintLinkedList(Pointer head) {
		Node traverser = head.reference;
		System.out.print("{");
		while (traverser.next != null) {
			System.out.print(traverser.data + ", ");
			traverser = traverser.next;
		}
		System.out.println(traverser.data + "}");
	}

	public static void main(String[] args) {
		Pointer head = new Pointer(new Node(8, new Node(3, new Node(6, new Node(4, null)))));
		System.out.println("Initial Linked List:");
		PrintLinkedList(head);
		head = reverseFunction(head);
		System.out.println("Linked List post Reverse Function call: ");
		PrintLinkedList(head);
	}
}
