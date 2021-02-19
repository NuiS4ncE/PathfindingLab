package PathfindingLab.algorithms;

import PathfindingLab.io.IOImg;
import PathfindingLab.utils.Node;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

import static java.lang.Math.sqrt;

public class DijkstraPath {

    IOImg ioImg;
    boolean truthTable[][];
    double[][] distance;
    BufferedImage buffImg;
    ArrayList<Node> routeNodes;
    Node routeFinal;
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
     * @param startY Integer parameter for starting point for Y coordinates
     * @param startX Integer parameter for starting pont for X coordinates
     * @param endY   Integer parameter for ending point for Y coordinate
     * @param endX   Integer parameter for ending point for X coordinates
     * @throws IOException
     */
    public boolean DPathFind(int[][] map, int startY, int startX, int endY, int endX, double startDistance) throws IOException {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node startNode = new Node(startY, startX, startDistance);
        int xLength = map[0].length;
        int yLength = map.length;
        int yNow = 0;
        int xNow = 0;
        distance = new double[yLength][xLength];
        truthTable = new boolean[yLength][xLength];
        for (int i = 0; i < yLength; i++) {
            for (int j = 0; j < xLength; j++) {
                distance[i][j] = Double.MAX_VALUE / 2;
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

                double distanceNext = movementChecks(moveY, moveX, currentNode);

                if (distanceNext < distance[moveY][moveX]) {
                    distance[moveY][moveX] = distanceNext;
                    Node pushNode = new Node(moveY, moveX, distanceNext, currentNode);
                    setVisitedNode(pushNode);
                    pq.add(pushNode);
                }
            }
        }
    }

    public double movementChecks(int moveY, int moveX, Node currentNode) {
        double distance = 0;
        if(Math.abs(moveY) + Math.abs(moveX) == 1) {
            return distance = currentNode.getDistance() + 1;
        } else {
            return distance = currentNode.getDistance() + sqrt(2);
        }
    }

    public ArrayList<Node> printRoute() {
        Node node = routeFinal.getPrevNode();
        while (node != null) {
            routeNodes.add(node);
            node = node.getPrevNode();
        }
        for (Node nodes : routeNodes) {
            System.out.println(nodes);
        }
        return routeNodes;
    }

    public void clearRoute() {
        routeNodes.clear();
    }

    public void setRoute(Node route) {
        this.routeFinal = route;
        routeNodes.add(route);
    }

    public void setVisitedNode(Node visitedNode) {
        this.visitedNode = visitedNode;
        visitedNodes.add(visitedNode);
    }

    public ArrayList<Node> getRoute() {
        return routeNodes;
    }

    public ArrayList<Node> printVisitedNodes() {
        Node node = visitedNode;
        while (node != null) {
            visitedNodes.add(node);
            node = node.getPrevNode();
        }
        for(Node nodes : visitedNodes) {
            System.out.println("visitedNodes: " + nodes);
        }
        return visitedNodes;
    }

}


//if(!ifChecks(yLength, xLength, moveY, moveY, mapFull, yNow, xNow)) {
//    continue;
//}

/*
    public boolean ifChecks(int yLengthNow, int xLengthNow, int moveOfY, int moveOfX, int[][] mapFull, int yOfNow, int xOfNow) {
        if (moveOfY < 0 || moveOfX < 0 || moveOfX >= xLengthNow || moveOfY >= yLengthNow) {
            return false;
        }
         else if (mapFull[yOfNow][xOfNow] == 0) {
            return false;
        } else {
            return true;
        }
    } */