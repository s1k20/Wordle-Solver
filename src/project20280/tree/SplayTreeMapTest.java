package project20280.tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SplayTreeMapTest {

    @Test
    void testGet() {
        SplayTreeMap<Integer, String> map = new SplayTreeMap<>();
        Integer[] arr = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }
        assertEquals("15", map.get(15));
        assertEquals("24", map.get(24));
        assertNull(map.get(-1));
    }

    @Test
    void testPut() {
        SplayTreeMap<Integer, String> map = new SplayTreeMap<>();
        Integer[] arr = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }

        Iterator<Integer> keys = map.keySet().iterator();
        List<Integer> list = new ArrayList<>();
        keys.forEachRemaining(list::add);

        assertEquals("[1, 2, 4, 5, 12, 15, 21, 23, 24, 26, 33, 35]", list.toString());
    }

    @Test
    void testRemoveK() {
        SplayTreeMap<Integer, String> map = new SplayTreeMap<>();
        Integer[] arr = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }

        assertEquals(12, map.size());
        assertEquals("26", map.remove(26));
        assertEquals(11, map.size());
    }

    @Test
    void testFirstEntry() {
        SplayTreeMap<Integer, String> map = new SplayTreeMap<>();
        Integer[] arr = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }

        assertEquals(1, map.firstEntry().getKey());
    }

    @Test
    void testLastEntry() {
        SplayTreeMap<Integer, String> map = new SplayTreeMap<>();
        //java.util.TreeMap<Integer, String> map = new java.util.TreeMap<>();
        Integer[] arr = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }

        assertEquals(35, map.lastEntry().getKey());
    }

    @Test
    void testCeilingEntry() {
        SplayTreeMap<Integer, String> map = new SplayTreeMap<>();
        //java.util.TreeMap<Integer, String> map = new java.util.TreeMap<>();
        Integer[] arr = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }

        assertEquals(12, map.ceilingEntry(11).getKey());

        assertEquals(2, map.ceilingEntry(2).getKey());
    }

    @Test
    void testFloorEntry() {
        SplayTreeMap<Integer, String> map = new SplayTreeMap<>();
        //java.util.TreeMap<Integer, String> map = new java.util.TreeMap<>();
        Integer[] arr = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }

        assertEquals(5, map.floorEntry(11).getKey());
        assertEquals(5, map.floorEntry(5).getKey());
    }

    @Test
    void testLowerEntry() {
        SplayTreeMap<Integer, String> map = new SplayTreeMap<>();
        //java.util.TreeMap<Integer, String> map = new java.util.TreeMap<>();
        Integer[] arr = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }

        assertEquals(23, map.lowerEntry(24).getKey());
        assertEquals(26, map.lowerEntry(31).getKey());
    }

    @Test
    void testHigherEntry() {
        SplayTreeMap<Integer, String> map = new SplayTreeMap<>();
        //java.util.TreeMap<Integer, String> map = new java.util.TreeMap<>();
        Integer[] arr = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }

        assertEquals(12, map.higherEntry(11).getKey());
    }

    @Test
    void testEntrySet() {
//        fail("Not yet implemented");
    }

//    @Test
//    void testToString() {
//        SplayTreeMap<Integer, String> map = new SplayTreeMap<>();
//        //java.util.TreeMap<Integer, String> map = new java.util.TreeMap<>();
//        Integer[] arr = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};
//
//        for (Integer i : arr) {
//            map.put(i, Integer.toString(i));
//        }
//        assertEquals("[⦰, 1, ⦰, 2, ⦰, 4, ⦰, 5, ⦰, 12, ⦰, 15, ⦰, 21, ⦰, 23, ⦰, 24, ⦰, 26, ⦰, 33, ⦰, 35, ⦰]", map.toString());
//    }

    @Test
    void testSubMap() {
        SplayTreeMap<Integer, String> map = new SplayTreeMap<>();
        //java.util.TreeMap<Integer, String> map = new java.util.TreeMap<>();
        Integer[] arr = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }

        assertEquals("[12, 15, 21, 23, 24, 26, 33]", map.subMap(12, 34).toString());
    }

}
