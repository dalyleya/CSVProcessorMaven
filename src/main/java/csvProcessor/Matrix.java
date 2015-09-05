package csvProcessor;

import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class info
 *
 * @author : dmalysheva
 * @since : 12.04.2013
 */
public class Matrix {
    public static final Logger LOGGER = Logger.getLogger(Matrix.class);

    private List<String> header;
    private List<Row> matrix;
//
//    public Matrix() {
//        header = new ArrayList<String>();
//        matrix = new LinkedList<Row>();
//    }

    public Matrix(List<String> header) {
        this.header = header;
        matrix = new LinkedList<Row>();
    }
//
//    public void setHeader(List<String> header) {
//        this.header = header;
//    }

    public void addRow(Row row){
        if (row.getRow().size() != header.size()){
            LOGGER.warn("Incorrect input row");
        }  else {
        matrix.add(row);
        }
    }

    public Row getRowByIndex(int index){
        return matrix.get(index);
    }

    public boolean sortByHeaderColumnName(String columnName){
        int index;
        if ((index = header.indexOf(columnName)) != -1){
            sortByColumn(index);
            return true;
        }
            //TODO: log it
            return false;
    }

    private void sortByColumn(final int columnIndex){
        Collections.sort(matrix, new Comparator<Row>() {
            @Override
            public int compare(Row o1, Row o2) {
                Comparable e1 = o1.getElementByIndex(columnIndex);
                Comparable e2 = o2.getElementByIndex(columnIndex);
                return e1.compareTo(e2);
            }
        });
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "header=" + header +
                ", matrix=" + matrix +
                '}';
    }

    public List<String> getHeader() {
        return header;
    }

    public List<Row> getMatrix() {
        return matrix;
    }
}
