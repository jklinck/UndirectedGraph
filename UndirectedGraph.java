// Interface imports
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.Queue;

// Class imports
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Collections;

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
      * Adds a vertex to the graph and also increments the size of the graph. If 
      * the graph is empty it will assign the key being added as the firstKey. We 
      * need the first key for use as a starting point of the BFS and DFS methods. 
      * 
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
      * This was my original version, but thought it would be better 
      * to return the sorted graph instead of printing it. 
      */
      // public void breadthFirstSearch(){
      //   HashSet<K> seen = new HashSet<>();
      //   Queue<K> queue = new LinkedList<>();

      //   queue.add(firstKey);

      //   while(!queue.isEmpty()){
      //     K current = queue.poll();
      //     if(!seen.contains(current)){
      //       seen.add(current);
      //       System.out.printf("%s ", current);
      //     }

      //     for(V adjacent : this.graph.get(current)){
      //       if(!seen.contains((K)adjacent)){
      //         queue.add((K)adjacent);
      //       } 
      //     }
      //   }
      // }

      /**
       * Performs a breadth first search on the graph and returns it. 
       * I used a LinkedHashSet to maintain the order of vertices added.
       */
      public LinkedHashSet<K> breadthFirstSearch(){
        LinkedHashSet<K> seen = new LinkedHashSet<>();
        Queue<K> queue = new LinkedList<>();

        queue.add(firstKey);

        while(!queue.isEmpty()){
          K current = queue.poll();
          if(!seen.contains(current)){
            seen.add(current);
            // System.out.printf("%s ", current);
          }

          for(V adjacent : this.graph.get(current)){
            if(!seen.contains((K)adjacent)){
              queue.add((K)adjacent);
            } 
          }
        }
        return seen;
      }

      /**
      * Depth first search recursive
      * @param key - K
      * @param seen - HashSet
      */
      public void depthFirstSearchUtil(K key, HashSet<K> seen){
        seen.add(key);
        // System.out.printf("%s ", key);
      
        for(V value: this.graph.get(key)){
          if(!seen.contains((K)value)){
              depthFirstSearchUtil((K)value, seen);
          }
        }
      }
      public LinkedHashSet<K> depthFirstSearchRecursive(K key){
          LinkedHashSet<K> seen = new LinkedHashSet<>();
          depthFirstSearchUtil(key, seen);

          return seen;
      }

      /**
       * Depth first search iterative
       * @param startKey  - K
       */
      // public void depthFirstSearchIterative(K startKey){
      //   Set<K> seen = new HashSet<>(); // To keep track of visited nodes
      //   Stack<K> stack = new Stack<>(); // Stack for DFS

      //   // Push the starting node onto the stack
      //   stack.push(startKey);

      //   while (!stack.isEmpty()) {
      //       // Pop a node from the stack
      //       K current = stack.pop();

      //       // If the node has not been seen yet
      //       if (!seen.contains(current)) {
      //           // Mark it as seen
      //           seen.add(current);
      //           // Process the current node (optional)
      //           System.out.printf("%s ", current);

      //           // Get the adjacent nodes and push them onto the stack
      //           List<V> neighbors = graph.get(current);
      //           if (neighbors != null) {
      //               // Push neighbors in reverse order to maintain the correct order
      //               for (int i = neighbors.size() - 1; i >= 0; i--) {
      //                   V value = neighbors.get(i);
      //                   if (!seen.contains(value)) {
      //                       stack.push((K) value);
      //                   }
      //               }
      //           }

      //           // another way to perform the loop above
      //           // List<V> neighbors = graph.get(current);
      //           // Collections.reverse(neighbors);
      //           // for(V value: neighbors){
      //           //     if (!seen.contains(value)) {
      //           //             stack.push((K) value);
      //           //     }
      //           // }
      //       }
      //   }
      // }

      public LinkedHashSet<K> depthFirstSearchIterative(K startKey){
        LinkedHashSet<K> seen = new LinkedHashSet<>(); // To keep track of visited nodes
        Stack<K> stack = new Stack<>(); // Stack for DFS

        // Push the starting node onto the stack
        stack.push(startKey);

        while (!stack.isEmpty()) {
            // Pop a node from the stack
            K current = stack.pop();

            // If the node has not been seen yet
            if (!seen.contains(current)) {
                // Mark it as seen
                seen.add(current);
                // Process the current node (optional)
                // System.out.printf("%s ", current);

                // Get the adjacent nodes and push them onto the stack
                List<V> neighbors = graph.get(current);
                if (neighbors != null) {
                    // Push neighbors in reverse order to maintain the correct order
                    for (int i = neighbors.size() - 1; i >= 0; i--) {
                        V value = neighbors.get(i);
                        if (!seen.contains(value)) {
                            stack.push((K) value);
                        }
                    }
                }

                // another way to perform the loop above
                // List<V> neighbors = graph.get(current);
                // Collections.reverse(neighbors);
                // for(V value: neighbors){
                //     if (!seen.contains(value)) {
                //             stack.push((K) value);
                //     }
                // }
            }
        }
        return seen;
      }

      

      /**
      * Throws an exception if a user attempts to perfrom a topological search on 
      * the graph as topological search cannot be perfromed on an undirected acyclic 
      * graph.
      * @param mygraph - UndirectedGraph 
      */
      public void topologicalSearch(){
        throw new UnsupportedOperationException("You cannot perfrom topological search on an undirected graph.");
      }
}


