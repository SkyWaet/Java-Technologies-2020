import com.skywaet.listandqueue.MyQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class MyQueueTest {
    @Test
    void TestIsEmptyForEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        Assertions.assertEquals(true, queue.isEmpty());
    }

    @Test
    void TestIsEmptyForNotEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.add(2);
        Assertions.assertEquals(false, queue.isEmpty());
    }

    @Test
    void TestSizeOfEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        Assertions.assertEquals(0, queue.size());
    }

    @Test
    void TestSizeNotOfEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.add(2);
        Assertions.assertEquals(1, queue.size());
    }

    @Test
    void TestAddToTheQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.add(2);
        Assertions.assertEquals(1, queue.size());
    }

    @Test
    void TestAddNullToTheQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        Assertions.assertThrows(NullPointerException.class, () -> {
            queue.add(null);
        });
    }

    @Test
    void TestRemoveFromNotEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.add(1);
        queue.add(2);
        Assertions.assertEquals(1, queue.remove());
        Assertions.assertEquals(1, queue.size());
    }

    @Test
    void TestRemoveFromEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            queue.remove();
        });
    }

    @Test
    void TestPollFromNotEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.add(1);
        queue.add(2);
        Assertions.assertEquals(1, queue.poll());
        Assertions.assertEquals(1, queue.size());
    }

    @Test
    void TestPollFromEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        Assertions.assertEquals(null, queue.poll());

    }

    @Test
    void TestElementFromNotEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.add(1);
        queue.add(2);
        Assertions.assertEquals(1, queue.element());
        Assertions.assertEquals(2, queue.size());
    }

    @Test
    void TestElementFromEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            queue.element();
        });
    }

    @Test
    void TestPeekFromNotEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.add(1);
        queue.add(2);
        Assertions.assertEquals(1, queue.peek());
        Assertions.assertEquals(2, queue.size());
    }

    @Test
    void TestPeekFromEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        Assertions.assertEquals(null, queue.peek());

    }

}