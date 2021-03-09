import java.util.List;

// --== CS400 File Header Information ==--                                                                                                                                         
// Author: CS400 Course Staff                                                                                                                                                      
// Email: hpark353@wisc.edu                                                                                                                                 
// Notes: This interface is part of the starter archive for the Back End developers 

public interface BackEndInterface {
  public void add(Reservation reservation);
  public List<Reservation> selectByOccupant(String occupantName);
  public List<Reservation> selectByDate(String checkIn,String checkOut);
  public int getSize();
  
}
