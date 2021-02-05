package PathfindingLab.utils;

public class Node implements Comparable<Node>{
    private int distance;
    private int node;
    private int x;
    private int y;

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Node(int x, int y, int distance, int node) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.node = node;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getNode() {
        return this.node;
    }

    @Override
    public int compareTo(Node node) {
        return this.distance - node.distance;
    }
}
