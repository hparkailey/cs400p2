import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * This class contains a set of tests for the HotelReservationInterface and
 * ReservationDataReaderInterface implementation of project two.
 */

public class DataWranglerTests {
  private static String name;
  private static int roomNumber;
  private static String checkInDate;
  private static String checkOutDate;

  private static HotelReservation reservation;

  // Intialize values
  @BeforeEach
  public void initialize() {
    name = "Nate";
    roomNumber = 275;
    checkInDate = "02/11";
    checkOutDate = "02/16";
    reservation = new HotelReservation(name, checkInDate, checkOutDate, roomNumber);
  }

  // This method tests whether getName returns the expected value.
  @Test
  public void testGetName() {
    assertEquals(reservation.getName(), name);
  }

  // This method tests whether getRoomNumber returns the expected value.
  @Test
  public void testGetRoomNumber() {
    assertEquals(reservation.getRoomNumber(), roomNumber);
  }

  // This method tests whether getCheckInDate returns the expected value.
  @Test
  public void testGetCheckInDate() {
    assertEquals(reservation.getCheckInDate(), checkInDate);
  }

  // This method tests whether getCheckOutDate returns the expected value.
  @Test
  public void testGetCheckOutDate() {
    assertEquals(reservation.getCheckOutDate(), checkOutDate);
  }

  // This method tests whether readDataSet contains the correct information.
  @Test
  public void testReadString() throws FileNotFoundException, IOException, DataFormatException {
    List<HotelReservation> reservationList = null;
    ReservationDataReader readerToTest = new ReservationDataReader();

 
      reservationList =
          readerToTest.readDataSet(new StringReader("name,room number,check-in date,check-out date\n"
              + "Rogen,123,03/22,03/30\n" + "James,439,03/26,03/29\n" + "Michelle,43,03/28,04/01\n"));


    assertEquals(reservationList.size(), 3);
  }
}