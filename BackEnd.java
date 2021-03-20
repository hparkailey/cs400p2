
// --== CS400 File Header Information ==--
// Name: Gabriela Setyawan
// Email: gsetyawan@wisc.edu
// Team: FB blue
// Role: Backend Developer
// TA: Daniel Finer
// Lecturer: Gary Dahl
// Notes to Grader: n/a
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
	protected List<HotelReservation> reservationListBasedOnName; // list of HotelReservation Objects based on the name
	protected List<HotelReservation> reservationListBasedOnDate; // list of HotelReservation Objects based on the
																	// checkInDate and checkOutDate

	/**
	 * Constructor for BackEnd object which takes in string from the user to put as
	 * elements in the corresponding RedBlackTree (depending on month)
	 * 
	 * @param input the data specified by the user to input the reservation
	 */
	public BackEnd(String input) {
		ReservationDataReader dataReaderForRes = new ReservationDataReader();
		try {
			reservationList = dataReaderForRes.readDataSet(new StringReader(input));
			System.out.print(reservationList.get(0).toString());
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
	 * @param filepath
	 */
	public BackEnd(Reader filepath) throws FileNotFoundException, IOException, DataFormatException {
		ReservationDataReader dataReaderForRes = new ReservationDataReader();
		try {
			reservationList = dataReaderForRes.readDataSet(filepath);
			System.out.println(reservationList.get(0).toString());
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
			HotelReservation res = reservationList.get(i);
			redBlackTreeMonthSelector(res);
		}
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
		newReservation = new HotelReservation(name, checkInDate, checkOutDate, roomNumber);
		redBlackTreeMonthSelector(newReservation);

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
		reservationListBasedOnName = new ArrayList<HotelReservation>();
		for (int i = 0; i < reservationList.size(); i++) {
			if (reservationList.get(i).getName().equals(occupantName)) {
				reservationListBasedOnName.add(reservationList.get(i));
			}
		}
		return reservationListBasedOnName;
	}

	/**
	 * Gets the correct node from the red black trees based on check in and check
	 * out date
	 * 
	 * @param checkIn  string specifying checkInDate with the format mm/dd/yy
	 * @param checkOut string specifying checkOutDate with the format mm/dd/yy
	 * @return reservationListBasedOnDate list of Hotel Reservation Object that
	 *         contains all HotelReservation that has matches the checkIn and
	 *         checkOut
	 */
	public List<HotelReservation> selectByDate(String checkIn, String checkOut) {
		reservationListBasedOnDate = new ArrayList<HotelReservation>();
		for (int i = 0; i < reservationList.size(); i++) {
			if (reservationList.get(i).getName().equals(checkIn) && reservationList.get(i).getName().equals(checkOut)) {
				reservationListBasedOnName.add(reservationList.get(i));
			}
		}
		return reservationListBasedOnDate;
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
		this.size = juneSize + julySize + augustSize;
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
		} else if (reservation.getCheckInDate().startsWith("07")) {
			july.insert(reservation);
		} else if (reservation.getCheckInDate().startsWith("08")) {
			august.insert(reservation);
		} else {
			System.out
					.print("Reservation cannot be added to any of the red black trees because the check in date is not"
							+ "in June/July/August");
			return;
		}
	}
	
	public static void main(String[] args) throws IOException, DataFormatException {
		Reader filePathInput;
		String dataInput = "name,check-in date,check-out date,room number\n"
				+ "Leslie Smith,07/09/2020,7/11/2020,2019\n" + "Guntur Rashik,06/05/2020,06/07/2020,2015\n"
				+ "Sarah Smith,08/20/2020,8/29/2020,1000";

		filePathInput = new FileReader("Reservations.csv");
		BackEnd backEnd1=new BackEnd(filePathInput);
			backEnd1.add("a", "b", "c", 1);
			System.out.println("a");
		BackEnd backEnd2;
		int expectedSizeForBE1 = 42;
		int expectedSizeForBE2 = 3;
		//try {
			backEnd2 = new BackEnd(dataInput);
		
//			if(backEnd1 != null) {
//				System.out.print("Successfully created backEnd object");
//			}
//			if(backEnd1.getSize()!=expectedSizeForBE1) {
//				System.out.print("Did not successfully add reservations to the tree");
//			}
//			if(backEnd2.getSize()!=expectedSizeForBE2) {
//				System.out.print("Did not successfully add reservations to the tree");
//			}
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		} catch (DataFormatException e1) {
//			e1.printStackTrace();
//		} catch (NullPointerException e) {
//			System.out.print("The backEnd object is still null");
//		}
		
		
	}

}
