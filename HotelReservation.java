// --== CS400 File Header Information ==--
// Name: Ethan Knifsend
// Email: Knifsend@wisc.edu
// Team: FB blue
// Role: Backend Developer
// TA: Daniel Finer
// Lecturer: Gary Dahl
// Notes to Grader: n/a

/**
 * HotelReservation implements HotelReservationInterface. It represents a reservation in a hotel,
 * containing the name of the booker, the date of check-in, the date of check-out, and the room
 * number
 *
 * <p>
 * Bugs: n/a
 *
 * @author Ethan Knifsend
 */
public class HotelReservation implements HotelReservationInterface {
  private String name;

  private String checkInDate; // String that contains date in 01/01/2021 format
  private String checkOutDate; // String that contains date in 01/01/2021 format

  private int roomNumber;

  /**
   * Constructor which intializes name, checkInDate, checkOutDate, and roomNumber with inputed
   * values
   * 
   * @param name         String representing the name of the person who booked this reservation
   * @param checkInDate  String representing the check-in date of this reservation
   * @param checkOutDate String representing the check-out date of this reservation
   * @param roomNumber   int representing the room number of this reservation
   * 
   */
  public HotelReservation(String name, String checkInDate, String checkOutDate, int roomNumber) {
    this.name = name;

    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;

    this.roomNumber = roomNumber;
  }

  /**
   * Accessor method for name
   * 
   * @return name String representing the name of the person who booked this reservation.
   */
  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return this.name;
  }

  /**
   * Accessor method for checkInDate
   * 
   * @return checkInDate String representing the check-in date of this reservation.
   */
  @Override
  public String getCheckInDate() {
    // TODO Auto-generated method stub
    return this.checkInDate;
  }

  /**
   * Accessor method for checkOutDate
   * 
   * @return checkOutDate String representing the check-out date of this reservation.
   */
  @Override
  public String getCheckOutDate() {
    // TODO Auto-generated method stub
    return this.checkOutDate;
  }

  /**
   * Accessor method for roomNumber
   * 
   * @return roomNumber int representing the room number of this reservation.
   */
  @Override
  public int getRoomNumber() {
    // TODO Auto-generated method stub
    return this.roomNumber;
  }

  /**
   * compareTo method which compares the check-in dates. If this check-in date is before that of the
   * inputed hotel reservation, it will return a negative number. If they are the same, it will then
   * compare the room numbers. If this room number is lower than that of the inputed hotel
   * reservation, it will return a negative number.
   * 
   * @param otherReservation HotelReservationInterface being compared to this HotelReservation.
   * @return int comparing the check-in date of the inputed HotelReservation and this
   *         HotelReservation
   */
  @Override
  public int compareTo(HotelReservationInterface otherReservation) {
    // TODO Auto-generated method stub
    if (this.checkInDate.equals(otherReservation.getCheckInDate())) {
      return this.roomNumber - otherReservation.getRoomNumber();
    } else {
      return convertDate(this.checkInDate) - convertDate(otherReservation.getCheckInDate());
    }
  }

  /**
   * Private helper method that converts the date in "mm/dd/year" format into numerical value (int)
   * that can be compared in yearmmdd format. For example, if it is given the date 01/02/2021, it
   * will return an int 20210102. If this is compared to converted date 01/03/2021, it will return
   * -1, as the former date is earlier than the later.
   * 
   * @param date String representing the date in mm/dd/year format
   * @return int the numerical conversion of the inputed date
   */
  private int convertDate(String date) {
    String day = date.substring(3, 5);
    String month = date.substring(0, 2);
    String year = date.substring(6);

    int dayNum = Integer.parseInt(day);
    int monthNum = Integer.parseInt(month);
    int yearNum = Integer.parseInt(year);

    return dayNum + monthNum * 100 + yearNum * 10000;
  }
}
