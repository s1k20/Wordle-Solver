package project20280.hashtable;

import project20280.interfaces.Entry;


import java.util.ArrayList;

/*
 * Map implementation using hash table with separate chaining.
 */

public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {
    // a fixed capacity array of UnsortedTableMap that serve as buckets
    private UnsortedTableMap<K, V>[] table; // initialized within createTable

    /**
     * Creates a hash table with capacity 11 and prime factor 109345121.
     */
    public ChainHashMap() {
        super();
    }

    /**
     * Creates a hash table with given capacity and prime factor 109345121.
     */
    public ChainHashMap(int cap) {
        super(cap);
    }

    /**
     * Creates a hash table with the given capacity and prime factor.
     */
    public ChainHashMap(int cap, int p) {
        super(cap, p);
    }

    /**
     * Creates an empty table having length equal to current capacity.
     */
    @Override
    @SuppressWarnings({"unchecked"})
    protected void createTable() {
        table = new UnsortedTableMap[capacity];
    }

    /**
     * Returns value associated with key k in bucket with hash value h. If no such
     * entry exists, returns null.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return associate value (or null, if no such entry)
     */
    @Override
    protected V bucketGet(int h, K k) {
        // TODO
        UnsortedTableMap<K, V> bucket = table[h];
        return bucket == null? null : bucket.get(k);
    }

    /**
     * Associates key k with value v in bucket with hash value h, returning the
     * previously associated value, if any.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @param v the value to be associated
     * @return previous value associated with k (or null, if no such entry)
     */
    @Override
    protected V bucketPut(int h, K k, V v) {
        // TODO
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null) {
            bucket = new UnsortedTableMap<>();
            table[h] = bucket;
        }
        int oldSize = bucket.size();
        V answer = bucket.put(k, v);
        n += (bucket.size() - oldSize); // Increase size only if a new entry was added
        return answer;
    }


    /**
     * Removes entry having key k from bucket with hash value h, returning the
     * previously associated value, if found.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return previous value associated with k (or null, if no such entry)
     */
    @Override
    protected V bucketRemove(int h, K k) {
        // TODO
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null) return null;
        int oldSize = bucket.size();
        V answer = bucket.remove(k);
        n -= (oldSize - bucket.size()); // Decrease size only if an entry was removed
        return answer;
    }

    /**
     * Returns an iterable collection of all key-value entries of the map.
     *
     * @return iterable collection of the map's entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        /*
        for each element in (UnsortedTableMap []) table
            for each element in bucket:
                print element
        */
        ArrayList<Entry<K, V>> entries = new ArrayList<>();
        for (UnsortedTableMap<K, V> tm : table) {
            if (tm != null) {
                for (Entry<K, V> e : tm.entrySet()) {
                    entries.add(e);
                }
            }
        }
        return entries;
    }

    // Implement the getOrDefault method
    public V getOrDefault(K key, V defaultValue) {
        int h = hashValue(key); //compute the hash value for the key to find the correct bucket
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null) {
            return defaultValue; // Return the default value if the bucket is empty
        }
        V value = bucket.get(key);
        return (value != null) ? value : defaultValue; // Return the found value or the default value if null
    }

    public void printHashMap() {
        System.out.println("Current HashMap:");
        for (UnsortedTableMap<K, V> bucket : table) {
            if (bucket != null && !bucket.isEmpty()) {
                for (Entry<K, V> entry : bucket.entrySet()) {
                    System.out.println(entry.getKey() + " -> " + entry.getValue());
                }
            }
        }
    }

    /**
     * Returns the current number of buckets (capacity) of the hash map.
     */
    public int getCapacity() {
        return table.length; // 'table' is the array of buckets
    }

    /**
     * Calculate the load factor for this hash map.
     */
    public double calculateLoadFactor() {
        return (double) super.size() / getCapacity();
    }

    public int countcollisions(){
        int collisions = 0;
        for (UnsortedTableMap<K,V> bucket : table){
            if (bucket != null && bucket.size() > 1){
                collisions += (bucket.size() - 1);
            }
        }
        return collisions;
    }

    public String toString() {
        return entrySet().toString();
    }

    public static void main(String[] args) {
        ChainHashMap<Integer, String> m = new ChainHashMap<Integer, String>();
        m.put(1, "One");
        m.put(10, "Ten");
        m.put(11, "Eleven");
        m.put(20, "Twenty");

        System.out.println("m: " + m);

        m.remove(11);
        System.out.println("m: " + m);
    }
}
