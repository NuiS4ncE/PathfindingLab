package PathfindingLab.algorithms;

import PathfindingLab.io.IOImg;
import PathfindingLab.utils.Node;

import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class DijkstraPath {

    IOImg ioImg;
    boolean truthTable[][];
    int[][] distance;
    BufferedImage buffImg;
    int[][] dropTheCourse;
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
    public void DPathFind(int startY, int startX, int endY, int endX, int startDistance) throws IOException {
        buffImg = ioImg.getBuffImg();
        buffImg = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        startY = 0;
        startX = 0;
        endY = 1;
        endX = 2;
        startDistance = 0;
        Node startNode = new Node(startY, startX, startDistance);

        dropTheCourse = new int[][]{
                {1, 1, 0},
                {0, 1, 1}
        };
        int xLength = dropTheCourse[0].length;
        int yLength = dropTheCourse.length;
        
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
            for (int movementY = -1; movementY < 2; movementY++) {
                for (int movementX = -1; movementX < 2; movementX++) {

                    if (movementX == 0 && movementY == 0) {
                        continue;
                    }

                    moveY = yNow + movementY;
                    moveX = xNow + movementX;
                    //System.out.println("Move coordinates. moveY: " + moveY + " moveX: " + moveX);
                    if(moveY < 0 || moveX < 0 || moveX >= xLength || moveY >= yLength) {
                        continue;
                    }
                    System.out.println("Move coordinates. moveY: " + moveY + " moveX: " + moveX);

                    System.out.println("After limit check: " + " moveY: " + moveY + " moveX: " + moveX);

                    if (dropTheCourse[yNow][xNow] == 0) {
                        continue;
                    }
                    System.out.println("Number in array pos: " + dropTheCourse[yNow][xNow] + " pos on array Y: " + yNow + " X: " + xNow);

                    //System.out.println("Did we get past if thingies?");
                    //int distanceNow = distance[moveY][moveX];
                    int distanceNext = currentNode.getDistance() + 1;

                    if (distanceNext < distance[moveY][moveX]) {
                        distance[moveY][moveX] = distanceNext;
                        Node pushNode = new Node(moveY, moveX, distanceNext, currentNode);
                        pq.add(pushNode);
                    }
                }
            }

        }

    }

    public void setRoute(Node route) {
        this.routeFinal = route;

    }

    public ArrayList<Node> getRoute() {
        return routeNodes;
    }

    public void printRoute() {
        for(Node node : routeNodes) {
            System.out.println(node);
        }
    }
}