package com.leetcode.trie;

/**
 * 实现一个字典树
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/description/
    实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。

     示例:
     Trie trie = new Trie();

     trie.insert("apple");
     trie.search("apple");   // 返回 true
     trie.search("app");     // 返回 false
     trie.startsWith("app"); // 返回 true
     trie.insert("app");
     trie.search("app");     // 返回 true

     说明:
     你可以假设所有的输入都是由小写字母 a-z 构成的。
     保证所有输入均为非空字符串。
 * @author 管世达
 * @create 2018-12-04
 **/
public class Trie {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("app");
        trie.insert("apple");
        trie.insert("add");
        System.out.println(trie.search("appl"));   // 返回 true
//        System.out.println(trie.search("app"));     // 返回 false
//        System.out.println(trie.startsWith("app")); // 返回 true
//        System.out.println(trie.search("app"));     // 返回 true
    }


    TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.equals("")) {
            return ;
        }
        TrieNode node = root;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            TrieNode child = node.children[index];
            if (child == null) {
                child = new TrieNode();
                node.children[index] = child;
            }
            if (i == chars.length - 1) {
                //最后一个
                child.isEndOfWord = true;
            }
            node = child;
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null || word.equals("")) {
            return false;
        }
        TrieNode child = this.getTrieNode(word);
        if (child == null) {
            return false;
        }
        return child.isEndOfWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.equals("")) {
            return true;
        }
        TrieNode child = this.getTrieNode(prefix);
        if (child == null) {
            return false;
        }
        return true;
    }

    /**
     * word 不为空
     * @param word
     * @return
     */
    private TrieNode getTrieNode(String word) {
        char[] chars = word.toCharArray();
        TrieNode child = root;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            child = child.children[c - 'a'];
            if (child == null) {
                return null;
            }
        }
        //找到
        return child;
    }

    class TrieNode {
        private static final int ALPHABET_SIZE = 26;
        TrieNode[] children;
        boolean isEndOfWord;
        public TrieNode() {
            this(false);
        }
        public TrieNode(boolean isEndOfWord) {
            children = new TrieNode[ALPHABET_SIZE];
            this.isEndOfWord = isEndOfWord;
        }
    }
}
