package com.leetcode.lru;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 管世达
 * @create 2019-01-10
 **/
public class LRUCache2 {
    public static void main(String[] args) {
        LRUCache2 a = new LRUCache2(2);
        a.put(1,1);
        a.put(2,2);
        System.out.println(a.get(1));
        a.put(3, 3);
        int i = a.get(2);
        System.out.println(i);
        a.put(4, 4);
        System.out.println(a.get(1));
        System.out.println(a.get(3));
        System.out.println(a.get(4));

    }
    LRU cache;
    public LRUCache2(int capacity) {
        cache = new LRU(capacity);
    }

    public int get(int key) {
        return cache.containsKey(key) ? cache.get(key) : -1;
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }

    class LRU extends LinkedHashMap<Integer,Integer> {
        int capacity;
        LRU(int capacity) {
            super(capacity,0.75f,true);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return this.size() > this.capacity;
        }
    }
}
