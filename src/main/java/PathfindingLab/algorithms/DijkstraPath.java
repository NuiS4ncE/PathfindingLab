package PathfindingLab.algorithms;

import PathfindingLab.io.IOImg;
import PathfindingLab.utils.Node;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

import static java.lang.Math.sqrt;

public class DijkstraPath {

    IOImg ioImg;
    boolean truthTable[][];
    double[][] distance;
    BufferedImage buffImg;
    int[][] map;
    int yNow;
    int xNow;
    int moveX;
    int moveY;
    ArrayList<Node> routeNodes;
    Node routeFinal;

    /**
     * Constructor for the class
     */
    public DijkstraPath() {
        ioImg = new IOImg();
        routeNodes = new ArrayList<>();
    }

    /**
     * @param startY Integer parameter for starting point for Y coordinates
     * @param startX Integer parameter for starting pont for X coordinates
     * @param endY   Integer parameter for ending point for Y coordinate
     * @param endX   Integer parameter for ending point for X coordinates
     * @throws IOException
     */
    public void DPathFind(double[][] map, int startY, int startX, int endY, int endX, int startDistance) throws IOException {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node startNode = new Node(startY, startX, startDistance);
        int xLength = map[0].length;
        int yLength = map.length;

        distance = new double[yLength][xLength];
        truthTable = new boolean[yLength][xLength];
        for (int i = 0; i < yLength; i++) {
            for (int j = 0; j < xLength; j++) {
                distance[i][j] = Double.MAX_VALUE;
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
                return;
            }
            truthTable[yNow][xNow] = true;
            checkNeighbours(map, currentNode, yLength, xLength, pq);
        }

    }

    public void setRoute(Node route) {
        this.routeFinal = route;
        routeNodes.add(route);
    }

    public ArrayList<Node> getRoute() {
        return routeNodes;
    }

    public void printRoute() {
        Node node = routeFinal.getPrevNode();
        while (node != null) {
            routeNodes.add(node);
            node = node.getPrevNode();
        }
        for (Node nodes : routeNodes) {
            System.out.println(nodes);
        }

    }

    public void checkNeighbours(double[][] map, Node currentNode, int yLength, int xLength, PriorityQueue<Node> pq) {
        for (int movementY = -1; movementY <= 1; movementY++) {
            for (int movementX = -1; movementX <= 1; movementX++) {

                if (movementX == 0 && movementY == 0) {
                    continue;
                }

                moveY = yNow + movementY;
                moveX = xNow + movementX;

                if(!ifChecks(yLength, xLength, moveY, moveX, map)) {
                    continue;
                }
                double distanceNext = movementChecks(moveY, moveX, currentNode);

                if (distanceNext < distance[moveY][moveX]) {
                    distance[moveY][moveX] = distanceNext;
                    Node pushNode = new Node(moveY, moveX, distanceNext, currentNode);
                    pq.add(pushNode);
                }
            }
        }
    }

    public boolean ifChecks(int yLength, int xLength, int moveY, int moveX, double[][] map) {
        if (moveY < 0 || moveX < 0 || moveX >= xLength || moveY >= yLength) {
            return false;
        }
        if (map[yNow][xNow] == 0) {
            return false;
        }
        return true;
    }

    public double movementChecks(int moveY, int moveX, Node currentNode) {
        int plusOne = 1;
        int minusOne = -1;
        int zeroNum = 0;
        double distance = 0;
        if(moveY == -1 && moveX == -1 && map[moveY][moveX] == 1 || moveY == 1 && moveX == -1 && map[moveY][moveX] == 1 || moveY == -1 && moveX == 1 && map[moveY][moveX] == 1 ||
                moveY == 1 && moveX == 1 && map[moveY][moveX] == 1) {
           return distance = currentNode.getDistance() + sqrt(2);
        } else {
             return distance = currentNode.getDistance() + 1;
        }
    }

}