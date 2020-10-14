import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class myListTest {
    @Test
    void TestIsEmptyForEmptyList() {
        myList<Integer> list = new myList<>();
        Assertions.assertEquals(true, list.isEmpty());
    }

    @Test
    void isEmptyForNotEmptyList() {
        myList<Integer> list = new myList<>();
        Assertions.assertEquals(false, list.isEmpty());
    }

    @Test
    void TestSizeOfForEmptyList() {
        myList<Integer> list = new myList<>();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void TestSizeOfForNotEmptyList() {
        myList<Integer> list = new myList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        Assertions.assertEquals(3, list.size());
    }

    @Test
    void TestAddElemToTheEndToEmptyList() {
        myList<Integer> list = new myList<>();
        list.add(1);
        Assertions.assertEquals(list.get(0), 1);
        Assertions.assertEquals(1, list.size());
    }

    @Test
    void TestAddElemToTheEndToNotEmptyList() {
        myList<Integer> list = new myList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        Assertions.assertEquals(list.get(2), 2);
        Assertions.assertEquals(3, list.size());
    }

    @Test
    void TestAddElemToTheEndAddNull() {
        myList<Integer> list = new myList<>();
        Assertions.assertThrows(NullPointerException.class, () -> {
            list.add(null);
        });
    }

    @Test
    void TestAddElemByIndexToEmptyList() {
        myList<Integer> list = new myList<>();
        list.add(0, 1);
        Assertions.assertEquals(list.get(0), 1);
        Assertions.assertEquals(1, list.size());
    }

    @Test
    void TestAddElemByIndexToNotEmptyList() {
        myList<Integer> list = new myList<>();
        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);
        list.add(2, 4);
        Assertions.assertEquals(list.get(2), 4);
        Assertions.assertEquals(list.get(3), 3);
        Assertions.assertEquals(4, list.size());
    }

    @Test
    void TestAddElemByIndexWithNegativeIndex() {
        myList<Integer> list = new myList<>();
        list.add(0, 1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(-1, 1);
        });
    }

    @Test
    void TestAddElemByIndexWithTooLargeIndex() {
        myList<Integer> list = new myList<>();
        list.add(0, 1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(3, 1);
        });
    }

    @Test
    void TestAddElemByIndexAddNull() {
        myList<Integer> list = new myList<>();
        Assertions.assertThrows(NullPointerException.class, () -> {
            list.add(0, null);
        });
    }


    @Test
    void TestRemoveElemWithNegativeIndex() {
        myList<Integer> list = new myList<>();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(-1);
        });
    }

    @Test
    void TestRemoveElemWithTooLargeIndex() {
        myList<Integer> list = new myList<>();
        list.add(1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(5);
        });
    }

    @Test
    void TestRemoveElemFromListWithOneElem() {
        myList<Integer> list = new myList<>();
        list.add(1);
        Assertions.assertEquals(list.remove(0), 1);
        Assertions.assertEquals(true, list.isEmpty());
    }

    @Test
    void TestRemoveElemFromListWithThreeElems() {
        myList<Integer> list = new myList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Assertions.assertEquals(list.remove(0), 1);
        Assertions.assertEquals(2, list.size());
    }

    @Test
    void TestGetElementFromListCorrect() {
        myList<Integer> list = new myList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Assertions.assertEquals(1, list.get(0));
    }

    @Test
    void TestGetElementWithTooLargeIndex() {
        myList<Integer> list = new myList<>();
        list.add(1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(2);
        });
    }

    void TestGetElementWithNegativeIndex() {
        myList<Integer> list = new myList<>();
        list.add(1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-1);
        });
    }


}