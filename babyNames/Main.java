
/**
 * Write a description of Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.CSVRecord;
import java.io.File;


public class Main {
    public static void main(String[] args){
        FileResource fr = new FileResource();
        totalBirths(fr);
        //whatIsNameInYear("Owen", 1974, 2014, "M");
        //int out = yearOfHighestRank("Mich", "M");
        //int rank = getTotalBirthsRankedHigher(1990, "Drew", "M");
        //System.out.println(rank);
        //testGetRank();
        //testGetName();
        //testWhatIsNameInYear();
        //testYearOfHighestRank();
        //testGetAverageRank();
        //testGetTotalBirthsRankedHigher();
    }
    
    
    public static void totalBirths(FileResource fr){
        int totalB = 0;
        int totalBoys = 0, totalGirls = 0;
        int boyNames = 0, girlNames = 0;
        for(CSVRecord r : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(r.get(2));
            totalB += numBorn;
            if (r.get(1).equals("M")){
                totalBoys += numBorn;
                boyNames++;
            }else{
                totalGirls += numBorn;
                girlNames++;
            }
        }
        System.out.println("Total births: " + totalB + " Total names: " + boyNames+girlNames);
        System.out.println("Total boys: " + totalBoys + " Total boy names: " + boyNames);
        System.out.println("Total girls: " + totalGirls + " Total girl names: " + girlNames);
    }
    
    public static void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public static int getRank(int year, String name, String gender){
        String fileName = "us_babynames_by_year/yob"+ Integer.toString(year) + ".csv";
        FileResource fr = new FileResource(fileName);
        int rank = 0;
        
        for(CSVRecord r : fr.getCSVParser(false)){
            if (r.get(1).equals(gender)){
                rank++;
            }
            if (r.get(0).equals(name) && r.get(1).equals(gender)){
                return rank;
            }
        }
        return -1;
    }
    
    public static void testGetRank(){
        System.out.println(getRank(2012, "Mason", "M"));    //2
        System.out.println(getRank(2012, "Mason", "F"));    //-1
        System.out.println(getRank(2012, "Ava", "F"));    //5
    }
    
    public static String getName(int year, int rank, String gender){
        String fileName = "us_babynames_by_year/yob"+ Integer.toString(year) + ".csv";
        FileResource fr = new FileResource(fileName);
        int currRank = 0;
        for(CSVRecord r : fr.getCSVParser(false)){
            if (r.get(1).equals(gender)){
                currRank++;
                if (currRank == rank){
                    return r.get(0);
                }
            }else{
                continue;
            }
        }
        return "NO NAME";
    }
    
    public static void testGetName(){
        System.out.println(getName(2012, 2, "M"));    //Mason
        System.out.println(getName(2012, 6, "M"));    //NO NAME
        System.out.println(getName(2012, 5, "F"));    //Ava
    }
    
    public static void whatIsNameInYear(String name, int year, int newYear, String gender){
        int currRank = getRank(year, name, gender);
        String newName = getName(newYear, currRank, gender);
        String p = null;
        if (gender.equals("F")){
            p = "she";
        }else{
            p = "he";
        }
        System.out.println(name + " would have been called " + newName + " if " + p 
                            + " had been born in " + Integer.toString(newYear));
    }
    
    public static void testWhatIsNameInYear(){
        whatIsNameInYear("Isabella", 2012, 2014, "F");      //Sophia
        whatIsNameInYear("Noah", 2013, 2012, "M");          //Jacob
    }
    
    public static int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int defaultRank = -1;
        int thisYear = -1;
        for (File f : dr.selectedFiles()){
            int currYear = Integer.parseInt(f.getName().replaceAll("[^\\d]", ""));
            int currRank = getRank(currYear, name, gender);
            if (defaultRank == -1 && currRank >0){
                defaultRank = currRank;
                thisYear = currYear;
            }else {
                if (currRank < defaultRank && currRank >0){
                    defaultRank = currRank;
                    thisYear = currYear;
                }
            }
        }
        return thisYear;
    }
    
    public static void testYearOfHighestRank(){
        System.out.println(yearOfHighestRank("Isabella", "F"));     //2012
        System.out.println(yearOfHighestRank("Lee", "M"));          //-1
        System.out.println(yearOfHighestRank("Emma", "F"));         //2014
        System.out.println(yearOfHighestRank("Emma", "M"));         //-1
    }
    
    public static double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int ranks = 0;
        int numberListed = 0;
        for(File f : dr.selectedFiles()){
            int currYear = Integer.parseInt(f.getName().replaceAll("[^\\d]", ""));
            int currRank = getRank(currYear, name, gender);
            if (currRank == -1){
                continue;
            }numberListed++;
            ranks += currRank;
        }
        if (numberListed == 0){
            return numberListed;
        }
        return ((double)ranks)/numberListed;
    }
    
    public static void testGetAverageRank(){
        System.out.println(getAverageRank("Sophia", "F"));      //1.66
        System.out.println(getAverageRank("Mason", "M"));       //3.0
        System.out.println(getAverageRank("Jacob", "M"));       //2.66
        System.out.println(getAverageRank("Lee", "M"));         //-1
        System.out.println(getAverageRank("Jacob", "F"));       //-1
    }
    
    public static int getTotalBirthsRankedHigher(int year, String name, String gender){
        String fileName = "us_babynames_by_year/yob"+ Integer.toString(year) + ".csv";
        FileResource fr = new FileResource(fileName);
        int totalBirths = 0;
        if (getRank(year, name, gender) == -1){
            return 0;
        }
        for(CSVRecord r : fr.getCSVParser(false)){
            if (r.get(1).equals(gender)){
                if (!r.get(0).equals(name)){
                    totalBirths += Integer.parseInt(r.get(2));
                }else{
                    break;
                }
            }else{
                continue;
            }
        }
        return totalBirths;
    }
    
    public static void testGetTotalBirthsRankedHigher(){
        System.out.println(getTotalBirthsRankedHigher(2012, "Ethan", "M"));     //15
        System.out.println(getTotalBirthsRankedHigher(2012, "Emma", "F"));      //10
        System.out.println(getTotalBirthsRankedHigher(2012, "Isabella", "F"));  //19
        System.out.println(getTotalBirthsRankedHigher(2012, "Sophia", "F"));    //0
        System.out.println(getTotalBirthsRankedHigher(2012, "Lee", "M"));       //0
    }
}
