package PathfindingLab.utils;

import java.util.Arrays;

public class MyList<E> {

    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private Object elements[];
    private int counter = 0;

    public MyList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public void add(E element) {
        if (size == elements.length) {
            growCapacity();
        }
        elements[size++] = element;
    }

    @SuppressWarnings("unchecked")
    public E get(int i) {
        E e = (E) elements[i];
        return e;
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (int i = 0; i < elements.length; i++) elements[i] = null;
    }

    @Override
    public String toString() {
        String closeStringBegin = "[";
        String closeStringEnd = "]";
        String result = " ";
        for(int i = 1; i < size; i++) {
            result += elements[i].toString() + ", ";
            if(i == size -1) result += elements[i].toString();
        }
        return closeStringBegin + result + closeStringEnd;
    }

    private void growCapacity() {
        int newSize = elements.length * 2;
        Object[] tempArray = new Object[newSize];
        for(int i = 0; i < size; i++) {
            tempArray[i] = elements[i];
        }
        elements = tempArray;
    }

}
