import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class myListTest {
    @Test
    void isEmptyForEmptyList(){
        myList<Integer> list = new myList<>();
        Assertions.assertEquals(true,list.isEmpty());
    }
    @Test
    void sizeOfEmptyList(){
        myList<Integer> list = new myList<>();
        Assertions.assertEquals(0,list.size());
    }
    @Test
    void addElemToEmptyList(){
        myList<Integer> list = new myList<>();

    }

    @Test
    void sizeOfListWithOneElem(){

    }
    @Test
    void sizeOfListWithManyElems(){

    }
    @Test
    void isEmptyForNotEmptyList(){
        myList<Integer> list = new myList<>();
        Assertions.assertEquals(false,list.isEmpty());
    }

}