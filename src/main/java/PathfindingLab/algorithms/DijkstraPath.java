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
    BufferedImage buffImg;

    public DijkstraPath() {
        ioImg = new IOImg();
    }

    public void DPathFind(int startY, int startX, int endY, int endX) throws IOException {
        buffImg = ioImg.getBuffImg();
        buffImg = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        Node startNode = new Node(startY, startX);
        pq.add(startNode);

        //truthTable marks visited coordinates
        for (int i = 1; i < buffImg.getHeight(); i++) {
            for (int j = 1; j < buffImg.getWidth(); j++) {
                truthTable = new boolean[i][j];
            }
        }
            while (!pq.isEmpty()) {
                Node currentNode = pq.poll();
                if(truthTable[currentNode.getX()][currentNode.getY()]) continue;
                if(currentNode.getX() == endX && currentNode.getY() == endY) {
                    System.out.println("Dijkstra completed successfully!");
                }
                //for()
            }

        }
}