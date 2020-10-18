package com.skywaet.listandqueue;

public class MyListItem<Type> {
    MyListItem<Type> prev;
    MyListItem<Type> next;
    Type data;

    public MyListItem(MyListItem<Type> Prev, MyListItem<Type> Next, Type Data) {
        prev = Prev;
        next = Next;
        data = Data;
    }
}

