package PathfindingLab;

import PathfindingLab.algorithms.DijkstraPath;
import PathfindingLab.ui.GUI;
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
    private int startBiggestX;
    private  int startBiggestY;
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
        assertEquals(true, dPath.DPathFind(smallInputMap, startX,startY,endXSmall,endYSmall, startDistance));
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
    public void nodesAreSaved() throws IOException{
        Node node = new Node(0,0,0);
        nodeArrayList.add(node);
        node = new Node(0,1,1);
        nodeArrayList.add(node);
        node = new Node(1,2,2.414213562373095);
        nodeArrayList.add(node);
        assertEquals(true, dPath.DPathFind(smallInputMap, startX, startY, endXSmall, endYSmall, startDistance));
        //assertEquals(nodeArrayList.size(), printRoute().size());
    }

    @Test
    public void nodeSetterWorks() {
        Node node = new Node(0, 0, 0);
        dPath.setRoute(node);
        //assertEquals(1, dPath.printRoute().size());
    }

    @Test
    public void visitedNodesSize() throws IOException{
        assertEquals(true, dPath.DPathFind(biggerInputMap, startX, startY, endXBigger, endYBigger, startDistance));
        //assertEquals(25, dPath.printVisitedNodes().size());
    }

}

