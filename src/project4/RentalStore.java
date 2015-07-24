package project4;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.*;

import javax.swing.AbstractListModel;

public class RentalStore extends AbstractListModel{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<DVD> listDVDs;
	
	private SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	
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
		s += "" + listDVDs.get(arg0).getNameOfRenter() + " ";
		s += "" + listDVDs.get(arg0).getTitle() + " ";
		if(listDVDs.get(arg0).getRentalDate() != null) {
		    s += "" + fmt.format(listDVDs.get(arg0).getRentalDate().getTime()) + " ";
		}
		s += "" + fmt.format(listDVDs.get(arg0).getDueBack().getTime());
		
		return s;
	
	}
	
	public void addDVD(DVD unit) {
	    if(unit != null) {	
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
