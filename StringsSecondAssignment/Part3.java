
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public static void main(String[] args) {
        
        testcountGenes();

    }

    public static int findStopCodon(String dna, int startIndex, String stopCodon){

        int currIndex = dna.indexOf(stopCodon, startIndex+1);
        while(currIndex != -1 ){
            //loop only if there is a stop codon
            if((currIndex - startIndex) % 3 == 0){ 
                return currIndex; 
            }
            currIndex = dna.indexOf(stopCodon, currIndex+3);
        }

        return dna.length();
    }


    public static String findGene(String dna, int where) {

        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) { 
            return ""; 
        }

        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");

        int minIndex = 0;

        if (taaIndex == -1 ||
                (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        }
        else { minIndex = taaIndex; }

        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }

        if (minIndex == -1){ 
            return ""; 
        }

        return dna.substring(startIndex,minIndex + 3);
    }

    public static void printAllGenes(String dna) {

        int startIndex = 0;
        int count = 0;

        while ( true) {
            String gene = findGene(dna, startIndex);
            if (gene == "") { 
                break; 
            }
            count += 1;
            startIndex = startIndex + gene.length();
        }System.out.println(count);
    }

    public static void testcountGenes(){
//                    012345678901234567890123456789012
        String dna = "ATGxyhTGAATGoTATAGTAAaATGnnATGoTAAOOOOOO"; //3
        printAllGenes(dna);

    }

    
}
