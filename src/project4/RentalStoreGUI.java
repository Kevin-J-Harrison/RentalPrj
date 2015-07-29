package project4;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import project4.RentDVDDialog;
import project4.RentGameDialog;
import project4.RentalStore;

/**
 * Creates a GUI for the RentalStore. Extends JFrame and implements
 * ActionListener.
 * 
 * @author alexvansteel, kevinharrison
 *
 */
public class RentalStoreGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	/** Format for dates. */
	private SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");

	/** The menu bar for the GUI. */
	private JMenuBar menu;

	/** Object on the menu bar for saving, loading, and exiting. */
	private JMenu file;
	/** Objects inside the file menu option. */
	private JMenuItem open, save, exit;

	/** Object on the menu bar for renting and returning. */
	private JMenu action;
	/** Objects in the action menu option. */
	private JMenuItem rentDVDI, rentGameI, returnI, searchI;

	/** Creates a scroll pane for the list. */
	private JScrollPane scrollPane;
	private JTextArea searchPane;

	/** Panel to hold the buttons as alternatives to the menu bar options. */
	private JPanel buttonPanel;
	/** Buttons for renting DVDs, Games, and returning. */
	private JButton rentDVDB, rentGameB, returnB, searchB;

	/** A JList of DVDs/Games. */
	private JList<DVD> list;

	/** Instance of the RentalStore. */
	private RentalStore store;

	/** Format for currency. */
	private NumberFormat numfmt = NumberFormat.getCurrencyInstance();

	/**
	 * Constructor for the RentalStoreGUI
	 */
	public RentalStoreGUI() {
		setFrame();
		store = new RentalStore();
		list.setModel(store);
	}

	/**
	 * Creates the Menu Bar for the GUI.
	 * 
	 * @return the created menu bar.
	 */
	private JMenuBar menuBar() {
		menu = new JMenuBar();

		file = new JMenu("File");
		menu.add(file);

		open = new JMenuItem("Open Serial...");
		open.addActionListener(this);
		file.add(open);
		save = new JMenuItem("Save Serial...");
		save.addActionListener(this);
		file.add(save);
		file.addSeparator();
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		file.add(exit);

		action = new JMenu("Action");
		menu.add(action);

		rentDVDI = new JMenuItem("Rent DVD");
		rentDVDI.addActionListener(this);
		action.add(rentDVDI);
		rentGameI = new JMenuItem("Rent Game");
		rentGameI.addActionListener(this);
		action.add(rentGameI);
		action.addSeparator();
		returnI = new JMenuItem("Return");
		returnI.addActionListener(this);
		action.add(returnI);
		action.addSeparator();
		searchI = new JMenuItem("Search");
		searchI.addActionListener(this);
		action.add(searchI);

		return menu;
	}

	/**
	 * Creates the Scroll Pane for the GUI
	 * 
	 * @return the created scroll pane.
	 */
	private JScrollPane scrollPane() {
		scrollPane = new JScrollPane();
		list = new JList<DVD>();

		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		list.setBorder(javax.swing.BorderFactory
				.createTitledBorder("List Of Rentals"));
		list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		list.setToolTipText("Select a rental to return");
		scrollPane.setViewportView(list);

		return scrollPane;
	}

	private JTextArea searchPane() {
		searchPane = new JTextArea();

		searchPane.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Search Results"));

		return searchPane;
	}

	/**
	 * Creates the Button Panel for the GUI.
	 * 
	 * @return the created button panel.
	 */
	private JPanel buttonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 1));

		rentDVDB = new JButton("Rent DVD");
		rentDVDB.addActionListener(this);
		buttonPanel.add(rentDVDB);

		rentGameB = new JButton("Rent Game");
		rentGameB.addActionListener(this);
		buttonPanel.add(rentGameB);

		returnB = new JButton("Return");
		returnB.addActionListener(this);
		buttonPanel.add(returnB);

		searchB = new JButton("Search");
		searchB.addActionListener(this);
		buttonPanel.add(searchB);

		return buttonPanel;
	}

	/**
	 * Sets the parameters for the frame.
	 */
	private void setFrame() {
		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Electric Boogaloo DVD & Game Rental");

		this.add(scrollPane(), BorderLayout.CENTER);
		this.add(buttonPanel(), BorderLayout.EAST);
		this.add(searchPane(), BorderLayout.SOUTH);
		this.setJMenuBar(menuBar());

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setSize(1500, 600);
		this.setResizable(true);
	}

	/**
	 * The created ActionListener for the buttons and menu items.
	 * 
	 * @param e
	 *            the event to be listened to.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JComponent comp = (JComponent) e.getSource();

		if (comp == rentDVDI || comp == rentDVDB) {
			DVD unit = new DVD();
			RentDVDDialog rent = new RentDVDDialog(this, unit);
			rent.setFrame();
			if (unit.getNameOfRenter() != null) {
				store.addDVD(unit);
			}
		}

		if (comp == rentGameI || comp == rentGameB) {
			Game unit = new Game();
			RentGameDialog rent = new RentGameDialog(this, unit);
			rent.setFrame();
			if (unit.getNameOfRenter() != null) {
				store.addDVD(unit);
			}
		}

		if (comp == returnI || comp == returnB) {
			int index = list.getSelectedIndex();

			if (index != -1) {
				DVD unit = store.getDVD(index);
				returning(unit, index);
			}
		}

		if (comp == searchI || comp == searchB) {
			search();
		}

		if (comp == save) {
			try {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"All Files", ".ser");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showSaveDialog(getParent());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					store.save(chooser.getSelectedFile().getName());
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		if (comp == open) {
			try {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"All Files", ".ser");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(getParent());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					store.load(chooser.getSelectedFile().getName());
				}
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
		}

		if (comp == exit) {
			System.exit(0);
		}
	}

	/**
	 * Returns the DVD/Game, creating boxes to enter the return date and
	 * calculate cost.
	 * 
	 * @param d
	 *            the object being returned.
	 * 
	 * @param index
	 *            the index of the object being returned
	 */
	private void returning(DVD d, int index) {
		DVD unit = d;
		if (unit instanceof Game) {
			unit = (Game) unit;
		}

		boolean returned = false;
		Date returnedDate = null;

		while (returned == false) {
			try {
				String returnedS = JOptionPane.showInputDialog(
						"Enter the return date:", "MM/DD/YYYY");
				if (returnedS == null) {
					returned = true;
					JOptionPane.showMessageDialog(null, "Return Canceled",
							"Cancel", JOptionPane.INFORMATION_MESSAGE);
				} else {
					returnedDate = fmt.parse(returnedS);
					GregorianCalendar rday = new GregorianCalendar();
					rday.setLenient(false);
					rday.setTime(returnedDate);

					if (rday.compareTo(unit.getRentalDate()) < 0) {
						JOptionPane.showMessageDialog(null,
								"DATE ENTERED IS BEFORE DATE RENTED",
								"DATE ERROR", JOptionPane.ERROR_MESSAGE);
						throw new IllegalArgumentException();
					} else {
						Date dueDay = unit.getDueBack().getTime();
						long diff = returnedDate.getTime() - dueDay.getTime();
						int dayDiff = (int) TimeUnit.DAYS.convert(diff,
								TimeUnit.MILLISECONDS);

						JOptionPane.showMessageDialog(
								null,
								"Thank you " + unit.getNameOfRenter() + "!\n"
										+ "for returning " + unit.getTitle()
										+ ", you owe: "
										+ numfmt.format(unit.getCost(dayDiff)));
						store.deleteDVD(index);
						returned = true;
					}
				}
			} catch (ParseException e1) {

			} catch (IllegalArgumentException ex) {

			}
		}
	}

	private void search() {
		searchPane.removeAll();
		boolean dateSet = false;
		Date searchDate = new Date();
		GregorianCalendar c = new GregorianCalendar();
		
		while (dateSet == false) {
			try {
				String date = JOptionPane.showInputDialog(
						"Enter A Search Date", "MM/DD/YYYY");

				if (date == null) {
					dateSet = true;
					JOptionPane.showMessageDialog(null, "Search Canceled",
							"Cancel", JOptionPane.INFORMATION_MESSAGE);
				} else {
					searchDate = fmt.parse(date);
					c.setTime(searchDate);
					dateSet = true;
				}
			} catch (Exception e) {

			}
		}
		
		for(int i = 0; i < store.getSize(); i++) {
			DVD d = store.getDVD(i);
			if(d.getDueBack().compareTo(c) < 0) {
				int daysLate = c.compareTo(d.getDueBack());
				searchPane.append(
						d.getNameOfRenter() + " " 
						+ d.getTitle() + " " 
						+ fmt.format(d.getDueBack().getTime()) 
						+ " Days Late: " 
						+ daysLate + "\n");
			}
		}
		searchPane.repaint();
	}

	/**
	 * The main method that starts the program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		RentalStoreGUI gui = new RentalStoreGUI();
	}

}
