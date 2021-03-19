public interface HotelReservationInterface extends Comparable<HotelReservationInterface> {
  public String getName();

  public String getCheckInDate();

  public String getCheckOutDate();

  public int getRoomNumber();

  // from super interface Comparable
  public int compareTo(HotelReservationInterface otherReservation);
}
