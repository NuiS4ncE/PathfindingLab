package PathfindingLab.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Heap {

    private Node[] minHeap;
    private int size;
    private int maxSize;
    private static final int firstEl = 1;

    public Heap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        minHeap = new Node[this.maxSize + 1];
        //minHeap[0] = null;
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
        if (i >= (size / 2) && i <= size) {
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
        if(minHeap[leftChild(i)] != null) {
            if (!isLeaf(i)) {
                if (minHeap[i].compareTo(minHeap[leftChild(i)]) == 1
                        || minHeap[i].compareTo(minHeap[rightChild(i)]) == 1) {
                    if (minHeap[leftChild(i)].compareTo(minHeap[rightChild(i)]) == 1) {
                        swapNodes(i, leftChild(i));
                        minHeapify(leftChild(i));
                    } else {
                        swapNodes(i, rightChild(i));
                        minHeapify(rightChild(i));
                    }
                }
            }
        }
    }

    public void add(Node node) {
        if (size >= maxSize) {
            return;
        }
        minHeap[++size] = node;
        int current = size;
        int counter = 0;
        System.out.println("GODDAMMIT" + " " + Arrays.toString(minHeap));
        //if(minHeap[parent(current)] != null) {
            while (minHeap[current].compareTo(minHeap[parent(current)]) > 0 || minHeap[parent(current)] == null) {
                swapNodes(current, parent(current));
                current = parent(current);
                System.out.println("This loop has been done: " + counter++ + " times.");
            }
        //}
        //setMinHeap();
    }

    public Node poll() throws NoSuchElementException {
        System.out.println(Arrays.toString(minHeap));
        Node polled = minHeap[firstEl];
        minHeap[firstEl] = minHeap[size--];
        minHeapify(firstEl);
        return polled;
    }

    public void setMinHeap() {
        for (int pos = (size / 2); pos >= 1; pos--) {
            minHeapify(pos);
        }
    }


    public void printHeap() {
        for (int i = 1; i <= size / 2; i++ ) {
            System.out.print(" PARENT : " + minHeap[i] + " LEFT CHILD : " + minHeap[2*i]
                    + " RIGHT CHILD :" + minHeap[2 * i  + 1]);
            System.out.println();
        }
    }

}
