import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MostSimilar {

	List<House> houses;
	House mostSimilarHouse;
	Graph g;
	
	int beds;
	int baths;
	int squareFootage;
	
	double scaleBeds;
	double scaleBaths;
	
	public MostSimilar() {
		
		//Get location
		Scanner sc = new Scanner(System.in);
		System.out.println("In order to predict the price of your house, we first need some info.");
		System.out.println("Enter Location in the form of 'city, state'. i.e. Philadelphia, PA");
		String location = sc.nextLine();
		
		//Collect Data
		System.out.println("Collecting Data");
		DataCollection data = new DataCollection(location);
		this.houses = data.getTrainingData();
		
		//Get Bed Info
		System.out.println("Enter Number of Beds");
		this.beds = sc.nextInt();
		
		//Get Bath info
		System.out.println("Enter Number of Baths");
		this.baths = sc.nextInt();
		
		//Get Square Footage Info
		System.out.println("Enter Square Footage number");
		this.squareFootage = sc.nextInt();
		
		
		double avgSize = data.getAverageSizeHouse();
		this.scaleBeds = avgSize / data.getAverageBeds();
		this.scaleBaths = avgSize / data.getAverageBaths();
		
		
		sc.close();
		
		createGraph();
		System.out.println(mostSimilar());
		
	}
	
	double mostSimilar() {
		double minDistance = Double.MAX_VALUE;
		double similarPrice = 0;
		int index = 0;
		
		for (int i = 3; i < houses.size(); i++) {
			double bed = g.getWeight(0, i);
			double bath = g.getWeight(1, i);
			double sqrF = g.getWeight(2, i);
			
			double euclidianDist = Math.pow((bed - (this.beds * scaleBeds)), 2) +
								Math.pow((bath - (this.baths * scaleBaths)), 2) + 
								Math.pow((sqrF - this.squareFootage), 2);
			
			if (euclidianDist < minDistance) {
				minDistance = euclidianDist;
				similarPrice = houses.get(i - 3).getPrice();
				index = i;
			}
			
		}		
		
		//closure
		g.addEdge(index, g.getSize() - 1, 1);
		mostSimilarHouse = houses.get(index - 3);
		
		return similarPrice;
	}
	
	void createGraph() {
		
		//Three nodes for the feature side and the houses size 
		//for the social size (prices) and the 1 is for the new node in the graph
		this.g = new Graph (3 + houses.size() + 1);
		
		//First three nodes will represent the features bed, baths, and sqr. footage
		for (int i = 3; i < houses.size(); i++) {
			//bed
			g.addEdge(0, i, (int) (houses.get(i - 3).getBeds() * scaleBeds));
			
			//bath
			g.addEdge(1, i, (int) (houses.get(i - 3).getBaths() * scaleBaths));
			
			//sqr footage
			g.addEdge(2, i, houses.get(i - 3).getSquareFootage());
			
		}
		
		
	}
	
	House getMostSimilarHouse() {
		return mostSimilarHouse;
	}
	
	public static void main(String args[]) {
		new MostSimilar();
    }
    
	
	
}
