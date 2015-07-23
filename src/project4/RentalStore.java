package project4;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import javax.swing.AbstractListModel;

public class RentalStore extends AbstractListModel{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<DVD> listDVDs;
	
	public RentalStore() {
		listDVDs = new ArrayList<DVD>();
	}

	@Override
	public int getSize() {
		return listDVDs.size();
	}

	@Override
	public Object getElementAt(int arg0) {
		String s = "";
		s += "" + listDVDs.get(arg0).getNameOfRenter().toString() + " ";
		s += "" + listDVDs.get(arg0).getTitle() + " ";
		s += "" + listDVDs.get(arg0).getRentalDate() + " ";
		s += "" + listDVDs.get(arg0).getDueBack();
		
		return s;
	
	}
	
	public void addDVD(DVD unit) {
	    if(unit != null) {	
	    	listDVDs.add(unit);
	    	fireIntervalAdded(this, 0, listDVDs.size());
	    }
	}
	
	public DVD deleteDVD(int index) {
		listDVDs.remove(index);
		fireIntervalRemoved(this, 0, listDVDs.size());
		return listDVDs.get(index);
	}
	
	//FIX
	public void updateDVDs() {
		//fireContentsChanged()
	}
	
	public void save(String filename) throws IOException {
		FileOutputStream fos = new FileOutputStream(filename);
		ObjectOutputStream os = new ObjectOutputStream(fos);
		os.writeObject(listDVDs);
		os.close();
	}
	
	public void load(String filename) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(filename);
		ObjectInputStream is = new ObjectInputStream(fis);
		listDVDs = (ArrayList<DVD>) is.readObject();
		is.close();
	}

}
