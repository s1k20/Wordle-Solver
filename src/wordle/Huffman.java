package wordle;

import project20280.hashtable.ChainHashMap;
import project20280.interfaces.BinaryTree;
import project20280.interfaces.Position;
import project20280.priorityqueue.HeapPriorityQueue;
import project20280.tree.AbstractBinaryTree;
import project20280.tree.BinaryTreePrinter;
import project20280.tree.LinkedBinaryTree;

public class Huffman {

    public AbstractBinaryTree<Object> bt;
    //String fileName = "wordle/resources/dictionary.txt";
    String fileName = "wordle/resources/extended-dictionary.txt";



    LinkedBinaryTree<HuffmanNode> huffmantree;

    ChainHashMap<String,String> EncodedValue;


    public Huffman(String data){

        EncodedValue = new project20280.hashtable.ChainHashMap<>();
        huffmantree = compress(data);
        generateCodes(huffmantree.root(),"",this.EncodedValue);
        EncodedValue.printHashMap();
        this.printTree(data);
    }

    class HuffmanNode implements Comparable<HuffmanNode>{
        String data;
        int frequency;
        HuffmanNode right;

        public HuffmanNode(String data, int frequency) {
            this.data = data;
            this.frequency = frequency;
        }

        public HuffmanNode getRight() {
            return right;
        }


        @Override
        public int compareTo(HuffmanNode o) {
            return this.frequency - o.frequency;
        }

        @Override
        public String toString() {
            return data;
        }

    }


    public LinkedBinaryTree<HuffmanNode> compress(String X) {
        ChainHashMap<String, Integer> frequencyMap = letterFrequency(X);

        HeapPriorityQueue<Integer, LinkedBinaryTree<HuffmanNode>> HuffmanNodeQ = new HeapPriorityQueue<>();

        for (String c : frequencyMap.keySet()) {
           LinkedBinaryTree<HuffmanNode> HuffmanTree = new LinkedBinaryTree<>();
            HuffmanTree.addRoot(new Huffman.HuffmanNode(c, frequencyMap.get(c)));
            HuffmanNodeQ.insert(frequencyMap.get(c), HuffmanTree);
        }

        while (HuffmanNodeQ.size() > 1) {
            var e = HuffmanNodeQ.removeMin();
            var f = HuffmanNodeQ.removeMin();

            LinkedBinaryTree<HuffmanNode> Tree1 = e.getValue();
            LinkedBinaryTree<HuffmanNode> Tree2 = f.getValue();
            LinkedBinaryTree<HuffmanNode> combinedTree = new LinkedBinaryTree<>();
            String CombinedData = Tree1.getRoot().getElement().data + Tree2.getRoot().getElement().data;
            Position<HuffmanNode> root = combinedTree.addRoot(
                    new HuffmanNode(CombinedData, e.getKey() + f.getKey()));
            combinedTree.attach(root, Tree1, Tree2);

            HuffmanNodeQ.insert(root.getElement().frequency, combinedTree);
        }

       LinkedBinaryTree<Huffman.HuffmanNode> HuffmanTree = HuffmanNodeQ.removeMin().getValue();
        return HuffmanTree;
    }


    public void printTree(String X) {
        BinaryTree<HuffmanNode> huffmanTree = compress(X);
        BinaryTreePrinter<HuffmanNode> printer = new BinaryTreePrinter<>(huffmanTree);
        System.out.println(printer.print());
    }

    private void generateCodes(Position<HuffmanNode> position, String code, ChainHashMap<String, String> letterCodes) {
        if (position == null) {
            return;
        }

        HuffmanNode Node = position.getElement();

        if (huffmantree.isExternal(position) && Node.data.length() == 1) {
            letterCodes.put(Node.data, code);
        } else {
            if (huffmantree.left(position) != null) {
                generateCodes(huffmantree.left(position), code + "0", letterCodes);
            }
            if (huffmantree.right(position) != null) {
                generateCodes(huffmantree.right(position), code + "1", letterCodes);
            }
        }
    }


    public ChainHashMap<String, Integer> letterFrequency(String X) {

        ChainHashMap<String, Integer> frequencyMap = new ChainHashMap<>();
        for (char c : X.toCharArray()) {
            String Key = String.valueOf(c);
            frequencyMap.put(Key, frequencyMap.getOrDefault(Key, 0) + 1);
        }

        return frequencyMap;
    }

    public ChainHashMap<String,String> getEncodedValue() {
        return EncodedValue;
    }

    public static void main(String[] args) {
        Wordle game = new Wordle();
        Huffman huffman = new Huffman(String.join("", game.dictionary));

    }




}