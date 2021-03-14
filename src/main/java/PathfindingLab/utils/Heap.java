package PathfindingLab.utils;

import java.util.Arrays;

public class Heap {

    private Node[] minHeap;
    private int lastEl;
    private int maxSize;
    private static final int firstEl = 1;
    int pollCounter = 0;

    public Heap(int maxSize) {
        this.maxSize = maxSize;
        this.lastEl = 0;
        minHeap = new Node[this.maxSize + 1];
    }

    private int parent(int i) {
        return i / 2;
    }

    private int leftChild(int i) {
        return (2 * i);
    }

    private int rightChild(int i) {
        return (2 * i) + 1;
    }

    private boolean isLeaf(int i) {
        if (i >= (lastEl / 2) && i <= lastEl) {
            return true;
        }
        return false;
    }

    private void swapNodes(int firstPos, int secondPos) {
        Node temp;
        temp = minHeap[firstPos];
        minHeap[firstPos] = minHeap[secondPos];
        minHeap[secondPos] = temp;
    }

    private void minHeapify(int i) {
        if (isLeaf(i)) return;
        int leftChildVal = leftChild(i);
        int rightChildVal = rightChild(i);

        int smallestChild = leftChildVal;
        if (rightChildVal <= lastEl && minHeap[rightChildVal].compareTo(minHeap[leftChildVal]) < 0) {
            smallestChild = rightChildVal;
        }
        if(minHeap[i].compareTo(minHeap[smallestChild]) > 0 || minHeap[1] == null) {
            swapNodes(i, smallestChild);
            minHeapify(smallestChild);
        }
    }

    public void add(Node node) {
        if (lastEl >= maxSize) {
            return;
        }
        minHeap[++lastEl] = node;
        int current = lastEl;
        int counter = 0;
            while (current > 1 && (minHeap[current].compareTo(minHeap[parent(current)]) < 0)) {
                swapNodes(current, parent(current));
                current = parent(current);
                //System.out.println("This loop has been done: " + counter++ + " times.");
            }
        setMinHeap();
    }

    public Node poll() {
        Node polled = minHeap[firstEl];
        minHeap[firstEl] = minHeap[lastEl];
        lastEl--;
        if(!isEmpty()) minHeapify(firstEl);
        return polled;
    }

    public void setMinHeap() {
        for (int pos = (lastEl / 2); pos >= 1; pos--) {
            minHeapify(pos);
        }
    }

    public Node lastNode() {
        Node lastNode = new Node(0,0,0);
        for(int i = minHeap.length - 1; i > 0; i--) {
            if(minHeap[i] != null) {
                lastNode =  minHeap[i];
                break;
            }
        }
        return lastNode;
    }

    public boolean isEmpty() {
        if(lastEl > 0) {
            return false;
        } else {
            return true;
        }
    }

    public void printHeap() {
        for (int i = 1; i <= lastEl / 2; i++ ) {
            System.out.print(" PARENT : " + minHeap[i] + " LEFT CHILD : " + minHeap[2*i]
                    + " RIGHT CHILD :" + minHeap[2 * i  + 1]);
            System.out.println();
        }
    }

}
