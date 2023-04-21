
/**
 * Write a description of weatherParse here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class weatherParse {
        public static void main(String[] args){
            //testcoldestHourInFile();
            fileWithColdestTemperature();
            //testlowestHumidityInFile();
            //lowestHumidityInManyFiles();
            //testAverageTemperatureInFile();
            //testAverageTemperatureWithHighHumidityInFile();
        }
        public static CSVRecord getLowest(CSVRecord currRow, CSVRecord lowest, String cond){
            if (lowest == null){
                lowest = currRow;
            }else{
                if (cond != "N/A"){
                    double currlow = Double.parseDouble(currRow.get(cond));
                    double lowestAllTime = Double.parseDouble(lowest.get(cond));
                    if (currlow < lowestAllTime && currlow != -9999){
                        lowest = currRow;
                    }
                }
            }
            return lowest;
        }
        
        public static CSVRecord getLowestofTwo(DirectoryResource dr, CSVRecord lowest, File fileName, String cond){
            for(File f : dr.selectedFiles()){
                FileResource fr = new FileResource(f);
                CSVRecord currRow = null;
                if (cond.equals("TemperatureF")){
                    currRow = coldestHourInFile(fr.getCSVParser());
                }else {
                    currRow = lowestHumidityInFile(fr.getCSVParser());
                }
                if (lowest == null){
                    fileName = f;
                    lowest = currRow;
                }else{
                    double currlow = Double.parseDouble(currRow.get(cond));
                    double lowestAllTime = Double.parseDouble(lowest.get(cond));
                    if (currlow < lowestAllTime && currlow != -9999){
                        lowest = currRow;
                        fileName = f;
                    }
                }
            }
            System.out.println("lowest occured on " + fileName.getAbsolutePath());
            return lowest;
        }
        
        
        public static CSVRecord coldestHourInFile(CSVParser parser){
            CSVRecord lowestAllTime = null;
            for(CSVRecord r : parser){
                lowestAllTime = getLowest(r, lowestAllTime, "TemperatureF");
            }
            return lowestAllTime;
        }
        
        public static void testcoldestHourInFile(){
            FileResource fr = new FileResource();
            CSVRecord largest = coldestHourInFile(fr.getCSVParser());
            System.out.println("coldest temperature was " + largest.get("TemperatureF")
                                                          + " at " + largest.get("DateUTC"));
        }
        
        public static void fileWithColdestTemperature(){
            DirectoryResource dr = new DirectoryResource();
            CSVRecord lowestAllTime = null;
            File fileName = null;
            lowestAllTime = getLowestofTwo(dr, lowestAllTime, 
                                        fileName, "TemperatureF");
            System.out.println("Coldest temperature on that day was " + lowestAllTime.get("TemperatureF"));
            FileResource fr = new FileResource(fileName);
            System.out.println("All the Temperatures on the coldest day were: \n");
            for(CSVRecord r : fr.getCSVParser()){
                System.out.println(r.get("TemperatureF"));
            }
        }
        
        public static CSVRecord lowestHumidityInFile(CSVParser parser){
            CSVRecord lowest = null;
            for (CSVRecord r : parser){
                if (!r.get("Humidity").equals("N/A")){
                    lowest = getLowest(r, lowest, "Humidity");
                }else{
                    continue;
                }
            }
            //System.out.println(lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
            return lowest;
        }
        
        public static void testlowestHumidityInFile(){
            FileResource fr = new FileResource();
            CSVParser parser = fr.getCSVParser();
            CSVRecord csv = lowestHumidityInFile(parser);
        }
        
        public static void lowestHumidityInManyFiles(){
            DirectoryResource dr = new DirectoryResource();
            CSVRecord lowestAllTime = null;
            File fileName = null;
            lowestAllTime = getLowestofTwo(dr, lowestAllTime, 
                                        fileName, "Humidity");
            System.out.println("lowest humidity on that day was " + lowestAllTime.get("Humidity") 
                            + " at " + lowestAllTime.get("DateUTC"));
        }
        
        public static double averageTemperatureInFile(CSVParser parser){
            double totalTemp = 0.0;
            int noOfRecords = 0;
            for (CSVRecord r : parser){
                 totalTemp += Double.parseDouble(r.get("TemperatureF"));
                 noOfRecords++;
                
            }
            return totalTemp/noOfRecords;
        }
        
        public static void testAverageTemperatureInFile(){
            FileResource fr = new FileResource();
            CSVParser parser = fr.getCSVParser();
            double result = averageTemperatureInFile(parser);
            System.out.println("Average temperature in file is " + result);
        }
        
        public static double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
            double hum = value;
            double totalTemp = 0.0;
            int noOfRecords = 0;
            int noOfRecordswCond = 0;
            for (CSVRecord r : parser){
                noOfRecords++;
                if (Double.parseDouble(r.get("Humidity")) >= value){
                    totalTemp += Double.parseDouble(r.get("TemperatureF"));
                    noOfRecordswCond++;
                }
            }
            if (noOfRecordswCond == 0){
                System.out.println("No records found.");
                return 0.0;
            }
            return totalTemp/noOfRecordswCond;
        }
        
        public static void testAverageTemperatureWithHighHumidityInFile(){
            FileResource fr = new FileResource();
            CSVParser parser = fr.getCSVParser();
            double result = averageTemperatureWithHighHumidityInFile(parser, 80);
            System.out.println("Average temperature in file is " + result);
        }
}
