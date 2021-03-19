
// --== CS400 File Header Information ==--
// Name: Gabriela Setyawan
// Email: gsetyawan@wisc.edu
// Team: FB blue
// Role: Backend Developer
// TA: Daniel Finer
// Lecturer: Gary Dahl
// Notes to Grader: n/a
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * BackEnd class to put reservation data in the red black tree data structure
 * depending on the month of the reservation object
 * 
 * @author Gabriela Setyawan
 *
 */

public class BackEnd implements BackEndInterface {
	protected List<HotelReservation> reservationList;
	private RedBlackTree<HotelReservation> june;
	private RedBlackTree<HotelReservation> july;
	private RedBlackTree<HotelReservation> august;
	private int size; // total number of nodes (reservations)
	// private

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
		
	}

	/**
	 * To add a node to the redblacktree as a HotelReservation Object using the help
	 * of redBlackTreeMonthSelector to help determine which redBlackTree to add to
	 * @param reservation HotelReservation Object that we want to add from the reservationList
	 * @throws IllegalArgumentException if there is an identical check in date and room number for a HotelReservation Object
	 */
	public void add(HotelReservation reservation) throws IllegalArgumentException{
		try {
		redBlackTreeMonthSelector(reservation);
		// To-do: add a line to check if room number comparison would equal 0
		}
		catch(IllegalArgumentException e) {
			System.out.print("We cannot have two occupants occupying a room at the same time");
		}
	}

	/**
	 * Gets the correct node from the red black trees based on name
	 * 
	 * @param occupantName string specifying the occupant name of the
	 *                     HotelReservation we want to look for
	 * @return listBasedOnName list of Hotel Reservation Object that contains all
	 *         HotelReservation that has matches the occupantName
	 */
	public List<HotelReservation> selectByOccupant(String occupantName) {

		return null;
	}

	/**
	 * Gets the correct node from the red black trees based on check in and check
	 * out date
	 * 
	 * @param checkIn  string specifying checkInDate with the format mm/dd/yy
	 * @param checkOut string specifying checkOutDate with the format mm/dd/yy
	 * @return listBasedOnDate list of Hotel Reservation Object that contains all
	 *         HotelReservation that has matches the checkIn and checkOut
	 */
	public List<HotelReservation> selectByDate(String checkIn, String checkOut) {

		return null;
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
	 */
	private void redBlackTreeMonthSelector(HotelReservation reservation) {
		if (reservation.getCheckInDate().startsWith("6")) {
			june.insert(reservation);
		} else if (reservation.getCheckInDate().startsWith("7")) {
			july.insert(reservation);
		} else if (reservation.getCheckInDate().startsWith("8")) {
			august.insert(reservation);
		} else {
			System.out
					.print("Reservation cannot be added to any of the red black trees because the check in date is not"
							+ "in June/July/August");
		}
	}

}
