package project4;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;

import project4.PlayerType;

/**
 * Custom JDialog Box for renting Games.
 * 
 * @author alexvansteel, kevinharrison
 *
 */
public class RentGameDialog extends JDialog implements ActionListener {

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

	/** Label for the game console. */
	private JLabel consoleL;
	/** Text field to enter game console */
	private JTextField consoleF;
	/** List of console types. */
	private PlayerType[] consoles;

	/** Button used for ok. */
	private JButton OK;
	/** Button used to cancel the dialog box. */
	private JButton cancel;

	private Date rent;
	private Date due;

	/** Checks to see if the dialog box was used to enter data. */
	private boolean closeStatus = false;
	private boolean rentSet = false;
	private boolean dueSet = false;
	private boolean tryStatus = false;
	/** Pointer for the newly created Game object. */
	private Game unit;

	/**
	 * Constructor for the Game Dialog Box
	 * 
	 * @param parent
	 * @param g
	 */
	public RentGameDialog(JFrame parent, Game g) {
		consoles = new PlayerType[PlayerType.values().length];
		int i = 0;
		for (PlayerType p : PlayerType.values()) {
			consoles[i] = p;
			i++;
		}

		rent = new Date();
		due = new Date();

		fmt.setLenient(false);

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
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		calendar.setLenient(false);
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

	/**
	 * Sets the parameters for the Dialog Box frame.
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
			setConsole();

			dispose();
		}

		if (comp == cancel) {
			unit.setNameOfRenter(null);
			dispose();
		}
	}

	/**
	 * Sets the dates for the day the Game is rented and due.
	 */
	private void setDates() {
		setRentalDate();
		setDueDate();
		checkDueDate();
	}

	/**
	 * Sets the rental date and makes sure it is in the correct date format.
	 */
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

	/**
	 * Sets the due date and makes sure it is in the correct date format.
	 */
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

	/**
	 * Checks the due date and makes sure it is after the rental date.
	 */
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

	/**
	 * Checks to see if the console is of a valid console type, and sets the
	 * console type.
	 */
	private void setConsole() {
		closeStatus = false;
		String console = consoleF.getText();
		console = console.replaceAll("\\s", "");

		while (closeStatus == false) {
			for (PlayerType p : consoles) {
				if (console.equalsIgnoreCase(p.name())) {
					PlayerType g = PlayerType.valueOf(p.name());
					unit.setConsole(g);
					closeStatus = true;
				}
			}
			if (closeStatus == false) {
				console = JOptionPane.showInputDialog("Console Type Incorrect",
						"try again");
			}
		}
	}

}
