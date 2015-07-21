package project4;

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
	private JMenuItem rentDVD, rentGame, Return;

	private JList list;
	
	private RentalStore store;

	public RentalStoreGUI() {
	    
	    	store = new RentalStore();

		JPanel panel = new JPanel();
		this.add(panel);
		list = new JList();

		this.setJMenuBar(menuBar());
		panel.add(list);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setSize(800, 400);
		this.setResizable(true);

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

		rentDVD = new JMenuItem("Rent DVD");
		rentDVD.addActionListener(this);
		action.add(rentDVD);
		rentGame = new JMenuItem("Rent Game");
		rentGame.addActionListener(this);
		action.add(rentGame);
		action.addSeparator();
		Return = new JMenuItem("Return");
		Return.addActionListener(this);
		action.add(Return);

		return menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JComponent comp = (JComponent) e.getSource();

		if (comp == rentDVD) {
			// System.out.print("yeah, the button works");
			DVD unit = new DVD();
			RentDVDDialog rent = new RentDVDDialog(new JFrame(), unit);
			rent.setFrame();
			store.addDVD(unit);
			
		}

		if (comp == rentGame) {
			Game unit = new Game();
			RentGameDialog rent = new RentGameDialog(new JFrame(), unit);
			rent.setFrame();
			store.addDVD(unit);
		}

	}

	public static void main(String[] args) {
		RentalStoreGUI gui = new RentalStoreGUI();
	}

}
