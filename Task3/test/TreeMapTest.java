import com.skywaet.treemap.TreeMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TreeMapTest {

    @Test
    void TestPutToEmptyTreeMap() {
        TreeMap mTm = new TreeMap();
        mTm.put(1, 2);
        Assertions.assertTrue(mTm.containsKey(1));
    }

    @Test
    void TestPutToNotEmptyTreeMap() {
        TreeMap mTm = new TreeMap();
        mTm.put(1, 2);
        mTm.put(13, 2);
        mTm.put(12, 2);
        Assertions.assertTrue(mTm.containsKey(12));
    }

    @Test
    void TestGetElem() {
        TreeMap mTm = new TreeMap();
        mTm.put(1, 2);
        mTm.put(13, 2);
        mTm.put(12, 2);
        Assertions.assertEquals(2, mTm.get(12));
    }

    @Test
    void TestContainsElem() {
        TreeMap mTm = new TreeMap();
        mTm.put(1, 2);
        mTm.put(13, 2);
        mTm.put(12, 2);
        Assertions.assertTrue(mTm.containsKey(12));
    }

    @Test
    void TestIsEmptyWithEmptyMap() {
        TreeMap mTm = new TreeMap();
        Assertions.assertTrue(mTm.isEmpty());
    }

    @Test
    void TestIsEmptyWithNotEmptyMap() {
        TreeMap mTm = new TreeMap();
        mTm.put(12, 2);
        Assertions.assertFalse(mTm.isEmpty());
    }

}