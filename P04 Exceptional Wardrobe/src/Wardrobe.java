//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P04 Toy Exceptional Wardrobe - Wardrobe Class
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

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

import java.io.File;
import java.text.ParseException;
import java.util.NoSuchElementException;

/**
 * Represents a wardrobe containing an array of Clothing items.
 * Extends the Object class.
 *
 * @author Winston Chan, Ishaan Udia, Diana
 */
public class Wardrobe extends Object {

    private Clothing[] wardrobe; // Array of Clothing items in the wardrobe
    private int wardrobeSize; // The size of the wardrobe

    /**
     * Creates a new Wardrobe object that is empty with the given capacity.
     *
     * @param capacity the number of clothing that the wardrobe can fit
     * @throws IllegalArgumentException with a descriptive message if the capacity is non-positive
     *                                  (less than or equal to 0)
     */
    public Wardrobe(int capacity) {

        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be greater than or equal to 1!");
        }
        this.wardrobe = new Clothing[capacity];
        this.wardrobeSize = 0;
    }


    /**
     * Finds and returns the piece of clothing with the matching description and brand.
     * The comparisons are CASE SENSITIVE.
     *
     * @param description the description of the piece of clothing to find
     * @param brand       the brand of the piece of clothing to find
     * @return the Clothing object in the Wardrobe that matches the given description and brand
     * @throws NoSuchElementException with a descriptive message if the clothing does not exist in the wardrobe
     */
    public Clothing getClothing(String description, String brand) {


        for (int i = 0; i < wardrobeSize; i++) {

            if (wardrobe[i].getBrand().equalsIgnoreCase(brand)
                    && wardrobe[i].getDescription().equalsIgnoreCase(description)) {
                return wardrobe[i];
            }
        }

        throw new NoSuchElementException("Clothing does not exist in wardobe");

    }

    /**
     * Adds a piece of clothing at the end of the wardrobe.
     * If the wardrobe does not have room for the piece of clothing, the wardrobe expands by
     * doubling in capacity. Then adds the new piece of clothing.
     *
     * @param toAdd the piece of clothing to add to the wardrobe
     * @throws IllegalArgumentException with a descriptive message if toAdd is already in the wardrobe
     */
    public void addClothing(Clothing toAdd) throws IllegalArgumentException {
        // Method implementation here
        for (int i = 0; i < wardrobeSize; i++) {
            if (wardrobe[i].equals(toAdd)) {
                throw new IllegalArgumentException("Clothing already exists");
            }
        }

        if (this.wardrobeSize == this.wardrobe.length) {
            Clothing[] arrayCopy = new Clothing[wardrobe.length * 2];
            for (int k = 0; k < wardrobeSize; k++) {
                arrayCopy[k] = wardrobe[k];
            }

            this.wardrobe = arrayCopy;
            this.wardrobe[wardrobeSize] = toAdd;
            this.wardrobeSize += 1;
        } else {
            this.wardrobe[wardrobeSize] = toAdd;
            this.wardrobeSize += 1;
        }
    }


    /**
     * Wears the piece of Clothing in this Wardrobe equal to the provided Clothing on the given date.
     *
     * @param toWear the piece of clothing in the Wardrobe that we want to wear
     * @param year   the year that it will be worn
     * @param month  the month that it will be worn
     * @param day    the day that it will be worn
     * @throws IllegalArgumentException with a descriptive message if the year is less than 1,
     *                                  or the month is outside the range [1,12]
     */
    public void wearClothing(Clothing toWear, int year, int month, int day) {
        boolean itemFound = false;

        for (int i = 0; i < wardrobeSize; i++) {
            if (wardrobe[i].equals(toWear)) {
                wardrobe[i].wearClothing(year, month, day);
                itemFound = true;
                break;
            }
        }

        if (!itemFound) {
            throw new IllegalArgumentException("Clothing item with name " + toWear.getBrand()
                    + " not found in wardrobe.");
        }
    }

    /**
     * Getter for the capacity of this wardrobe.
     *
     * @return the number of pieces of clothing this wardrobe can potentially hold
     */
    public int capacity() {

        return this.wardrobe.length;
    }

    /**
     * Getter for the size of this wardrobe.
     *
     * @return the number of pieces of clothing in this wardrobe
     */
    public int size() {
        return this.wardrobeSize;
    }

    /**
     * Removes the piece of clothing from the wardrobe that has a matching description and brand.
     *
     * @param description the description of the piece of clothing to remove
     * @param brand       the brand of the piece of clothing to remove
     * @throws IllegalStateException  with a descriptive message if the wardrobe is empty
     * @throws NoSuchElementException with a descriptive message if the piece of clothing
     *                                is not in the wardrobe
     */
    public void removeClothing(String description, String brand) {
        int indextoRemove = -1;

        for (int i = 0; i < wardrobeSize; i++) {
            if (this.wardrobeSize == 0) {
                throw new IllegalStateException("Wardrobe is empty");
            }

            if (wardrobe[i].getBrand().equals(brand)
                    && wardrobe[i].getDescription().equals(description)) {
                indextoRemove = i;
            }
        }

        if (indextoRemove == -1) {
            throw new NoSuchElementException("Clothing not present in wardrobe");
        }

        for (int j = indextoRemove; j < wardrobeSize - 1; j++) {
            wardrobe[j] = wardrobe[j + 1];
        }

        wardrobe[wardrobeSize - 1] = null;
        this.wardrobeSize--;
    }

    /**
     * Removes all pieces of clothing from the wardrobe whose last worn date is BEFORE
     * the given day, month, and year.
     *
     * @param year  the year of the date to use to remove clothes
     * @param month the month of the date to use to remove clothes
     * @param day   the day of the date to use to remove clothes
     */
    public void removeAllClothingWornBefore(int year, int month, int day) {
        LocalDate a = LocalDate.of(year, month, day);
        int newSize = 0;
        for (int i = 0; i < wardrobeSize; i++) {
            LocalDate lastWornDate = wardrobe[i].getLastWornDate();
            if (lastWornDate != null && !lastWornDate.isBefore(a)) {
                wardrobe[newSize++] = wardrobe[i];
            }
        }

        for (int i = newSize; i < wardrobeSize; i++) {
            wardrobe[i] = null;
        }

        wardrobeSize = newSize;
    }

    /**
     * Removes all pieces of clothing from the wardrobe who have been worn less times than the
     * given threshold.
     *
     * @param threshold the upper bound (exclusive) of number of times worn
     */
    public void removeAllClothingWornNumTimes(int threshold) {
        int newSize = 0;
        for (int i = 0; i < wardrobeSize; i++) {
            if (wardrobe[i].getNumOfTimesWorn() > threshold) {
                wardrobe[newSize++] = wardrobe[i];
            }
        }

        for (int i = newSize; i < wardrobeSize; i++) {
            wardrobe[i] = null;
        }
        wardrobeSize = newSize;
    }

    /**
     * Creates a new Clothing object based on the given String formatted
     * "description,brand,lastWornDate,timesWorn".
     *
     * @param str the String parse to make a Clothing object
     * @return a Clothing object with the pieces of information in the given string
     * @throws ParseException with a descriptive message if the string does not have the 4 required
     *                        pieces of information OR if there was an issue converting pieces of information to an int or
     *                        Date object
     */

    public static Clothing parseClothing(String str) throws ParseException {

        String[] strArr = str.split(",");
        if (strArr.length != 4) {
            throw new ParseException("One of the variables in the string is missing", 0);
        }

        String description = strArr[0].trim();
        String brand = strArr[1].trim();
        int timesWorn;
        LocalDate lastWornDate;

        try {
            timesWorn = Integer.parseInt(strArr[3].trim());
        } catch (NumberFormatException e) {
            throw new ParseException("Number of times worn could not be parsed as an integer", 0);
        }

        if (strArr[2].trim().equalsIgnoreCase("null")) {
            lastWornDate = null;
        } else {
            try {
                lastWornDate = LocalDate.parse(strArr[2].trim(),
                        DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            } catch (DateTimeParseException e) {
                throw new ParseException("Last worn date could not be parsed", 0);
            }
        }

        if (lastWornDate == null) {
            return new Clothing(description, brand);
        }

        return new Clothing(description, brand, timesWorn, lastWornDate);
    }

    /**
     * Loads all pieces of clothing into this wardrobe from the designated file. Each piece of
     * clothing in the Wardrobe is written on its own line, formatted as description,brand,
     * lastWornDate,timesWorn.The date must be formatted MM/DD/YYYY.
     * If a line is NOT properly formatted 1) the String "Cannot parse line to Clothing object"
     * should be printed out to the console and 2) will be skipped, the method should continue to
     * read the remaining lines.
     *
     * @param saveFile the File that the information should be read from
     * @return true if ANY of the lines from the file were parsed successfully into Clothing for this Wardrobe, false otherwise
     */
    public boolean loadFromFile(File saveFile) {
        boolean hasValidLine = false;

        try (Scanner scanner = new Scanner(saveFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    Clothing item = parseClothing(line);
                    if (item != null) {
                        addClothing(item);
                        hasValidLine = true;
                    }
                } catch (ParseException e) {
                    System.err.println("Error parsing line: " + line + " - " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.err.println("No more tokens are available in the file: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("Scanner closed: " + e.getMessage());
        }

        return hasValidLine;
    }

    /**
     * Saves all pieces of clothing in this wardrobe to the designated file. Each piece of clothing
     * in the Wardrobe is written on its own line, formatted as description,brand,lastWornDate,timesWorn.
     * The date must be formatted MM/DD/YYYY.
     *
     * @param saveFile the File that the information should be written to
     * @return true if the file saved successfully, false otherwise
     */
    public boolean saveToFile(File saveFile) {
        try (FileWriter writer = new FileWriter(saveFile)) {
            for (int i = 0; i < wardrobeSize; i++) {
                writer.write(wardrobe[i] + "\n");
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Gets the array that contains all the Clothing in the wardrobe.
     *
     * @return the wardrobe array
     */
    protected Clothing[] getArray() {
        return this.wardrobe;
    }

    /**
     * Creates and returns a string representation of this Wardrobe object. Each piece of clothing
     * in the wardrobe should be printed in order on a new line enclosed in [] brackets. The last
     * line should NOT have a new line character.
     *
     * @return the String representation of this Wardrobe object
     */
    @Override
    public String toString() {
        String toReturn = "";

        for (int i = 0; i < this.wardrobeSize; i++) {
            if (i < wardrobeSize - 1) {
                toReturn += "[" + wardrobe[i].toString() + "]" + "\n";
            }
            toReturn += "[" + wardrobe[i].toString() + "]";
        }
        return toReturn;
    }
}
