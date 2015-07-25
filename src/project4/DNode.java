package project4;

import java.io.Serializable;

public class DNode<E> implements Serializable {
   
    private static final long serialVersionUID = 1L;
    
    public E data;
    public DNode<E> next;
    public DNode<E> previous;

    public DNode(E data, DNode<E> previous, DNode<E> next) {
	super();
	this.data = data;
	this.next = next;
	this.previous = previous;
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

    public DNode<E> getPrevious() {
	return previous;
    }

    public void setPrevious(DNode<E> previous) {
	this.previous = previous;
    }
}