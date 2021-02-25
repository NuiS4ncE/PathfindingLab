package PathfindingLab.algorithms;

import PathfindingLab.io.IOImg;
import PathfindingLab.utils.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

import static java.lang.Math.sqrt;

public class AStar {

    IOImg ioImg;
    boolean truthTable[][];
    double[][] distance;
    ArrayList<Node> routeNodes;
    Node routeFinal, startNode;
    ArrayList<Node> visitedNodes;
    Node visitedNode;
    int endY, endX;

    public AStar() {
        ioImg = new IOImg();
        routeNodes = new ArrayList<>();
        visitedNodes = new ArrayList<>();
    }

    /** Main pathfinding method
     * @param startY Integer parameter for starting point for Y coordinates
     * @param startX Integer parameter for starting pont for X coordinates
     * @param endY   Integer parameter for ending point for Y coordinate
     * @param endX   Integer parameter for ending point for X coordinates
     * @throws IOException
     */
    public boolean aStarFind(int[][] map, int startY, int startX, int endY, int endX, int startDistance) throws IOException {
        this.endY = endY;
        this.endX = endX;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        startNode = new Node(startY, startX, startDistance);
        int xLength = map[0].length;
        int yLength = map.length;
        int yNow = 0;
        int xNow = 0;
        distance = new double[yLength][xLength];
        truthTable = new boolean[yLength][xLength];
        for (int i = 0; i < yLength; i++) {
            for (int j = 0; j < xLength; j++) {
                distance[i][j] = 9999;
            }
        }
        distance[startY][startX] = 0;
        pq.add(startNode);
        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            yNow = currentNode.getY();
            xNow = currentNode.getX();

            if (truthTable[yNow][xNow]) continue;
            if (xNow == endX && yNow == endY) {
                setRoute(currentNode);
                System.out.println("Dijkstra completed successfully!");
                return true;
            }
            truthTable[yNow][xNow] = true;
            checkNeighbours(map, currentNode, yLength, xLength, pq, yNow, xNow);
        }
        return false;
    }

    /**
     * Method for checking the neighbours and moving in the array
     * @param mapFull Two dimensional integer array parameter
     * @param currentNode Node object parameter
     * @param yLength Integer parameter for length of y-coordinates
     * @param xLength Integer parameter for length of x-coordinates
     * @param pq PriorityQueue parameter
     * @param yNow Integer parameter for current y-position
     * @param xNow Integer parameter for current x-position
     */
    public void checkNeighbours(int[][] mapFull, Node currentNode, int yLength, int xLength, PriorityQueue<Node> pq, int yNow, int xNow) {
        for (int movementY = -1; movementY <= 1; movementY++) {
            for (int movementX = -1; movementX <= 1; movementX++) {
                if (movementX == 0 && movementY == 0) {
                    continue;
                }
                int moveY = yNow + movementY;
                int moveX = xNow + movementX;
                if (moveY < 0 || moveX < 0 || moveX >= xLength || moveY >= yLength) {
                    continue;
                }
                if (mapFull[yNow][xNow] == 0) {
                    continue;
                }

                double distanceNext = movementChecks(moveY, moveX, yNow, xNow, currentNode);

                //double distanceNext = currentNode.getDistance() + 1 + heuristicDistance(moveY, moveX, this.endY, this.endX);

                if (distanceNext < distance[moveY][moveX]) {
                    distance[moveY][moveX] = distanceNext;
                    Node pushNode = new Node(moveY, moveX, distanceNext, currentNode);
                    setVisitedNode(pushNode);
                    pq.add(pushNode);
                }
            }
        }
    }

    /**
     * Method for checking if movement is done diagonally or horizontally and vertically
     * @param moveY Integer parameter for current move on Y-axel
     * @param moveX Integer parameter for current move on X-axel
     * @param currentNode Node parameter for the current node being inspected
     * @return Returns a double value of distance
     */
    public double movementChecks(int moveY, int moveX, int yNow, int xNow, Node currentNode) {
        double distance = 0;
        if (Math.abs(moveY) + Math.abs(moveX) == 1) {
            return distance = currentNode.getDistance() + 1 + heuristicDistance(yNow, xNow, this.endY, this.endX, 0);
        } else {
            return distance = currentNode.getDistance() + sqrt(2) + heuristicDistance(yNow, xNow, this.endY, this.endX, 1);
        }
    }

    public double heuristicDistance(int moveY, int moveX, int endY, int endX, int checkNum) {
        if (checkNum == 0) {
            return Math.abs(endY - moveY) + Math.abs(endX - moveX);
        } else {
            return 0;
        }
    }

    /**
     * Method for printing, adding and returning the route in an ArrayList
     *
     * @return Returns and ArrayList with Node objects
     */
    public ArrayList<Node> printRoute() {
        if (routeFinal != null) {
            while (routeFinal != startNode) {
                routeNodes.add(routeFinal.getPrevNode());
                routeFinal = routeFinal.getPrevNode();
            }
            for (Node nodes : routeNodes) {
                System.out.println(nodes);
            }
            return routeNodes;
        } else {
            System.out.println("No route available! " + routeNodes.toString());
            return routeNodes;
        }
    }

    /**
     * Method for clearing the route ArrayList
     */
    public void clearRoute() {
        if (routeFinal != null) {
            routeFinal.clearNode();
            routeFinal = null;
            routeNodes.clear();
        } else {
            System.out.println("Clearing of AStar failed!");
        }
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
    public ArrayList<Node> getRoute() {
        return routeNodes;
    }

    /**
     * Method for printing, adding and returning the visited nodes in an ArrayList
     *
     * @return Returns visited nodes in an ArrayList
     */
    public ArrayList<Node> printVisitedNodes() {
        while (visitedNode != startNode) {
            visitedNodes.add(visitedNode.getPrevNode());
            visitedNode = visitedNode.getPrevNode();
        }
        for (Node nodes : visitedNodes) {
            System.out.println("visitedNodes: " + nodes);
        }
        return visitedNodes;
    }


}
