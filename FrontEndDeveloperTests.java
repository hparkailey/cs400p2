//--== CS400 File Header Information ==--
//Name: Jessica Xu
//Email: jxxu2@wisc.edu
//Team: FB blue
//Role: front end developer
//TA: Daniel
//Lecturer: Gary Dahl
//Notes to Grader: TestCreateNewReservation() may not work because something 
// in BackEnd might not correctly create the new Reservation and add it to the tree

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.zip.DataFormatException;

/**
 * This class contains a set of tests for the front end of the Hotel Database
 * project.
 */
class FrontEndDeveloperTests {

	@Test
	/**
	 * This will test requesting reservations in a range of dates and compare them
	 * to the expected output. The user will be prompted two questions: what date
	 * they want to start looking for and what date they want to end looking for.
	 * The dates returned will be compared to the expected dates that should be
	 * returned, and this test will see if they're the same or not. The user first
	 * selects 2 to indicate that they would like to search for reservations based
	 * on dates then will input two numbers.
	 * 
	 * @return true if the test passed, false if it failed
	 */
	void testViewReservationsByDate() {

		PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
		try {
//			// set the input stream to our input (with 2 dates to test if the program
//			// lists
//			// the correct reservations) and r for return to main screen and x to exit out
//			// of program
			String input = "f" + System.lineSeparator() + "Reservations.csv" + System.lineSeparator() + "b"
					+ System.lineSeparator() + "06/02/2020" + System.lineSeparator() + "06/11/2020"
					+ System.lineSeparator() + "n" + System.lineSeparator() + "r" + System.lineSeparator() + "x";
			InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());

			System.setIn(inputStreamSimulator);
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
			// set the output to the stream captor to read the output of the front end
			System.setOut(new PrintStream(outputStreamCaptor));
			Frontend.run(new BackEnd(new FileReader("Reservations.csv")));
			// set the output back to standard out for running the test
			System.setOut(standardOut);
			// same for standard in
			System.setIn(standardIn);

			String appOutput = new String(outputStreamCaptor.toByteArray());
			String expectedOutput = "************ WELCOME TO THE HOTEL DATABASE! ************\n"
					+ "This program lets you create reservations and view previous and newly made reservations.\n"
					+ "\n" + "This is the FILE SCREEN.\n" + "(Please enter a file to proceed with the program.)\n"
					+ "Enter 'x' to quit \n" + "Enter 'f' to enter a filepath \n" + "Enter 's' to enter a string\n"
					+ "Please enter the filepath to the hotel database csv file:	\n" + "\n"
					+ "This is the MAIN SCREEN.\n"
					+ "There are three options to choose from in this app. Enter the letter corresponding to the mode you'd like to go to.\n"
					+ "	A. Create a reservation\n" + "	B. View reservations based by dates\n"
					+ "	C. Search reservations based on guest name\n"
					+ "Enter 'r' to go back to the file screen and 'x' to quit.\n" + "\n" + "Please enter a letter: \n"
					+ "\n"
					+ "What is the exact START DATE that you would like to look for (what date reservations are made on)?\n"
					+ "(Please enter in format mm/dd/yyyy and must be between 06/01/2020 - 08/31/2020)	\n"
					+ "What is the exact END DATE that you would like to look for (what date reservations are made on)?\n"
					+ "(Please enter in format mm/dd/yyyy and must be between 06/01/20 - 08/31/2020)	\n"
					+ "Here are the reservations between 06/02/2020 and 06/11/2020:\n"
					+ "[Gaby Setyawan, 06/02/2020-06/11/2020, room 3149]\n" + "\n"
					+ "Would you like to search by date again? (enter 'y' for yes and 'n' for no):	\n" + "\n"
					+ "This is the MAIN SCREEN.\n"
					+ "There are three options to choose from in this app. Enter the letter corresponding to the mode you'd like to go to.\n"
					+ "	A. Create a reservation\n" + "	B. View reservations based by dates\n"
					+ "	C. Search reservations based on guest name\n"
					+ "Enter 'r' to go back to the file screen and 'x' to quit.\n" + "\n" + "Please enter a letter: \n"
					+ "This is the FILE SCREEN.\n" + "(Please enter a file to proceed with the program.)\n"
					+ "Enter 'x' to quit \n" + "Enter 'f' to enter a filepath \n" + "Enter 's' to enter a string\n"
					+ "Thank you for using the Hotel Database! Have a great day :)\n" + "";
			assertEquals(expectedOutput, appOutput);

		} catch (Exception e) {
			// make sure stdin and stdout are set correctly after we get exception in test
			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();
			// test failed
			fail("testViewReservationsByDate() failed");
		}
	}

	@Test
	/**
	 * This will test requesting reservations based on an inputed name and will
	 * compare the date(s) returned to the expected output. The user will be
	 * prompted a name and it can either be the first or last name or both and this
	 * method should be able to find the name. The dates returned will be compared
	 * to the expected dates that should be returned, and this test will see if
	 * they're the same or not.
	 * 
	 * @return true if the test passed, false if it failed
	 */
	void testViewReservationsByName() {
		PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
		try {
			// set the input stream to our input (with a name to test if the program lists
			// the correct reservations) and r for return to main screen and x to exit out
			// of program
			String input = "f" + System.lineSeparator() + "Reservations.csv" + System.lineSeparator() + "c"
					+ System.lineSeparator() + "Jessica Xu" + System.lineSeparator() + "n" + System.lineSeparator()
					+ "r" + System.lineSeparator() + "x";
			InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());

			System.setIn(inputStreamSimulator);
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
			// set the output to the stream captor to read the output of the front end
			System.setOut(new PrintStream(outputStreamCaptor));
			Frontend.run(new BackEnd(new FileReader("Reservations.csv")));
			// set the output back to standard out for running the test
			System.setOut(standardOut);
			// same for standard in
			System.setIn(standardIn);

			String appOutput = new String(outputStreamCaptor.toByteArray());
			String expectedOutput = "************ WELCOME TO THE HOTEL DATABASE! ************\n"
					+ "This program lets you create reservations and view previous and newly made reservations.\n"
					+ "\n" + "This is the FILE SCREEN.\n" + "(Please enter a file to proceed with the program.)\n"
					+ "Enter 'x' to quit \n" + "Enter 'f' to enter a filepath \n" + "Enter 's' to enter a string\n"
					+ "Please enter the filepath to the hotel database csv file:	\n" + "\n"
					+ "This is the MAIN SCREEN.\n"
					+ "There are three options to choose from in this app. Enter the letter corresponding to the mode you'd like to go to.\n"
					+ "	A. Create a reservation\n" + "	B. View reservations based by dates\n"
					+ "	C. Search reservations based on guest name\n"
					+ "Enter 'r' to go back to the file screen and 'x' to quit.\n" + "\n" + "Please enter a letter: \n"
					+ "\n" + "What is the NAME (first and last) of the reservation you're looking for is under?	\n"
					+ "Here are the reservations found for Jessica Xu:\n"
					+ "[Jessica Xu, 06/28/2020-06/30/2020, room 5099]\n" + "\n"
					+ "Would you like to search by name again? (enter 'y' for yes and 'n' for no):	\n"
					+ "This is the MAIN SCREEN.\n"
					+ "There are three options to choose from in this app. Enter the letter corresponding to the mode you'd like to go to.\n"
					+ "	A. Create a reservation\n" + "	B. View reservations based by dates\n"
					+ "	C. Search reservations based on guest name\n"
					+ "Enter 'r' to go back to the file screen and 'x' to quit.\n" + "\n" + "Please enter a letter: \n"
					+ "This is the FILE SCREEN.\n" + "(Please enter a file to proceed with the program.)\n"
					+ "Enter 'x' to quit \n" + "Enter 'f' to enter a filepath \n" + "Enter 's' to enter a string\n"
					+ "Thank you for using the Hotel Database! Have a great day :)\n";
			assertEquals(expectedOutput, appOutput);

		} catch (Exception e) {
			// make sure stdin and stdout are set correctly after we get exception in test
			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();
			// test failed
			fail("testViewReservationsByName() failed");
		}
	}

	@Test
	/**
	 * This will test to make sure that it correctly adds the Reservation object. It
	 * will add a new Reservation and make sure it's the correct one by searching
	 * for it by name and making sure the output is correct. It will test the
	 * toString output is the same as the expected output.
	 * 
	 * @return true if the test passed, false if it failed
	 */
	void testCreateNewReservation() {
		PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
		try {
			// set the input stream to our input (with a name to test if the program lists
			// the correct reservations) and r for return to main screen and x to exit out
			// of program
			String input = "f" + System.lineSeparator() + "Reservations.csv" + System.lineSeparator() + "a"
					+ System.lineSeparator() + "Bob Marley" + System.lineSeparator() + "4040" + System.lineSeparator()
					+ "06/10/2020" + System.lineSeparator() + "06/12/2020" + System.lineSeparator() + "n"
					+ System.lineSeparator() + "c" + System.lineSeparator() + "Bob Marley" + System.lineSeparator()
					+ "n" + System.lineSeparator() + "r" + System.lineSeparator() + "x";
			InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());

			System.setIn(inputStreamSimulator);
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
			// set the output to the stream captor to read the output of the front end
			System.setOut(new PrintStream(outputStreamCaptor));
			Frontend.run(new BackEnd(new FileReader("Reservations.csv")));
			// set the output back to standard out for running the test
			System.setOut(standardOut);
			// same for standard in
			System.setIn(standardIn);

			String appOutput = new String(outputStreamCaptor.toByteArray());
			String expectedOutput = "************ WELCOME TO THE HOTEL DATABASE! ************\n"
					+ "This program lets you create reservations and view previous and newly made reservations.\n"
					+ "\n" + "This is the FILE SCREEN.\n" + "(Please enter a file to proceed with the program.)\n"
					+ "Enter 'x' to quit \n" + "Enter 'f' to enter a filepath \n" + "Enter 's' to enter a string\n"
					+ "Please enter the filepath to the hotel database csv file:	\n" + "\n"
					+ "This is the MAIN SCREEN.\n"
					+ "There are three options to choose from in this app. Enter the letter corresponding to the mode you'd like to go to.\n"
					+ "	A. Create a reservation\n" + "	B. View reservations based by dates\n"
					+ "	C. Search reservations based on guest name\n"
					+ "Enter 'r' to go back to the file screen and 'x' to quit.\n" + "\n" + "Please enter a letter: \n"
					+ "\n" + "What is the NAME for the Reservation you would like to make?	\n"
					+ "What is the ROOM NUMBER (4 digits) for the Reservation you would like to make?	\n"
					+ "What is the CHECK IN DATE (mm/dd/yyyy) for the Reservation you would like to make? (must be between 06/01/2020 - 08/31/2020)	\n"
					+ "What is the CHECK OUT DATE (mm/dd/yyyy) for the Reservation you would like to make? (must be between 06/01/2020 - 08/31/2020)	\n"
					+ "Your reservation has been succesfully made!!\n" + "\n" + "\n"
					+ "Would you like to create another reservation? (enter 'y' for yes and 'n' for no):	\n" + "\n"
					+ "This is the MAIN SCREEN.\n"
					+ "There are three options to choose from in this app. Enter the letter corresponding to the mode you'd like to go to.\n"
					+ "	A. Create a reservation\n" + "	B. View reservations based by dates\n"
					+ "	C. Search reservations based on guest name\n"
					+ "Enter 'r' to go back to the file screen and 'x' to quit.\n" + "\n" + "Please enter a letter: \n"
					+ "\n" + "What is the NAME (first and last) of the reservation you're looking for is under?	\n"
					+ "Here are the reservations found for Bob Marley:\n"
					+ "[Bob Marley, 06/10/2020-06/12/2020, room 4040]\n" + "\n"
					+ "Would you like to search by name again? (enter 'y' for yes and 'n' for no):	\n"
					+ "This is the MAIN SCREEN.\n"
					+ "There are three options to choose from in this app. Enter the letter corresponding to the mode you'd like to go to.\n"
					+ "	A. Create a reservation\n" + "	B. View reservations based by dates\n"
					+ "	C. Search reservations based on guest name\n"
					+ "Enter 'r' to go back to the file screen and 'x' to quit.\n" + "\n" + "Please enter a letter: \n"
					+ "This is the FILE SCREEN.\n" + "(Please enter a file to proceed with the program.)\n"
					+ "Enter 'x' to quit \n" + "Enter 'f' to enter a filepath \n" + "Enter 's' to enter a string\n"
					+ "Thank you for using the Hotel Database! Have a great day :)\n";
			assertEquals(expectedOutput, appOutput);

		} catch (Exception e) {
			// make sure stdin and stdout are set correctly after we get exception in test
			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();
			// test failed
			fail("testViewReservationsByName() failed");
		}
	}

	@Test
	/**
	 * This will test to make sure that it adds the Reservation object to the right
	 * location in the Red Black Tree when there's duplicates. If there is a
	 * Reservation object being added with the same pre-existing date then this will
	 * make sure that it adds it to the right side. It will test the toString output
	 * is the same as the expected output.
	 * 
	 * @return true if the test passed, false if it failed
	 */
	void testReservationDuplicates() {
		PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
		try {
			// set the input stream to our input (with a name, room number, check in date,
			// and check out day to test if the program lists
			// the correct reservations) and r for return to main screen and x to exit out
			// of program
			String input = "f" + System.lineSeparator() + "Reservations.csv" + System.lineSeparator() + "a"
					+ System.lineSeparator() + "Jessica Xu" + System.lineSeparator() + "5099" + System.lineSeparator()
					+ "06/28/2020" + System.lineSeparator() + "06/30/2020" + System.lineSeparator() + "n"
					+ System.lineSeparator() + "r" + System.lineSeparator() + "x";
			InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());

			System.setIn(inputStreamSimulator);
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
			// set the output to the stream captor to read the output of the front end
			System.setOut(new PrintStream(outputStreamCaptor));
			Frontend.run(new BackEnd(new FileReader("Reservations.csv")));
			// set the output back to standard out for running the test
			System.setOut(standardOut);
			// same for standard in
			System.setIn(standardIn);

			String appOutput = new String(outputStreamCaptor.toByteArray());
			String expectedOutput = "************ WELCOME TO THE HOTEL DATABASE! ************\n"
					+ "This program lets you create reservations and view previous and newly made reservations.\n"
					+ "\n" + "This is the FILE SCREEN.\n" + "(Please enter a file to proceed with the program.)\n"
					+ "Enter 'x' to quit \n" + "Enter 'f' to enter a filepath \n" + "Enter 's' to enter a string\n"
					+ "Please enter the filepath to the hotel database csv file:	\n" + "\n"
					+ "This is the MAIN SCREEN.\n"
					+ "There are three options to choose from in this app. Enter the letter corresponding to the mode you'd like to go to.\n"
					+ "	A. Create a reservation\n" + "	B. View reservations based by dates\n"
					+ "	C. Search reservations based on guest name\n"
					+ "Enter 'r' to go back to the file screen and 'x' to quit.\n" + "\n" + "Please enter a letter: \n"
					+ "\n" + "What is the NAME for the Reservation you would like to make?	\n"
					+ "What is the ROOM NUMBER (4 digits) for the Reservation you would like to make?	\n"
					+ "What is the CHECK IN DATE (mm/dd/yyyy) for the Reservation you would like to make? (must be between 06/01/2020 - 08/31/2020)	\n"
					+ "What is the CHECK OUT DATE (mm/dd/yyyy) for the Reservation you would like to make? (must be between 06/01/2020 - 08/31/2020)	\n"
					+ "There is already a reservation made exactly like the one you entered.\n" + "\n"
					+ "Would you like to create another reservation? (enter 'y' for yes and 'n' for no):	\n" + "\n"
					+ "This is the MAIN SCREEN.\n"
					+ "There are three options to choose from in this app. Enter the letter corresponding to the mode you'd like to go to.\n"
					+ "	A. Create a reservation\n" + "	B. View reservations based by dates\n"
					+ "	C. Search reservations based on guest name\n"
					+ "Enter 'r' to go back to the file screen and 'x' to quit.\n" + "\n" + "Please enter a letter: \n"
					+ "This is the FILE SCREEN.\n" + "(Please enter a file to proceed with the program.)\n"
					+ "Enter 'x' to quit \n" + "Enter 'f' to enter a filepath \n" + "Enter 's' to enter a string\n"
					+ "Thank you for using the Hotel Database! Have a great day :)\n" + "";
			assertEquals(expectedOutput, appOutput);

		} catch (Exception e) {
			// make sure stdin and stdout are set correctly after we get exception in test
			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();
			// test failed
			fail("testViewReservationsByName() failed");
		}
	}

	@Test
	/**
	 * This test runs the front end and redirects its output to a string. It then
	 * passes in 'x' as a command. When the front end exists, the tests succeeds. If
	 * 'x' does not exist the in app, the test will not terminate (it won't fail
	 * explicitly in this case). The test fails explicitly if the front end is not
	 * instantiated or if an exception occurs.
	 * 
	 * @return true if the test passed, false if it failed
	 */
	void testExitAndReturn() {
		PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
		try {
			// set the input stream to our input (with an x to test if the program exists)
			String input = "x";
			InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());

			System.setIn(inputStreamSimulator);
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
			// set the output to the stream captor to read the output of the front end
			System.setOut(new PrintStream(outputStreamCaptor));
			Frontend.run(new BackEnd(new FileReader("Reservations.csv")));
			// set the output back to standard out for running the test
			System.setOut(standardOut);
			// same for standard in
			System.setIn(standardIn);

			String appOutput = new String(outputStreamCaptor.toByteArray());
			String expectedOutput = "************ WELCOME TO THE HOTEL DATABASE! ************\n"
					+ "This program lets you create reservations and view previous and newly made reservations.\n"
					+ "\n" + "This is the FILE SCREEN.\n" + "(Please enter a file to proceed with the program.)\n"
					+ "Enter 'x' to quit \n" + "Enter 'f' to enter a filepath \n" + "Enter 's' to enter a string\n"
					+ "Thank you for using the Hotel Database! Have a great day :)\n";
			assertEquals(expectedOutput, appOutput);

		} catch (Exception e) {
			// make sure stdin and stdout are set correctly after we get exception in test
			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();
			// test failed
			fail("testExitAndReturn() failed");
		}
	}
}