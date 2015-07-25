package project4;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.*;

import javax.management.RuntimeErrorException;
import javax.swing.AbstractListModel;
import project4.MyDoubleLinkedList;

public class RentalStore extends AbstractListModel {

	private static final long serialVersionUID = 1L;

	//private ArrayList<DVD> listDVDs;
	//private LinkedList<DVD> listDVDs;
	private MyDoubleLinkedList<DVD> listDVDs;
	private SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");

	public RentalStore() {

		//listDVDs = new ArrayList<DVD>();
		//listDVDs = new LinkedList<DVD>();
		listDVDs = new MyDoubleLinkedList<DVD>();
	}

	@Override
	public int getSize() {
		return listDVDs.size();
	}

	@Override
	public Object getElementAt(int arg0) {
		DVD d = listDVDs.get(arg0);

		String s = "";
		if (d.getNameOfRenter() != null) {
			s += "" + d.getNameOfRenter() + " ";
		}
		if (d.getTitle() != null) {
			s += "" + d.getTitle() + " ";
		}
		if (d.getRentalDate() != null) {
			s += "" + fmt.format(listDVDs.get(arg0).getRentalDate().getTime()) + " ";
		}
		if (d.getDueBack() != null) {
			s += "" + fmt.format(d.getDueBack().getTime()) + " ";
		}
		
		if(d instanceof Game) {
			s += "" +((Game) d).getConsole();
		}
		
	

		return s;

	}

	public void addDVD(DVD unit) {
		if (unit != null) {
			listDVDs.add(unit);
			fireIntervalAdded(this, 0, listDVDs.size());
		}
	}

	public DVD deleteDVD(int index) {
		DVD unit = listDVDs.get(index);
		listDVDs.remove(index);
		fireIntervalRemoved(unit, 0, listDVDs.size());
		return unit;
	}

	public void updateDVDs() {
		fireContentsChanged(this, 0, listDVDs.size());
	}

	public void save(String filename) throws IOException {
		FileOutputStream fos = new FileOutputStream(filename);
		ObjectOutputStream os = new ObjectOutputStream(fos);
		os.writeObject(listDVDs);
		os.close();
	}


	public void load(String filename) throws IOException,
			ClassNotFoundException {
		FileInputStream fis = new FileInputStream(filename);
		ObjectInputStream is = new ObjectInputStream(fis);
		//listDVDs = (ArrayList<DVD>) is.readObject();
		//listDVDs = (LinkedList<DVD>) is.readObject();
		listDVDs = (MyDoubleLinkedList<DVD>) is.readObject();
		is.close();
		updateDVDs();

	}
}
