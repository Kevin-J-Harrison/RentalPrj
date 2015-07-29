package project4;

import java.io.*;
import java.text.SimpleDateFormat;

import javax.swing.AbstractListModel;

import project4.MyDoubleLinkedList;

/**
 * Creates the rental store, which extends AbstractListModel
 * 
 * @author alexvansteel, kevinharrison
 *
 */
public class RentalStore extends AbstractListModel {

	private static final long serialVersionUID = 1L;

	// private ArrayList<DVD> listDVDs;
	// private LinkedList<DVD> listDVDs;

	/** Doubly Linked List of DVDs and Games */
	private MyDoubleLinkedList<DVD> listDVDs;
	/** Format for dates. */
	private SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");

	/**
	 * Constructs the rental store.
	 */
	public RentalStore() {

		// listDVDs = new ArrayList<DVD>();
		// listDVDs = new LinkedList<DVD>();
		listDVDs = new MyDoubleLinkedList<DVD>();
	}

	/**
	 * Returns the size of the list as an int.
	 */
	@Override
	public int getSize() {
		return listDVDs.size();
	}

	/**
	 * Creates the string to be displayed in the RentalStoreGUI JList.
	 * 
	 * @param arg0
	 */
	@Override
	public Object getElementAt(int arg0) {
		DVD d = listDVDs.get(arg0);

		String s = "";
		if (d.getNameOfRenter() != null) {
			s += "" + d.getNameOfRenter() + " ";
		}
		if (d.getTitle() != null) {
			s += "   Rented: " + d.getTitle() + " ";
		}
		if (d.getRentalDate() != null) {
			s += "   Date Rented: " + fmt.format(listDVDs.get(arg0).getRentalDate().getTime())
					+ " ";
		}
		if (d.getDueBack() != null) {
			s += "   Due Back: " + fmt.format(d.getDueBack().getTime()) + " ";
		}

		if (d instanceof Game) {
			s += "   Console: " + ((Game) d).getConsole();
		}

		return s;
	}
	
	public DVD getDVD(int index) {
		return listDVDs.get(index);
	}

	/**
	 * Adds a DVD or Game to the list and updates the list.
	 * 
	 * @param unit
	 *            the object to be added.
	 */
	public void addDVD(DVD unit) {
		if (unit != null) {
			listDVDs.add(unit);
			fireIntervalAdded(this, 0, listDVDs.size());
		}
	}

	/**
	 * Deletes the selected unit from the list and updates the list.
	 * 
	 * @param index
	 *            unit to be deleted.
	 */
	public void deleteDVD(int index) {
		DVD unit = listDVDs.get(index);
		listDVDs.remove(index);
		fireIntervalRemoved(unit, 0, listDVDs.size());
	}

	/**
	 * Updates the list of DVDs/Games.
	 */
	public void updateDVDs() {
		fireContentsChanged(this, 0, listDVDs.size());
	}

	/**
	 * Saves the list to a file to be loaded at a later date.
	 * 
	 * @param filename
	 *            name of the file.
	 * @throws IOException
	 *             if the file is of the wrong type.
	 */
	public void save(String filename) throws IOException {
		FileOutputStream fos = new FileOutputStream(filename);
		ObjectOutputStream os = new ObjectOutputStream(fos);
		os.writeObject(listDVDs);
		os.close();
	}

	/**
	 * Loads a previously saved file holding a rental store.
	 * 
	 * @param filename
	 *            name of the file to be loaded.
	 * @throws IOException
	 *             if the file is not of the appropriate type.
	 * @throws ClassNotFoundException
	 *             if the file is not found.
	 */
	public void load(String filename) throws IOException,
			ClassNotFoundException {
		FileInputStream fis = new FileInputStream(filename);
		ObjectInputStream is = new ObjectInputStream(fis);
		// listDVDs = (ArrayList<DVD>) is.readObject();
		// listDVDs = (LinkedList<DVD>) is.readObject();
		listDVDs = (MyDoubleLinkedList<DVD>) is.readObject();
		is.close();
		updateDVDs();
	}

	/**
	 * Returns the Doubly Linked List.
	 * 
	 * @return the list.
	 */
	public <E> MyDoubleLinkedList<DVD> getList() {
		return listDVDs;
	}
}
