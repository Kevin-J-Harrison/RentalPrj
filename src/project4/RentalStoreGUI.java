package project4;

import java.awt.BorderLayout;
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
	
	private JScrollPane scrollPane;

	private JList<DVD> list;
	private DVD d;
	
	private RentalStore store;

	public RentalStoreGUI() {
	    
		setFrame();
	    store = new RentalStore();
		list.setModel(store);
		d = new DVD();

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
	
	private void setFrame() {
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Electric Boogaloo DVD & Game Rental");
        
        scrollPane = new JScrollPane();
        list = new JList<DVD>();

        scrollPane.setVerticalScrollBarPolicy(
                        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        list.setBorder(javax.swing.BorderFactory.createTitledBorder("List Of Rentals"));
        list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        list.setToolTipText("Select a rental to return");
        list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                selectMouseClicked(e);
            }
        });
        scrollPane.setViewportView(list);

        this.add(scrollPane, BorderLayout.CENTER);

        this.setJMenuBar(menuBar());
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setSize(800, 400);
		this.setResizable(true);
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
	
	private void selectMouseClicked(java.awt.event.MouseEvent e) {
		int index = list.getSelectedIndex();
		
		if(index != -1) {
			DVD d = store.getElementAt(index);
		}
	}

	public static void main(String[] args) {
		RentalStoreGUI gui = new RentalStoreGUI();
	}

}
