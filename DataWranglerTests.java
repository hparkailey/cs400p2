import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.*;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;

/**
 * This class contains a set of tests for the HotelReservationInterface and
 * ReservationDataReaderInterface implementation of project two.
 */

public class DataWranglerTests {
  private static String name;
  private static int roomNumber;
  private static String checkInDate;
  private static String checkOutDate;

  private static HotelReservationInterface reservation;

  // Intialize values
  @BeforeEach
  public static void initialize() {
    name = "Nate";
    roomNumber = 275;
    checkInDate = "02/11";
    checkOutDate = "02/16";

    reservation = new HotelReservation(name, roomNumber, checkInDate, checkOutDate);
  }

  // This method tests whether getName returns the expected value.
  @Test
  public static void testGetName() {
    assertEquals(reservation.getName(), name);
  }

  // This method tests whether getRoomNumber returns the expected value.
  @Test
  public static void testGetRoomNumber() {
    assertEquals(reservation.getRoomNumber(), roomNumber);
  }

  // This method tests whether getCheckInDate returns the expected value.
  @Test
  public static void testGetCheckInDate() {
    assertEquals(reservation.getCheckInDate(), checkInDate);
  }

  // This method tests whether getCheckOutDate returns the expected value.
  @Test
  public static void testGetCheckOutDate() {
    assertEquals(reservation.getCheckOutDate(), checkOutDate);
  }

  // This method tests whether readDataSet contains the correct information.
  @Test
  public static void testReadString() {
    List<HotelReservationInterface> reservationList;
    ReservationDataReaderInterface readerToTest = new ReservationDataReader();

    reservationList =
        readerToTest.readDataSet(new StringReader("name,room number,check-in date,check-out date\n"
            + "Rogen,123,03/22,03/30\n" + "James,439,03/26,03/29\n" + "Michelle,43,03/28,04/01\n"));

    assertEquals(reservationList.size(), 3);
  }
}
