package csvProcessor;

import org.apache.log4j.BasicConfigurator;

/**
 * Class info
 *
 * @author : dmalysheva
 * @since : 15.04.2013
 */
public class Runner {
    public static void run() {
        BasicConfigurator.configure();

        //dvfdvdf
        String fileToRead = "";
        String fileToWrite = "";
        Matrix matrix = CSVReader.readCSVToMatrix(fileToRead);
        matrix.sortByHeaderColumnName("name");
        CSVWriter.writeToFile(fileToWrite, matrix);
    }

    public static void main(String[] args) {
        run();
    }
}
