package project4;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import project4.RentDVDDialog;
import project4.RentGameDialog;
import project4.RentalStore;

public class RentalStoreGUI extends JFrame implements ActionListener {

	private JMenuBar menu;

	private JMenu file;
	private JMenuItem open, save, exit;

	private JMenu action;
	private JMenuItem rentDVDI, rentGameI, returnI;

	private JScrollPane scrollPane;

	private JPanel buttonPanel;
	private JButton rentDVDB, rentGameB, returnB;

	private JList<DVD> list;
	private DVD d;
	private Game g;

	private RentalStore store;

	public RentalStoreGUI() {

		setFrame();
		store = new RentalStore();
		list.setModel(store);
		d = new DVD();
		g = new Game();

	}

	public JMenuBar menuBar() {
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

		return menu;
	}

	private JScrollPane scrollPane() {
		scrollPane = new JScrollPane();
		list = new JList<DVD>();
		store = new RentalStore();
		list.setModel(store);

		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		list.setBorder(javax.swing.BorderFactory.createTitledBorder("List Of Rentals"));
		list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		list.setToolTipText("Select a rental to return");
		list.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {

			}
		});
		scrollPane.setViewportView(list);

		return scrollPane;
	}

	private JPanel buttonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3, 1));

		rentDVDB = new JButton("Rent DVD");
		rentDVDB.addActionListener(this);
		buttonPanel.add(rentDVDB);

		rentGameB = new JButton("Rent Game");
		rentGameB.addActionListener(this);
		buttonPanel.add(rentGameB);

		returnB = new JButton("Return");
		returnB.addActionListener(this);
		buttonPanel.add(returnB);

		return buttonPanel;
	}

	private void setFrame() {
		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Electric Boogaloo DVD & Game Rental");

		this.add(scrollPane(), BorderLayout.CENTER);
		this.add(buttonPanel(), BorderLayout.EAST);
		this.setJMenuBar(menuBar());

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setSize(1000, 400);
		this.setResizable(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComponent comp = (JComponent) e.getSource();

		if (comp == rentDVDI || comp == rentDVDB) {
			// System.out.print("yeah, the button works");
			DVD unit = new DVD();
			RentDVDDialog rent = new RentDVDDialog(this, unit);
			rent.setFrame();
			store.addDVD(unit);

		}

		if (comp == rentGameI || comp == rentGameB) {
			Game unit = new Game();
			RentGameDialog rent = new RentGameDialog(new JFrame(), unit);
			rent.setFrame();
			store.addDVD(unit);
		}

		if (comp == returnI || comp == returnB) {
			int index = list.getSelectedIndex();

			if (index != -1) {
				DVD dvd = (DVD)store.deleteDVD(index);
			}
		}
		
		if(comp == exit) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		RentalStoreGUI gui = new RentalStoreGUI();
	}

}
