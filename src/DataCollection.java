import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class DataCollection {
    
    private final String baseURL = "https://www.cia.gov/library/publications/the-world-factbook/";
    private Document currentDoc;
    
    public DataCollection()  {
        try {
            this.currentDoc = Jsoup.connect(this.baseURL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public House[] getTrainingData(String location) {
        throw new UnsupportedOperationException("TODO: implement");
    }
   
    
}
