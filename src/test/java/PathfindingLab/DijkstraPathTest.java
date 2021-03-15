package PathfindingLab;

import PathfindingLab.algorithms.DijkstraPath;
import PathfindingLab.utils.MyList;
import org.junit.Before;
import org.junit.Test;
import PathfindingLab.utils.Node;

import static org.junit.Assert.*;

import java.io.IOException;


public class DijkstraPathTest {

    private DijkstraPath dPath;
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
    private MyList<Node> nodeMyList;

    @Before
    public void setUp() {
        dPath = new DijkstraPath();
        nodeMyList = new MyList<>();
        smallInputMap = new int[][]{
                {1, 1, 0},
                {0, 1, 1}
        };
        biggerInputMap = new int[][]{
                {1, 1, 1, 0, 0, 1, 1},
                {0, 0, 1, 0, 0, 0, 1},
                {0, 0, 1, 1, 1, 1, 1}
        };
        biggestInputMap = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 0},
                {1, 0, 1, 1, 1, 1, 0, 1, 0},
                {1, 0, 1, 1, 1, 1, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 1, 1, 1, 1, 1, 1, 0},
                {1, 0, 1, 0, 1, 1, 0, 0, 1},
                {1, 1, 1, 0, 1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 1, 1, 1},
                {0, 1, 0, 1, 1, 1, 1, 0, 1}
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
    public void dijkstraFindsRoute() throws IOException {
        assertEquals(true, dPath.DPathFind(smallInputMap, startX, startY, endXSmall, endYSmall, startDistance));
    }

    @Test
    public void dijkstraFindsRouteBiggest() throws IOException {
        assertEquals(true, dPath.DPathFind(biggestInputMap, endXBiggest, endYBiggest, startBiggestX, startBiggestY, startDistance));


    }

    @Test
    public void dijkstraFindsRouteBigger() throws IOException {
        assertEquals(true, dPath.DPathFind(biggerInputMap, endXBigger, endYBigger, startX, startY, startDistance));

    }

    @Test
    public void nodesAreSaved() throws IOException {
        Node node = new Node(0, 0, 0);
        nodeMyList.add(node);
        node = new Node(0, 1, 1);
        nodeMyList.add(node);
        node = new Node(1, 2, 2.414213562373095);
        nodeMyList.add(node);
        assertEquals(true, dPath.DPathFind(smallInputMap, startX, startY, endXSmall, endYSmall, startDistance));
        MyList<Node> myList = new MyList<>();
        Node finalNode = dPath.getVisitedNode();
        while(finalNode != null) {
            myList.add(finalNode);
            finalNode = finalNode.getPrevNode();
        }
        assertEquals(nodeMyList.size(), myList.size());
    }

    @Test
    public void nodeSetterAndGetterWorks() {
        Node node = new Node(0, 0, 0);
        dPath.setRoute(node);
        assertEquals(node, dPath.getRouteFinal());
    }


    @Test
    public void visitedNodesSize() throws IOException {
        assertEquals(true, dPath.DPathFind(biggerInputMap, startX, startY, endXBigger, endYBigger, startDistance));
        //assertEquals(25, dPath.printVisitedNodes().size());
    }

}

