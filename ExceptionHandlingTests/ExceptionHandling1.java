
/**
 * Write a description of ExceptionHandling1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;
import java.io.IOException;

public class ExceptionHandling1 {
    public static void main(String[] args){
        //Scanner sc = new Scanner(System.in);
        //simpleDivision(sc);
        //tryMulti();
        //mostSpecifictoGeneral(); // compile time error
        //nestedExceptions();
        //useOfFinally();
        //practiceWithThrow();
        //callStack();
        //checking();
        doubleChecking();
    }
    
    public static void simpleDivision(Scanner sc){
        System.out.println("numerator is ");
        int numerator = sc.nextInt();
        
        System.out.println("denominator is ");
        int denominator = sc.nextInt();
        
        try{
            int result = numerator/ denominator;
        }catch (ArithmeticException e){
            System.out.println(e);
        }
        System.out.println("rest of code after correct exception handling");
    }
    
    public static void simpleDivision_withWrongError(Scanner sc){
        System.out.println("numerator is ");
        int numerator = sc.nextInt();
        
        System.out.println("denominator is ");
        int denominator = sc.nextInt();
        
        try{
            int result = numerator/ denominator;
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        }
        System.out.println("rest of the code after wrong exception handling");
    }
    
    public static void tryMulti(){
        int ar[] = new int[5];
        try{
            ar[6] = 30/0;
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Array index out of bounds!");
            
        }catch(ArithmeticException e ){
            System.out.println("Arithmetic exception occurred!");
        }catch(Exception e){
            System.out.println("Exception encountered!");
        }
        
    }
    
    public static void mostSpecifictoGeneral(){
        int ar[] = new int[5];
        try{
            ar[6] = 30/0;
        }catch(Exception e){
            System.out.println("Exception encountered!");
        }//catch(ArithmeticException e ){
        //    System.out.println("Arithmetic exception occurred!");
        //}catch(ArrayIndexOutOfBoundsException e){
        //    System.out.println("Array index out of bounds!");
        //    
        //}catch(Exception e){
        //    System.out.println("Exception encountered!");
        //}
        //System.out.println("rest of the code");
    }
    
    public static void nestedExceptions(){
        try{
            int result = 0;
            try{
                result = 30/0;
            }catch(ArithmeticException e){
                System.out.println("internal exception raised!");
            }
            int result2 = result;
            System.out.println("result2 is "+ result2);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static void useOfFinally(){
        int result = 0;
        //try{
            
        try{
            result = 30/0;
        }catch(ArrayIndexOutOfBoundsException e){ //exception not caught so we skipped everything inside here
            System.out.println("internal exception raised!");
        }
        //int result2 = result;
        //System.out.println("result2 is "+ result2);
        //}catch(Exception e){
        //    System.out.println(e);
        finally{
            System.out.println("finally will stil be ran "+ result);
        }
        System.out.println("rest of the code..."); 
    }
    
    public static void practiceWithThrow(){  
        int age = 4;
        if(age<18)  {
            throw new ArrayIndexOutOfBoundsException("not valid");  
        }
        else {
            System.out.println("welcome to vote");  
        }
        
        try{
            int result = 30/0;
        }catch(ArithmeticException e ){
            System.out.println("division by 0!");
        }
    }
    
    public static void  m(){  
        int data=50/0;  
    }  
    public static void n(){  
        try{
            m();
        }catch(ArithmeticException e){
            System.out.println("not throwing arithmetic exception");
            throw new ArithmeticException("it is not working");
        }finally{
            int ar[] = new int[2];
            ar[10] = 4;
        }
    }  
    public static void p(){  
        try{  
            n();  
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("exception handled");
        }finally{
            System.out.println("was exception handled?");
        }
    }  
    public static void callStack(){  
        p();  
        System.out.println("normal flow...");  
    }  
    // for checked expressions (errors raised during compile time)
    public static void x()throws IOException{  
        throw new IOException("device error");//checked exception 

    }  
    public static void y()throws IOException{  
        x();  
    }  
    public static void z(){  
        try{  
            y();  
        }catch(Exception e){
            System.out.println("exception handled");
        }  
    }  
    public static void checking(){  
        z();  
        System.out.println("normal flow...");  
    } 
    
    public static void doubleChecking(){  
        try{
            x();  
            //System.out.println("normal flow...");  
        }catch(Exception e){
            System.out.println("Exception handled");
        }
        
        System.out.println("normal flow...");  
    } 
}
