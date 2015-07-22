package project4;

import java.io.Serializable;

public class MyDoubleLinkedList<E> implements Serializable {

	private DNode<E> top;
	private DNode<E> tail;

	private int sier;

	public MyDoubleLinkedList() {
		super();
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
		if (top == null)
			return;
		
		if (top == tail)
			top = tail = null;
	}

	public void add(E data) {
		if (top == null)
			return;
		
		tail = new DNode<E>(data, tail, null);
	}

	public void addFirst(E data) {
		if (top == null)
			return;
		
		if (top == tail)
			top = new DNode<E>(data, null, tail);
		
		top = new DNode<E>(data, null, top);
		
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
