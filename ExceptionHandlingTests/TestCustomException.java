
/**
 * Write a description of TestCustomeException here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCustomException {
    static void validate(int age)throws ArithmeticException{  
     if(age<18)  
      throw new ArithmeticException("not valid");  
     else  
      System.out.println("welcome to vote");  
   }  
     
   public static void main(String args[]){  
      try{  
      validate(13);  
      }catch(Exception e){System.out.println("Exception occured: "+e);}  
  
      System.out.println("rest of the code...");  
  }  
}

class InvalidAgeException extends Exception{  
    InvalidAgeException(String s){  
        super(s);  
    }  
}  
