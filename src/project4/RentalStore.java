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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//TO-DO
	public void add() {
		//TO-DO
	}
	
	//TO-DO
	public void delete(){
		//TO-DO
	}
	
	//TO-DO
	public void update(){
		//TO-DO
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
