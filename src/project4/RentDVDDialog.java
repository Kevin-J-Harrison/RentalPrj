package project4;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class RentDVDDialog extends JDialog {

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

	private JButton OK;
	private JButton cancel;

	private boolean closeStatus;

	private DVD unit;

	public RentDVDDialog(JFrame parent, DVD d) {
		
	    	JPanel panel = new JPanel();
	    	panel.setLayout(new GridLayout(5, 2));

		unit = d;
		

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

		OK = new JButton("OK");
		panel.add(OK);

		cancel = new JButton("Cancel");
		panel.add(cancel);

	}

	public void actionPerformedEvent(ActionEvent e) {
		if (e.getSource() == OK) {
			unit.setNameOfRenter(nameF.toString());
			unit.setTitle(titleF.toString());
			try {

				unit.setRentalDate(fmt.parse(rentDateF.toString()));
				unit.setDueBack(fmt.parse(dueDateF.toString()));
			} catch (Exception ex) {

			}
		}
	}
}
