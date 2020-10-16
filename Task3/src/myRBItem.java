import java.util.Comparator;

public class myRBItem<keyType, valType> {
    public char color;
    public myRBItem<keyType, valType> left;
    public myRBItem<keyType, valType> right;
    public keyType key;
    public valType value;

    public myRBItem(myRBItem<keyType, valType> left, myRBItem<keyType, valType> right, keyType key, valType value, char color) {
        this.left = left;
        this.right = right;
        this.key = key;
        this.value = value;
        this.color = color;
    }

    public int compareTo(keyType val1) {
        Comparator<keyType> comp = new Comparator<keyType>() {
            @Override
            public int compare(keyType o1, keyType o2) {
                try {
                    Comparable val1 = (Comparable) o1;
                    Comparable val2 = (Comparable) o2;
                    return val1.compareTo(val2);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        return comp.compare(val1, this.key);
    }
}
