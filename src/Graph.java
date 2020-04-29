import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;


public class Graph {
    
    //KEY, WEIGHT
    List<HashMap<Integer, Integer>> adjList;
    int size;
    
    public Graph(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Graph size can't be less than or equal to 0");
        }
        
        adjList = new ArrayList<HashMap<Integer, Integer>>();
        
        for (int i = 0; i < n; i++) {
            adjList.add(new HashMap<Integer, Integer>());
        }
        
        size = n;
    }

    public int getSize() {
        return size;
    }

   
    public boolean hasEdge(int u, int v) {
        if (u < 0 || u >= getSize() || v < 0 || v >= getSize()) {
            throw new IllegalArgumentException();
        }
        
        return adjList.get(u).get(v) != null;
        
    }

    public int getWeight(int u, int v) {
        if (!hasEdge(u, v)) {
            throw new NoSuchElementException();
        }

        return adjList.get(u).get(v);
        
        
    }

    public boolean addEdge(int u, int v, int weight) {
        if (u < 0 || u >= getSize() || v < 0 || v >= getSize() || u == v) {
            throw new IllegalArgumentException("Edge values not valid");
        }
        
        if (hasEdge(u, v) && getWeight(u, v) == weight) {
            return false;
        }
        
        //Clearly expected O(1)
        adjList.get(u).put(v, weight);
        
        return true;
        
        
    }
    
    public Set<Integer> outNeighbors(int v) {
        if (v < 0 || v >= getSize()) {
            throw new IllegalArgumentException();
        }
        

        return adjList.get(v).keySet();
    }
}