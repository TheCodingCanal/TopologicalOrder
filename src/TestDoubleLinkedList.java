/**
 * Tests the double linked list's methods.
 * 
 * CS 340, Fall 2014, Assignment 01
 * 
 * @author Jesse Dahir-Kanehl
 *
 */
public class TestDoubleLinkedList {

	public static void main(String[] args) {
		DoubleLinkedList<Integer> q = new DoubleLinkedList<Integer>();
		q.insertLast(5);
		q.insertLast(21);
		q.insertLast(10);
		System.out.println(q.getPosition());
		for (int i = 0; i < 12; i++) {
			q.insertLast(i*9);
		}
		System.out.println(q.getPosition());
		q.insertAt(5, -63);
		q.insertAt(8, -34);
		q.insertAt(17, -25);
		q.insertAt(14, -1);
		q.insertAt(1, -13);
		q.insertAt(16, -69);
		System.out.println(q.deleteFirst());
		int size = q.getSize();
		for (int i = 0; i < size; i++) {
			System.out.println(q.getDataAt(i));
		}
	}

}
