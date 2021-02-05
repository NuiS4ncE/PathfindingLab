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

    public DijkstraPath() {
        ioImg = new IOImg();
        //truthTable marks visited coordinates
        dropTheCourse = new int[][] {
                {1,1,0},
                {0,1,1}
        };
        for (int i = 1; i < dropTheCourse.length; i++) {
            for (int j = 1; j < dropTheCourse.length; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    /**
     *
     * @param startY Starting point for Y coordinates
     * @param startX Starting pont for X coordinates
     * @param endY Ending point for Y coordinate
     * @param endX Ending point for X coordinates
     * @throws IOException
     */
    public void DPathFind(int startY, int startX, int endY, int endX) throws IOException {
        buffImg = ioImg.getBuffImg();
        buffImg = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        startX = 0;
        startY = 0;
        Node startNode = new Node(startY, startX);
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