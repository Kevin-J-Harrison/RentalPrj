package project4;

import java.util.Date;
import java.util.GregorianCalendar;

public class Game extends DVD {

	private static final long serialVersionUID = 1L;

	private PlayerType console;

	public Game() {
		super();
	}

	public Game(GregorianCalendar rentalDateP, GregorianCalendar dueDateP,
			String nameOfRenterP, String titleP, PlayerType consoleP) {
		super();
		bought = rentalDateP;
		dueBack = dueDateP;
		nameOfRenter = nameOfRenterP;
		title = titleP;
		console = consoleP;
	}

	public GregorianCalendar getRentalDate() {
		return bought;
	}

	public void setRentalDate(Date bought) {
		this.bought.setTime(bought);
	}

	public GregorianCalendar getDueBack() {
		return dueBack;
	}

	public void setDueBack(Date dueBack) {
		this.dueBack.setTime(dueBack);
	}

	public String getNameOfRenter() {
		return nameOfRenter;
	}

	public void setNameOfRenter(String nameOfRenter) {
		this.nameOfRenter = nameOfRenter;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public PlayerType getConsole() {
		return console;
	}

	public void setConsole(PlayerType console) {
		this.console = console;
	}

	public double getCost() {
		double cost = 0.0;
		return cost;
	}
}
