
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
        public static void main(String[] args){
            System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));     //3
            System.out.println(howMany("AA", "ATAAAA"));                //2
            System.out.println(howMany("SS", "SSASSBBCCSSXXSXXSS"));    //4
        }
        
        public static int howMany(String stringa, String stringb){
            int currOcc = stringb.indexOf(stringa);
            int count= 0;
            while (currOcc !=-1){
                count +=1;
                currOcc = stringb.indexOf(stringa,currOcc + stringa.length());
            }return count;
        }
}
