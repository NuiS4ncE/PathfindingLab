package PathfindingLab.utils;

public class Node implements Comparable<Node> {
    private double distance;
    private Node prevNode;
    private int x;
    private int y;

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


    public Node(int x, int y, double distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    public Node(int x, int y, double distance, Node prevNode) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.prevNode = prevNode;
    }

    public void clearNode() {
        this.prevNode = null;
        this.x = 0;
        this.y = 0;
        this.distance = 0;
    }

    public double getDistance() {
        return this.distance;
    }

    public Node getPrevNode() {
        return this.prevNode;
    }

    @Override
    public int compareTo(Node node) {
        int compare = Double.compare(this.distance, node.distance);
        if(compare == 0) compare = node.compareTo(this.prevNode);
        return compare;
    }

    @Override
    public String toString() {
        return "X: " + this.y + " Y: " + this.x + " Distance: " + this.distance;
    }
}
