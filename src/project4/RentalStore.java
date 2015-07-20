package project4;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import javax.swing.AbstractListModel;

public class RentalStore extends AbstractListModel{
	
	private ArrayList<DVD> listDVDs;
	
	public RentalStore() {
		listDVDs = new ArrayList<DVD>();
	}

	@Override
	public int getSize() {
		return listDVDs.size();
	}

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//TO-DO
	public void addDVD(DVD unit) {
	   
	    
	   fireIntervalAdded();
	}
	
	//TO-DO
	public void deleteDVD(){
		//TO-DO
	    
	    fireIntevalRemoved();
	}
	
	//TO-DO
	public void updateDVDs(){
		//TO-DO
	    
	    fireContentsChanged();
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
	
	public void fireIntervalAdded() {
	    
	}

}
