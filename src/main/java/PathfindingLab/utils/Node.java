package PathfindingLab.utils;

public class Node implements Comparable<Node>{
    private int distance;
    private Node prevNode;
    private int x;
    private int y;

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


    public Node(int y, int x, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    public Node(int y, int x, int distance, Node prevNode) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.prevNode = prevNode;
    }

    public int getDistance() {
        return this.distance;
    }

    public Node getPrevNode() {
        return this.prevNode;
    }

    @Override
    public int compareTo(Node node) {
        return this.distance - node.distance;
    }

    @Override
    public String toString() {
        return "Y: " + this.y + " X: " + this.x + " Distance: " + this.distance;
    }
}
