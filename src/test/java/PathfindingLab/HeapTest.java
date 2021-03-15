package PathfindingLab;

import PathfindingLab.algorithms.DijkstraPath;
import PathfindingLab.utils.Heap;
import PathfindingLab.utils.Node;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class HeapTest {

    private DijkstraPath dPath;
    private Heap heapTest;
    private int[][] smallInputMap, biggerInputMap, biggestInputMap;
    private int startY;
    private int startX;
    private int startBiggestX;
    private int startBiggestY;
    private int endYSmall;
    private int endXSmall;
    private int endYBigger;
    private int endXBigger;
    private int endYBiggest;
    private int endXBiggest;
    private int startDistance;
    private ArrayList<Node> nodeArrayList;

    @Before
    public void setUp() {
        dPath = new DijkstraPath();
        heapTest = new Heap(999);
        nodeArrayList = new ArrayList<>();
        smallInputMap = new int[][]{
                {1, 1, 0}, {0, 1, 1}
        };
        biggerInputMap = new int[][]{
                {1, 1, 1, 0, 0, 1, 1}, {0, 0, 1, 0, 0, 0, 1}, {0, 0, 1, 1, 1, 1, 1}
        };
        biggestInputMap = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 0}, {1, 0, 1, 1, 1, 1, 0, 1, 0}, {1, 0, 1, 1, 1, 1, 1, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 0, 1, 1, 1, 1, 1, 1, 0}, {1, 0, 1, 0, 1, 1, 0, 0, 1}, {1, 1, 1, 0, 1, 1, 0, 0, 0}, {1, 1, 1, 1, 0, 0, 1, 1, 1}, {0, 1, 0, 1, 1, 1, 1, 0, 1}
        };
        startX = 0;
        startY = 0;
        startBiggestX = 4;
        startBiggestY = 0;
        endYSmall = 2;
        endXSmall = 1;
        endYBigger = 5;
        endXBigger = 0;
        endXBiggest = 4;
        endYBiggest = 7;
        startDistance = 0;
    }

    @Test
    public void dijkstraFindsRouteWithHeapSmall() throws IOException {
        assertEquals(true, dPath.DPathFind(smallInputMap, startX, startY, endXSmall, endYSmall, startDistance));
    }

    @Test
    public void heapReturnsCorrectOrder() {
        Node first = new Node(0, 0, 0);
        Node second = new Node(1, 0, 1);
        Node third = new Node(2, 0, 2.4);
        heapTest.add(third);
        heapTest.add(first);
        heapTest.add(second);
        assertEquals(first, heapTest.poll());
    }

    @Test
    public void heapGetsEmpty() {
        Node first = new Node(0, 0, 0);
        Node second = new Node(1, 0, 1);
        Node third = new Node(2, 0, 2.4);
        heapTest.add(third);
        heapTest.add(first);
        heapTest.add(second);
        first = heapTest.poll();
        second = heapTest.poll();
        third = heapTest.poll();
        assertEquals(true, heapTest.isEmpty());
    }
}
