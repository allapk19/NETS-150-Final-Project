import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DataCollection {
    
    private String baseURL = "https://www.trulia.com/";
    private Document currentDoc;
    
    ArrayList<House> list;
    
    public DataCollection(String location)  {
        try {
            String city = location.substring(0, location.indexOf(","));
            String state = location.substring(location.indexOf(",") + 2).toUpperCase();
            this.baseURL = this.baseURL + state + "/" + city + "/";
            this.currentDoc = Jsoup.connect(this.baseURL).get();
        } catch (IOException e) {
            System.out.println("Invalid Location");
        }
    }
    
    public ArrayList<House> getTrainingData() {
        list = new ArrayList<House>();
        
        int pages;
        
        try {
        	String pagesStr = this.currentDoc.select("li[data-testid='pagination-page-link']").last().text();
            pages = Integer.parseInt(pagesStr);
        } catch (Exception e) {
        	System.out.println ("Invalid Location");
        	return null;
        }
        
        for (int i = 1; i <= pages; i++) {
            Document housesPage = null;
            try {
                housesPage = Jsoup.connect(this.baseURL + i + "_p").get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements houses = housesPage.select("div[data-testid='home-card-sale']");
            for (Element house: houses) {
                double price = 0;
                int beds = 0;
                int baths = 0;
                int squareFootage = 0;
                
                try {
                    String priceStr = house.selectFirst("div[data-testid='property-price']").text();
                    Matcher m = Pattern.compile("\\$[0-9]+(,[0-9]+)*\\.?[0-9]*").matcher(priceStr);
                    if (m.find()) {
                        price = Double.parseDouble(m.group().replace(",", "").replace("$", ""));
                    }
                    
                    String bedsStr = house.selectFirst("div[data-testid='property-beds']").text();
                    Matcher m2 = Pattern.compile("[0-9]+").matcher(bedsStr);
                    if (m2.find()) {
                        beds = Integer.parseInt(m2.group());
                    }
                    
                    String bathsStr = house.selectFirst("div[data-testid='property-baths']").text();
                    Matcher m3 = Pattern.compile("[0-9]+").matcher(bathsStr);
                    if (m3.find()) {
                        baths = Integer.parseInt(m3.group());
                    }
                    
                    String squareFootStr = house.selectFirst("div[data-testid='property-floorSpace']").text();
                    Matcher m4 = Pattern.compile("[0-9]+(,[0-9]+)*").matcher(squareFootStr);
                    if (m4.find()) {
                        squareFootage = Integer.parseInt(m4.group().replace(",", ""));
                    }
                    //House test = new House(beds, baths, squareFootage, price);
                    //System.out.println(test);
                    list.add(new House(beds, baths, squareFootage, price));
                } catch (NullPointerException e) {
                    //System.out.println("House does not have sufficient data");
                }
            }
        }
        return list;
    }
    
    
    public double getAverageSizeHouse() {
    	double count = 0;
    	int size = list.size();
    	
    	for (int i = 0; i < list.size(); i++) {
    		count += list.get(i).getSquareFootage();
    	}
    	
    	return count / size;
    }
    
    public double getAverageBaths() {
    	double count = 0;
    	int size = list.size();
    	
    	for (int i = 0; i < list.size(); i++) {
    		count += list.get(i).getBaths();
    	}
    	
    	return count / size;
    }
    
    public double getAverageBeds() {
    	double count = 0;
    	int size = list.size();
    	
    	for (int i = 0; i < list.size(); i++) {
    		count += list.get(i).getBeds();
    	}
    	
    	return count / size;
    }
    
//    public static void main(String args[]) {
//        DataCollection data = new DataCollection("Philadelphia, PA");
//        data.getTrainingData();
//    }
    
}
