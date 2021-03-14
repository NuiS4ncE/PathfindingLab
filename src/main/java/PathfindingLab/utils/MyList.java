package PathfindingLab.utils;


public class MyList<E> {

    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private Object elements[];
    private int counter = 0;

    /**
     * Constructor for the class
     */
    public MyList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Method for adding into the list
     *
     * @param element Generic E type parameter for adding an element into the list
     */
    public void add(E element) {
        if (size == elements.length) {
            growCapacity();
        }
        elements[size++] = element;
    }

    /**
     * Getter for getting and element from position i
     *
     * @param i integer type parameter for position
     * @return returns E type element from list
     */
    @SuppressWarnings("unchecked")
    public E get(int i) {
        E e = (E) elements[i];
        return e;
    }

    /**
     * Getter for list size
     *
     * @return returns integer type size
     */
    public int size() {
        return size;
    }

    /**
     * Method for clearing the list
     */
    public void clear() {
        for (int i = 0; i < elements.length; i++) elements[i] = null;
    }

    /**
     * Method for printing out the contents of the list
     *
     * @return returns string of list contents
     */
    @Override
    public String toString() {
        String closeStringBegin = "[";
        String closeStringEnd = "]";
        String result = " ";
        for (int i = 1; i < size; i++) {
            result += elements[i].toString() + ", ";
            if (i == size - 1) result += elements[i].toString();
        }
        return closeStringBegin + result + closeStringEnd;
    }

    /**
     * Method for dynamically growing the capacity of the list
     */
    private void growCapacity() {
        int newSize = elements.length * 2;
        Object[] tempArray = new Object[newSize];
        for (int i = 0; i < size; i++) {
            tempArray[i] = elements[i];
        }
        elements = tempArray;
    }

}
