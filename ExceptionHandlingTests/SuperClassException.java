
/**
 * Write a description of SuperclassExceptions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
class SuperClassException {  
    void msg(){
        int result = 30/0;
        //System.out.println("parent");
    }  
}  
  
class TestExceptionChild extends SuperClassException{  
    //void msg()throws IOException{ 
        //cannot throw checked expression if parent class does not declare
        //System.out.println("TestExceptionChild");  
    //}  
    void msg()throws ArithmeticException{  //unchecked expression can be thrown by child classes
        // if super class does not declare
        // if parent class already declared a certain unchecked expression
        // then child class cannot override with a more general exception
        //checked durin runtime
        System.out.println("TestExceptionChild");  
    }  
    public static void main(String args[]){  
        SuperClassException p = new TestExceptionChild();  
        try{  
            p.msg();  
        }catch(Exception e){}  
    }  
     
}  