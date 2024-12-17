import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

public class UndirectedGraph<K, V>{
      private Map<K, List<V>> graph;
      private int size;
      private K firstKey;

      /**
      * Default consructor, creates a new graph and allows user to choose their
      * data type as well as setting the size to zero.
      */
      public UndirectedGraph(){
        this.graph = new LinkedHashMap<>();
        this.size = 0;
      }

      /**
      * Adds a vertex to the graph and also increments the size of the graph.
      * @param key - K
      */
      public void addVertex(K key){
        if(this.graph.isEmpty()){
          firstKey = key;
        }
        this.graph.putIfAbsent(key, new ArrayList<>());
        this.size++;
      }

      /**
      * Connects two vertices in the graph. Retrieves the key 
      * paramter and adds value parameter to it's adjacency list. 
      * Then performs the same operation in reverse and retrieves the 
      * value (which is now a key) and add the key (which is now a value) 
      * to it's adjacency list. Casting is performed on the second line since 
      * there is a type issue if you do not since we are treating a parameter of 
      * type V as a key, thus it needs to be casted to type K. The value being 
      * added also needs to be cast as it is of type K and needs to be cast to 
      * type V. While all the vertices will be of the same type, casting is still 
      * necessary since the compiler considers K and V to be different types (even 
      * though after instantiating an UndirectedGraph object they will be the same 
      * type).
      * @param key - K
      * @param value - V
      */
      public void connectVertices(K key, V value){
        this.graph.get(key).add(value);
        this.graph.get((K)value).add((V)key);
      }

      /**
      * Returns the size of the graph.
      */
      public int getSize(){
        return this.size;
      }

      /**
       * Return the first key in the graph.
       */
      public K getFirstKey(){
        return this.firstKey;
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
      public void breadthFirstSearch(){
        HashSet<K> seen = new HashSet<>();
        Queue<K> queue = new LinkedList<>();
        K current = this.firstKey;

        for(K key: this.graph.keySet()){
          if(!seen.contains(key)){
            queue.add(key);
          }
          // for(List<V> adjacentVertices: this.graph.values()){
          //   if(!seen.contains((K)adjacentVertices)){
          //     queue.add((K)adjacentVertices);
          //   }
          // }
        }

        System.out.println("Start: ");
        while(!queue.isEmpty()){
          System.out.println(queue.poll());
        }
        System.out.println("End.");

         /*
        should print [0, 1, 2, 5, 6, 3, 4]
        */
      }

      /*
      * Performs a depth first search on the graph and prints it out.
      */
      public void depthFirstSearch(){
        HashSet<K> seen = new HashSet<>();
        Stack<K> stack = new Stack<>();
        K current = this.firstKey;









      /*
      should print [0, 1, 2, 5, 3, 4, 6]
      */
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


