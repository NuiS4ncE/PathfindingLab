package PathfindingLab.algorithms;

import PathfindingLab.utils.Heap;
import PathfindingLab.utils.MyList;
import PathfindingLab.utils.Node;
import javafx.scene.layout.Priority;

import java.io.IOException;
import java.util.PriorityQueue;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

//This algorithm doesn't work therefore the class doesn't work either.
public class IDAStar {

    Node startNode, routeFinal, visitedNode;
    MyList<Node> routeNodes, visitedNodes;
    int endX, endY;

    public IDAStar() {

    }

    public boolean idaStar(int[][] map, int startX, int startY, int endX, int endY) {
        Heap pq = new Heap(9999999);
        double t;
        int foundValue = 0;
        boolean truthValue = false;
        this.endX = endX;
        this.endY = endY;
        double heuristic = euclideanDistance(startX, startY, endX, endY);
        Node startNode = new Node(startX, startY, 0);
        pq.add(startNode);
        while(!truthValue) {
            t = search(map, pq, 0, heuristic);
            if(t == heuristic) truthValue = true;
            if(t == Integer.MAX_VALUE) break;
            heuristic = t;
        }
        if(truthValue) {
            System.out.println("IDAStar completed successfully!");
            return true;
        }
        return false;
    }

    public double search(int[][] map, Heap pq, double distance, double heuristic) {
        double t;
        Node currentNode = pq.poll();
        Node succNode = currentNode.getPrevNode();
        double min = Integer.MAX_VALUE;
        int xNow = currentNode.getX();
        int yNow = currentNode.getY();
        int yLength = map[0].length;
        int xLength = map.length;
        double f = distance + euclideanDistance(currentNode.getX(), currentNode.getY(), endX, endY);
        if(f > heuristic) return min;
        if(xNow == endX && yNow == endY) return min;
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

                if (map[xNow][yNow] == 0) {
                    continue;
                }
                while(succNode != null) {
                    pq.add(succNode);
                    t = search(map, pq, distance, heuristic);
                    if(t == 1) return min;
                    routeFinal = pq.poll();
                }

            }
        }
        return min;
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

    public Node getRouteFinal() {
        return routeFinal;
    }


    /**
     * Getter for the route ArrayList
     *
     * @return Returns route in an ArrayList
     */
    public MyList<Node> getRoute() {
        return routeNodes;
    }

    public MyList<Node> getVisitedNodes() {
        return visitedNodes;
    }
    public Node getVisitedNode() {
        return visitedNode;
    }
}
