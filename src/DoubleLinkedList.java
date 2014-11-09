/**
 * Creates a double linked list structure with methods for implementing
 * a FIFO queue or other potential structures.
 * 
 * CS 340, Fall 2014, Assignment 01
 * 
 * @author Jesse Dahir-Kanehl
 *
 */

public class DoubleLinkedList<T> {

	class Node {
		Node prev;
		T data;
		Node next;

		Node(Node p, T d, Node n) {
			prev = p;
			data = d;
			next = n;
		}
	}
	//position is only meaningful when the list is not empty
	protected Node head;
	protected Node current;
	protected Node last;
	protected int position;
	protected int size;

	public DoubleLinkedList() {
		head = new Node(null, null, null);
		head.next = new Node(head, null, null);
		size = 0;
		position = 0;
		current = head.next;
		last = current;
		
	}
	// Uses current to insert a new node at the end of the list
	public void insertLast(T d) {
		current = last.prev;
		last.prev = new Node(current, d, last);
		current.next = last.prev;
		current = last;
		size++;
		position = size;
	}
	
	/**
	 * Uses the three points of head, current, and tail to optimize insertion
	 * into the list at an index point
	 * PRE: 0>= p <= size
	 * 
	 * @param p the index point where the new node should be
	 * @param d the data to be stored in the new node
	 */
	public void insertAt(int p, T d) {
		if (size == 0)
			insertLast(d);
		else if (p < position) {
			if (position-p < p) {
				for (int i = 0; i <= position-p; i++) {
					current = current.prev;
				}
				Node newNode = new Node(current.prev, d, current);
				current.prev = newNode;
				newNode.prev.next = newNode;
				size++;
				position = p;
			}
			else {
				current = head.next;
				for (int i = 0; i < p; i++) {
					current = current.next;
				}
				Node newNode = new Node(current.prev, d, current);
				current.prev = newNode;
				newNode.prev.next = newNode;
				size++;
				position = p;
			}
				
		}
		else {
			if (p-position < size-p) {
				for (int i = 1; i < p-position; i++) {
					current = current.next;
				}
				Node newNode = new Node(current.prev, d, current);
				current.prev = newNode;
				newNode.prev.next = newNode;
				size++;
				position = p;
			}
			else {
				current = last;
				for (int i = 0; i < size-p; i++) {
					current = current.prev;
				}
				Node newNode = new Node(current.prev, d, current);
				current.prev = newNode;
				newNode.prev.next = newNode;
				position = p;
				size++;
				
			}
		}
			
	}

	// PRE: size>0
	// Uses the head node to delete the first node
	public T deleteFirst() {
		T data;
		data = head.next.data;
		head.next = head.next.next;
		head.next.prev = head;
		size--;
		position = 0;
		current = head.next;
		return data;
	}

	public int getSize() {
		return size;
	}
	
	//PRE: size>0
	public int getPosition() {
		return position;
	}
	
	/**
	 * Uses the three known nodes, head, current, and tail nodes to optimize
	 * the query of the data for the node at a certain position
	 * PRE: size>0 and 0<= p <=size
	 * 
	 * @param p	The position of the node for which the query is about
	 * @return
	 */
	public T getDataAt(int p) {
		T data;
		if (p < position) {
			if (position-p < p) {
				for (int i = 0; i < position-p; i++) {
					current = current.prev;
				}
				data = current.data;
				position = p;
			}
			else {
				current = head.next;
				for (int i = 0; i < p; i++) {
					current = current.next;
				}
				data = current.data;
				position = p;
			}
				
		}
		else {
			if (p-position < size-p) {
				for (int i = 0; i < p-position; i++) {
					current = current.next;
				}
				data = current.data;
				position = p;
			}
			else {
				current = last;
				for (int i = 0; i < size-p; i++) {
					current = current.prev;
				}
				data = current.data;
				position = p;
			}
		}
		return data;
	}
	
}

		