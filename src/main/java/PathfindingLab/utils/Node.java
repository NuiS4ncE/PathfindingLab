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
        //System.out.println("This.distance: " + this.distance + " node.distance: " + node.distance);
        /*int compare = Double.compare(this.distance, node.distance);
        if(compare == 0) compare = node.compareTo(this.prevNode);
        return compare; */
        if((this.distance - node.distance) > 0) {
            return 1;
        } else if((this.distance - node.distance) < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "X: " + this.x + " Y: " + this.y + " Distance: " + this.distance;
    }
}
