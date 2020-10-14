import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        myList<Integer> list = new myList<>();
        for (int i = 0; i < 10; i++) {
            //System.out.print(" i = "+i+"; ");
            list.add(i);
        }
        System.out.println("Size = " + list.size());

        for (int i = 0; i < 10; i++) {
            System.out.println(list.get(i));
        }
        System.out.println("\n Remove Element");
        list.remove(2);
        for (Integer integer : list) System.out.println(integer);
        System.out.println("Contains 8: " + list.contains(8));
        System.out.println(" Adding new value ");
        list.add(3, 10);
        for (var i = 0; i < list.size(); i++) {
            System.out.print(i + " ");
        }

        System.out.println("Queue testing");
        myQueue<String> queue = new myQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.offer("a" + i);
        }
        for (int i = 0; i < 20; i++) {
            System.out.print(queue.remove());
        }

    }
}
