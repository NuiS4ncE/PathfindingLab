package PathfindingLab.utils;

public class Node implements Comparable<Node>{
    private int distance;
    private int prevNode;
    private int x;
    private int y;

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


    public Node(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public Node(int y, int x, int distance, int prevNode) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.prevNode = prevNode;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getPrevNode() {
        return this.prevNode;
    }

    @Override
    public int compareTo(Node node) {
        return this.distance - node.distance;
    }
}