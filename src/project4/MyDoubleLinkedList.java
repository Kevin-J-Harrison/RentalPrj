package project4;

import java.io.Serializable;

public class MyDoubleLinkedList<E> implements Serializable {

	private DNode<E> top;
	private DNode<E> tail;

	private int size;
	private boolean removed = false;

	public MyDoubleLinkedList() {
		super();
		top = null;
		tail = null;
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

	// still need to figure out clear
	public void clear() {
		if (top == null)
			return;

		while (top != tail) {
			top.setPrevious(null);
			top = top.getNext();
		}
		if (top == tail)
			top = tail = null;

		size = 0;
	}

	public void add(E data) {
		if (top == null) {
			top = tail = new DNode<E>(data, null, null);
			size++;
			return;
		}

		tail = new DNode<E>(data, tail, null);
		tail.getPrevious().setNext(tail);
		size++;
	}

	public void addFirst(E data) {
		if (top == null) {
			top = tail = new DNode<E>(data, null, null);
			size++;
			return;
		}

		if (top == tail) {
			top = new DNode<E>(data, null, tail);
			size++;
		}

		top = new DNode<E>(data, null, top);
		size++;

	}

	// not sure how to return E, which should be a generic for the list?
	public E remove(int index) {
		if (size == 0)
			return null;

		if (size == 1) {
			top = tail = null;
			size = 0;
			removed = true;
			return null;
		}

		if (index == size) {
			tail = tail.getPrevious();
			size--;
			removed = true;
		}

		DNode<E> temp = top.getNext();
		for (int i = 1; i <= size; i++) {
			if (i == index) {
				temp.getPrevious().setNext(temp.getNext());
				temp.getNext().setPrevious(temp.getPrevious());
				size--;
				removed = true;
			}
			temp = temp.getNext();
		}
		return null;
	}

	// still don't know how to return E, which should be a generic for the list?
	public E get(int index) {
		if (size == 0)
			return null;

		if (index == 1) {
			return top;
		}
		
		if (index == size) {
			return tail;
		}
		
		DNode<E> temp = top.getNext();
		for (int i = 1; i <= size; i++) {
			if (i == index) {
				return temp;
		}
		return null;
	}

	public boolean returnAll() {
		return removed;
	}

	public int find(E data) {
		DNode<E> temp = top;
		for (int i = 0; i <= size; i++) {
			if (temp.getData().equals(data))
				return i;
			temp = temp.getNext();
		}
		return -1;
	}
}
