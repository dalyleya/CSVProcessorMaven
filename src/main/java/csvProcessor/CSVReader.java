package csvProcessor;
import org.apache.log4j.Logger;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Class info
 *
 * @author : dmalysheva
 * @since : 12.04.2013
 */
public final class CSVReader {
    public static final Logger LOGGER = Logger.getLogger(CSVReader.class);

    private static String separator = ",";
//    public static readCSV

    private CSVReader() {
    }

    public static Matrix readCSVToMatrix(String address){
        Scanner scanner = null;
        Matrix matrix = null;
        try {
            scanner = new Scanner(new InputStreamReader(new FileInputStream(address)));
            matrix = new Matrix(getHeader(scanner.nextLine()));
            while (scanner.hasNextLine()){
                Row row = getRow(scanner.nextLine());
                matrix.addRow(row);
            }

        } catch (FileNotFoundException e) {
            LOGGER.error("file " + address + " doesn't exist");
        }
        finally {
            closeStream(scanner);
        }
        return matrix;
    }

    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                LOGGER.error("Can't close " + stream);
            }
        }
    }

    private static Row getRow(String s) {
        List<String> parts = Arrays.asList(s.split(separator));
//        Class dataType = getDataType(parts);
        List<? extends Comparable> data = TypeChecker.getListComparables(parts);
        return new Row(data);
    }

    private static List<String> getHeader(String s) {
        return Arrays.asList(s.split(separator));
    }
}
