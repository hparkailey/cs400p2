import java.util.List;
import java.util.zip.DataFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

public interface ReservationDataReaderInterface {
  public List<HotelReservationInterface> readDataSet(Reader inputFileReader)
      throws FileNotFoundException, IOException, DataFormatException;
}
