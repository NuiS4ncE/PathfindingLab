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
    float[][] distance;
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
        Node startNode = new Node(startY, startX);

        dropTheCourse = new int[][] {
                {1,1,0},
                {0,1,1}
        };
        distance = new float[dropTheCourse.length][dropTheCourse.length];
        for (int i = 1; i < dropTheCourse.length; i++) {
            for (int j = 1; j < dropTheCourse.length; j++) {
                distance[i][j] = Float.MAX_VALUE;
            }
        }
        distance[startY][startX] = 0;
        pq.add(startNode);
       /* while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int yNow = currentNode.getY();
            int xNow = currentNode.getX();
            if(truthTable[currentNode.getX()][currentNode.getY()]) continue;
            if(yNow == endY && xNow == endX) return;
            if(currentNode.getX() == endX && currentNode.getY() == endY) {
                System.out.println("Dijkstra completed successfully!");
            }

        } */

    }
}