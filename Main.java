import java.util.Scanner;

public class Main{


	/**
	 * Creates an undirected graph. 
	 */
	public static UndirectedGraph<Integer, Integer> createGraph(){
		UndirectedGraph<Integer, Integer> myGraph = new UndirectedGraph<>();

		myGraph.addVertex(0);
		myGraph.addVertex(1);
		myGraph.addVertex(2);
		myGraph.addVertex(3);
		myGraph.addVertex(4);
		myGraph.addVertex(5);
		myGraph.addVertex(6);

		myGraph.connectVertices(0, 1);
		myGraph.connectVertices(0, 2);
		myGraph.connectVertices(0, 5);
		myGraph.connectVertices(0, 6);

		// myGraph.connectVertices(1, 0);
		// myGraph.connectVertices(2, 0);

		myGraph.connectVertices(3, 4);
		myGraph.connectVertices(3, 5);

		// myGraph.connectVertices(4, 3);
		myGraph.connectVertices(4, 5);
		myGraph.connectVertices(4, 6);

		// myGraph.connectVertices(5, 0);
		// myGraph.connectVertices(5, 3);
		// myGraph.connectVertices(5, 4);

		// myGraph.connectVertices(6, 0);
		// myGraph.connectVertices(6, 4);

		return myGraph;
	}

	/**
	 * Method for testing the undirected graph class and it's methods.
	 */
	public static void testGraph(){
		UndirectedGraph<Integer, Integer> myGraph = createGraph();

		System.out.println();
		myGraph.printAllVertices();
		System.out.println("Should return 7, returns " + myGraph.getSize());
		myGraph.deleteVertex(6);
		System.out.println("Should return 6, returns " + myGraph.getSize());
		myGraph.printAllVertices();
	} 

	/**
	 * Tests topologicalSearch exception method. 
	 */
	public static void testTopologicalSearchException(){
		UndirectedGraph<Integer, Integer> myGraph = createGraph();
		myGraph.topologicalSearch();
	} 

	/**
	 * Selection menu for user.
	 */
	public static void userMenu(){
		System.out.println("\nWhat would you like to test?");
		System.out.println("    1. Test graph methods.");
		System.out.println("    2. Test topologicalSearch exception method.");
		System.out.println("    3. Exit program.");
	}

	public static void main(String[] args){
		int userSelection = -1;
		boolean exit = false;

		Scanner input = new Scanner(System.in);
		userMenu();

		while(!exit){
			if(input.hasNextInt()){
				userSelection = input.nextInt();

				if(userSelection == 1){
					testGraph();
					userMenu();
				}
				else if(userSelection == 2){
					testTopologicalSearchException();
				}
				else if(userSelection == 3){
					exit = true;
				}
				else{
					System.out.println("Invalid option, please make another selection.");
					// clear the input buffer
					input.nextLine();
				}
			}
			else{
				System.out.println("Invalid option, please make another selection.");
				// clear the input buffer
				input.nextLine();
				continue;
			}
		}

		input.close();
	}
}


/*
  This is the graph built above
  0: 1,2,5,6
  1: 0
  2: 0
  3: 4,5
  4: 3,5,6
  5: 0, 3, 4
  6: 0, 4
*/

/*
  This is how you could change the graph above to be 
  undirected and acyclic if you wanted to perform a 
  topological search
  0: 1,2,5
  1: 
  2: 
  3: 
  4: 3,6
  5: 4
  6: 
*/






