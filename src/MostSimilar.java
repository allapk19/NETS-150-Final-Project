import java.util.Scanner;

public class MostSimilar {

	House[] houses;
	int beds;
	int baths;
	int squareFootage;
	
	public MostSimilar() {
		
		//Get location
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Location");
		String location = sc.nextLine();
		
		//Collect Data
		System.out.println("Collecting Data");
		DataCollection data = new DataCollection();
		this.houses = data.getTrainingData(location);
		
		//Get Bed Info
		System.out.println("Enter Number of Beds");
		this.beds = sc.nextInt();
		
		//Get Bath info
		System.out.println("Enter Number of Baths");
		this.baths = sc.nextInt();
		
		//Get Square Footage Info
		System.out.println("Enter Square Footage number");
		this.squareFootage = sc.nextInt();
		
		sc.close();
		
		calculateMostsimilar();
		
	}
	
	void calculateMostsimilar() {
		
		for (House house: houses) {
	        throw new UnsupportedOperationException("TODO: implement");
		}
	}
	
	
	public static void main(String args[]) {
		new MostSimilar();
    }
    
	
	
}
