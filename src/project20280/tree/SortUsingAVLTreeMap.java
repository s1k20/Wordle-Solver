package project20280.tree;

import project20280.interfaces.Entry;

import java.util.ArrayList;
import java.util.List;

public class SortUsingAVLTreeMap<K extends Comparable<K>, V> {

    private AVLTreeMap<K, V> map;

    public SortUsingAVLTreeMap() {
        this.map = new AVLTreeMap<>();
    }

    public List<K> sort(K[] array) {
        // Insert all elements into the AVL tree.
        for (K key : array) {
            map.put(key, null); // Value is irrelevant for sorting.
        }

        // Perform an in-order traversal to collect the keys in sorted order.
        return getInOrderKeys();
    }

    private List<K> getInOrderKeys() {
        List<K> keys = new ArrayList<>();
        for (Entry<K, V> entry : map.entrySet()) {
            keys.add(entry.getKey());
        }
        return keys;
    }

    public static void main(String[] args) {
        SortUsingAVLTreeMap<Integer, Integer> sorter = new SortUsingAVLTreeMap<>();
        Integer[] numbers = {5, 3, 10, 2, 4, 7, 11, 1, 6, 9, 12, 8};

        List<Integer> sortedNumbers = sorter.sort(numbers);
        System.out.println("Sorted numbers: " + sortedNumbers);
    }
}
