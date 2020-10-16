import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class myTreeMapTest {

    @Test
    void TestPutToEmptyTreeMap() {
        myTreeMap mTm = new myTreeMap();
        mTm.put(1, 2);
        Assertions.assertTrue(mTm.containsKey(1));
    }

    @Test
    void TestPutToNotEmptyTreeMap() {
        myTreeMap mTm = new myTreeMap();
        mTm.put(1, 2);
        mTm.put(13, 2);
        mTm.put(12, 2);
        Assertions.assertTrue(mTm.containsKey(12));
    }

    @Test
    void TestGetElem() {
        myTreeMap mTm = new myTreeMap();
        mTm.put(1, 2);
        mTm.put(13, 2);
        mTm.put(12, 2);
        Assertions.assertEquals(2, mTm.get(12));
    }

    @Test
    void TestContainsElem() {
        myTreeMap mTm = new myTreeMap();
        mTm.put(1, 2);
        mTm.put(13, 2);
        mTm.put(12, 2);
        Assertions.assertTrue(mTm.containsKey(12));
    }

    @Test
    void TestIsEmptyWithEmptyMap() {
        myTreeMap mTm = new myTreeMap();
        Assertions.assertTrue(mTm.isEmpty());
    }

    @Test
    void TestIsEmptyWithNotEmptyMap() {
        myTreeMap mTm = new myTreeMap();
        mTm.put(12, 2);
        Assertions.assertFalse(mTm.isEmpty());
    }

}