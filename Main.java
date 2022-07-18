import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static final String filnavn = "tekst.txt";

    public static void main(String[] args) {

        /* Imagine sender */
        String text = getTextFromFile(filnavn);
        HashMap<Integer, Integer> asciiMap = createFrequencyMap(text);
        HuffmanTree senderHuffTree = new HuffmanTree(asciiMap);
        String binaryRepresentation = senderHuffTree.encode(text);

        /*
         * transmitt the ascii map and the encoded binary representation of string to
         * some other party
         */
        System.out.println("-----------------");
        System.out.println(asciiMap.toString());
        System.out.println(binaryRepresentation);
        System.out.println("-----------------");

        /* Imagine recipient */
        HuffmanTree recipientHuffTree = new HuffmanTree(asciiMap);
        String decodedString = recipientHuffTree.decode(binaryRepresentation);
        System.out.println(decodedString);
    }

    /* Return String of file content */
    public static String getTextFromFile(String fileName) {
        String text = "";
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File(fileName));
            ;
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

    /*
     * Returns a frequency map of
     * first key Integer is ascii of symbol, value Integer is frequency
     */
    public static HashMap<Integer, Integer> createFrequencyMap(String text) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < text.length(); i++) {
            int asciiChar = (int) text.charAt(i);
            if (!map.containsKey(asciiChar)) {
                map.put(asciiChar, 1);
            } else {
                map.put(asciiChar, map.get(asciiChar) + 1);
            }
        }
        return map;
    }
}
