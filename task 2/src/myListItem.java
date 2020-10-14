public class myListItem<Type> {
    myListItem<Type> prev;
    myListItem<Type> next;
    Type data;

    public myListItem(myListItem<Type> Prev, myListItem<Type> Next, Type Data) {
        prev = Prev;
        next = Next;
        data = Data;
    }
}

