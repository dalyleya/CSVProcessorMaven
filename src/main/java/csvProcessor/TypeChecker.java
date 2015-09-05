package csvProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class info
 *
 * @author : dmalysheva
 * @since : 15.04.2013
 */
public final class TypeChecker {
    private TypeChecker() {
    }

    public static List<? extends Comparable> getListComparables(List<String> parts) {
        List<Comparable> modificated = new ArrayList<Comparable>(parts.size());
        for (String element : parts){
            modificated.add(getTypedElement(element));
        }
        return modificated;
    }

    private static Comparable getTypedElement(String element) {
        Type type = Type.getType(element);
        switch (type){
            case INTEGER:
                return Integer.parseInt(element);
            case STRING:
                return element;
            default:
                return element;
        }
    }

    private enum Type{
        INTEGER("[+-]?[\\d]{1, 10}"), STRING(".*");
        private String regExp;

        private Type(String regExp) {
            this.regExp = regExp;
        }

        public static Type getType(String data){
            Pattern pattern;
            for (Type checkedType : Type.values()){
                pattern = Pattern.compile(checkedType.regExp);
                Matcher matcher = pattern.matcher(data);
                if (matcher.matches()){
                    return checkedType;
                }
            }
            return null;
        }
    }
}
