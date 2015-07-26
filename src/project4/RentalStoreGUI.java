package project4;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import project4.RentDVDDialog;
import project4.RentGameDialog;
import project4.RentalStore;

public class RentalStoreGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");

	private JMenuBar menu;

	private JMenu file;
	private JMenuItem open, save, exit;

	private JMenu action;
	private JMenuItem rentDVDI, rentGameI, returnI;

	private JScrollPane scrollPane;

	private JPanel buttonPanel;
	private JButton rentDVDB, rentGameB, returnB;

	private JList<DVD> list;

	private RentalStore store;

	private NumberFormat numfmt = NumberFormat.getCurrencyInstance();

	public RentalStoreGUI() {

		setFrame();
		store = new RentalStore();
		list.setModel(store);

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

		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		list.setBorder(javax.swing.BorderFactory
				.createTitledBorder("List Of Rentals"));
		list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		list.setToolTipText("Select a rental to return");
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
				DVD unit = store.deleteDVD(index);
				
				if (unit instanceof DVD)
					returning(unit);
				
				if (unit instanceof Game) {
					unit = (Game) unit;
					returning(unit);
				}
			}
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
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if (comp == exit) {
			System.exit(0);
		}
	}

	public void returning(DVD d) {
		DVD unit = d;
		Date returned = null;
		
		while (returned == null) {
			try {
				returned = fmt.parse(JOptionPane.showInputDialog(
						"Enter the return date:", "MM/DD/YYYY"));

				GregorianCalendar rday = new GregorianCalendar();
				rday.setTime(returned);
				if (rday.compareTo(unit.getRentalDate()) < 0)
					returned = null;

				Date dueDay = unit.getDueBack().getTime();
				long diff = returned.getTime() - dueDay.getTime();
				int dayDiff = (int) TimeUnit.DAYS.convert(diff,
						TimeUnit.MILLISECONDS);

				JOptionPane.showMessageDialog(
						this,
						"Thank you " + unit.getNameOfRenter() + "!\n"
								+ "for returning " + unit.getTitle()
								+ ", you owe: "
								+ numfmt.format(unit.getCost(dayDiff)));
				returned = null;

			} catch (ParseException e1) {
				returned = null;
			}
		}
	}
	
	public static void main(String[] args) {
		RentalStoreGUI gui = new RentalStoreGUI();
	}

}
