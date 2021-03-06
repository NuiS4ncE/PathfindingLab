package PathfindingLab.algorithms;

import PathfindingLab.io.IOImg;
import PathfindingLab.utils.Heap;
import PathfindingLab.utils.MyList;
import PathfindingLab.utils.Node;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class AStar {

    IOImg ioImg;
    boolean truthTable[][];
    double[][] distance;
    MyList<Node> routeNodes;
    Node routeFinal, startNode;
    MyList<Node> visitedNodes;
    Node visitedNode;
    int endY, endX, xNow, yNow;

    /**
     * Constructor for the class
     */
    public AStar() {
        ioImg = new IOImg();
        routeNodes = new MyList<>();
        visitedNodes = new MyList<>();
    }

    /**
     * Main pathfinding method
     *
     * @param startY Integer parameter for starting point for Y coordinates
     * @param startX Integer parameter for starting pont for X coordinates
     * @param endY   Integer parameter for ending point for Y coordinate
     * @param endX   Integer parameter for ending point for X coordinates
     * @throws IOException
     */
    public boolean aStarFind(int[][] map, int startX, int startY, int endX, int endY, double startDistance) throws IOException {
        long timeStart = System.nanoTime();
        this.endX = endX;
        this.endY = endY;
        Heap pq = new Heap(999999);
        startNode = new Node(startX, startY, startDistance);
        int yLength = map[0].length;
        int xLength = map.length;
        distance = new double[xLength][yLength];
        truthTable = new boolean[xLength][yLength];
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                distance[i][j] = 999999;
            }
        }
        distance[startX][startY] = 0;
        pq.add(startNode);
        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            xNow = currentNode.getX();
            yNow = currentNode.getY();
            if (truthTable[xNow][yNow]) continue;
            if (xNow == endX && yNow == endY) {
                setRoute(currentNode);
                long timeElapsed = getTime(timeStart);
                System.out.println("AStar completed successfully! Time elapsed: " + timeElapsed + " ms " + "Distance in AStar: " + routeFinal.getAStarDistance());
                return true;
            }
            truthTable[xNow][yNow] = true;
            checkNeighbours(map, currentNode, xLength, yLength, pq, xNow, yNow, distance);
        }
        return false;
    }

    /**
     * Method for checking the neighbours and moving in the array
     *
     * @param mapFull     Two dimensional integer array parameter
     * @param currentNode Node object parameter
     * @param yLength     Integer parameter for length of y-coordinates
     * @param xLength     Integer parameter for length of x-coordinates
     * @param pq          PriorityQueue parameter
     * @param yNow        Integer parameter for current y-position
     * @param xNow        Integer parameter for current x-position
     */
    public void checkNeighbours(int[][] mapFull, Node currentNode, int xLength, int yLength, Heap pq, int xNow, int yNow, double[][] distance) {
        for (int movementX = -1; movementX <= 1; movementX++) {
            for (int movementY = -1; movementY <= 1; movementY++) {

                if (movementX == 0 && movementY == 0) {
                    continue;
                }
                int moveX = xNow + movementX;
                int moveY = yNow + movementY;

                if (moveX < 0 || moveY < 0 || moveX >= xLength || moveY >= yLength) {
                    continue;
                }
                if (mapFull[xNow][yNow] == 0) {
                    continue;
                }

                double distanceNext = movementChecks(movementX, movementY, currentNode, distance);

                if (distanceNext < distance[moveX][moveY]) {
                    distance[moveX][moveY] = distanceNext;
                    Node pushNode = new Node(moveX, moveY, distanceNext + euclideanDistance(moveX, moveY, endX, endY), currentNode, distanceNext);
                    setVisitedNode(pushNode);
                    pq.add(pushNode);
                }
            }
        }
    }

    /**
     * Method for checking if movement is done diagonally or horizontally and vertically
     *
     * @param movementY       Integer parameter for current move on Y-axel
     * @param movementX       Integer parameter for current move on X-axel
     * @param currentNode Node parameter for the current node being inspected
     * @return Returns a double value of distance
     */
    public double movementChecks(int movementX, int movementY, Node currentNode, double[][] distance) {
        double distanceNext = 0;
        if (abs(movementX) + abs(movementY) == 1) {
            return distanceNext = distance[currentNode.getX()][currentNode.getY()] + 1;
        }
        return distanceNext = distance[currentNode.getX()][currentNode.getY()] + sqrt(2);
    }

    /**
     * Method for euclidean distance from current position till the end
     * @param xNow Integer parameter for current x-coordinate
     * @param yNow Integer parameter for current y-coordinate
     * @param endX Integer parameter for end x-coordinate
     * @param endY Integer parameter for end y-coordinate
     * @return Returns the square root of the sum of the product of the multiplication of the end and start x- and y-coordinates
     */
    public double euclideanDistance(int xNow, int yNow, int endX, int endY) {
        double dx = abs(xNow - endX);
        double dy = abs(yNow - endY);
        return sqrt(dx * dx + dy * dy);
    }

    /**
     * Getter for time difference
     * @param timeStart long type parameter for beginning of time count
     * @return returns the time difference from the beginning and the end
     */
    public long getTime(long timeStart) {
        long timeElapsed;
        long timeEnd = System.nanoTime();
        return timeElapsed = TimeUnit.NANOSECONDS.toMillis((timeEnd - timeStart));
    }

    /**
     * Method for getting the last Node in route
     * @return returns last Node type object in route
     */
    public Node getRouteFinal() {
        return routeFinal;
    }


    /**
     * Method for setting the route
     *
     * @param route Parameter for node object
     */
    public void setRoute(Node route) {
        this.routeFinal = route;
        routeNodes.add(route);
    }

    /**
     * Method for setting the visited nodes
     *
     * @param visitedNode Parameter for node object
     */
    public void setVisitedNode(Node visitedNode) {
        this.visitedNode = visitedNode;
        visitedNodes.add(visitedNode);
    }

    /**
     * Getter for the route ArrayList
     *
     * @return Returns route in an ArrayList
     */
    public MyList<Node> getRoute() {
        return routeNodes;
    }

    /**
     * Getter for visited Nodes in MyList form
     * @return return MyList with Nodes
     */
    public MyList<Node> getVisitedNodes() {
        return visitedNodes;
    }

    /**
     * Getter for getting the last visited Node
     * @return returns last visited Node
     */
    public Node getVisitedNode() {
        return visitedNode;
    }


}
