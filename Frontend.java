import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import java.util.List;

// --== CS400 File Header Information ==--
// Name: Jessica Xu
// Email: jxxu2@wisc.edu
// Team: FB blue
// Role: Front end developer
// TA: Daniel Finer
// Lecturer: Gary Dahl
// Notes to Grader:

/**
 * Frontend interface for the user to interact with the Hotel Database by
 * prompting them to enter inputs. There is one main screen and three modes in
 * this App (Create Reservation, View Reservations by Date, View Reservations by
 * Name) where contents are displayed based on user input.
 * 
 * @author xujessica
 *
 */
public class Frontend {
	private static Backend backend;
	private static Scanner scnr;

	/**
	 * Main method that calls the run method to start the Hotel Database
	 * 
	 * @param args arguments passed from the command line
	 * @throws DataFormatException
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException, DataFormatException {
		Frontend.run(backend);
	}

	/**
	 * Key path to run the Hotel Database app, when it starts it should print out a
	 * welcome message and guides the user to determine how they can enter a file
	 * path, either by file path or by manually enter the reservations.
	 * 
	 * @param args String array from the command prompt
	 * @throws DataFormatException
	 * @throws IOException
	 */
	public static void run(Backend backEnd) throws IOException, DataFormatException {
		String filePath; // String object that contains the filePath to the csvFile
		String csvAsAString; // String object that contains the csv inputted as a string
		String userChoice; // Defines the userChoice given a prompt
		Reader filePathInput; // ReaderObject to read the filePathInput
		boolean running = true;

		String[] csvArray = new String[1];
		scnr = new Scanner(System.in);
		System.out.println("************ WELCOME TO THE HOTEL DATABASE! ************");

		String enterFile = "This is the FILE SCREEN.\nEnter 'x' to quit \nEnter 'f' to enter a filepath \nEnter 's' to enter a string";
		String modeOptions = "\nThis is the MAIN SCREEN.\nThere are three options to choose from in this app. Enter the letter corresponding to the mode you'd like to go to.\n"
				+ "\tA. Create a reservation\n\tB. View reservations based by dates\n\tC. Search reservations based on guest name\n"
				+ "Enter 'r' to go back to the file screen and 'x' to quit.\n";
		String promptChar = "Please enter a letter: ";

		// while look to run app
		while (running) {
			System.out.println(enterFile);
			userChoice = scnr.next();

			// ASKS USER FOR FILE
			try {
				// if user enters x
				if (userChoice.equalsIgnoreCase("x")) {
					// exits the while loop
					running = false;
					break;
				}
				// if user enters f
				else if (userChoice.equalsIgnoreCase("f")) {
					System.out.println("Please enter the filepath to the hotel database csv file:\t");
					filePath = scnr.next();
					try {
						filePathInput = new FileReader(filePath);
						// initializes the backend with a reader object as the argument
						backEnd = new Backend(filePathInput);

					} catch (FileNotFoundException e) {
						System.out.println("This file does not exist.\n");
						e.printStackTrace();// handle FileNotFoundException if file is not found
						continue;
					}

				} else {
					// if user enters s
					if (userChoice.equalsIgnoreCase("s")) {
						System.out.println("Please enter a string to the hotel database file:\t");
						csvAsAString = scnr.next();
						csvArray[0] = csvAsAString;
						// intializes backend with a String array as the object
						backEnd = new Backend(csvArray);
					}
					// if input is null
					else if (userChoice.isEmpty() || userChoice.trim().isEmpty()) {
						throw new NullPointerException("Your input is invalid. Please enter a valid option.\n");
					}
					// if input doesn't match any of the options
					else {
						// restarts the while loop
						System.out.println("Your input is not one of the options. Please enter a valid option.\n");
						continue;
					}

				}
			} catch (NullPointerException e) {
				System.out.println(e);
				continue;
			}

			boolean modeRunning = true;
			// while loop to keep modes running
			while (modeRunning) {
				System.out.println(modeOptions);
				System.out.println(promptChar);
				userChoice = scnr.next();

				// if user wants to go back to file screen
				if (userChoice.equalsIgnoreCase("r")) {
					modeRunning = false;
					continue;

				}
				// if user wants to exit app
				else if (userChoice.equalsIgnoreCase("x")) {
					modeRunning = false;
					running = false;
					break;
				}
				// if user chooses to create a reservation (a)
				else if (userChoice.equalsIgnoreCase("a")) {
					createReservation(backend);
				}
				// if user chooses to search reservations by dates (b)
				else if (userChoice.equalsIgnoreCase("b")) {
					searchByDate(backend);

				} else {
					// if user chooses to search reservations by name (c)
					if (userChoice.equalsIgnoreCase("c")) {
						searchByName(backend);
					}

					// if input doesn't match any of the options
					else {
						// restarts the while loop
						System.out.println("Your input is not one of the options. Please enter a valid option.");
						continue;

					}
				}

			}
		}

		System.out.println("Thank you for using the Hotel Database! Have a great day :)");
	}

	/**
	 * Method to allow user to create a Reservation to be added to the correct red
	 * black tree.
	 * 
	 * @param backEnd
	 */
	static void createReservation(Backend backend) {
		String askName = "\nWhat is the NAME for the Reservation you would like to make?\t";
		String askRoomNum = "What is the ROOM NUMBER for the Reservation you would like to make?\t";
		String askCheckIn = "What is the CHECK IN DATE (mm/dd/yy) for the Reservation you would like to make?\t";
		String askCheckOut = "What is the CHECK OUT DATE (mm/dd/yy) for the Reservation you would like to make?\t";
		
		String name = "";

		boolean createRunning = true;
		boolean askAgain = true;

		while (createRunning) {
			System.out.print(askName);
			name = scnr.nextLine();
			System.out.print(askRoomNum);
			int roomNum = scnr.nextInt();
			System.out.print(askCheckIn);
			String checkIn = scnr.next();
			System.out.print(askCheckOut);
			String checkOut = scnr.next();

			backend.add(name, roomNum, checkIn, checkOut);

			// ask if user wants to search for again
			while (askAgain) {
				System.out
						.println("Would you like to create another reservation? (enter 'y' for yes and 'n' for no):\t");
				String userChoice = scnr.next();
				if (userChoice.equalsIgnoreCase("y")) {
					break;
				} else if (userChoice.equalsIgnoreCase("n")) {
					createRunning = false;
					break;
				} else {
					System.out.println("Your input is not one of the options. Please enter a valid option.");
					continue;
				}
			}

		}

		System.out.println("Your reservation for " + name + " has been succesfully made!!");

	}

	/**
	 * Method to allow user to search Reservations by entering two dates to set as
	 * the range of searches.
	 * 
	 * @param backend
	 */
	static void searchByDate(Backend backend) {
		String askStartDate = "What is the START DATE that you would like to look in?"
				+ "\n(Please enter in format mm/dd/yy)\t";
		String askEndDate = "What is the END DATE that you would like to look in?"
				+ "\n(Please enter in format mm/dd/yy)\t";

		boolean searchDateRunning = true;
		boolean askAgain = true;

		while (searchDateRunning) {
			System.out.println(askStartDate);
			String startDate = scnr.next();
			System.out.println(askEndDate);
			String endDate = scnr.next();
			List<?> reservations = null;

			// prints out reservations
			try {
				reservations = backend.selectByDate(startDate, endDate);
				System.out.println("Here are the reservations between " + startDate + " and " + endDate + ":\n");
				for (int i = 0; i < reservations.size(); i++) {
					System.out.println(reservations.toString());
				}

			} catch (NullPointerException e) {
				System.out.println("\nThere are no matches for reservations between the dates you selected.\n");
			}

			// ask if user wants to search for again
			while (askAgain) {
				System.out.println("Would you like to search by date again? (enter 'y' for yes and 'n' for no):\t");
				String userChoice = scnr.next();
				if (userChoice.equalsIgnoreCase("y")) {
					break;
				} else if (userChoice.equalsIgnoreCase("n")) {
					searchDateRunning = false;
					break;
				} else {
					System.out.println("Your input is not one of the options. Please enter a valid option.");
					continue;
				}
			}

		}

	}

	/**
	 * Method to allow used to search Reservations by entering either a first or
	 * last name
	 * 
	 * @param backend
	 */
	static void searchByName(Backend backend) {
		String askName = "\nWhat is the NAME (first and last) of the reservation you're looking for is under?\t";
		String name = "";

		boolean searchNameRunning = true;
		boolean askAgain = true;

		while (searchNameRunning) {
			System.out.print(askName);
			name = scnr.nextLine();
			List<?> reservations = null;

			// prints reservations based on name
			try {
				reservations = backend.selectByOccupant(name);
				System.out.println("Here are the reservations found for " + name + ":\n");
				for (int i = 0; i < reservations.size(); i++) {
					System.out.println(reservations.toString());
				}
			} catch (NullPointerException e) {
				System.out.println("\nThere are no matches for reservations for the name you entered\n");
			}

			// ask if user wants to search for again
			while (askAgain) {
				System.out.println("Would you like to search by name again? (enter 'y' for yes and 'n' for no):\t");
				String userChoice = scnr.next();
				if (userChoice.equalsIgnoreCase("y")) {
					break;
				} else if (userChoice.equalsIgnoreCase("n")) {
					searchNameRunning = false;
					break;
				} else {
					System.out.println("Your input is not one of the options. Please enter a valid option.");
					continue;
				}
			}

		}
	}

}
