package project4;

import java.io.Serializable;

public class MyDoubleLinkedList<E> implements Serializable {

	private DNode<E> top;
	private DNode<E> tail;

	private int sier;

	public MyDoubleLinkedList() {

	}

	public int size() {
		int count = 0;
		DNode<E> temp = top;
		while (temp != null) {

			count++;
			temp = temp.getNext();

		}
		return count;
	}

	public void clear() {

	}

	public void add(E data) {

	}

	public void addFirst(E data) {

	}

	public void remove(int index) {
		
	}

	public void get(int index) {

	}

	public void removeAll() {

	}

	public int find(E data) {
		return 0;
	}
}
