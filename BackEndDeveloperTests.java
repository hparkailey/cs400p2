//--== CS400 File Header Information ==--
//Name: Gabriela Nawangsari Setyawan
//Email: gsetyawan@wisc.edu
//Team: Blue
//Role: Backend developer
//TA: Daniel Finer
//Lecturer: Gary Dahl
//Notes to Grader: n/a

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class BackEndDeveloperTests{
	 /**
	  *  Test to see if a backend object is created correctly (check to see if size is 0 and backend is not null)
	 */
	@Test
	public void testBackEndObject() throws NullPointerException{
		BackEnd backEnd = null; // once implemented, change it to new BackEnd
		// once implemented, add StringReader that corresponds to csv file
		// new BackEndDummy(String R)
		try{
			assertEquals(0, backEnd.getSize());
		}
		catch(NullPointerException e) {
			fail("The backEnd object is still null");
		}
		
	}
	
	/**
	 * Add 2 Reservations and check if the number of size return 
	 * using getSize() is as expected every time we add a reservation
	 */
	@Test
	public void testAddingReservation() {
		ReservationDummy res1 = new ReservationDummy("03/09/21", "Mike");
		ReservationDummy res2 = new ReservationDummy("03/10/21", "Scott");
		BackEnd backEnd= null; // once implemented, change it to new BackEnd
		// once implemented, add StringReader that corresponds to csv file
		// new BackEndDummy(String R)
		
		backEnd.add(res1);
		assertEquals(1, backEnd.getSize());
		
		backEnd.add(res2);
		assertEquals(2, backEnd.getSize());
		
		fail("Not yet implemented");
	}
	
	/**
	 * Tests if the correct node is returned if we try to find a reservation by passing in 
	 * the name of the occupant
	 */
	@Test
	public void testSelectByOccupant() {
		ReservationDummy res1 = new ReservationDummy("03/09/21", "Mike");
		ReservationDummy res2 = new ReservationDummy("03/10/21", "Scott");
		BackEnd backEnd = null; // once implemented, change it to new BackEnd
		// once implemented, add StringReader that corresponds to csv file
		// new BackEndDummy(String R)
		
		String occupantInRes1 = backEnd.selectByOccupant("Mike").toString();
		assertEquals("03/09/21", occupantInRes1);
		
		fail("Not yet implemented");
	}
	/**
	 * Tests if the correct node is returned when we try to find a reservation by passing
	 * the checkin date
	 */
	@Test
	public void testSelectByDate() {
		ReservationDummy res1 = new ReservationDummy("03/09/21", "Mike");
		ReservationDummy res2 = new ReservationDummy("03/10/21", "Scott");
		BackEnd backEnd = null; // once implemented, change it to new BackEnd
		// once implemented, add StringReader that corresponds to csv file
		// new BackEndDummy(String R)
		
		String occupantInRes1 = backEnd.selectByOccupant("03/09/21").toString();
		assertEquals("03/09/21", occupantInRes1);
		
		fail("Not yet implemented");
	}
	
	/**
	 * Adds nodes with same dates and checks if the order is correct 
	 * (based on room number, larger room number should be on the 
	 * right side of parent while smaller room number should be on the left side)
	 */
	@Test
	public void testOrder() {
		ReservationDummy res1 = new ReservationDummy("03/09/21", "Mike");
		ReservationDummy res2 = new ReservationDummy("03/09/21", "Scott");
		BackEnd backEnd = null; // once implemented, change it to new BackEnd
		// once implemented, add StringReader that corresponds to csv file
		// new BackEndDummy(String R)
		
		// using the toString method from the red black tree check if the order of the nodes are correct
		fail("Not yet implemented");
	}
	

}
