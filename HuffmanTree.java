import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Map.Entry;

class HuffmanTree {

    HashMap<Integer, Integer> map;
    Node root;
    HashMap<Integer, String> asciiToCode = new HashMap<Integer, String>(); // ascii, encoded
    HashMap<String, Integer> codeToAscii = new HashMap<String, Integer>(); // encoded, ascii

    HuffmanTree(HashMap<Integer, Integer> map) {
        this.map = map;
        createHuffmanTree(map);
        populateDictionary();
    }

    public class Node implements Comparable<Node> {
        int freq, asciiChar;
        Node leftChild, rightChild;

        // for nodes when they are first inserted into priority queue
        public Node(int asciiChar, int freq, Node leftChild, Node rightChild) {
            this.asciiChar = asciiChar;
            this.freq = freq;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        // for nodes in huffman tree which does not contain data
        public Node(int freq, Node leftChild, Node rightChild) {
            this.asciiChar = -1;
            this.freq = freq;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        @Override
        public int compareTo(Node otherNode) {
            if (freq > otherNode.freq)
                return 1;
            if (freq < otherNode.freq)
                return -1;
            return 0;
        }

        @Override
        public String toString() {
            return "[" + asciiChar + ": " + freq + "]";
        }

        /**
         * Method which visits all nodes in huffman tree
         * to let leaf nodes put their data in dictionary for easy access
         */
        public void setBinRepr(String binRepr) {
            if (asciiChar != -1) {
                asciiToCode.put(asciiChar, binRepr);
                codeToAscii.put(binRepr, asciiChar);
            }
            if (leftChild != null) {
                leftChild.setBinRepr(binRepr + 0);
            }
            if (rightChild != null) {
                rightChild.setBinRepr(binRepr + 1);
            }
        }
    }

    /**
     * Creates a Huffmantree from a frequency map
     * Stores root node in Huffman tree (this)
     * 
     * @param asciiMap
     */
    private void createHuffmanTree(HashMap<Integer, Integer> asciiMap) {

        PriorityQueue<Node> q = new PriorityQueue<Node>();
        for (Entry<Integer, Integer> entry : asciiMap.entrySet()) {
            q.add(new Node(entry.getKey(), entry.getValue(), null, null));
        }
        while (q.size() > 1) {
            Node v1 = q.remove();
            Node v2 = q.remove();
            int f = v1.freq + v2.freq;
            q.add(new Node(f, v1, v2));
        }
        root = q.remove();
    }

    /**
     * Starts of a recursive call from root of tree,
     * letting all nodes put their ascii and huffman representation
     * into the dictionaries
     */
    private void populateDictionary() {
        root.setBinRepr("");
    }

    /**
     * Encodes a String with current Huffman encoding.
     * Is not tested for situations where String contains chars which are not in
     * dictionary
     * 
     * @param text
     * @return
     */
    public String encode(String text) {
        String encodedText = "";
        for (int i = 0; i < text.length(); i++) {
            int asciiChar = (int) text.charAt(i);
            encodedText += asciiToCode.get(asciiChar);
        }
        return encodedText;
    }

    /**
     * Returns a String in plain text from decoding an encoded message.
     * 
     * @param binaryRepresentation
     * @return
     */
    public String decode(String binaryRepresentation) {
        String text = "";

        int start = 0;
        int end = 1;
        while (end <= binaryRepresentation.length()) {
            String part = binaryRepresentation.substring(start, end);
            if (codeToAscii.containsKey(part)) {
                text += (char) (int) codeToAscii.get(part);
                start = end;
            }
            end++;
        }
        return text;
    }

    /**
     * Prints the dictionary created by the Huffman tree.
     */
    public void printDictionary() {
        System.out.println("Dictionary: ");
        for (Entry<Integer, String> entry : asciiToCode.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
