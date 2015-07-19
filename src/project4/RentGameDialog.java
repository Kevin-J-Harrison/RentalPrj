package project4;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;

public class RentGameDialog extends JDialog {
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
//	private PlayerType[] consoles;
	
	private JButton OK;
	private JButton cancel;
	
	private boolean closeStatus;
	
	private Game unit;
	
	public RentGameDialog( JFrame parent, Game g) {
		this.setLayout(new GridLayout(6, 2));
		
		unit = g;
		
		nameL = new JLabel("Your Name:");
		nameL.add(this);
		nameF = new JTextField("Max Mustermann");
		nameF.add(this);
		
		titleL = new JLabel("Title of Game:");
		titleL.add(this);
		titleF = new JTextField("Space Invaders");
		titleF.add(this);
		
		Date date = calendar.getTime();
		rentDateL = new JLabel("Rented on Date:");
		rentDateL.add(this);
		rentDateF = new JTextField(fmt.format(date));
		rentDateF.add(this);
		
		// creates a suggested due date of 1 week
		calendar.add(calendar.DAY_OF_MONTH, 7);
		Date dueDay = calendar.getTime();
		
		dueDateL = new JLabel("Due Back:");
		dueDateL.add(this);
		dueDateF = new JTextField(fmt.format(dueDay));
		dueDateF.add(this);
		
		consoleL = new JLabel("Console Type:");
		consoleL.add(this);

		OK = new JButton("OK");
		OK.add(this);
		
		cancel = new JButton("Cancel");
		cancel.add(this);
	}
	
	public void actionedPerformedEvent(ActionEvent e) {
		if (e.getSource() == OK) {
//			unit.setName(nameF.toString());
//			unit.setTitle(titleF.toString());
//			unit.setRentDate(rentDateF.toString());
//			unit.setDueDate(dueDateF.toString());
//			unit.setConsole(consoleF.toString());
		}
		
	}

}
