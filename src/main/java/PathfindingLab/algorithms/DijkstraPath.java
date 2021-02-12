package PathfindingLab.algorithms;

import PathfindingLab.io.IOImg;
import PathfindingLab.utils.Node;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.PriorityQueue;

public class DijkstraPath {

    IOImg ioImg;
    boolean solved;
    boolean truthTable[][];
    int[][] distance;
    BufferedImage buffImg;
    int[][] dropTheCourse;

    /**
     * Constructor for the class
     */
    public DijkstraPath() {
        ioImg = new IOImg();

    }

    /**
     *
     * @param startY Integer parameter for starting point for Y coordinates
     * @param startX Integer parameter for starting pont for X coordinates
     * @param endY Integer parameter for ending point for Y coordinate
     * @param endX Integer parameter for ending point for X coordinates
     * @throws IOException
     */
    public void DPathFind(int startY, int startX, int endY, int endX) throws IOException {
        buffImg = ioImg.getBuffImg();
        buffImg = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        startX = 0;
        startY = 0;
        endX = 3;
        endY = 3;
        Node startNode = new Node(startY, startX);

        dropTheCourse = new int[][] {
                {1,1,0},
                {0,1,1}
        };
        distance = new int[dropTheCourse.length][dropTheCourse.length];
        truthTable = new boolean[dropTheCourse.length][dropTheCourse.length];
        for (int i = 0; i < dropTheCourse.length; i++) {
            for (int j = 0; j < dropTheCourse.length; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        distance[startY][startX] = 0;
        pq.add(startNode);
       while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int yNow = currentNode.getY();
            int xNow = currentNode.getX();
            if(truthTable[yNow][xNow]) continue;
            if(xNow == endX && yNow == endY) {
                System.out.println("Dijkstra completed successfully!");
                return;
            }
            truthTable[yNow][xNow] = true;
            for(int movementY = -1; movementY < 2; movementY++) {
                for (int movementX = -1; movementX < 2; movementX++) {

                    if(movementX == 0 && movementY == 0) {
                        continue;
                    }

                    int moveY = yNow + movementY;
                    int moveX = xNow + movementX;

                    if(dropTheCourse[yNow][xNow] == 0) {
                        continue;
                    }
                    //int distanceNow = distance[moveY][moveX];
                    int distanceNext = currentNode.getDistance() + 1;
                    if (distanceNext < distance[moveY][moveX]){
                        distance[moveY][moveX] = distanceNext;
                        Node pushNode = new Node(moveY, moveX, distanceNext, currentNode);
                        pq.add(pushNode);
                    }
                }
            }

        }

    }
}