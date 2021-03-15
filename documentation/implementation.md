## Implementation 


## Structure 

The application consists of packages `algorithms`, `ui`, `io` and `utils`. 

`algorithms` package consists of classes `AStar`, `DijkstraPath` and `IDAStar`. Each 
algorithm class handles their algorithmic task. 

`ui` package consists of classes `GUI` and `UIMain`. `GUI` handles the 
the rendering of the user interface and actions within it while `UIMain` is used for running the 
`GUI` class.

`io` package consists of class `IOImg`. `IOImg` handles the 
conversion of images to arrays with 0 and 1 values for pathfinding purposes. 

`utils` package consists of `Heap`, `MyList` and `Node`. `Heap` is the implementation 
of a minimum heap data structure. `MyList` is the 
implementation of an ArrayList data structure. `Node` is the object 
for saving the nodes for the algorithms. 

## Time and space complexities 

