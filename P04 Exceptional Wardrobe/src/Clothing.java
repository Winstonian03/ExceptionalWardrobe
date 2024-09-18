//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P04 Toy Exceptional Wardrobe - Clothing Class
// Course:   CS 300 Spring 2024
//
// Author:   Winston Chan
// Email:    wchan35@wisc.edu
// Lecturer: Andrew Kuemmel
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Ishaan Udia
// Partner Email:   udia@wisc.edu
// Partner Lecturer's Name: Andrew Kuemmel
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X_ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Online Sources:  P03 Toy Saga I
//                  P04 JavaDocs
// Assistance:      TA Diana, with exception tester methods + Wardrobe.addClothing
//
///////////////////////////////////////////////////////////////////////////////

import java.time.LocalDate;

/**
 * A class that represents a piece of clothing.
 * This class provides functionality to manage clothing items including
 * tracking the brand, description, last worn date, and the number of times worn.
 *
 * @author Winston Chan, Ishaan Udia
 */
public class Clothing {

    private String brand; // The brand of the item
    private String description; // A description of the item
    private LocalDate lastWornDate; // The last date the item was worn
    private int timesWorn; // The number of times the item has been worn

    /**
     * Creates a new clothing object with the given description and brand.
     *
     * @param description The description for this piece of clothing.
     * @param brand       The brand of this piece of clothing.
     * @throws IllegalArgumentException if the description or brand is blank or null.
     */
    public Clothing(String description, String brand) {
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Brand has a null value");
        } else if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description has a null value");
        }
        this.brand = brand;
        this.description = description;
    }

    /**
     * Creates a new clothing object with the given description, brand, times worn, and last worn date.
     *
     * @param description  The description for this piece of clothing.
     * @param brand        The brand of this piece of clothing.
     * @param timesWorn    The number of times this piece of clothing has been worn.
     * @param lastWornDate The date that this piece of clothing was last worn.
     * @throws IllegalArgumentException if the description or brand is blank or null.
     */
    public Clothing(String description, String brand, int timesWorn, LocalDate lastWornDate) {
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Brand has a null value");
        } else if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description has a null value");
        }
        this.brand = brand;
        this.description = description;
        this.lastWornDate = lastWornDate;
        this.timesWorn = timesWorn;
    }

    /**
     * Checks if Object o equals this clothing object.
     *
     * @param o The object to check if it equals this piece of clothing.
     * @return true if o is of type Clothing, the brands match ignoring case,
     * and the descriptions match ignoring case, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Clothing)) {
            return false;
        }
        Clothing O = (Clothing) o;
        return O.brand.equalsIgnoreCase(this.brand) && O.description.equalsIgnoreCase(this.description);
    }

    /**
     * Getter for the brand of this piece of clothing.
     *
     * @return The brand of this piece of clothing.
     */
    public String getBrand() {
        return this.brand;
    }

    /**
     * Getter for the description of this piece of clothing.
     *
     * @return The description of this piece of clothing.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter for the date that this piece of clothing was last worn.
     *
     * @return The last worn date of this piece of clothing.
     */
    public LocalDate getLastWornDate() {
        return this.lastWornDate;
    }

    /**
     * Getter for the number of times this piece of clothing has been worn.
     *
     * @return The number of times this piece of clothing has been worn.
     */
    public int getNumOfTimesWorn() {
        return this.timesWorn;
    }

    /**
     * Creates and returns a string representation of this Clothing object.
     * The String is formatted as "description,brand,lastWornDate,timesWorn".
     * If the last worn date is null, it is represented as "null".
     *
     * @return The string representation of this Clothing object.
     */
    @Override
    public String toString() {
        String strDate;
        if (this.lastWornDate == null) {
            strDate = "null";
        } else {
            strDate = String.format("%02d/%02d/%04d", this.lastWornDate.getMonthValue(),
                    this.lastWornDate.getDayOfMonth(), this.lastWornDate.getYear());
        }
        return (this.description + "," + this.brand + "," + strDate + "," + this.timesWorn);
    }

    /**
     * Updates the number of times this piece of clothing has been worn and the last worn date.
     *
     * @param year  The year of the new last worn date.
     * @param month The month of the new last worn date.
     * @param day   The day of the new last worn date.
     * @throws IllegalArgumentException if the year is less than 1, or the month is outside the
     *                                  range [1-12].
     */
    public void wearClothing(int year, int month, int day) throws IllegalArgumentException {
        if (year < 1) {
            throw new IllegalArgumentException("Year cannot be less than 1");
        }
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Months entered must be in range [1-12]");
        }
        this.lastWornDate = LocalDate.of(year, month, day);
        this.timesWorn += 1;
    }
}
