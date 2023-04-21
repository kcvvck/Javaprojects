
/**
 * Write a description of ReadFile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
public class ReadFile {
    public static void main(String[] args){
        File f = new File("READMEi.TXT");
        readFile(f);
        fileInfo(f);
    }
    
    public static void readFile(File f){
        try{
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                String data = sc.nextLine();
                System.out.println(data);
            }
            sc.close();
        }catch(FileNotFoundException e){
            //e.printStackTrace(); //-> typical error thrown
            System.out.println("file is not found!");
        }
    }
    
    public static void fileInfo(File myObj) {
        if (myObj.exists()) {
            System.out.println("File name: " + myObj.getName());
            System.out.println("Absolute path: " + myObj.getAbsolutePath());
            System.out.println("Writeable: " + myObj.canWrite());
            System.out.println("Readable " + myObj.canRead());
            System.out.println("File size in bytes " + myObj.length());
        } else {
            System.out.println("The file does not exist.");
        }
  }
  
  public static void deleteFile(File myObj) { 
    if (myObj.delete()) { 
      System.out.println("Deleted the file: " + myObj.getName());
    } else {
      System.out.println("Failed to delete the file.");
    } 
  } 
}
