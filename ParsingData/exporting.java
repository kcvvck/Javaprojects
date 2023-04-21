
/**
 * Write a description of exporting here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class exporting {
    public static void main(String[] args){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        parser = fr.getCSVParser();
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //numberOfExporters(parser, "cocoa");
        //countryInfo(parser, "Nauru");
        
        bigExporters(parser, "$1,000,000,000,000");
    }
    
    public static void countryInfo(CSVParser parser, String country){
        
        for(CSVRecord r : parser){
            if (r.get("Country").equals(country)){
                System.out.println(country + ": " + r.get("Exports")
                                           + ": " + r.get("Value (dollars)"));
            }
        }
        System.out.println("NOT FOUND");
    }
    
    public static void listExportersTwoProducts(CSVParser parser,
                                                    String exportitem1, 
                                                    String exportitem2){
        for (CSVRecord r : parser){
            if (r.get("Exports").contains(exportitem1) && r.get("Exports").contains(exportitem2));
            System.out.println(r.get("Country"));
        }
    }
    
    public static void numberOfExporters(CSVParser parser, String exportitem){
        int counter = 0;
        for (CSVRecord r : parser){
            if (r.get("Exports").contains(exportitem)){
                counter++;
            }
        }
        System.out.println(counter);
    }
    
    public static void bigExporters(CSVParser parser, String amount){
        String newAmount = amount.replaceAll("[$,]", "");
        for(CSVRecord r : parser){
            String currAmount = r.get("Value (dollars)").replaceAll("[$,]", "");
            if (Float.parseFloat(currAmount)> Float.parseFloat(newAmount)){
                System.out.println(r.get("Country") + ": " + r.get("Value (dollars)"));
            }
        }
    }
}
