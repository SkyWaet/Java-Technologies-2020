import com.skywaet.redblacktree.RedBlackTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RedBlackTreeTest {

    @Test
    void TestAddElemToEmptyTree() {
        RedBlackTree<Integer, Integer> rbTree = new RedBlackTree<>();
        rbTree.add(10, 1, rbTree.root);
        boolean contains = rbTree.contains(10);
        int elem = rbTree.get(10);
        Assertions.assertEquals(true, contains);
        Assertions.assertEquals(1, elem);
    }

    @Test
    void TestAddElemToNotEmptyTree() {
        RedBlackTree<Integer, Integer> rbTree = new RedBlackTree<>();
        rbTree.add(10, 1, rbTree.root);
        rbTree.add(11, 2, rbTree.root);
        boolean contains = rbTree.contains(11);
        int elem = rbTree.get(11);
        Assertions.assertTrue(contains);
        Assertions.assertEquals(2, elem);
    }

    @Test
    void TestRemoveElem() {
        RedBlackTree<Integer, Integer> rbTree = new RedBlackTree<>();
        rbTree.add(8, 8, rbTree.root);
        rbTree.add(3, 3, rbTree.root);
        rbTree.add(10, 1, rbTree.root);
        rbTree.add(11, 2, rbTree.root);
        rbTree.remove(8);
        Assertions.assertFalse(rbTree.contains(8));
    }

    @Test
    void TestContainsElem() {
        RedBlackTree<Integer, Integer> rbTree = new RedBlackTree<>();
        rbTree.add(8, 8, rbTree.root);
        rbTree.add(3, 3, rbTree.root);
        rbTree.add(10, 1, rbTree.root);
        rbTree.add(11, 2, rbTree.root);
        Assertions.assertTrue(rbTree.contains(10));
        Assertions.assertFalse(rbTree.contains(-1));
    }

    @Test
    void TestGetElem() {
        RedBlackTree<Integer, Integer> rbTree = new RedBlackTree<>();
        rbTree.add(8, 8, rbTree.root);
        rbTree.add(3, 3, rbTree.root);
        rbTree.add(10, 1, rbTree.root);
        rbTree.add(11, 2, rbTree.root);
        Assertions.assertEquals(2, rbTree.get(11));
    }

}