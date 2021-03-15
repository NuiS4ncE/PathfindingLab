package PathfindingLab;

import PathfindingLab.utils.MyList;
import PathfindingLab.utils.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyListTest {

    private MyList<Node> myList;

    @Before
    public void setUp() {
        myList = new MyList<>();
    }

    @Test
    public void setMyListWorks() {
        Node node = new Node(0, 0, 0);
        myList.add(node);
        assertEquals(1, myList.size());
    }

    @Test
    public void getMyListWorks() {
        Node first = new Node(0, 0, 0);
        Node second = new Node(0, 1, 1);
        myList.add(first);
        myList.add(second);
        assertEquals(second, myList.get(1));
    }

    @Test
    public void myListGrowsCapacity() {
        //Default size for MyList is 10
        Node first = new Node(0, 0, 0);
        myList.add(first);
        myList.add(first);
        myList.add(first);
        myList.add(first);
        myList.add(first);
        myList.add(first);
        myList.add(first);
        myList.add(first);
        myList.add(first);
        myList.add(first);
        myList.add(first);
        assertEquals(11, myList.size());
    }

    @Test
    public void myListIsCleared() {
        Node node = new Node(9, 9, 9);
        myList.add(node);
        myList.clear();
        assertEquals(null, myList.get(0));
    }

    @Test
    public void elementIsRemovedAndMyListIsPrinted() {
        Node first = new Node(0, 0, 0);
        myList.add(first);
        myList.add(first);
        myList.add(first);
        myList.remove(1);
        assertEquals(2, myList.size());
    }

    @Test
    public void toStringPrintsCorrectly() {
        Node first = new Node(0,0,0);
        myList.add(first);
        String testString = "[" + first.toString() + "]";
        String testString2 = myList.toString();
        System.out.println(testString);
        System.out.println(testString2);
        assertEquals(testString, testString2);
    }

    @Test
    public void toStringPrintsCorrectlyMultiple() {
        Node first = new Node(0,0,0);
        Node second = new Node(1,2,1);
        myList.add(first);
        myList.add(second);
        myList.add(first);
        myList.add(second);
        myList.add(first);
        String testString = "[" + first.toString() + ", " + second.toString() + ", " + first.toString() + ", " + second.toString() + ", " + first.toString() + "]";
        String testString2 = myList.toString();
        assertEquals(testString, testString2);
    }
}
