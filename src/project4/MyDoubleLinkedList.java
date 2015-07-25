package project4;

import java.io.Serializable;
import project4.DNode;

public class MyDoubleLinkedList<E> implements Serializable {

	private static final long serialVersionUID = 1L;
	private DNode<E> top;
	private DNode<E> tail;

	private int size;
	private boolean removed = false;

	public MyDoubleLinkedList() {
		super();
		top =  null;
		tail = null;
		size = 0;
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

	public E remove(int index) {
		if (size == 0)
			return null;

		if (size == 1) {
			DNode<E> temp = top;
			top = tail = null;
			size = 0;
			removed = true;
			return temp.getData();
		}

		if (index == size) {
			DNode<E> temp = tail;
			tail = tail.getPrevious();
			size--;
			removed = true;
			return temp.getData();
		}
		
		if (index == 1) {
			DNode<E> temp = top;
			top = top.getNext();
			top.setPrevious(null);
			size--;
			return temp.getData();
		}

		DNode<E> temp = top.getNext();
		for (int i = 2; i <= size; i++) {
			if (i == index) {
				temp.getPrevious().setNext(temp.getNext());
				temp.getNext().setPrevious(temp.getPrevious());
				size--;
				removed = true;
				return temp.getData();
			}
			else {
			temp = temp.getNext();
			}
		}
		return null;
	}

	public E get(int index) {
		if (size == 0)
			return null;

		if (index == 0)
			return top.getData();

		if (index == size)
			return tail.getData();

		DNode<E> temp = top.getNext();
		for (int i = 1; i <= size; i++) {
			if (i == index) {
				return temp.getData();
			}
			else {
				temp = temp.getNext();
			}
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
