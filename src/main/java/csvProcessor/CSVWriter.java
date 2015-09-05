package csvProcessor;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;

/**
 * Class info
 *
 * @author : dmalysheva
 * @since : 12.04.2013
 */
public final class CSVWriter {
    public static final Logger LOGGER = Logger.getLogger(CSVWriter.class);
    private static String separator = ",";

    private CSVWriter() {
    }

    public static void writeToFile(String fileName, Matrix matrix){
        File file = new File(fileName);
        if (! file.exists())  {
            try {
                file.createNewFile();
            } catch (IOException e) {
                LOGGER.error("Can't create file " + fileName);
            }
        }
            PrintWriter out = null;
            try {
                out = new PrintWriter(file);
                out.print(matrixToCSV(matrix));
                LOGGER.info("File " + fileName + " was write");

            } catch (FileNotFoundException e) {
                LOGGER.error("File " + fileName + " wasn't created");
            } finally {
                closeStream(out);
            }
    }

    private static String matrixToCSV(Matrix matrix) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getCSVRow(matrix.getHeader()));
        List<Comparable> row;
        for (Row currrent : matrix.getMatrix()){
            row = currrent.getRow();
            stringBuilder.append("\n").append(getCSVRow(row));
        }
        return stringBuilder.toString();
    }

    private static StringBuilder getCSVRow(List<? extends Comparable> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Comparable element : list){
            stringBuilder.append(element).append(separator);
        }
        return stringBuilder;
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
}
