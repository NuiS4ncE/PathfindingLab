package PathfindingLab.algorithms;

import PathfindingLab.io.IOImg;
import PathfindingLab.utils.Heap;
import PathfindingLab.utils.Node;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.sqrt;

public class DijkstraPath {

    IOImg ioImg;
    boolean truthTable[][];
    double[][] distance;
    ArrayList<Node> routeNodes;
    Node routeFinal, startNode;
    ArrayList<Node> visitedNodes;
    Node visitedNode;

    /**
     * Constructor for the class
     */
    public DijkstraPath() {
        ioImg = new IOImg();
        routeNodes = new ArrayList<>();
        visitedNodes = new ArrayList<>();
    }

    /**
     * Main pathfinding method for dijkstra
     *
     * @param startY Integer parameter for starting point for Y coordinates
     * @param startX Integer parameter for starting pont for X coordinates
     * @param endY   Integer parameter for ending point for Y coordinate
     * @param endX   Integer parameter for ending point for X coordinates
     * @throws IOException
     */
    public boolean DPathFind(int[][] map, int startX, int startY, int endX, int endY, double startDistance) throws IOException {
        long timeStart = System.nanoTime();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        //Heap pq = new Heap(14);
        startNode = new Node(startX, startY, startDistance);
        int yLength = map[0].length;
        int xLength = map.length;
        int xNow = 0;
        int yNow = 0;
        distance = new double[xLength][yLength];
        truthTable = new boolean[xLength][yLength];
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                distance[i][j] = 999999;
            }
        }
        distance[startX][startY] = 0;
        pq.add(startNode);
        while (pq != null) {
        //while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            xNow = currentNode.getX();
            yNow = currentNode.getY();

            //System.out.println("DO WE GET HERE?");
            //pq.printHeap();
            if (truthTable[xNow][yNow]) continue;
            if (xNow == endX && yNow == endY) {
                setRoute(currentNode);
                long timeEnd = System.nanoTime();
                long timeElapsed = TimeUnit.NANOSECONDS.toMillis((timeEnd - timeStart));
                System.out.println("Dijkstra completed successfully! Time elapsed: " + timeElapsed + " ms");
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
    public void checkNeighbours(int[][] mapFull, Node currentNode, int xLength, int yLength, PriorityQueue pq, int xNow, int yNow, double[][] distance) {
        //Heap pq
        //PriorityQueue pq
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
                    Node pushNode = new Node(moveX, moveY, distanceNext, currentNode);
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
        if (Math.abs(movementX) + Math.abs(movementY) == 1) {
            return distanceNext = distance[currentNode.getX()][currentNode.getY()] + 1;
        }
        return distanceNext = distance[currentNode.getX()][currentNode.getY()] + sqrt(2);
    }


    /**
     * Method for printing, adding and returning the route in an ArrayList
     *
     * @return Returns and ArrayList with Node objects
     */
    public ArrayList<Node> printRoute() {
        if (routeFinal != null) {
            while (routeFinal != startNode) {
                if (routeFinal.getPrevNode() == null) break;
                routeNodes.add(routeFinal.getPrevNode());
                routeFinal = routeFinal.getPrevNode();

            }
            for (Node nodes: routeNodes) {
                System.out.println(nodes);
            }

        } else {
            System.out.println("Dijkstra route not found! " + routeNodes.toString());
        }

        return routeNodes;
    }

    /**
     * Method for clearing the route ArrayList
     */
    public void clearRoute() {
        if (routeFinal != null) {
            routeFinal.clearNode();
            routeFinal = null;
            routeNodes.clear();
            visitedNodes.clear();
        }
        System.out.println("Clearing of dijkstra failed! ");
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
            if (routeFinal.getPrevNode() == null) break;
            visitedNodes.add(visitedNode.getPrevNode());
            visitedNode = visitedNode.getPrevNode();
        }
        for (Node nodes : visitedNodes) {
            System.out.println("visitedNodes: " + nodes + " " + visitedNodes.toString());
        }
        return visitedNodes;
    }


}




