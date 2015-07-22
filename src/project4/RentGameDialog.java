package project4;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;

import project4.PlayerType;

public class RentGameDialog extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel nameL;
	private JTextField nameF;

	private JLabel titleL;
	private JTextField titleF;

	private GregorianCalendar calendar = new GregorianCalendar();
	private SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

	private JLabel rentDateL;
	private JTextField rentDateF;

	private JLabel dueDateL;
	private JTextField dueDateF;

	private JLabel consoleL;
	private JTextField consoleF;
	private PlayerType[] consoles;

	private JButton OK;
	private JButton cancel;

	private boolean closeStatus;

	private Game unit;

	public RentGameDialog(JFrame parent, Game g) {
	    	consoles = new PlayerType[5];
	    	for(int i = 0; i < consoles.length; i++) {
	    	    //consoles[i] = PlayerType.get
	    	}
	    	
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));
		panel.setVisible(true);

		unit = g;

		nameL = new JLabel("Your Name:");
		panel.add(nameL);
		nameF = new JTextField("Max Mustermann");
		panel.add(nameF);

		titleL = new JLabel("Title of Movie:");
		panel.add(titleL);
		titleF = new JTextField("Run Lola Run");
		panel.add(titleF);

		Date date = calendar.getTime();
		rentDateL = new JLabel("Rented on Date:");
		panel.add(rentDateL);
		rentDateF = new JTextField(fmt.format(date));
		panel.add(rentDateF);

		// creates a suggested due date of 1 week
		calendar.add(calendar.DAY_OF_MONTH, 7);
		Date dueDay = calendar.getTime();

		dueDateL = new JLabel("Due Back:");
		panel.add(dueDateL);
		dueDateF = new JTextField(fmt.format(dueDay));
		panel.add(dueDateF);
		

		consoleL = new JLabel("Console Type:");
		panel.add(consoleL);
		consoleF = new JTextField("GameCube");
		panel.add(consoleF);

		OK = new JButton("OK");
		OK.addActionListener(this);
		panel.add(OK);

		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		panel.add(cancel);

		getContentPane().add(panel);

	}
	
	public void setFrame() {
	    setModal(true);
	    setSize(300, 250);
	    setLocationRelativeTo(this);
	    setVisible(true);
	    setResizable(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == OK) {
		    	unit.setNameOfRenter(nameF.toString());
			unit.setTitle(titleF.toString());
			try {
				unit.setRentalDate(fmt.parse(rentDateF.toString()));
				unit.setDueBack(fmt.parse(dueDateF.toString()));
				PlayerType p = PlayerType.valueOf(consoleF.toString());
				unit.setConsole(p);
			} catch (Exception ex) {

			}
			dispose();
		}
		
		if(e.getSource() == cancel) {
		    	dispose();
		}
	}

}
