// --== CS400 File Header Information ==--
// Name: Gabriela Setyawan
// Email: gsetyawan@wisc.edu
// Team: FB blue
// Role: Backend Developer
// TA: Daniel Finer
// Lecturer: Gary Dahl
// Notes to Grader: Utilized red black tree data structure to add HotelReservation objects but used linear search when trying to search
// for a specific HotelReservation object using a specific field (already approved by Daniel)
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;


/**
 * BackEnd class to put reservation data in the red black tree data structure
 * depending on the month of the reservation object
 * 
 * @author Gabriela Setyawan
 *
 */

public class BackEnd {
	protected List<HotelReservation> reservationList; // list of HotelReservation Objects that is based on the data
														// input
	protected RedBlackTree<HotelReservation> june; // red black tree for June reservations
	protected RedBlackTree<HotelReservation> july; // red black tree for July reservations
	protected RedBlackTree<HotelReservation> august; // red black tree for August reservations
	private int size; // total number of nodes (reservations)
	protected List<HotelReservation> reservationListBasedOnName= new ArrayList<HotelReservation>(); // list of HotelReservation Objects based on the name
	protected List<HotelReservation> reservationListBasedOnDate = new ArrayList<HotelReservation>(); // list of HotelReservation Objects based on the
																	// checkInDate and checkOutDate

	/**
	 * Constructor for BackEnd object which takes in string from the user to put as
	 * elements in the corresponding RedBlackTree (depending on month)
	 * 
	 * @param input the data specified by the user to input the reservation
	 * @throws FileNotFoundException if the csv file is not found
	 */
	public BackEnd(String input) {
		ReservationDataReader dataReaderForRes = new ReservationDataReader();
		try {
			reservationList = dataReaderForRes.readDataSet(new StringReader(input));
			//for (int i = 0; i < reservationList.size(); i++) {
			//	System.out.print(i+1);
			//System.out.println( " " + reservationList.get(i).toString());
			//}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DataFormatException e) {
			e.printStackTrace();
		} finally {
			initializeRBTrees();
		}

	}

	/**
	 * Constructor for BackEnd object which takes a filepath from the user (console)
	 * 
	 * @param filepath Reader object that contains the filepath
	 */
	public BackEnd(Reader filepath) throws FileNotFoundException, IOException, DataFormatException {
		ReservationDataReader dataReaderForRes = new ReservationDataReader();
		try {
			reservationList = dataReaderForRes.readDataSet(filepath);		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DataFormatException e) {
			e.printStackTrace();
		} finally {
			initializeRBTrees();
		}
	}

	/**
	 * Private helper method to initialize the three red black trees once the
	 * BackEnd Object is created and the add all the HotelReservation data to the
	 * red black trees
	 */
	private void initializeRBTrees() {
		june = new RedBlackTree<HotelReservation>();
		july = new RedBlackTree<HotelReservation>();
		august = new RedBlackTree<HotelReservation>();
		for (int i = 0; i < reservationList.size(); i++) {
			HotelReservation res = reservationList.get(i); // get every HotelReservation Object from the list
			redBlackTreeMonthSelector(res); // add it to the corresponding month tree with the help of redBlackTreeMonthSelector
			}
		//}
	}

	/**
	 * To add a node to the redblacktree when it is inserted manually by the user
	 * 
	 * @param name         String containing the name of the occupant for this
	 *                     reservation
	 * @param checkInDate  String object containing the date the occupant checks in
	 * @param checkOutDate String object containing the date the occupant checks out
	 * @param roomNumber   int that specifies the room number
	 * @throws IllegalArgumentException if there is an identical check in date and
	 *                                  room number for a HotelReservation Object
	 */
	public void add(String name, String checkInDate, String checkOutDate, int roomNumber)
			throws IllegalArgumentException {
		HotelReservation newReservation;
		newReservation = new HotelReservation(name, checkInDate, checkOutDate, roomNumber); // create a new HotelReservation object from 
		redBlackTreeMonthSelector(newReservation); // call the private helper method to determine where to add the node to

	}

	/**
	 * Gets the correct node from the red black trees based on name
	 * 
	 * @param occupantName string specifying the occupant name of the
	 *                     HotelReservation we want to look for
	 * @return reservationListBasedOnName list of Hotel Reservation Object that
	 *         contains all HotelReservation that has matches the occupantName
	 */
	public List<HotelReservation> selectByOccupant(String occupantName) {
		for (int i = 0; i < reservationList.size(); i++) { // iterate through the reservationList
			if (reservationList.get(i).getName().equals(occupantName)) {
				this.reservationListBasedOnName.add(reservationList.get(i)); // check if there are any HotelReservation Objects with the same occupant name
			}
		}
		return this.reservationListBasedOnName;
	}
//
	/**
	 * Gets the correct node from the red black trees based on check in and check
	 * out date
	 * 
	 * @param checkIn  string specifying checkInDate with the format mm/dd/yyyy
	 * @param checkOut string specifying checkOutDate with the format mm/dd/yyyy
	 * @return reservationListBasedOnDate list of Hotel Reservation Object that
	 *         contains all HotelReservation that has matches the checkIn and
	 *         checkOut
	 */
	public List<HotelReservation> selectByDate(String checkIn, String checkOut) {
		for (int i = 0; i < reservationList.size(); i++) { // iterate through the reservationList
			if (reservationList.get(i).getCheckInDate().equals(checkIn) && reservationList.get(i).getCheckOutDate().equals(checkOut)) {
				this.reservationListBasedOnDate.add(reservationList.get(i)); // check if there are any HotelReservation objects matches the checkIn and checkOut dates
			}
		}
		return this.reservationListBasedOnDate;
	}

	/**
	 * To get the total number of nodes (reservations) across the three red black
	 * trees
	 * 
	 * @return this.size total number of nodes (reservations) across the three red
	 *         black trees
	 */
	public int getSize() {
		int juneSize = june.size();
		int julySize = july.size();
		int augustSize = august.size();
		this.size = juneSize + julySize + augustSize; // add up all the number of nodes from the three trees
		return this.size;
	}

	/**
	 * Private helper method to determine where the HotelReservation Object will be
	 * added
	 * 
	 * @param reservation HotelReservation that we want to add
	 */
	private void redBlackTreeMonthSelector(HotelReservation reservation) {
		if (reservation.getCheckInDate().startsWith("06")) { // check to see if the reservation checkInDate is on the month of June
			june.insert(reservation);
		} else if (reservation.getCheckInDate().startsWith("07")) { // check to see if the reservation checkInDate is on the month of July
			july.insert(reservation);
		} else if (reservation.getCheckInDate().startsWith("08")) { // check to see if the reservation checkInDate is on the month of August
			august.insert(reservation);
		} else {
			System.out
					.print("Reservation cannot be added to any of the red black trees because the check in date is not"
							+ "in June/July/August");
			return;
		}
	}
}
