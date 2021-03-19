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
 * BackEnd class to put reservation data in the red black tree data structure depending
 * on the month of the reservation object
 * 
 * @author Gabriela Setyawan
 *
 */

public class BackEnd implements BackEndInterface{
	protected List<HotelReservation> reservationList; 
	private RedBlackTree<HotelReservation> june;
	private RedBlackTree<HotelReservation> july;
	private RedBlackTree<HotelReservation> august;
	private int size; // total number of nodes (reservations)
	/**
	 * Constructor for BackEnd object which takes in string from the user to put as
	 * elements in the corresponding RedBlackTree (depending on month)
	 * @param input
	 */
	public BackEnd(StringReader input){
		ReservationDataReader dataReaderForRes = new ReservationDataReader();
		try {
			reservationList = dataReaderForRes.readDataSet(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
		finally {
			initializeRBTrees();
		}
		
	}
	/**
	 * Constructor for BackEnd object which takes a filepath from the user (console)
	 * @param filepath
	 */
	public BackEnd(Reader filepath) throws FileNotFoundException, IOException, DataFormatException{
		ReservationDataReader dataReaderForRes = new ReservationDataReader();
		try {
			reservationList = dataReaderForRes.readDataSet(filepath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
		finally {
			initializeRBTrees();
		}
	}
	
	/**
	 * To add a node to the redblacktree as a HotelReservation Object 
	 */
	public void add(HotelReservation reservation) {
		
		
	}

	/**
	 * 
	 */
	private void initializeRBTrees() {
		
	}
	/**
	 *
	 */
	public List<HotelReservation> selectByOccupant(String occupantName) {
		
		return null;
	}

	
	
	/**
	 *
	 */
	public List<HotelReservation>selectByDate(String checkIn, String checkOut) {
		
		return null;
	}


	/**
	 *
	 */
	public int getSize() {
		// TODO Auto-generated method stub
		int juneSize = june.size();
		int julySize = july.size();
		int augustSize = august.size();
		this.size = juneSize + julySize + augustSize;
		return this.size;
	}
	
	/**
	 * Private helper method to determine where the HotelReservation Object will be added
	 */
	private void redBlackTreeMonthSelector() {
		
	}
	
	 
	
	

}
