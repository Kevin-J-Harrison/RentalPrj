package project4;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class DVD implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected GregorianCalendar bought;
	protected GregorianCalendar dueBack;
	
	protected String nameOfRenter;
	protected String title;
	
	public DVD() {
		
	}
	
	public DVD(GregorianCalendar rentalDateP, GregorianCalendar dueDateP,
			String nameOfRenterP, String titleP) {
		this.bought = rentalDateP;
		this.dueBack = dueDateP;
		this.nameOfRenter = nameOfRenterP;
		this.title = titleP;
	}

	public GregorianCalendar getRentalDate() {
		return bought;
	}

	public void setRentalDate(GregorianCalendar bought) {
		this.bought = bought;
	}

	public GregorianCalendar getDueBack() {
		return dueBack;
	}

	public void setDueBack(GregorianCalendar dueBack) {
		this.dueBack = dueBack;
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
	
	public double getCost() {
		double cost = 0.0;
		return cost;
	}
}
