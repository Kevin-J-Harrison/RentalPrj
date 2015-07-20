package project4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

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
		
		//menuBar();
		
		this.setJMenuBar(menuBar());
		panel.add(list);
		this.pack();
		this.setVisible(true);
		
	
	}
	
	public JMenuBar menuBar() {
		menu = new JMenuBar();
		
		file = new JMenu("File");
		menu.add(file);
		
		open = new JMenuItem("Open Serial...");
		open.addActionListener(null); //FIX
		file.add(open);
		save = new JMenuItem("Save Serial...");
		save.addActionListener(null); //FIX
		file.add(save);
		exit = new JMenuItem("Exit");
		exit.addActionListener(null); //FIX
		file.add(exit);
		
		action = new JMenu("Action");
		menu.add(action);
		
		rentDVD = new JMenuItem("Rent DVD");
		rentDVD.addActionListener(null); //FIX
		action.add(rentDVD);
		rentGame = new JMenuItem("Rent Game");
		rentGame.addActionListener(null); //FIX
		action.add(rentGame);
		Return = new JMenuItem("Return");
		Return.addActionListener(null); //FIX
		action.add(Return);
		
		return menu;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	
	public static void main(String [] args) {
	    RentalStoreGUI gui = new RentalStoreGUI();
	}

}
