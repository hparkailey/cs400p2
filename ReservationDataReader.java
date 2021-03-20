import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

// --== CS400 File Header Information ==--
// Name: Ethan Knifsend
// Email: Knifsend@wisc.edu
// Team: FB blue
// Role: Backend Developer
// TA: Daniel Finer
// Lecturer: Gary Dahl
// Notes to Grader: n/a

/**
 * ReservationDataReader reads a csv file containing all the hotel reservations
 *
 * <p>
 * Bugs: n/a
 *
 * @author Ethan Knifsend
 */
public class ReservationDataReader {

  /**
   * Reads the inputed Reader and converts the csv file entries into a list of HotelReservation
   * objects
   * 
   * @param inputFileReader Reader of csv file containing reservation data
   * @return List<HotelReservationInterface> list of hotel reservations contained in csv
   * @see convertReservation()
   * @throws FileNotFoundException
   * @throws IOException
   * @throws DataFormatException
   */
  public List<HotelReservation> readDataSet(Reader inputFileReader)
      throws FileNotFoundException, IOException, DataFormatException {
    // TODO Auto-generated method stub

    try {
      List<HotelReservation> reservations = new ArrayList<HotelReservation>();
      BufferedReader reader = new BufferedReader(inputFileReader);

      String[] header = reader.readLine().split(",");
      String row = reader.readLine();

      while (row != null) {
        String[] reservationEntry = row.split(",");

        reservations.add(convertReservation(reservationEntry, header));

        row = reader.readLine();
      }

      reader.close();

      return reservations;
    } catch (FileNotFoundException fnfe) {
      throw fnfe;
    } catch (IOException ioe) {
      throw ioe;
    } catch (DataFormatException dfe) {
      throw dfe;
    }
  }

  /**
   * Private helper method which converts the inputed reservation, which is a line in the file held
   * in an array of String, into a HotelReservation object.
   * 
   * @param reservation String [] representing the line in the file, which should represent a single
   *                    reservation
   * @param header      String [] representing the title of each column, which is used to find the
   *                    correct data for each value in the HotelResevation object
   * @return HotelReservation reservation created with information
   * @see findCol()
   */
  private HotelReservation convertReservation(String[] reservation, String[] header)
      throws DataFormatException {
    try {
      String name = reservation[findCol(header, "name")];

      String checkInDate = reservation[findCol(header, "check-in date")];
      String checkOutDate = reservation[findCol(header, "check-out date")];

      int roomNumber = Integer.parseInt(reservation[findCol(header, "room number")]);

      return new HotelReservation(name, checkInDate, checkOutDate, roomNumber);
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new DataFormatException("The file format is incorrect.");
    }
  }

  /**
   * Private helper method which finds the index of the inputed title in the header array. This
   * index represents which column the data values of that title are.
   * 
   * @param header String [] representing the list of column titles
   * @param title  String representing the desired title
   * @return an index representing which column contains the values of the title
   */
  private int findCol(String[] header, String title) {
    for (int i = 0; i < header.length; i++) {
      if (title.equals(header[i])) {
        return i;
      }
    }

    return -1;
  }
  
}

