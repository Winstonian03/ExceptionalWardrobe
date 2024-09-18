//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P04 Toy Exceptional Wardrobe - WardrobeManagerTester
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
import java.util.NoSuchElementException;
import java.io.File;
import java.util.Arrays;

/**
 * A tester class for the Wardrobe Manager project. It contains various tests
 * to check the correctness of the Wardrobe and Clothing classes.
 *
 * @author Winston Chan, Ishaan Udia, Diana
 */
public class WardrobeManagerTester {

    /**
     * Tests both of the Clothing constructors and all getters for correctness.
     * This test accounts for the fact a bad implementation may throw an Exception.
     *
     * @return true if all tests pass, false otherwise
     * @author Michelle
     */
    public static boolean testClothingConstructorAndGetters() {

        // in case we get an unexpected exception from a broken implementation
        // we handle it with a try-catch to avoid our tester from crashing
        try {
            // test the 2-argument constructor
            Clothing c = new Clothing("black t-shirt", "Gildan");

            // check that the four instance data fields have been initialized correctly
            // using the getters to do this we are also checking their correctness
            // in a bad implementation either 1) the constructor didn't intialize a data field correctly
            // OR 2) the getter doesn't return the correct value
            if (!c.getDescription().equals("black t-shirt")) return false;
            if (!c.getBrand().equals("Gildan")) return false;
            if (c.getNumOfTimesWorn() != 0) return false;
            if (c.getLastWornDate() != null) return false;

            // test the 4 argument constructor
            // same idea as the previous test case
            LocalDate date = LocalDate.of(2024, 2, 14); // create a date object for last worn
            c = new Clothing("jeans", "Levi", 3, date);
            if (!c.getDescription().equals("jeans")) return false;
            if (!c.getBrand().equals("Levi")) return false;
            if (c.getNumOfTimesWorn() != 3) return false;
            if (!c.getLastWornDate().equals(date)) return false;

        } catch (Exception e) { // we encounter an exception when we should not, it is a bad implementation
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Tests that both of the Clothing constructors throw the correct type of exception(s)
     * with a message in situations where an exception is expected.
     *
     * @return true if all tests pass, false otherwise
     * @author Michelle and Hobbes
     */
    public static boolean testClothingConstructorExceptions() {
        // Here we call constructors with input that should lead to an IllegalArgumentException
        // on a correct (good) implementation. ALL of these cases SHOULD throw exceptions,
        // so we test them in separate try-catch statements to verify that each individual
        // case throws an exception.

        try {
            // test the 2 argument constructor with a blank description
            new Clothing(" ", "Gildan");

            return false; // no exception was thrown when it should have been; it's a broken implementation
        } catch (IllegalArgumentException e) {
            // check if the exception has any message; if there is NO message it's a broken implementation
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) { // any other type of exception is not good, it's a broken implementation
            e.printStackTrace();
            return false;
        }

        try {
            // and make sure a blank brand will also throw an exception
            new Clothing("black t-shirt", "  ");

            return false; // no exception was thrown
        } catch (IllegalArgumentException e) {
            // check if the exception has a message
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) { // any other type of exception is not good
            e.printStackTrace();
            return false;
        }

        try {
            // test the 4 argument constructor with a blank description
            LocalDate date = LocalDate.of(2021, 12, 25);
            new Clothing(" ", "Gildan", 4, date);

            return false; // no exception was thrown
        } catch (IllegalArgumentException e) {
            // check if the exception has a message
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) { // any other type of exception is not good
            e.printStackTrace();
            return false;
        }

        try {
            // and verifying that a blank brand will also throw an exception
            LocalDate date = LocalDate.of(2021, 12, 25);
            new Clothing("black t-shirt", "  ", 6, date);

            return false; // no exception was thrown
        } catch (IllegalArgumentException e) {
            // check if the exception has a message,
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) { // any other type of exception is not good
            e.printStackTrace();
            return false;
        }

        // passed all the tests!
        return true;
    }

    /**
     * Tests for the correctness of the Clothing classes' wearClothing() method.
     * This test accounts for the fact a bad implementation may throw an Exception.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testClothingWear() {
        try {
            // and verifying that wearClothing will change the last worn date and increment numTimesWorn
            LocalDate date = LocalDate.of(2021, 12, 25);
            LocalDate presentDate = LocalDate.of(2024, 6, 23);
            Clothing c = new Clothing("black t-shirt", "Gildan", 6, date);
            c.wearClothing(2024, 6, 23);

            if (!c.getDescription().equals("black t-shirt")) return false;
            if (!c.getBrand().equals("Gildan")) return false;
            if (c.getNumOfTimesWorn() != 7) return false;
            if (!c.getLastWornDate().equals(presentDate)) return false;
        } catch (IllegalArgumentException e) {
            // check if the exception has a message,
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) { // any other type of exception is not good
            e.printStackTrace();
            return false;
        }

        try {
            // and verifying that a negative year will throw an exception
            LocalDate date = LocalDate.of(2021, 12, 25);
            LocalDate presentDate = LocalDate.of(2021, 6, 23);
            Clothing c = new Clothing("black t-shirt", "Gildan", 6, date);
            c.wearClothing(-1, 6, 23);

            return false; // no exception was thrown
        } catch (IllegalArgumentException e) {
            // check if the exception has a message,
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) { // any other type of exception is not good
            e.printStackTrace();
            return false;
        }

        try {
            // and verifying that a incorrect month will also throw an exception
            LocalDate date = LocalDate.of(2021, 12, 25);
            LocalDate presentDate = LocalDate.of(2021, 6, 23);
            Clothing c = new Clothing("black t-shirt", "Gildan", 6, date);
            c.wearClothing(2024, 13, 23);

            return false; // no exception was thrown
        } catch (IllegalArgumentException e) {
            // check if the exception has a message,
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) { // any other type of exception is not good
            e.printStackTrace();
            return false;
        }

        try {
            // and verifying that a incorrect day on a non-leap year will also throw an exception
            LocalDate date = LocalDate.of(2021, 12, 25);
            LocalDate presentDate = LocalDate.of(2021, 6, 23);
            Clothing c = new Clothing("black t-shirt", "Gildan", 6, date);
            c.wearClothing(2025, 2, 29);

            return false; // no exception was thrown
        } catch (IllegalArgumentException e) {
            // check if the exception has a message,
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                return false;
            }
        } catch (Exception e) { // any other type of exception is not good
            if (e.getMessage() == null || e.getMessage().isBlank()) {

                return false;
            }
        }
        return true;
    }

    /**
     * Tests the Wardrobe constructor and all getters for correctness.
     * This test accounts for the fact a bad implementation may throw an Exception.
     *
     * @return true if all tests pass, false otherwise
     */

    public static boolean testWardrobeConstructorAndGetters() {
        Clothing c1 = new Clothing("blue jeans", "Levi's", 32,
                LocalDate.of(2023, 5, 10));
        Clothing c2 = new Clothing("red dress", "Zara", 8,
                LocalDate.of(2022, 7, 15));

        Clothing expectedArray[] = new Clothing[3];
        expectedArray[0] = c1;
        expectedArray[1] = c2;

        int expectedCapacity = 3;//  expected Size for wardrobe 3
        int expectedSize = 2; //  expected Size for wardrobe 3
        Wardrobe wardrobe3 = new Wardrobe(3);

        //testing  capacity getter
        if (wardrobe3.capacity() != expectedCapacity) {
            return false;
        }

        wardrobe3.addClothing(c1); //added two clothes objects to array
        wardrobe3.addClothing(c2);

        //test get getArray()
        if (!Arrays.equals(wardrobe3.getArray(), expectedArray)) {
            return false;
        }

        //test size getter
        if (wardrobe3.size() != expectedSize) {
            return false;
        }

        //test get clothing
        try {
            if (!(wardrobe3.getClothing("blue jeans", "Levi's").equals(c1))) {
                return false;       //returns false if clothing does not match expected clothing c1
            }
        } catch (Exception e) {
            return false; //in this case an exception should not be caught
        }
        return true; //returns true if all tests are passed.
    }

    /**
     * Tests that the Wardrobe constructor throws the correct type of exception(s)
     * with a message in situations where an exception is expected.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testWardrobeConstructorExceptions() {
        //testing constructor with positive integer capacity
        try {
            Wardrobe wardrobe1 = new Wardrobe(3);
        } catch (Exception e) {
            e.printStackTrace();
            return false;   //returns false any  exception is caught,
        }


        //testing constructor with negative integer
        try {
            Wardrobe wardrobe2 = new Wardrobe(0);
            return false; // returns false if exception has been caught
        } catch (IllegalArgumentException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                return false;   // if no exception message has been detected
            }
        }

        //tests for presence of thrown NoSuchException
        Wardrobe wardrobe3 = new Wardrobe(3);

        //added two new objects into wardrobe array
        wardrobe3.addClothing(new Clothing("blue shorts", "Levi's", 32, LocalDate.of(2023, 5, 10)));
        wardrobe3.addClothing(new Clothing("red pants", "Zara", 8, LocalDate.of(2022, 7, 15)));

        try {
            Clothing getClothe = wardrobe3.getClothing("abcd", "edfg");
        } catch (NoSuchElementException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                return false;   // if no exception message has been detected
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; //should not return this type of exception
        }
        return true;
    }

    /**
     * Tests that the Wardrobe's addClothing() method throws the correct type of exception(s)
     * with a message in situations where an exception is expected.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testAddClothingExceptions() {
        //tests already added
        Wardrobe wardrobe1 = new Wardrobe(4);

        try {
            wardrobe1.addClothing(new Clothing("blue jeans", "Levis", 32, LocalDate.of(2023, 5, 10)));
            wardrobe1.addClothing(new Clothing("red dress", "Zara", 8, LocalDate.of(2022, 7, 15)));
            wardrobe1.addClothing(new Clothing("blue t-shirt", "Gildan", 6, LocalDate.of(2024, 6, 23)));
            //same clothing added
            wardrobe1.addClothing(new Clothing("blue t-shirt", "Gildan", 6, LocalDate.of(2024, 6, 23)));

            return false;

        } catch (IllegalArgumentException e) {
            if (wardrobe1.size() != 3 || wardrobe1.capacity() != 4) {
                return false; //ensures array hasn't been modified
            } else if (e.getMessage() == null || e.getMessage().isBlank()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Tests the Wardrobe's addClothing() method for correctness.
     * This test accounts for the fact a bad implementation may throw an Exception.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testAddClothing() {
        Wardrobe wardrobe1 = new Wardrobe(2);

        try {
            wardrobe1.addClothing(new Clothing("blue jeans", "Levis", 32, LocalDate.of(2023, 5, 10)));
            wardrobe1.addClothing(new Clothing("red dress", "Zara", 8, LocalDate.of(2022, 7, 15)));
            if (wardrobe1.size() != 2 || wardrobe1.capacity() != 2) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

// now tests if wardrobe (array) doubles in capacity
        try {
            wardrobe1.addClothing(new Clothing("jeans", "Denim", 32, LocalDate.of(2022, 11, 21)));
            //checks where wardrobe maximum capacity update to 4
            if (wardrobe1.size() != 3 || wardrobe1.capacity() != 4) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false; //should not be throw
        }
        return true;
    }


    /**
     * Tests the Wardrobe's getClothing() method for correctness.
     * This test accounts for the fact a bad implementation may throw an Exception.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testGetClothing() {
        Wardrobe wardrobe1 = new Wardrobe(3);

        try {
            Clothing c1 = new Clothing("blue jeans", "Levis", 32, LocalDate.of(2023, 5, 10));
            Clothing c2 = new Clothing("red dress", "Zara", 8, LocalDate.of(2022, 7, 15));
            Clothing c3 = new Clothing("blue t-shirt", "Gildan", 6, LocalDate.of(2024, 6, 23));

            wardrobe1.addClothing(c1); //added all clothing variables
            wardrobe1.addClothing(c2);
            wardrobe1.addClothing(c3);

            if (!(wardrobe1.getClothing(c1.getDescription(), c1.getBrand()).equals(c1))) {
                return false;
            } else if (!(wardrobe1.getClothing(c2.getDescription(), c2.getBrand()).equals(c2))) {
                return false;       //returns false if clothing does not match expected clothing c2
            } else if (!(wardrobe1.getClothing(c3.getDescription(), c3.getBrand()).equals(c3))) {
                return false;       //returns false if clothing does not match expected clothing c3
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * Tests that the Wardrobe's getClothing() method throws the correct type of exception(s)
     * with a message in situations where an exception is expected.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testGetClothingExceptions() {
        Wardrobe wardrobe1 = new Wardrobe(3);

        try {
            Clothing c1 = new Clothing("blue jeans", "Levis", 32, LocalDate.of(2023, 5, 10));
            Clothing c2 = new Clothing("red dress", "Zara", 8, LocalDate.of(2022, 7, 15));
            Clothing c3 = new Clothing("blue t-shirt", "Gildan", 6, LocalDate.of(2024, 6, 23));

            wardrobe1.addClothing(c1); //added all clothing variables
            wardrobe1.addClothing(c2);
            wardrobe1.addClothing(c3);

            if (!(wardrobe1.getClothing(c1.getDescription(), c1.getBrand()).equals(c1))) {
                return false;
            } else if ((wardrobe1.getClothing("pink pants", "Zaran").equals(c2))) {

                return false;       //returns false if clothing does not match expected clothing c2
            } else if (wardrobe1.getClothing("black t-shirt", "Gillliedan").equals(c3)) {
                return false;       //returns false if clothing does not match expected clothing c3
            }
        } catch (NoSuchElementException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Tests that the Wardrobe's removeClothing() method throws the correct type of exception(s)
     * with a message in situations where an exception is expected.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testRemoveClothingExceptions() {
        Wardrobe wardrobe1 = new Wardrobe(3);

        try {
            Clothing c1 = new Clothing("blue jeans", "Levis", 32, LocalDate.of(2023, 5, 10));
            Clothing c2 = new Clothing("red dress", "Zara", 8, LocalDate.of(2022, 7, 15));
            Clothing c3 = new Clothing("blue t-shirt", "Gildan", 6, LocalDate.of(2024, 6, 23));

            wardrobe1.addClothing(c1); //added all clothing variables
            wardrobe1.addClothing(c2);
            wardrobe1.addClothing(c3);

            wardrobe1.removeClothing("difje", "sdjes");
            return false;
        } catch (NoSuchElementException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Tests the Wardrobe's removeClothings() method for correctness.
     * This test accounts for the fact a bad implementation may throw an Exception.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testRemoveClothing() {

        Wardrobe wardrobe1 = new Wardrobe(3);

        try {
            Clothing c1 = new Clothing("blue jeans", "Levis", 32, LocalDate.of(2023, 5, 10));
            Clothing c2 = new Clothing("red dress", "Zara", 8, LocalDate.of(2022, 7, 15));
            Clothing c3 = new Clothing("blue t-shirt", "Gildan", 6, LocalDate.of(2024, 6, 23));

            wardrobe1.addClothing(c1); //added all clothing variables
            wardrobe1.addClothing(c2);
            wardrobe1.addClothing(c3);

            //tests removal of c1 clothing object
            wardrobe1.removeClothing("blue jeans", "Levis");
            if (!wardrobe1.getArray()[0].equals(c2) || wardrobe1.capacity() != 3) {
                //checks for correct change in position and change in capacity
                return false;
            }
            wardrobe1.removeClothing("red dress", "Zara");

            //tests removal of c2 clothing object
            if (!wardrobe1.getArray()[0].equals(c3) || wardrobe1.capacity() != 3) {
                //checks for correct change in position and change in capacity
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Tests the Wardrobe's removeAllClothingWornBefore() method for correctness.
     * This test accounts for the fact a bad implementation may throw an Exception.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testRemoveAllClothingWornBefore() {

        Wardrobe wardrobe1 = new Wardrobe(3);
        Clothing c1 = new Clothing("blue jeans", "Levis", 4, LocalDate.of(2023, 5, 10));
        Clothing c2 = new Clothing("red dress", "Zara", 8, LocalDate.of(2022, 7, 15));
        Clothing c3 = new Clothing("blue t-shirt", "Gildan", 3, LocalDate.of(2024, 6, 23));
        Clothing c4 = new Clothing("jeans", "denim", 3, LocalDate.of(2024, 6, 23));

        wardrobe1.addClothing(c1);
        wardrobe1.addClothing(c2);
        wardrobe1.addClothing(c3);
        wardrobe1.addClothing(c4);

        //this removes all items before 2023-12-31
        try {
            wardrobe1.removeAllClothingWornBefore(2023, 12, 30);
            if (!wardrobe1.getArray()[0].equals(c3) || !wardrobe1.getArray()[1].equals(c4)) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try {
            Clothing clothe1 = wardrobe1.getClothing("blue jeans", "Levis");
        } catch (NoSuchElementException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        try {
            Clothing clothe2 = wardrobe1.getClothing("red dress", "Zara");
        } catch (NoSuchElementException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Tests the Wardrobe's removeAllClothingWornNumTimes() method for correctness.
     * This test accounts for the fact a bad implementation may throw an Exception.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testRemoveAllClothingWornNumTimes() {

        //created new sample object
        Wardrobe wardrobe1 = new Wardrobe(3);
        Clothing c1 = new Clothing("blue jeans", "Levis", 4, LocalDate.of(2023, 5, 10));
        Clothing c2 = new Clothing("red dress", "Zara", 21, LocalDate.of(2022, 7, 15));
        Clothing c3 = new Clothing("blue t-shirt", "Gildan", 3, LocalDate.of(2024, 6, 23));

        wardrobe1.addClothing(c1);
        wardrobe1.addClothing(c2);
        wardrobe1.addClothing(c3);

        try {
            wardrobe1.removeAllClothingWornNumTimes(5);

            if (!wardrobe1.getArray()[0].equals(c2)) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        // checks if removed clothing throw an exception or not
        try {
            Clothing clothe1 = wardrobe1.getClothing("blue t-shirt", "Gildan");
        } catch (NoSuchElementException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // checks if removed clothing throw an exception or not
        try {
            Clothing clothe2 = wardrobe1.getClothing("blue jeans", "Levis");
        } catch (NoSuchElementException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Tests that the Wardrobe's parseClothing() method throws the correct type of exception(s)
     * with a message in situations where an exception is expected.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testParseClothingExceptions() {
        String clothingParse = "blue jeans,Levis,05/10/2023,12/03/2024,4";

        Wardrobe wardrobeA = new Wardrobe(1);
        Wardrobe wardrobeB = new Wardrobe(1);

        Clothing expected = new Clothing("blue jeans", "Levis", 4, LocalDate.of(2023, 5, 10));
        wardrobeA.addClothing(expected);

        try {
            Clothing actual = Wardrobe.parseClothing(clothingParse);
            wardrobeB.addClothing(actual);
            return false;
        } catch (ParseException e) {
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Tests the Wardrobe's parseClothing method for correctness.
     * This test accounts for the fact a bad implementation may throw an Exception.
     *
     * @return true if all tests pass, false otherwise
     */
    public static boolean testParseClothing() {
        String clothingParse = "blue jeans,Levis,05/10/2023,4";

        Wardrobe wardrobeA = new Wardrobe(1);
        Wardrobe wardrobeB = new Wardrobe(1);

        Clothing expected = new Clothing("blue jeans", "Levis", 4, LocalDate.of(2023, 5, 10));
        wardrobeA.addClothing(expected);

        try {
            Clothing actual = Wardrobe.parseClothing(clothingParse);
            wardrobeB.addClothing(actual);

            if (!expected.equals(actual)) {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * Runs all testing methods and prints out their results.
     *
     * @return true if and only if all the tests return true, false otherwise
     */
    public static boolean runAllTests() {
        boolean test1 = testClothingConstructorExceptions();
        System.out.println("testClothingConstructorExceptions(): " + (test1 ? "pass" : "FAIL"));

        boolean test2 = testClothingConstructorAndGetters();
        System.out.println("testClothingConstructorAndGetters(): " + (test2 ? "pass" : "FAIL"));

        boolean test3 = testClothingWear();
        System.out.println("testClothingWear(): " + (test3 ? "pass" : "FAIL"));

        boolean test4 = testWardrobeConstructorAndGetters();
        System.out.println("testWardrobeConstructorAndGetters(): " + (test4 ? "pass" : "FAIL"));

        boolean test5 = testWardrobeConstructorExceptions();
        System.out.println("testWardrobeConstructorExceptions(): " + (test5 ? "pass" : "FAIL"));

        boolean test6 = testAddClothingExceptions();
        System.out.println("testAddClothingExceptions(): " + (test6 ? "pass" : "FAIL"));

        boolean test7 = testAddClothing();
        System.out.println("testAddClothing(): " + (test7 ? "pass" : "FAIL"));

        boolean test8 = testGetClothing();
        System.out.println("testGetClothing(): " + (test8 ? "pass" : "FAIL"));

        boolean test9 = testGetClothingExceptions();
        System.out.println("testGetClothingExceptions(): " + (test9 ? "pass" : "FAIL"));

        boolean test10 = testRemoveClothing();
        System.out.println("testRemoveClothing(): " + (test10 ? "pass" : "FAIL"));

        boolean test11 = testRemoveClothingExceptions();
        System.out.println("testRemoveClothingExceptions(): " + (test11 ? "pass" : "FAIL"));

        boolean test12 = testRemoveAllClothingWornBefore();
        System.out.println("testRemoveAllClothingWornBefore(): " + (test12 ? "pass" : "FAIL"));

        boolean test13 = testRemoveAllClothingWornNumTimes();
        System.out.println("testRemoveAllClothingWornNumTimes(): "
                + (test13 ? "pass" : "FAIL"));

        boolean test14 = testParseClothingExceptions();
        System.out.println("testParseClothingExceptions(): " + (test14 ? "pass" : "FAIL"));

        boolean test15 = testParseClothing();
        System.out.println("testParseClothing(): " + (test15 ? "pass" : "FAIL"));

        return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8 && test9 && test10
                && test11 && test12 && test13 && test14 && test15;
    }

    public static void main(String[] args) {
        System.out.println("runAllTests(): " + runAllTests());
    }
}
