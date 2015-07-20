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
		this.setLayout(new GridLayout(5, 2));

		unit = d;

		nameL = new JLabel("Your Name:");
		nameL.add(this);
		nameF = new JTextField("Max Mustermann");
		nameF.add(this);

		titleL = new JLabel("Title of Movie:");
		titleL.add(this);
		titleF = new JTextField("Run Lola Run");
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

		OK = new JButton("OK");
		OK.add(this);

		cancel = new JButton("Cancel");
		cancel.add(this);

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
