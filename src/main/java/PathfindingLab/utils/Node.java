package PathfindingLab.utils;

public class Node implements Comparable<Node>{
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


    public Node(int y, int x, double distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    public Node(int y, int x, double distance, Node prevNode) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.prevNode = prevNode;
    }

    public double getDistance() {
        return this.distance;
    }

    public Node getPrevNode() {
        return this.prevNode;
    }

    @Override
    public int compareTo(Node node) {
        if(this.distance - node.distance > 0) {
           return 1;
        } else if (this.distance - node.distance < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Y: " + this.y + " X: " + this.x + " Distance: " + this.distance;
    }
}
