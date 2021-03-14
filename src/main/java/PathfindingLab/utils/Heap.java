package PathfindingLab.utils;

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
        if (minHeap[i].compareTo(minHeap[smallestChild]) > 0 || minHeap[1] == null) {
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
        while (current > 1 && (minHeap[current].compareTo(minHeap[parent(current)]) < 0)) {
            swapNodes(current, parent(current));
            current = parent(current);
        }
    }

    public Node poll() {
        Node polled = minHeap[firstEl];
        minHeap[firstEl] = minHeap[lastEl];
        lastEl--;
        if (!isEmpty()) minHeapify(firstEl);
        return polled;
    }

    public boolean isEmpty() {
        if (lastEl > 0) {
            return false;
        } else {
            return true;
        }
    }

}