import com.skywaet.listandqueue.MyList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MyListTest {
    @Test
    void TestIsEmptyForEmptyList() {
        MyList<Integer> list = new MyList<>();
        Assertions.assertEquals(true, list.isEmpty());
    }

    @Test
    void isEmptyForNotEmptyList() {
        MyList<Integer> list = new MyList<>();
        Assertions.assertEquals(false, list.isEmpty());
    }

    @Test
    void TestSizeOfForEmptyList() {
        MyList<Integer> list = new MyList<>();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void TestSizeOfForNotEmptyList() {
        MyList<Integer> list = new MyList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        Assertions.assertEquals(3, list.size());
    }

    @Test
    void TestAddElemToTheEndToEmptyList() {
        MyList<Integer> list = new MyList<>();
        list.add(1);
        Assertions.assertEquals(list.get(0), 1);
        Assertions.assertEquals(1, list.size());
    }

    @Test
    void TestAddElemToTheEndToNotEmptyList() {
        MyList<Integer> list = new MyList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        Assertions.assertEquals(list.get(2), 2);
        Assertions.assertEquals(3, list.size());
    }

    @Test
    void TestAddElemToTheEndAddNull() {
        MyList<Integer> list = new MyList<>();
        Assertions.assertThrows(NullPointerException.class, () -> {
            list.add(null);
        });
    }

    @Test
    void TestAddElemByIndexToEmptyList() {
        MyList<Integer> list = new MyList<>();
        list.add(0, 1);
        Assertions.assertEquals(list.get(0), 1);
        Assertions.assertEquals(1, list.size());
    }

    @Test
    void TestAddElemByIndexToNotEmptyList() {
        MyList<Integer> list = new MyList<>();
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
        MyList<Integer> list = new MyList<>();
        list.add(0, 1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(-1, 1);
        });
    }

    @Test
    void TestAddElemByIndexWithTooLargeIndex() {
        MyList<Integer> list = new MyList<>();
        list.add(0, 1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(3, 1);
        });
    }

    @Test
    void TestAddElemByIndexAddNull() {
        MyList<Integer> list = new MyList<>();
        Assertions.assertThrows(NullPointerException.class, () -> {
            list.add(0, null);
        });
    }


    @Test
    void TestRemoveElemWithNegativeIndex() {
        MyList<Integer> list = new MyList<>();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(-1);
        });
    }

    @Test
    void TestRemoveElemWithTooLargeIndex() {
        MyList<Integer> list = new MyList<>();
        list.add(1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(5);
        });
    }

    @Test
    void TestRemoveElemFromListWithOneElem() {
        MyList<Integer> list = new MyList<>();
        list.add(1);
        Assertions.assertEquals(list.remove(0), 1);
        Assertions.assertEquals(true, list.isEmpty());
    }

    @Test
    void TestRemoveElemFromListWithThreeElems() {
        MyList<Integer> list = new MyList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Assertions.assertEquals(list.remove(0), 1);
        Assertions.assertEquals(2, list.size());
    }

    @Test
    void TestGetElementFromListCorrect() {
        MyList<Integer> list = new MyList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Assertions.assertEquals(1, list.get(0));
    }

    @Test
    void TestGetElementWithTooLargeIndex() {
        MyList<Integer> list = new MyList<>();
        list.add(1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(2);
        });
    }

    void TestGetElementWithNegativeIndex() {
        MyList<Integer> list = new MyList<>();
        list.add(1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-1);
        });
    }


}