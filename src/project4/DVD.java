package project4;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class DVD implements Serializable {

    private static final long serialVersionUID = 1L;

    protected GregorianCalendar bought;
    protected GregorianCalendar dueBack;

    protected String nameOfRenter;
    protected String title;

    public DVD() {
	bought = new GregorianCalendar();
	dueBack = new GregorianCalendar();
    }

    public DVD(GregorianCalendar rentalDateP, GregorianCalendar dueDateP,
	    String nameOfRenterP, String titleP) {
	bought = new GregorianCalendar(); 
	bought = rentalDateP;
	
	dueBack = new GregorianCalendar();
	dueBack = dueDateP;
	
	nameOfRenter = nameOfRenterP;
	title = titleP;
    }

    public GregorianCalendar getRentalDate() {
	return bought;
    }

    public void setRentalDate(Date rented) {
	bought.setTime(rented);
    }

    public GregorianCalendar getDueBack() {
	return dueBack;
    }

    public void setDueBack(Date due) {
	dueBack.setTime(due);
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
