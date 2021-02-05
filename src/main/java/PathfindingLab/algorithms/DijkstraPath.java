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

    public DijkstraPath() {
        ioImg = new IOImg();
    }

    public void DPathFind(int startX, int startY, int endX, int endY) throws IOException {
        BufferedImage buffImg = ioImg.getBuffImg();
        buffImg = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        Node startNode = new Node(startX, startY);
        pq.add(startNode);
        for (int i = 1; i < buffImg.getHeight(); i++) {
            for (int j = 1; j < buffImg.getWidth(); j++) {
                truthTable = new boolean[i][j];
            }
        }
            while (!pq.isEmpty()) {
                startNode = pq.poll();

            }

        }
}