package PathfindingLab.utils;

public class Heap {

    private Node[] minHeap;
    private int lastEl;
    private int maxSize;
    private static final int firstEl = 1;

    /**
     * Constructor for the class
     *
     * @param maxSize integer type parameter for maximum size of heap
     */
    public Heap(int maxSize) {
        this.maxSize = maxSize;
        this.lastEl = 0;
        minHeap = new Node[this.maxSize + 1];
    }

    /**
     * Method for getting the parent node in heap
     *
     * @param i integer type parameter for array pointer
     * @return returns value of i / 2
     */
    private int parent(int i) {
        return i / 2;
    }

    /**
     * Method for getting the left child from array
     *
     * @param i integer type parameter for array pointer
     * @return returns value of 2 * i
     */
    private int leftChild(int i) {
        return (2 * i);
    }

    /**
     * Method for getting the right child from array
     *
     * @param i integer type parameter for array pointer
     * @return returns value of 2 * i + 1
     */
    private int rightChild(int i) {
        return (2 * i) + 1;
    }

    /**
     * Checks whether position in array is a leaf
     *
     * @param i integer type parameter for position in array
     * @return returns true, if leaf and false, if not
     */
    private boolean isLeaf(int i) {
        if (i >= (lastEl / 2) && i <= lastEl) {
            return true;
        }
        return false;
    }

    /**
     * Swaps Nodes in array between the first and second parameter
     *
     * @param firstPos  integer type parameter for the first swappable position
     * @param secondPos integer type parameter for the second swappable position
     */
    private void swapNodes(int firstPos, int secondPos) {
        Node temp;
        temp = minHeap[firstPos];
        minHeap[firstPos] = minHeap[secondPos];
        minHeap[secondPos] = temp;
    }

    /**
     * Recursive method to heapify a subtree
     *
     * @param i integer type parameter for the position of root for heapifying
     */
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

    /**
     * Add method for adding Nodes in the heap
     *
     * @param node Node type parameter for adding the Node
     */
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

    /**
     * Polling method for removing and getting the root Node in heap
     *
     * @return returns root Node from heap
     */
    public Node poll() {
        Node polled = minHeap[firstEl];
        minHeap[firstEl] = minHeap[lastEl];
        lastEl--;
        if (!isEmpty()) minHeapify(firstEl);
        return polled;
    }

    /**
     * Checks whether heap is empty
     *
     * @return returns false if lastEl integer value is higher that 0, returns true if not
     */
    public boolean isEmpty() {
        if (lastEl > 0) {
            return false;
        } else {
            return true;
        }
    }

}