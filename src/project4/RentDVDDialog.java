package project4;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Custom JDialog Box for renting DVDs
 * 
 * @author alexvansteel, kevinharrison
 *
 */
public class RentDVDDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	/** Label for the name. */
	private JLabel nameL;
	/** Field for entering name. */
	private JTextField nameF;

	/** Label for title. */
	private JLabel titleL;
	/** Field of entering title. */
	private JTextField titleF;

	/** Gregorian Calendar for calculating dates. */
	private GregorianCalendar calendar = new GregorianCalendar();
	/** Format for dates. */
	private SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");

	/** Label for renting. */
	private JLabel rentDateL;
	/** Text Field for entering rental date. */
	private JTextField rentDateF;

	/** Label for due date. */
	private JLabel dueDateL;
	/** Text field for entering due date. */
	private JTextField dueDateF;

	/** Button used for ok. */
	private JButton OK;
	/** Button used to cancel the dialog box. */
	private JButton cancel;
	
	private Date rent;
	private Date due;

	/** Checks to see if the dialog box was used to enter data. */
	private boolean closeStatus = false;
	private boolean tryStatus = false;
	private boolean rentSet = false;
	private boolean dueSet = false;

	/** Pointer for the newly created DVD object. */
	private DVD unit;

	/**
	 * Constructor for the Dialog Box.
	 * 
	 * @param parent
	 * @param d
	 */

	public RentDVDDialog(JFrame parent, DVD d) {
		fmt.setLenient(false);
		rent = new Date();
		due = new Date();

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));
		panel.setVisible(true);

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
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		calendar.setLenient(false);
		Date dueDay = calendar.getTime();

		dueDateL = new JLabel("Due Back:");
		panel.add(dueDateL);
		dueDateF = new JTextField(fmt.format(dueDay));
		panel.add(dueDateF);

		OK = new JButton("OK");
		OK.addActionListener(this);
		panel.add(OK);

		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		panel.add(cancel);

		getContentPane().add(panel);

	}

	/**
	 * Sets parameters for the Dialog Box frame.
	 */
	public void setFrame() {
		setModal(true);
		setSize(300, 250);
		setLocationRelativeTo(this);
		setVisible(true);
		setResizable(true);
	}

	/**
	 * Action Listener and events for using the frame.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JComponent comp = (JComponent) e.getSource();
		if (comp == OK) {
			unit.setNameOfRenter(nameF.getText());
			unit.setTitle(titleF.getText());
			setDates();

			dispose();
		}

		if (comp == cancel) {
			unit.setNameOfRenter(null);
			dispose();
		}
	}

	private void setDates() {
		setRentalDate();
		setDueDate();
		checkDueDate();
	}
	
	private void setRentalDate() {
		while (rentSet == false) {
			try {
				if (tryStatus == false) {
					tryStatus = true;
					rent = fmt.parse(rentDateF.getText());
					unit.setRentalDate(rent);
					rentSet = true;
				} else {
					String rentDate = JOptionPane.showInputDialog(
							"Incorrect Rental Date Format, Enter Again",
							"MM/DD/YYY");
					if (rentDate == null) {
						rentSet = true;
						dueSet = true;
						closeStatus = true;
						unit.setNameOfRenter(null);
					} else {
						rent = fmt.parse(rentDate);
						unit.setRentalDate(rent);
						rentSet = true;
					}
				}
			} catch (ParseException e1) {

			}
		}

		tryStatus = false;
	}
	
	private void setDueDate() {
		while (dueSet == false) {
			try {
				if (tryStatus == false) {
					tryStatus = true;
					due = fmt.parse(dueDateF.getText());
					unit.setDueBack(due);
					dueSet = true;
				} else {
					String dueDate = JOptionPane.showInputDialog(
							"Incorrect Due Date Format, Enter Again",
							"MM/DD/YYYY");
					if (dueDate == null) {
						dueSet = true;
						closeStatus = true;
						unit.setNameOfRenter(null);
					} else {
						due = fmt.parse(dueDate);
						unit.setDueBack(due);
						dueSet = true;
					}
				}
			} catch (ParseException e2) {

			}
		}
	}
	
	private void checkDueDate() {
		while (closeStatus == false) {
			try {
				if (due.after(rent)) {
					closeStatus = true;
				} else {
					String dueDate = JOptionPane.showInputDialog(
							"Due Date Must Be After " + fmt.format(rent),
							"MM/DD/YYYY");
					if (dueDate == null) {
						closeStatus = true;
						unit.setNameOfRenter(null);
					} else {
						due = fmt.parse(dueDate);
						unit.setDueBack(due);
						closeStatus = true;
					}
				}
			} catch (ParseException e3) {

			}
		}
	}
}
