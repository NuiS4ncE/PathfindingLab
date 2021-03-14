package PathfindingLab;

import PathfindingLab.algorithms.AStar;
import PathfindingLab.utils.MyList;
import PathfindingLab.utils.Node;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AStarTest {

    private AStar aStar;
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
    private MyList<Node> nodeMyList;

    @Before
    public void setUp() {
        aStar = new AStar();
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
    public void astarFindsRoute() throws IOException {
        assertEquals(true, aStar.aStarFind(smallInputMap, startX,startY,endXSmall,endYSmall, startDistance));
    }

    @Test
    public void astarFindsRouteBiggest() throws IOException {
        assertEquals(true, aStar.aStarFind(biggestInputMap, endXBiggest, endYBiggest, startBiggestX, startBiggestY, startDistance));
    }

    @Test
    public void astarFindsRouteBigger() throws IOException {
        assertEquals(true, aStar.aStarFind(biggerInputMap, endXBigger, endYBigger, startX, startY, startDistance));
    }

    @Test
    public void nodesAreSaved() throws IOException{
        Node node = new Node(0,0,0);
        nodeMyList.add(node);
        node = new Node(0,1,1);
        nodeMyList.add(node);
        node = new Node(1,2,2.414213562373095);
        nodeMyList.add(node);
        assertEquals(true, aStar.aStarFind(smallInputMap, startX, startY, endXSmall, endYSmall, startDistance));
    }

    @Test
    public void nodeSetterWorks() {
        Node node = new Node(0, 0, 0);
        aStar.setRoute(node);
        assertEquals(node, aStar.getRouteFinal());
    }


}
