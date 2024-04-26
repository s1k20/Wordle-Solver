package project20280.hashtable;

import project20280.interfaces.Entry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordFrequencyCounter {

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("src/project20280/hashtable/sample_text.txt"); // Adjust the path accordingly
        ChainHashMap<String, Integer> counter = new ChainHashMap<>();

        // Use a Scanner to read words from the file
        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            String word = scanner.next().replaceAll("[^a-zA-Z]", "").toLowerCase(); // Normalize the word
            if (!word.isEmpty()) {
                // Increment the count for each word
                counter.put(word, counter.getOrDefault(word, 0) + 1);
            }
        }
        scanner.close();

        // Now to sort and display the top 10 most frequent words
        List<Entry<String, Integer>> entries = new ArrayList<>();
        for (Entry<String, Integer> entry : counter.entrySet()) {
            entries.add(entry);
        }
        entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue())); // Sort by frequency in descending order

        System.out.println("Top 10 most frequent words:");
        for (int i = 0; i < Math.min(10, entries.size()); i++) {
            Entry<String, Integer> entry = entries.get(i);
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
