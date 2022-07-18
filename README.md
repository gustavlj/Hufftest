A program which performs Huffman encoding of a String, and then decodes it to get the original String.

Main file reads a String from a text file.
Main creates a frequency table from file.
Main creates a HuffmanTree based on frequency table.
Main gets binary representation of text from HuffmanTree object.

The transmitting process is omitted.
Reader can imagine sending the binary representation of text and frequency map.

Main creates a new Huffman tree from frequency table.
Main gets decoded text by passing encoded text through the Huffman tree.
