package project4;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;

import project4.PlayerType;

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
	private PlayerType[] consoles;

	private JButton OK;
	private JButton cancel;

	private boolean closeStatus;

	private Game unit;

	public RentGameDialog(JFrame parent, Game g) {
		super(parent);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));
		panel.setVisible(true);
		this.setModal(true);

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
		//consoleL.add(this);
		panel.add(consoleL);
		consoleF = new JTextField("GameCube");
		panel.add(consoleF);
		// will need something for this grid slot once we know what is going on with the
		// PlayerType[] thing

		OK = new JButton("OK");
		panel.add(OK);

		cancel = new JButton("Cancel");
		panel.add(cancel);

		getContentPane().add(panel);

	}

	public void actionedPerformedEvent(ActionEvent e) {
		if (e.getSource() == OK) {
			unit.setNameOfRenter(nameF.toString());
			unit.setTitle(titleF.toString());
			try {
				unit.setRentalDate(fmt.parse(rentDateF.toString()));
				unit.setDueBack(fmt.parse(dueDateF.toString()));
				PlayerType p = PlayerType.valueOf(consoleF.toString());
				unit.setConsole(p);
			} catch (Exception ec) {

			}
		}
	}

}
