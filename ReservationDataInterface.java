public interface ReservationDataInterface extends Comparable<ReservationDataInterface>{

    public Integer getRoomNumber();
    public String getName();
    public String getCheckInDate();
    public String getCheckOutDate(); // I don't think there should be a getCheckoutDate() because we are not removing anything

    //from comparable
    public int compareTo(ReservationDataInterface otherReservation);
}
