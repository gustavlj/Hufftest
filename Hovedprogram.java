import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Hovedprogram {

    static final String filnavn = "tekst.txt";
    
    public static void main(String[] args) {
        // leser tekst fra fil
        String text = getTextFromFile(filnavn);
        //System.out.println(text);
        
        // lager frekvenstabell
        // first integer is ascii, second is freq
        HashMap<Integer, Integer> asciiMap = createFrequencyMap(text); 
        
        // create a HuffManTree from map
        LudHuffManTree huffTree = new LudHuffManTree(asciiMap);
        huffTree.printDictionary();

        // use HuffmanTree to encode to a binary version of text
        String binaryRepresentation = huffTree.encode(text);

        // look at String
        System.out.println(binaryRepresentation);

        // use HuffmanTree to decode representation
        String decodedString = huffTree.decode(binaryRepresentation);
        
        System.out.println(decodedString);
    }
    
    public static String getTextFromFile(String fileName) {
        String text = "";
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner (new File(fileName));;
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.exit(-1);
        }

        String line = "";
        while (fileScanner.hasNextLine()) {
            line = fileScanner.nextLine();
            text += line + "\n";
        }
        return text;
    }
    
    public static HashMap<Integer, Integer> createFrequencyMap(String text) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < text.length(); i++) {
            int asciiChar = (int) text.charAt(i);
            if (!map.containsKey(asciiChar)) {
                map.put(asciiChar, 1);
            } else {
                map.put(asciiChar, map.get(asciiChar)+1);
            }
        }
        return map;
    }
}
