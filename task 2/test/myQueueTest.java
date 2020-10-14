import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class myQueueTest {
    @Test
    void TestIsEmptyForEmptyQueue() {
        myQueue<Integer> queue = new myQueue<>();
        Assertions.assertEquals(true, queue.isEmpty());
    }

    @Test
    void TestIsEmptyForNotEmptyQueue() {
        myQueue<Integer> queue = new myQueue<>();
        queue.add(2);
        Assertions.assertEquals(false, queue.isEmpty());
    }

    @Test
    void TestSizeOfEmptyQueue() {
        myQueue<Integer> queue = new myQueue<>();
        Assertions.assertEquals(0, queue.size());
    }

    @Test
    void TestSizeNotOfEmptyQueue() {
        myQueue<Integer> queue = new myQueue<>();
        queue.add(2);
        Assertions.assertEquals(1, queue.size());
    }

    @Test
    void TestAddToTheQueue() {
        myQueue<Integer> queue = new myQueue<>();
        queue.add(2);
        Assertions.assertEquals(1, queue.size());
    }

    @Test
    void TestAddNullToTheQueue() {
        myQueue<Integer> queue = new myQueue<>();
        Assertions.assertThrows(NullPointerException.class, () -> {
            queue.add(null);
        });
    }

    @Test
    void TestRemoveFromNotEmptyQueue() {
        myQueue<Integer> queue = new myQueue<>();
        queue.add(1);
        queue.add(2);
        Assertions.assertEquals(1, queue.remove());
        Assertions.assertEquals(1, queue.size());
    }

    @Test
    void TestRemoveFromEmptyQueue() {
        myQueue<Integer> queue = new myQueue<>();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            queue.remove();
        });
    }

    @Test
    void TestPollFromNotEmptyQueue() {
        myQueue<Integer> queue = new myQueue<>();
        queue.add(1);
        queue.add(2);
        Assertions.assertEquals(1, queue.poll());
        Assertions.assertEquals(1, queue.size());
    }

    @Test
    void TestPollFromEmptyQueue() {
        myQueue<Integer> queue = new myQueue<>();
        Assertions.assertEquals(null, queue.poll());

    }

    @Test
    void TestElementFromNotEmptyQueue() {
        myQueue<Integer> queue = new myQueue<>();
        queue.add(1);
        queue.add(2);
        Assertions.assertEquals(1, queue.element());
        Assertions.assertEquals(2, queue.size());
    }

    @Test
    void TestElementFromEmptyQueue() {
        myQueue<Integer> queue = new myQueue<>();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            queue.element();
        });
    }

    @Test
    void TestPeekFromNotEmptyQueue() {
        myQueue<Integer> queue = new myQueue<>();
        queue.add(1);
        queue.add(2);
        Assertions.assertEquals(1, queue.peek());
        Assertions.assertEquals(2, queue.size());
    }

    @Test
    void TestPeekFromEmptyQueue() {
        myQueue<Integer> queue = new myQueue<>();
        Assertions.assertEquals(null, queue.peek());

    }

}