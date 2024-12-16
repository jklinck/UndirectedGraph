import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class UndirectedGraph<K, V>{
      private Map<K, List<V>> graph;
      int size;

      /**
      * Default consructor, creates a new graph and allows user to choose their
      * data type as well as setting the size to zero.
      */
      public UndirectedGraph(){
        this.graph = new HashMap<>();
        this.size = 0;
      }

      /**
      * Adds a vertex to the graph and also increments the size of the graph.
      * @param key - K
      */
      public void addVertex(K key){
        this.graph.putIfAbsent(key, new ArrayList<>());
        this.size++;
      }

      /**
      * Connects two vertices in the graph.
      * @param key - K
      * @param value - V
      */
      public void connectVertices(K key, V value){
        this.graph.get(key).add(value);

        for(List<V> adjacentVertices: this.graph.values()){
          adjacentVertices.add(value);
        }
      }

      /**
      * Returns the size of the graph.
      */
      public int getSize(){
        return this.size;
      }

      /**
      * Prints all vertices in the graph. The purpose of the boolean isFirst 
      * exists to prevent a comma from being printed before the first key. 
      */
      public void printAllVertices() {
        System.out.print("[");
        boolean isFirst = true;
        for (K key : this.graph.keySet()) {
            if (!isFirst) {
                System.out.print(", ");
            }
            System.out.print(key);
            isFirst = false;
        }
        System.out.println("]");
      }

      /**
      * Deltes a vertex from the graph and also deletes it from all 
      * adjacency lists. Also decrements the size of the graph.
      * @param key - K
      */
      public void deleteVertex(K key){
        graph.remove(key);
        this.size--;

        // removes the vertex from the adjacency lists of other vertices
        for(List<V> adjacentVertices: this.graph.values()){
          adjacentVertices.remove(key);
        }
      }

      /**
      * Performs a breadth first search on the graph and prints it out.
      */
      public void breadthFirstSearch(UndirectedGraph myGraph){
        
      }

      /*
      * Performs a depth first search on the graph and prints it out.
      */
      public void depthFirstSearch(UndirectedGraph myGraph){
        
      }

      /**
      * @param mygraph - UndirectedGraph 
      * Throws an exception if a user attempts to perfrom a topological search on 
      * the graph as topological search cannot be perfromed on an undirected acyclic 
      * graph.
      */
      public void topologicalSearch(){
        throw new UnsupportedOperationException("You cannot perfrom topological search on an undirected graph.");
      }
}


