
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;

public class Part4 {
    public static void main (String[] args) {
        //Part4 pr = new Part4();
        // pr.testPerimeter();
        // pr.testFileWithLargestPerimeter();
        //pr.testPerimeterMultipleFiles();
        //double perim = getPerimeter (Shape s);
        //testPerimeter ();
        testPerimeterMultipleFiles();
    }

    public static double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public static int getNumPoints (Shape s) {
       int numPoints = 0;
       for(Point p: s.getPoints()){
           numPoints += 1;
        }
        return numPoints;
    }

    public static double getAverageLength(Shape s) {
        return getPerimeter(s) / getNumPoints(s);
    }

    public static double getLargestSide(Shape s) {
        // Start with totalPerim = 0
        double largestSide = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Check if the current distance bigger than the largestSide, if so update the largestSide
            if(currDist > largestSide){
                largestSide = currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        return largestSide;
    }

    public static double getLargestX(Shape s) {
        int largestX = 0;
        for(Point p: s.getPoints()){
            if(p.getX() > largestX){
                System.out.println(p.getX());
                largestX = p.getX();
            }
        }
        return largestX;
    }

    public static double getLargestPerimeterMultipleFiles() {
        double largestPer = 0.0;
        DirectoryResource dir = new DirectoryResource();
        for (File f : dir.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if(getPerimeter(s) > largestPer){
                largestPer = getPerimeter(s);
            }
        }
        return largestPer;
    }

    public static String getFileWithLargestPerimeter() {
        File temp = null;    
        double largestPer = 0.0;
        DirectoryResource dir = new DirectoryResource();
        for (File f : dir.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if(getPerimeter(s) > largestPer){
                largestPer = getPerimeter(s);
                temp = f;
            }
        }
        return temp.getName();
    }

    public static void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double avgLen = getAverageLength(s);
        double largestX = getLargestX(s);
        double largestSide = getLargestSide(s);
        System.out.println("perimeter = " + length);
        System.out.println("number of points = " + numPoints);
        System.out.println("average length = " + avgLen);
        System.out.println("largest X = " + largestX);
        System.out.println("largest side = " + largestSide);
    }
    
    public static void testPerimeterMultipleFiles() {
        System.out.println(getLargestPerimeterMultipleFiles());
    }

    public static void testFileWithLargestPerimeter() {
        System.out.println(getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public static void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public static void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    
}


