public interface ReservationDataInterface extends Comparable<ReservationDataInterface>{

    public Integer getRoomNumber();
    public String getName();
    public String getCheckInDate();
    public String getCheckOutDate();

    //from comparable
    public int compareTo(ReservationDataInterface otherReservation);
}
