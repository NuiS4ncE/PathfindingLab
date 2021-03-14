package PathfindingLab.utils;

import java.util.Arrays;

public class Heap {

    private Node[] minHeap;
    private int lastEl;
    private int maxSize;
    private static final int firstEl = 1;
    int pollCounter = 0;
    //private int lastEl;

    public Heap(int maxSize) {
        this.maxSize = maxSize;
        this.lastEl = 0;
        minHeap = new Node[this.maxSize + 1];
        //minHeap[0] = null;
    }

    private int parent(int i) {
        //System.out.println("Parent: " + i/2);
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

        System.out.println("minHeap in minHeapify: " + Arrays.toString(minHeap));
        int smallestChild = leftChildVal;
        if (rightChildVal <= lastEl && minHeap[rightChildVal].compareTo(minHeap[leftChildVal]) < 0) {
            smallestChild = rightChildVal;
        }
        if(minHeap[i].compareTo(minHeap[smallestChild]) > 0 || minHeap[1] == null) {
            swapNodes(i, smallestChild);
            minHeapify(smallestChild);
        }
        //System.out.println("minHeap in minHeapify: " + Arrays.toString(minHeap));
        /*if(size > 1) {
            if (!isLeaf(i)) {
                if (minHeap[i].compareTo(minHeap[leftChild(i)]) > 0
                        || minHeap[i].compareTo(minHeap[rightChild(i)]) > 0) {
                    if (minHeap[leftChild(i)].compareTo(minHeap[rightChild(i)])< 0) {
                        swapNodes(i, leftChild(i));
                        minHeapify(leftChild(i));
                    } else {
                        swapNodes(i, rightChild(i));
                        minHeapify(rightChild(i));
                    }
                }
            }
        }*/
    }

    public void add(Node node) {
        //System.out.println("lastEl: " + lastEl + " maxSize: " + maxSize + " in add");
        //System.out.println("minHeap in add beginning: " + Arrays.toString(minHeap));
        /*if(size == 0) { //This screws up the system
            minHeap[size] = node;
            size++;
            return;
        }*/
        if (lastEl >= maxSize) {
            return;
        }
        minHeap[++lastEl] = node;
        int current = lastEl;
        int counter = 0;
        //System.out.println("Size in add: " + size + " current in add: " + current);
        //System.out.println("minHeap after adding: " + Arrays.toString(minHeap) + " Current: " + current);
        //if(minHeap[parent(current)] != null) {
        //current > 1 &&
        //System.out.println((minHeap[current].compareTo(minHeap[parent(current)]) < 0));
            while (current > 1 && (minHeap[current].compareTo(minHeap[parent(current)]) < 0)) {
                swapNodes(current, parent(current));
                current = parent(current);
                //System.out.println("This loop has been done: " + counter++ + " times.");
            }
        //}
        setMinHeap();
    }

    public Node poll() {
        if(minHeap.length == 1) {
            Node polled = minHeap[firstEl];
            return polled;
        }
        //System.out.println("lastEl in poll: " + lastEl);
        Node polled = minHeap[firstEl];
        if(lastEl <= 0){
            //System.out.println("minHeap: " + Arrays.toString(minHeap));
            //return minHeap[3];
            return lastNode();
        }
        if(minHeap.length == 0) return polled;
        if(minHeap[--lastEl] == null) return polled;
        minHeap[firstEl] = minHeap[--lastEl];
        minHeapify(firstEl);
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

    public void printHeap() {
        for (int i = 1; i <= lastEl / 2; i++ ) {
            System.out.print(" PARENT : " + minHeap[i] + " LEFT CHILD : " + minHeap[2*i]
                    + " RIGHT CHILD :" + minHeap[2 * i  + 1]);
            System.out.println();
        }
    }
/*
    public Heap(int heapSize) {
        minHeap = new Node[heapSize + 1];
        lastEl = 0;
    }


    public Node min() {
        return minHeap[firstEl];
    }

    public void add(Node node) {
        System.out.println("lastEl beginning of add: " + lastEl);
        lastEl = lastEl + 1;
        int currentEl = lastEl;
        System.out.println("currentEl in add: "+ currentEl);
        System.out.println();
        if(currentEl == 1) minHeap[currentEl] = node;
        while ((currentEl > 1) && (node.compareTo(minHeap[parentNode(currentEl)]) < 0)) {
            minHeap[currentEl] = minHeap[parentNode(currentEl)];
            currentEl = parentNode(currentEl);
            minHeap[currentEl] = node;
            System.out.println("While loop done");
        }
    }

    public Node poll() {
        System.out.println(Arrays.toString(minHeap));
        Node node = minHeap[firstEl];
        System.out.println(firstEl);
        System.out.println(node.toString());
        System.out.println();
        minHeap[firstEl] = minHeap[lastEl];
        lastEl = lastEl - 1;
        siftDown(firstEl);
        return node;
    }

    public void siftDown(int i) {
        int smallerChild;
        if(minHeap.length == 1) return;
        if (left(i) == 0) return;
        else if (left(i) == lastEl) smallerChild = left(i);
        else {
            if (minHeap[left(i)].compareTo(minHeap[right(i)]) < 0) smallerChild = left(i);
            else smallerChild = right(i);
        }
        if (minHeap[i].compareTo(minHeap[smallerChild]) > 0) {
            swapNodes(i, smallerChild);
            siftDown(smallerChild);
        }
    }

    private void swapNodes(int firstPos, int secondPos) {
        Node temp;
        temp = minHeap[firstPos];
        minHeap[firstPos] = minHeap[secondPos];
        minHeap[secondPos] = temp;
    }

    public void makeHeap(Node[] minHeap) {
        lastEl = minHeap.length;
        int lastWithChild = parentNode(lastEl);
        for(int i = lastWithChild; i > 1; i--) {
            siftDown(i);
        }
    }
    public int left(int i) {
        if (2 * i > lastEl) return 0;
        return 2 * i;
    }

    public int right(int i) {
        if(2*i + 1 > lastEl) return 0;
        return (2 * i) + 1;
    }

    private int parentNode(int i) {
        return i / 2;
    }
*/
}
