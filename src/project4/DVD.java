package project4;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Class to create DVD objects.
 * 
 * @author alexvansteel, kevinharrison
 *
 */
public class DVD implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Date the DVD was bought. */
    protected GregorianCalendar bought = new GregorianCalendar();
    /** Date the DVD is due back. */
    protected GregorianCalendar dueBack = new GregorianCalendar();

    /** Name of the Renter. */
    protected String nameOfRenter;
    /** Title of the DVD. */
    protected String title;

    public DVD() {
	bought.setLenient(false);
	dueBack.setLenient(false);
    }

    public DVD(GregorianCalendar rentalDateP, GregorianCalendar dueDateP,
	    String nameOfRenterP, String titleP) {
	bought = rentalDateP;
	dueBack = dueDateP;
	nameOfRenter = nameOfRenterP;
	title = titleP;
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

    /**
     * Calculates the cost after returning the DVD.
     * 
     * @param dayDiff
     *            number of days the DVD is late.
     * @return the cost of the rental.
     */
    public double getCost(int dayDiff) {
	double cost = 0.0;

	if (dayDiff <= 0)
	    return cost = 1.2;

	cost = 3.2;

	return cost;
    }
}
