package project4;

public class DNode<E> {
	public E data;
	public DNode<E> next;
	
	public DNode(E data, DNode<E> next) {
		super();
		this.data = data;
		this.next = next;
	}

	public DNode() {
		super();
	}

	public E getData() {
		return data;
	}
	
	public void setData(E data) {
		this.data = data;
	}
	public DNode<E> getNext() {
		return next;
	}
	public void setNext(DNode<E> next) {
		this.next = next;
	}
}