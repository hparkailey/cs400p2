import java.util.List;

// --== CS400 File Header Information ==--                                                                                                                                         
// Author: Hailey Park                                                                                                                                                      
// Email: hpark353@wisc.edu                                                                                                                                 
// Notes: This interface is part of the starter archive for the Back End developers 

public interface BackEndInterface {
  public void add(HotelReservation reservation);
  public List<HotelReservation> selectByOccupant(String occupantName);
  public List<HotelReservation> selectByDate(String checkIn,String checkOut);
  public int getSize();
  
}
