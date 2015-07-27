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

public class RentGameDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JLabel nameL;
    private JTextField nameF;

    private JLabel titleL;
    private JTextField titleF;

    private GregorianCalendar calendar = new GregorianCalendar();
    private final SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

    private JLabel rentDateL;
    private JTextField rentDateF;

    private JLabel dueDateL;
    private JTextField dueDateF;

    private JLabel consoleL;
    private JTextField consoleF;

    private JButton OK;
    private JButton cancel;

    private boolean closeStatus;

    private Game unit;

    public RentGameDialog(JFrame parent, Game g) {
	closeStatus = false;
	int i = 0;

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

    public void setFrame() {
	setModal(true);
	setSize(300, 250);
	setLocationRelativeTo(this);
	setVisible(true);
	setResizable(true);
    }

    public boolean getCloseStatus() {
	return closeStatus;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	JComponent comp = (JComponent) e.getSource();
	if (comp == OK) {
	    unit.setNameOfRenter(nameF.getText());
	    unit.setTitle(titleF.getText());
	    while (closeStatus == false) {
		try {
		    unit.setRentalDate(fmt.parse(rentDateF.getText()));
		} catch (ParseException ex) {

		}

		try {
		    unit.setDueBack(fmt.parse(dueDateF.getText()));
		} catch (ParseException ex) {

		}
	    }

	    PlayerType p = PlayerType.valueOf(consoleF.getText());
	    unit.setConsole(p);
	    dispose();
	}

	if (comp == cancel) {
	    dispose();
	}
    }

}
