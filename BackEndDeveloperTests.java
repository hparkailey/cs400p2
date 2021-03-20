
//--== CS400 File Header Information ==--
//Name: Gabriela Nawangsari Setyawan
//Email: gsetyawan@wisc.edu
//Team: Blue
//Role: Backend developer
//TA: Daniel Finer
//Lecturer: Gary Dahl
//Notes to Grader: n/a

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.Test;

public class BackEndDeveloperTests {
	/**
	 * Test to see if a BackEnd object is created correctly (check to see if size is
	 * correct based on the data input and BackEnd is not null)
	 * 
	 * @throws FileNotFoundException if csv file is not found
	 * @throws NullPointerException if BackEnd object is not created
	 */
	@Test
	public void testBackEndObject() throws NullPointerException, FileNotFoundException {
		Reader filePathInput;
		String dataInput = "name,check-in date,check-out date,room number\n"
				+ "Leslie Smith,07/09/2020,7/11/2020,2019\n" + "Guntur Rashik,06/05/2020,06/07/2020,2015\n"
				+ "Sarah Smith,08/20/2020,8/29/2020,1000";

		filePathInput = new FileReader("Reservations.csv");
		BackEnd backEnd1; 
		BackEnd backEnd2;
		int expectedSizeForBE1 = 41;
		int expectedSizeForBE2 = 3;
		try {
			backEnd1 = new BackEnd(filePathInput); // backEnd object using the csv file as the data input
			backEnd2 = new BackEnd(dataInput); // backEnd object constructed using the 
			if (backEnd1.getSize() != expectedSizeForBE1) { // test to check if the size corresponds to the number of rows in the csv file (41)
				fail("Did not successfully add reservations to the tree");
			}
			if (backEnd2.getSize() != expectedSizeForBE2) { // test to check if the size corresponds to the number of elements from the dataInput (3)
				fail("Did not successfully add reservations to the tree");
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (DataFormatException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e) {
			fail("The backEnd object is still null");
		}

	}

	/**
	 * Add 2 Reservations and check if the number of size return using getSize() is
	 * as expected every time we add a reservation
	 * 
	 * @throws FileNotFoundException is thrown if csv file is not found
	 */
	@Test
	public void testAddingReservation() throws FileNotFoundException {
		// Initializing all the required fields to make the HotelReservation objects in order to add it
		String nameRes1 = "Michael Jordan"; 
		String nameRes2 = "Sandra Bullock";
		String checkInDate1 = "08/08/2020";
		String checkInDate2 = "08/08/2020";
		String checkOutDate1 = "08/18/2020";
		String checkOutDate2 = "08/14/2020";
		int roomNumberRes1 = 3124;
		int roomNumberRes2 = 3124;

		Reader filePathInput;
		filePathInput = new FileReader("Reservations.csv");
		BackEnd backEnd;

		try {
			backEnd = new BackEnd(filePathInput);
			backEnd.add(nameRes1, checkInDate1, checkOutDate1, roomNumberRes1); // add a new reservation
			assertEquals(42, backEnd.getSize()); // see if the size increases by 1
			backEnd.add(nameRes2, checkInDate2, checkOutDate2, roomNumberRes2); // add a new Reservation
			assertEquals(43, backEnd.getSize()); // see if the size increases by 1
		} catch (IOException | DataFormatException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.print("We cannot have identical checkInDate and room number");
		}
	}

	/**
	 * Tests if the correct node is returned if we try to find a reservation by
	 * passing in the checkIn and checkOut dates
	 * 
	 * @throws FileNotFoundException if Reservations.csv is not found
	 */
	@Test
	public void testSelectByDate() throws FileNotFoundException {
		Reader filePathInput;
		filePathInput = new FileReader("Reservations.csv");
		BackEnd backEnd;
		try {
			backEnd = new BackEnd(filePathInput);
			backEnd.selectByDate("06/27/2020", "07/01/2020"); // first attempt to look for a HotelReservationObject by checkIn and checkOut date
			if (!backEnd.reservationListBasedOnDate.get(0).toString()
					.equals("Denis Villeneuve, 06/27/2020-07/01/2020, room 2049")) {
				fail("Did not get correct reservation");
			}
			backEnd.selectByDate("06/01/2020", "06/07/2020"); // second attempt
			if (!backEnd.reservationListBasedOnDate.get(1).toString()
					.equals("Barack Obama, 06/01/2020-06/07/2020, room 4128")) {
				fail("Did not get correct reservation");
			}
		} catch (NullPointerException e) {
			System.out.print("Tried to check an invalid reservation");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests if the correct node is returned when we try to find a reservation by
	 * passing the occupant's name
	 * 
	 * @throws DataFormatException if there are any incorrect data format when we try to read the data from the user
	 * @throws IOException if there is an error when trying to input/output data
	 * @throws FileNotFoundException if the csv file is not found
	 */
	@Test
	public void testSelectByOccupant() throws FileNotFoundException {
		Reader filePathInput;
		filePathInput = new FileReader("Reservations.csv");
		BackEnd backEnd;
		String expectedFirst;
		String expectedSecond;
		try {
			backEnd = new BackEnd(filePathInput);
			backEnd.selectByOccupant("Martin Garrix");
			expectedFirst = "Martin Garrix, 07/22/2020-07/31/2020, room 2044";
			if (!backEnd.reservationListBasedOnName.get(0).toString().equals(expectedFirst)) {
				fail("Did not get the correct reservation");
			}
			expectedSecond = "Barack Obama, 06/01/2020-06/07/2020, room 4128";
			backEnd.selectByOccupant("Barack Obama");
			if (!backEnd.reservationListBasedOnName.get(1).toString().equals(expectedSecond)) {
				fail("Did not get the correct reservation");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException | DataFormatException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds nodes with same dates and checks if the order is correct (based on room
	 * number, larger room number should be on the right side of parent while
	 * smaller room number should be on the left side)
	 * @throws DataFormatException if there are any incorrect data format when we try to read the data from the user
	 * @throws IOException if there is an error when trying to input/output data
	 * @throws FileNotFoundException if the csv file is not found
	 */
	@Test
	public void testOrder() throws FileNotFoundException, IOException, DataFormatException {
		Reader filePathInput;
		filePathInput = new FileReader("Reservations.csv");
		BackEnd backEnd;
			backEnd = new BackEnd(filePathInput);
			String expected = "[ Matthew McConaughey, 06/01/2020-06/12/2020, room 2020,"
					+ " Calvin Johnson, 06/01/2020-06/15/2020, room 2137, "
					+ "Mitchell Trubisky, 06/01/2020-08/30/2020, "
					+ "room 3079, Barack Obama, 06/01/2020-06/07/2020, room 4128, "
					+ "Gaby Setyawan, 06/02/2020-06/11/2020, room 3149, "
					+ "Jennifer Lawrence, 06/04/2020-06/10/2020, room 1010, "
					+ "Paul Atreides, 06/11/2020-06/29/2020, room 3122,"
					+ " Zion Williamson, 06/12/2020-06/25/2020, room 1111, "
					+ "Davis Mills, 06/20/2020-06/23/2020, room 4079, "
					+ "Meghan Markle, 06/23/2020-07/10/2020, room 2021, "
					+ "Damian Lillard, 06/24/2020-07/07/2020, room 5132, "
					+ "Denis Villeneuve, 06/27/2020-07/01/2020, room 2049, "
					+ "Jessica Xu, 06/28/2020-06/30/2020, room 5099, "
					+ "Sandra Day-O'Connor , 06/29/2020-07/06/2020, room 1141, "
					+ "Kevin Faulconer, 06/29/2020-07/05/2020, room 4044, "
					+ "Ryan Gosling, 06/30/2020-07/06/2020, room 1010 ]";
			if(!backEnd.june.toString().equals(expected)) {
				fail("Didn't get the correct order, need to check red black tree again");
			}
	}
}
