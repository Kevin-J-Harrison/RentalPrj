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
	public DVD getElementAt(int index) {
		return listDVDs.get(index);
	}
	
	public int indexOf(DVD unit) {
		return listDVDs.indexOf(unit);
	}
	
	public void addDVD(DVD unit) {
	    if(unit != null) {	
	    	listDVDs.add(unit);
	    	//fireIntervalAdded(unit, listDVDs.size(), 0)
	    	fireIntervalAdded(this, listDVDs.size()-1, listDVDs.size()-1);
	    }
	}
	
	public void addDVD(int index, DVD unit) {
		if(unit != null) {
			listDVDs.add(index, unit);
			fireIntervalAdded(this, index, index);
		}
	}
	
	public void deleteDVD(int index) {
		listDVDs.remove(index);
		fireIntervalRemoved(this, index, index);
	}
	
	public void deleteDVD(DVD unit) {
		listDVDs.remove(indexOf(unit));
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
