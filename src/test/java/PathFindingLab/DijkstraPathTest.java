package PathFindingLab;

import PathfindingLab.algorithms.DijkstraPath;
import org.junit.Before;
import org.junit.Test;
import PathfindingLab.utils.Node;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;


public class DijkstraPathTest {

    private DijkstraPath dPath;
    private int[][] smallInputMap, biggerInputMap, biggestInputMap;
    private int startY;
    private int startX;
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
        nodeArrayList = new ArrayList<>();
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
                {1, 0, 1, 1, 0, 0, 0, 1, 0},
                {1, 0, 1, 1, 0, 0, 0, 1, 0},
                {1, 0, 1, 1, 1, 1, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 1, 0},
                {1, 1, 1, 0, 1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 1, 0, 0, 1},
                {1, 1, 1, 0, 1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 1, 1, 1},
                {0, 1, 0, 1, 1, 1, 1, 0, 1}
        };
        startX = 0;
        startY = 0;
        endXSmall = 2;
        endYSmall = 1;
        endXBigger = 5;
        endYBigger = 0;
        endYBiggest = 9;
        endXBiggest = 8;
        startDistance = 0;
    }

    @Test
    public void dijkstraFindsRoute() throws IOException {
        assertEquals(true, dPath.DPathFind(smallInputMap, startY,startX,endYSmall,endXSmall, startDistance));
    }

    @Test
    public void dijkstraFindsRouteBiggest() throws IOException {
        assertEquals(true, dPath.DPathFind(biggestInputMap, endYBiggest, endXBiggest, startY, startX, startDistance));
        //assertEquals(true, dPath.DPathFind(biggestInputMap, startY, startX, endYBiggest, endXBiggest, startDistance));
        dPath.printRoute();

    }

    @Test
    public void nodesAreSaved() throws IOException{
        Node node = new Node(0,0,0);
        nodeArrayList.add(node);
        node = new Node(0,1,1);
        nodeArrayList.add(node);
        node = new Node(1,2,2.414213562373095);
        nodeArrayList.add(node);
        assertEquals(true, dPath.DPathFind(smallInputMap, startY, startX, endYSmall, endXSmall, startDistance));
        assertEquals(nodeArrayList.size(), dPath.printRoute().size());
    }

    @Test
    public void nodeSetterWorks() {
        Node node = new Node(0, 0, 0);
        dPath.setRoute(node);
        assertEquals(1, dPath.printRoute().size());
    }

    @Test
    public void visitedNodesSize() throws IOException{
        assertEquals(true, dPath.DPathFind(biggerInputMap, startY, startX, endYBigger, endXBigger, startDistance));
        assertEquals(26, dPath.printVisitedNodes().size());
    }

}

