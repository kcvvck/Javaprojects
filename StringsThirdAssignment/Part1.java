
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part1 {
    public static void main(String[] args) {
        
        
        //testFindStopCodon();
        //testprintAllGenes();
        //testfindGene();
        //testCTG();
        //FileResource fr = new FileResource("brca1line.fa");
        //String dna = fr.asString();
        //getAllGenes(dna);
        //System.out.println(dna);
        //String nineLong = "ATGxxxTAAyyyATGxxxTAG";             //no genes longer than 9
        //StorageResource geneList = getAllGenes(dna);
        //processGenes(geneList);
        //testProcessGenes();
        testProcesswithRealDNA();
        //testgetAllGenes();

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

        return -1;
    }


    public static String findGene(String dna, int where) {
        if (where < dna.length()){
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
        else{
            return "";
        }
    }
    

    public static void printAllGenes(String dna) {

        int startIndex = 0;

        while ( true) {
            String gene = findGene(dna, startIndex);
            if (gene == "") { 
                break; 
            }
            System.out.println(gene);
            startIndex = startIndex + gene.length();
        }
    }

    public static void testprintAllGenes(){
//                    012345678901234567890123456789012
        String dna = "ATGxyhTGAATGoTATAGTAAaATGnnATGoTAAOOOOOO"; //3
        printAllGenes(dna);

    }
    
    public static StorageResource getAllGenes(String dna) {

        int startIndex = 0;
        StorageResource geneList = new StorageResource();
        
        while ( true) {
            String gene = findGene(dna, startIndex);
            if (gene == "") { 
                break; 
            }
            geneList.add(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
        return geneList;
    }
    
    public static void testgetAllGenes(){
        String dna = "ATGxxxyyyTAAATGTGAxxx";
        StorageResource genes = getAllGenes(dna);
        for(String s: genes.data()){
            System.out.println(s);
        }
    }
    
    public static double cgRatio(String dna){
        dna = dna.toUpperCase();
        double counter=0.0;
        for (int i = 0; i< dna.length(); i++){
            if (dna.charAt(i) == 'C'|| dna.charAt(i) == 'G'){
                counter++;
            }
        }return (double)(counter/dna.length());
    }
    
    public static int countCTG(String dna){
        dna = dna.toUpperCase();
        int counter = 0;
        int occ = 0;
        occ = dna.indexOf("CTG");
        while (true){
            if (occ == -1){
                break;
            }
            counter++;
            occ = dna.indexOf("CTG", occ + 3);
        }
        return counter;
    }
    
    public static void testCTG(){
        System.out.println("Number of CTGs: " + countCTG("CTGxxxCTGxxxCTGyyyCTGxCTG"));      //5
    }
    
    public static void processGenes(StorageResource sr){
        
        //strings that are longer than 9
        //number of strings that are longer than 9
        int longerThan9 = 0;
        //strings in sr that have more than 0.35 cgratio
        int moreThan35 = 0;
        int longestString = 0;
        int totalGenes = 0;
        for (String s : sr.data()){
            
            if(s != ""){
                totalGenes++;
                if (s.length() > 60){
                    System.out.println("More than 60 char in Gene: " + s);
                    longerThan9 ++;
                }
                if (cgRatio(s) > 0.35){
                    System.out.println("Ratio bigger than 0.35: " + s);
                    moreThan35 ++;
                }
              
                if (s.length() > longestString){
                    longestString = s.length();
                }
            }
        }
        System.out.println("genes with CG Ratio > 0.35: " + moreThan35);
        System.out.println("Genes that are 60+ chars: " + longerThan9);
        System.out.println("The length of the longest gene: " + longestString);
        System.out.println("Total number of genes: " + totalGenes);
        System.out.println("\n");
    }
    
    public static void testProcessGenes(){

        String nineLong = "ATGTAA";
        StorageResource geneList = getAllGenes(nineLong);
        processGenes(geneList);

        //String dna1 = "ATGxxxyyyTAAyyyATGxxxyyyxxxyyyxxxTAG";  //2 genes longer than 9
        //geneList = getAllGenes(dna1);
        //processGenes(geneList);

        //String dna2 = "ATGCGCCyyTAAyyyATGxxxyyyCGGGGCxxxTAG";  //genes with 0.35+ CG ratio
        //geneList = getAllGenes(dna2);
        //processGenes(geneList);

        //String dna3 = "ATGxxxyyyxxxyyyTAG";                    //genes with 0.35- CG ratio
        //geneList = getAllGenes(dna3);
        //processGenes(geneList);

    }
    
    public static void testProcesswithRealDNA(){

        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        System.out.println("Total CTGs: " + countCTG(dna));
        System.out.println(dna);

        StorageResource geneList = getAllGenes(dna);
        System.out.println(geneList);
        processGenes(geneList);

    }
}
