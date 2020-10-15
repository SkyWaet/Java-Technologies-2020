public class myBstItem<Type> {
    public myBstItem<Type> left;
    public myBstItem<Type> right;
    public int key;
    public Type value;

    public myBstItem(myBstItem<Type> left, myBstItem<Type> right, int key, Type value) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
