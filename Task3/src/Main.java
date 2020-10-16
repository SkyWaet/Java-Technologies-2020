public class Main {
    public static void main(String[] args) {

        myTreeMap tm = new myTreeMap();
        tm.put(10, 10);
        tm.put(1, 1);
        tm.put(2, 2);
        tm.put(5, 5);
        tm.put(8, 8);
        tm.put(13, 13);
        tm.put(16, 16);
        tm.put(11, 11);
        tm.put(14, 14);
        tm.put(1, 15);

        System.out.println("Blach height: " + tm.container.blackHeight());
        System.out.println(tm.containsKey(28));
    }
}
