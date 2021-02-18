package PathfindingLab.algorithms;

import PathfindingLab.io.IOImg;
import PathfindingLab.utils.Node;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class DijkstraPath {

    IOImg ioImg;
    boolean truthTable[][];
    int[][] distance;
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
    public void DPathFind(int[][] map, int startY, int startX, int endY, int endX, int startDistance) throws IOException {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node startNode = new Node(startY, startX, startDistance);
        int xLength = map[0].length;
        int yLength = map.length;

        distance = new int[yLength][xLength];
        truthTable = new boolean[yLength][xLength];
        for (int i = 0; i < yLength; i++) {
            for (int j = 0; j < xLength; j++) {
                distance[i][j] = Integer.MAX_VALUE;
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

    public void checkNeighbours(int[][] map, Node currentNode, int yLength, int xLength, PriorityQueue<Node> pq) {
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
                int distanceNext = currentNode.getDistance() + 1;

                if (distanceNext < distance[moveY][moveX]) {
                    distance[moveY][moveX] = distanceNext;
                    Node pushNode = new Node(moveY, moveX, distanceNext, currentNode);
                    pq.add(pushNode);
                }
            }
        }
    }

    public boolean ifChecks(int yLength, int xLength, int moveY, int moveX, int[][] map) {
        if (moveY < 0 || moveX < 0 || moveX >= xLength || moveY >= yLength) {
            return false;
        }
        if (map[yNow][xNow] == 0) {
            return false;
        }
        return true;
    }

    public void movementChecks(int moveY, int moveX) {
        if(map[moveY][moveX] == 0) {

        }
    }

}