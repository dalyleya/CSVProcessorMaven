package csvProcessor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This Class contain list of elements that match one row from matrix
 *
 * @author : dmalysheva
 * @since : 12.04.2013
 */
public class Row {

    private List<? extends Comparable> members;

    public Row() {
        members = new LinkedList<Comparable>();
    }

    public Row(List<? extends Comparable> members) {
        this.members = members;
    }

//    public void addMember(Comparable element){
//        members.add(element);
//    }

    public List<Comparable> getRow(){
        return Collections.unmodifiableList(members);
    }

    // start from 0
    public Comparable getElementByIndex(int index){
        return members.get(index);
    }

    @Override
    public String toString() {
        return "Row{" +
                "members=" + members +
                '}';
    }
//    private class Element<T extends Comparable>{
//
//    }
}
