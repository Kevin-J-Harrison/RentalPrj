package project4;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RentDVDDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JLabel nameL;
	private JTextField nameF;

	private JLabel titleL;
	private JTextField titleF;

	private GregorianCalendar calendar = new GregorianCalendar();
	private SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");

	private JLabel rentDateL;
	private JTextField rentDateF;

	private JLabel dueDateL;
	private JTextField dueDateF;

	private JButton OK;
	private JButton cancel;

	private boolean closeStatus;

	private DVD unit;

	public RentDVDDialog(JFrame parent, DVD d) {
		closeStatus = false;

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

	public void setFrame() {
		setModal(true);
		setSize(300, 250);
		setLocationRelativeTo(this);
		setVisible(true);
		setResizable(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComponent comp = (JComponent) e.getSource();
		if (comp == OK) {
			closeStatus = true;
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
		closeStatus = false;
		boolean tryStatus = false;
		boolean rentSet = false;
		boolean dueSet = false;

		Date rent = new Date();
		Date due = new Date();

		while (rentSet == false) {
			try {
				if (tryStatus = false) {
					tryStatus = true;
					rent = fmt.parse(rentDateF.getText());
					unit.setRentalDate(rent);
					rentSet = true;
				} else {
					rent = fmt.parse(JOptionPane.showInputDialog(
							"Incorrect Rental Date Format, Enter Again",
							"MM/DD/YYY"));
					unit.setRentalDate(rent);
					rentSet = true;
				}
			} catch (ParseException e1) {

			}
		}

		tryStatus = false;
		while (dueSet == false) {
			try {
				if (tryStatus == false) {
					due = fmt.parse(dueDateF.getText());
					unit.setDueBack(due);
					dueSet = true;
				} else {
					due = fmt.parse(JOptionPane.showInputDialog(
							"Incorrect Due Date Format, Enter Again",
							"MM/DD/YYYY"));
					unit.setDueBack(due);
					dueSet = true;
				}
			} catch (ParseException e2) {

			}
		}

		while (closeStatus == false) {
			try {
				if (due.after(rent)) {
					closeStatus = true;
				} else {
					due = fmt.parse(JOptionPane.showInputDialog(
							"Due Date Must Be After " + fmt.format(rent),
							"MM/DD/YYYY"));
					unit.setDueBack(due);
				}
			} catch (ParseException e3) {

			}
		}
	}
}
