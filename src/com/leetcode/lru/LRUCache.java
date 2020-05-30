package com.leetcode.lru;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sound.midi.Soundbank;

/**
 * @author 管世达
 * @create 2019-01-10
 **/
public class LRUCache {
    public static void main(String[] args) {
        LRUCache a = new LRUCache(2);
        a.put(1,1);
        a.put(2,2);
        a.get(1);
        a.put(3, 3);
        int i = a.get(2);
        System.out.println(i);
    }
    LinkedHashMap<Integer,Integer> map;
    int capacity;
    public LRUCache(int capacity) {
        map = new LinkedHashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Integer v = map.remove(key);
        map.put(key, v);
        return  v;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.remove(key);
        } else if (map.size() >= capacity) {
            // 删除最后一个值
            Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
            iterator.hasNext();
            iterator.next();
            iterator.remove();
        }
        map.put(key,value);
    }
}
