import java.util.Scanner;
import java.util.LinkedHashSet;

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

		/*
		each call below will add the second parameter to the first parameters 
		adjacency list and will also add the first parameter to the second 
		parameters adjacency list
		*/
		myGraph.connectVertices(0, 1);
		myGraph.connectVertices(0, 2);
		myGraph.connectVertices(0, 5);
		myGraph.connectVertices(0, 6);
		myGraph.connectVertices(3, 4);
		myGraph.connectVertices(3, 5);
		myGraph.connectVertices(4, 5);
		myGraph.connectVertices(4, 6);

		/*
		  Adjacency list of the graph created above
		  0: 1,2,5,6
		  1: 0
		  2: 0
		  3: 4,5
		  4: 3,5,6
		  5: 0, 3, 4
		  6: 0, 4
		*/


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
	 * Tests breadthFirstSearch method. 
	 */
	public static void breadthFirstSearchTest(){
		UndirectedGraph<Integer, Integer> myGraph = createGraph();
		System.out.println("Should print: 0 1 2 5 6 3 4");
		System.out.printf("Prints:       ");
		LinkedHashSet<Integer> set = myGraph.breadthFirstSearch();
		for(Integer node: set){
			System.out.printf("%d ", node);
		}

	} 

	/**
	 * Tests depthFirstSearch recursive method. 
	 */
	public static void depthFirstSearchRecursiveTest(){
		UndirectedGraph<Integer, Integer> myGraph = createGraph();
		System.out.println("Should print: 0 1 2 5 3 4 6");
		System.out.printf("Prints:       ");
		myGraph.depthFirstSearchRecursive(myGraph.getFirstKey());
	} 

	/**
	 * Tests depthFirstSearch iterative method. 
	 */
	public static void depthFirstSearchIterativeTest(){
		UndirectedGraph<Integer, Integer> myGraph = createGraph();
		System.out.println("Should print: 0 1 2 5 3 4 6");
		System.out.printf("Prints:       ");
		myGraph.depthFirstSearchIterative(myGraph.getFirstKey());
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
		System.out.println("    2. Test breadth first search.");
		System.out.println("    3. Test depth first search recursive.");
		System.out.println("    4. Test depth first search iterative.");
		System.out.println("    5. Test topologicalSearch exception method.");
		System.out.println("    6. Exit program.");
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
					breadthFirstSearchTest();
					userMenu();
				}
				else if(userSelection == 3){
					depthFirstSearchRecursiveTest();
					userMenu();
				}
				else if(userSelection == 4){
					depthFirstSearchIterativeTest();
					userMenu();
				}
				else if(userSelection == 5){
					testTopologicalSearchException();
				}
				else if(userSelection == 6){
					exit = true;
				}
				else{
					System.out.println("Invalid option, please select a different number.");
					// clear the input buffer
					input.nextLine();
				}
			}
			else{
				System.out.println("Invalid option, please only select a number.");
				// clear the input buffer
				input.nextLine();
				continue;
			}
		}

		input.close();
	}
}


