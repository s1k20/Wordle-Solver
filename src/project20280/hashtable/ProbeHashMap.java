package project20280.hashtable;

import project20280.interfaces.Entry;

import java.util.ArrayList;
import java.util.List;

public class ProbeHashMap<K, V> extends AbstractHashMap<K, V> {
    private MapEntry<K, V>[] table;
    private final MapEntry<K, V> DEFUNCT = new MapEntry<>(null, null);

    public ProbeHashMap() {
        super();
    }

    /**
     * Creates a hash table with given capacity and prime factor 109345121.
     */
    public ProbeHashMap(int cap) {
        super(cap);
    }

    /**
     * Creates a hash table with the given capacity and prime factor.
     */
    public ProbeHashMap(int cap, int p) {
        super(cap, p);
    }

    @Override
    protected void createTable() {
        table = new MapEntry[capacity];
    }

    int findSlot(int h, K k) {
        // TODO
        int avail = -1; // No slot available found initially
        int j = h; // Initial index from hash code
        do {
            if (table[j] == null) {
                if (avail == -1) avail = j; // This slot is available for new entry
                break;
            } else if (table[j] == DEFUNCT) {
                if (avail == -1) avail = j; // Mark this as available if no other available slot found
            } else if (table[j].getKey().equals(k)) {
                return j; // Key found in the table
            }
            j = (j + 1) % capacity; // Linear probing
        } while (j != h); // Stop if we've looped back to the start
        return avail;
    }

    @Override
    protected V bucketGet(int h, K k) {
        // TODO
        int j = findSlot(h, k);
        if (j < 0 || table[j] == null || table[j] == DEFUNCT) return null; // No entry found
        return table[j].getValue();
    }

    @Override
    protected V bucketPut(int h, K k, V v) {
        // TODO
        int j = findSlot(h, k);
        if (j >= 0 && table[j] != null && table[j] != DEFUNCT) {
            return table[j].setValue(v); // Key exists, replace value
        }
        if (table[j] == null || table[j] == DEFUNCT) {
            table[j] = new MapEntry<>(k, v); // New entry or reusing defunct slot
            n++; // Increase size
            return null;
        }
        return null;
    }

    @Override
    protected V bucketRemove(int h, K k) {
        // TODO
        int j = findSlot(h, k);
        if (j < 0 || table[j] == null || table[j] == DEFUNCT) {
            return null; // No entry found to remove
        }
        V oldValue = table[j].getValue();
        table[j] = DEFUNCT; // Mark the entry as removed
        n--; // Decrease size
        return oldValue;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> buffer = new ArrayList<>();
        for (int h = 0; h < capacity; h++) {
            if (table[h] != null && table[h] != DEFUNCT) {
                buffer.add(table[h]);
            }
        }
        return buffer;
    }
}
