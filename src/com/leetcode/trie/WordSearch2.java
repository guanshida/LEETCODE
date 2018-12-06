package com.leetcode.trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/word-search-ii/description/
     给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
     单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
        同一个单元格内的字母在一个单词中不允许被重复使用。

     示例:
     输入:
     words = ["oath","pea","eat","rain"] and board =
     [
     ['o','a','a','n'],
     ['e','t','a','e'],
     ['i','h','k','r'],
     ['i','f','l','v']
     ]

     输出: ["eat","oath"]
     说明:
     你可以假设所有输入都由小写字母 a-z 组成。

     提示:

     你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
     如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？
        散列表是否可行？为什么？ 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
 * @author 管世达
 * @create 2018-12-06
 **/
public class WordSearch2 {
    public static void main(String[] args) {
//        String[] strs = new String[]{"oath","pea","eat","rain"};
//        char[][] board = new char[][] {
//                          {'o','a','a','n'},
//                          {'e','t','a','e'},
//                          {'i','h','k','r'},
//                          {'i','f','l','v'}};

        String[] strs = new String[]{"aba","baa","bab","aaab","aaa","aaaa","aaba"};
        char[][] board = new char[][] {{'a','b'},{'a','a'}};

        WordSearch2 a = new WordSearch2();
        List<String> w = a.findWords1(board, strs);
        System.out.println(w);
    }
    /**
     * 解法一：DFS  直接超时
     * 解法二：Trie
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords1(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            searchWord(board,words[i],result);
        }
        return result;
    }

    private void searchWord(char[][] board, String word, List<String> result) {
        Set<String> exist = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean flag = DFS(board, i, j, word, 0, exist, sb, result);
                    if (flag) {
                        return ;
                    }
                }
            }
        }
    }

    private boolean DFS(char[][] board, int i, int j, String word, int iWord, Set<String> exist, StringBuilder sb, List<String> list) {
        if (exist.contains(i + "," + j)) {
            return false;
        }
        sb.append(board[i][j]);
        if (iWord >= word.length() - 1) {
            String s = sb.toString();
            if (!list.contains(s))
                list.add(s);
            return true;
        }
        exist.add(i + "," + j);
        if (i > 0 && board[i - 1][j] == word.charAt(iWord + 1)) { // i-1
            boolean flag = DFS(board, i - 1, j, word, iWord + 1, exist, sb, list);
            if (flag) {
                return flag;
            }
        }
        if (j > 0 && board[i][j - 1] == word.charAt(iWord + 1)) { // j-1
            boolean flag = DFS(board, i, j - 1, word, iWord + 1, exist, sb, list);
            if (flag) {
                return flag;
            }
        }
        if (i < board.length - 1 && board[i + 1][j] == word.charAt(iWord + 1)) { // i-1
            boolean flag = DFS(board, i + 1, j, word, iWord + 1, exist, sb, list);
            if (flag) {
                return flag;
            }
        }
        if (j < board[i].length - 1 && board[i][j + 1] == word.charAt(iWord + 1)) { // i-1
            boolean flag = DFS(board, i, j + 1, word, iWord + 1, exist, sb, list);
            if (flag) {
                return flag;
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        exist.remove(i + "," + j);
        return false;
    }


    public List<String> findWords2(char[][] board, String[] words) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return new ArrayList<>();
        if (words == null || words.length == 0)
            return new ArrayList<>();

        Set<String> result = new HashSet<>();
        Trie trie = new Trie();
        //init
        init(trie, words);
        Trie.TrieNode root = trie.getRoot();
        StringBuilder sb = new StringBuilder();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (root.children[board[i][j] - 'a'] != null)
                    search(board, i, j, root.children[board[i][j] - 'a'], result, sb, set);
            }
        }
        return new ArrayList<>(result);
    }

    private void search(char[][] board, int i, int j, Trie.TrieNode curNode, Set<String> list, StringBuilder curWord,Set<String> exist) {
        if (exist.contains(i + "," + j)) {
            return ;
        }
        curWord.append(board[i][j]);
        if (curNode.isEndOfWord == true) {
            String s = curWord.toString();
            list.add(s);
//            return ;
        }
        exist.add(i + "," + j);
        if (i > 0 && curNode.children[board[i - 1][j] - 'a'] != null) { // i-1
            search(board, i - 1, j, curNode.children[board[i - 1][j] - 'a'], list, curWord, exist);
        }
        if (j > 0 && curNode.children[board[i][j - 1] - 'a'] != null) { // j-1
            search(board, i, j - 1, curNode.children[board[i][j - 1] - 'a'], list, curWord, exist);
        }
        if (i < board.length - 1 && curNode.children[board[i + 1][j] - 'a'] != null) { // i-1
            search(board, i + 1, j, curNode.children[board[i + 1][j] - 'a'], list, curWord, exist);
        }
        if (j < board[i].length - 1 && curNode.children[board[i][j + 1] - 'a'] != null) { // i-1
            search(board, i, j + 1, curNode.children[board[i][j + 1] - 'a'], list, curWord, exist);
        }
        curWord.deleteCharAt(curWord.length() - 1);
        exist.remove(i + "," + j);
    }
    private void init(Trie trie, String[] words) {
        for (String w : words) {
            trie.insert(w);
        }
    }
}
