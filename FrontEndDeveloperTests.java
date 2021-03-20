import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

//--== CS400 File Header Information ==--
//Name: Jessica Xu
//Email: jxxu2@wisc.edu
//Team: FB blue
//Role: front end developer
//TA: Daniel
//Lecturer: Gary Dahl
//Notes to Grader: 

/**
 * This class contains a set of tests for the front end of the Hotel Database
 * project.
 */
public class FrontEndDeveloperTests {
	public static void main(String[] args) {
		(new FrontEndDeveloperTests()).runTests();
	}

	/**
	 * This method calls all of the test methods in the class and ouputs pass / fail
	 * for each test.
	 */
	public void runTests() {
		System.out.print(
				"Test enter date and return reservations that fall under that date (WARNING: if pressing 'r' then 'x' from the View Reservations by Date screen does not exit app, test won't exit and run indefinitely!): ");
		if (this.testViewReservationsByDate()) {
			System.out.println("PASSED");
		} else {
			System.out.println("FAILED");
		}
		System.out.print(
				"Test enter name and return reservations that fall under that name (WARNING: if pressing 'r' then 'x' from the View Reservations by Date screen does not exit app, test won't exit and run indefinitely!): ");
		if (this.testViewReservationsByName()) {
			System.out.println("PASSED");
		} else {
			System.out.println("FAILED");
		}
		System.out.print("Test that Reservation object being inserted is added to correct spot");
		if (this.testReservationLocation()) {
			System.out.println("PASSED");
		} else {
			System.out.println("FAILED");
		}
		System.out.print(
				"Test that Reservation object being inserted is added to correct spot when there is another Reservation object with the same date");
		if (this.testReservationDuplicates()) {
			System.out.println("PASSED");
		} else {
			System.out.println("FAILED");
		}
		System.out.print(
				"Test enter 'x' to exit (WARNING: if 'x' does not exit app, test won't exit and run indefinitely!): ");
		if (this.testExitAndReturn()) {
			System.out.println("PASSED");
		} else {
			System.out.println("FAILED");
		}
	}

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
	public boolean testViewReservationsByDate() {
		PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
		try {
			// set the input stream to our input (with 2 numbers to test if the program lists
			// the correct reservations) and r for return to main screen and x to exit out of program
			String input = "2" + System.lineSeparator() + "3" + System.lineSeparator() + "9"  + System.lineSeparator() + "r" + System.lineSeparator() + "x";
			InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
			System.setIn(inputStreamSimulator);
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
			// set the output to the stream captor to read the output of the front end
			System.setOut(new PrintStream(outputStreamCaptor));
			// instantiate when front end is implemented
			Object frontend = null; 
			// set the output back to standard out for running the test
			System.setOut(standardOut);
			// same for standard in
			System.setIn(standardIn);
			// add all tests to this method
			String appOutput = outputStreamCaptor.toString();
			// want to return reservations made by Bob and Marla
			if (frontend != null && appOutput.contains("Bob") && appOutput.contains("Marla") && appOutput.contains("Leah") && appOutput.contains("Victoria")) {
				// test passes if these specific Reservations from the data are displayed on the screen
				return true;
			} else {
				// test failed
				return false;
			}
		} catch (Exception e) {
			// make sure stdin and stdout are set correctly after we get exception in test
			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();
			// test failed
			return false;
		}
	}

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
	public boolean testViewReservationsByName() {
		PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
		try {
			// set the input stream to our input (with a name to test if the program lists
			// the correct reservations) and r for return to main screen and x to exit out of program
			String input = "3" + System.lineSeparator() + "Bob" + System.lineSeparator() + "r" + System.lineSeparator() + "x";
			InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
			System.setIn(inputStreamSimulator);
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
			// set the output to the stream captor to read the output of the front end
			System.setOut(new PrintStream(outputStreamCaptor));
			// instantiate when front end is implemented
			Object frontend = null; 
			// set the output back to standard out for running the test
			System.setOut(standardOut);
			// same for standard in
			System.setIn(standardIn);
			// add all tests to this method
			String appOutput = outputStreamCaptor.toString();
			// want to return reservations made on the 3, 4, and 5
			if (frontend != null && appOutput.contains("3") && appOutput.contains("4") && appOutput.contains("5")) {
				// test passes if these specific Reservations from the data are displayed on the screen
				return true;
			} else {
				// test failed
				return false;
			}
		} catch (Exception e) {
			// make sure stdin and stdout are set correctly after we get exception in test
			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();
			// test failed
			return false;
		}
	}

	/**
	 * This will test to make sure that it adds the Reservation object to the right
	 * location in the Red Black Tree. It will test the toString output is the same
	 * as the expected output.
	 * 
	 * @return true if the test passed, false if it failed
	 */
	public boolean testReservationLocation() {
		PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
		try {
			// set the input stream to our input (with a name, room number, check in date, and check out day to test if the program lists
			// the correct reservations) and r for return to main screen and x to exit out of program
			String input = "1" + System.lineSeparator() + "Bob" + System.lineSeparator() + "404" + System.lineSeparator() + "5" + System.lineSeparator() + "7" + System.lineSeparator() + "r" + System.lineSeparator() + "x";
			InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
			System.setIn(inputStreamSimulator);
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
			// set the output to the stream captor to read the output of the front end
			System.setOut(new PrintStream(outputStreamCaptor));
			// instantiate when front end is implemented
			Object frontend = null; 
			// set the output back to standard out for running the test
			System.setOut(standardOut);
			// same for standard in
			System.setIn(standardIn);
			// add all tests to this method
			String appOutput = outputStreamCaptor.toString();
			// want to return reservations in correct order 
			if (frontend != null && appOutput.equals("[3, 2, 5]")) {
				// test passes if these specific Reservations from the data are displayed on the screen
				return true;
			} else {
				// test failed
				return false;
			}
		} catch (Exception e) {
			// make sure stdin and stdout are set correctly after we get exception in test
			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();
			// test failed
			return false;
		}
	}

	/**
	 * This will test to make sure that it adds the Reservation object to the right
	 * location in the Red Black Tree when there's duplicates. If there is a
	 * Reservation object being added with the same pre-existing date then this will
	 * make sure that it adds it to the right side. It will test the toString output
	 * is the same as the expected output.
	 * 
	 * @return true if the test passed, false if it failed
	 */
	public boolean testReservationDuplicates() {
		PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
		try {
			// set the input stream to our input (with a name, room number, check in date, and check out day to test if the program lists
			// the correct reservations) and r for return to main screen and x to exit out of program
			String input = "1" + System.lineSeparator() + "Bob" + System.lineSeparator() + "405" + System.lineSeparator() + "5" + System.lineSeparator() + "7" + System.lineSeparator() + "r" + System.lineSeparator() + "x";
			InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
			System.setIn(inputStreamSimulator);
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
			// set the output to the stream captor to read the output of the front end
			System.setOut(new PrintStream(outputStreamCaptor));
			// instantiate when front end is implemented
			Object frontend = null; 
			// set the output back to standard out for running the test
			System.setOut(standardOut);
			// same for standard in
			System.setIn(standardIn);
			// add all tests to this method
			String appOutput = outputStreamCaptor.toString();
			// want to return reservations in correct order 
			if (frontend != null && appOutput.equals("[3, 2, 5, 5]")) {
				// test passes if these specific Reservations from the data are displayed on the screen
				return true;
			} else {
				// test failed
				return false;
			}
		} catch (Exception e) {
			// make sure stdin and stdout are set correctly after we get exception in test
			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();
			// test failed
			return false;
		}
	}

	/**
	 * This test runs the front end and redirects its output to a string. It then
	 * passes in 'x' as a command. When the front end exists, the tests succeeds. If
	 * 'x' does not exist the in app, the test will not terminate (it won't fail
	 * explicitly in this case). The test fails explicitly if the front end is not
	 * instantiated or if an exception occurs.
	 * 
	 * @return true if the test passed, false if it failed
	 */
	public boolean testExitAndReturn() {
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
			// instantiate when front end is implemented
			Object frontend = null;

			// set the output back to standard out for running the test
			System.setOut(standardOut);
			// same for standard in
			System.setIn(standardIn);
			if (frontend == null) {
				// test fails
				return false;
			} else {
				// test passed
				return true;
			}
		} catch (Exception e) {
			// make sure stdin and stdout are set correctly after we get exception in test
			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();
			// test failed
			return false;
		}
	}
}
