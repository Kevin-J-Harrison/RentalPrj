package project4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import project4.RentDVDDialog;
import project4.RentGameDialog;

public class RentalStoreGUI extends JFrame implements ActionListener {

	private JMenuBar menu;

	private JMenu file;
	private JMenuItem open, save, exit;

	private JMenu action;
	private JMenuItem rentDVD, rentGame, Return;

	private JList list;

	public RentalStoreGUI() {

		JPanel panel = new JPanel();
		this.add(panel);
		list = new JList();

		// menuBar();

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
			JDialog rent = new RentDVDDialog(new JFrame(), unit);
			rent.setModal(true);
			rent.setSize(250, 200);
			rent.setLocationRelativeTo(this);
			rent.setVisible(true);
			rent.setResizable(true);
			
		}

		if (comp == rentGame) {
			Game unit = new Game();
			JDialog rent = new RentGameDialog(new JFrame(), unit);
			rent.setModal(true);
			rent.setSize(300, 250);
			rent.setLocationRelativeTo(this);
			rent.setVisible(true);
			rent.setResizable(true);
		}

	}

	public static void main(String[] args) {
		RentalStoreGUI gui = new RentalStoreGUI();
	}

}
