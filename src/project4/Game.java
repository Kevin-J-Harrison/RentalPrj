package project4;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Extends the DVD class to create a Game object, which extends the DVD object.
 * 
 * @author alexvansteel, kevinharrison
 *
 */
public class Game extends DVD {

	private static final long serialVersionUID = 1L;

	/** The type of game console. */
	private PlayerType console;

	public Game() {
		super();
		dueBack.setLenient(false);
		bought.setLenient(false);
	}

	/**
	 * Constructor for the Game class
	 * 
	 * @param rentalDateP
	 * @param dueDateP
	 * @param nameOfRenterP
	 * @param titleP
	 * @param consoleP
	 */
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

	/**
	 * Calculates the cost after returning the Game.
	 * 
	 * @param dayDiff
	 *            number of days the Game is late.
	 * @return the cost of the rental.
	 */
	public double getCost(int dayDiff) {
		double cost = 0.0;

		if (dayDiff <= 0)
			return cost = 5;

		cost = 15;

		return cost;
	}
}
