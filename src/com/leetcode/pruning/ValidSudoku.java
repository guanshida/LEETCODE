package com.leetcode.pruning;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/valid-sudoku/description/
     判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。

     数字 1-9 在每一行只能出现一次。
     数字 1-9 在每一列只能出现一次。
     数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。


     上图是一个部分填充的有效的数独。

     数独部分空格内已填入了数字，空白格用 '.' 表示。

     示例 1:

     输入:
     [
         ['5','3','.','.','7','.','.','.','.'],
         ['6','.','.','1','9','5','.','.','.'],
         ['.','9','8','.','.','.','.','6','.'],
         ['8','.','.','.','6','.','.','.','3'],
         ['4','.','.','8','.','3','.','.','1'],
         ['7','.','.','.','2','.','.','.','6'],
         ['.','6','.','.','.','.','2','8','.'],
         ['.','.','.','4','1','9','.','.','5'],
         ['.','.','.','.','8','.','.','7','9']
     ]
     输出: true
     示例 2:

     输入:
     [
         ['8','3','.','.','7','.','.','.','.'],
         ['6','.','.','1','9','5','.','.','.'],
         ['.','9','8','.','.','.','.','6','.'],
         ['8','.','.','.','6','.','.','.','3'],
         ['4','.','.','8','.','3','.','.','1'],
         ['7','.','.','.','2','.','.','.','6'],
         ['.','6','.','.','.','.','2','8','.'],
         ['.','.','.','4','1','9','.','.','5'],
         ['.','.','.','.','8','.','.','7','9']
     ]
     输出: false
     解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
     但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。

     说明:
     一个有效的数独（部分已被填充）不一定是可解的。
     只需要根据以上规则，验证已经填入的数字是否有效即可。
     给定数独序列只包含数字 1-9 和字符 '.' 。
     给定数独永远是 9x9 形式的。
 * @author 管世达
 * @create 2018-11-28
 **/
public class ValidSudoku {
    public static void main(String[] args) {
        char[][] b = {
                      {'5','3','.','.','7','.','.','.','.'},
                      {'6','.','.','1','9','5','.','.','.'},
                      {'.','9','8','.','.','.','.','6','.'},
                      {'8','.','.','.','6','.','.','.','3'},
                      {'4','.','.','8','.','3','.','.','1'},
                      {'7','.','.','.','2','.','.','.','6'},
                      {'.','6','.','.','.','.','2','8','.'},
                      {'.','.','.','4','1','9','.','.','5'},
                      {'.','.','.','.','8','.','.','7','9'}
                    };
        ValidSudoku a = new ValidSudoku();
        boolean bool = a.isValidSudoku2(b);
        System.out.println(bool);
    }
    /**
     * 解法一：暴力解法。遍历三遍。一次满足一个条件即可。
     *      Time：O(9 * 9 * 3)
     * 解法二：使用HashMap。遍历一遍。
     *      Time: O(9 * 9)
     * @param board
     * @return
     */
    public boolean isValidSudoku2(char[][] board) {
        Map<Integer,Set<Integer>> rows = new HashMap<>();
        Map<Integer,Set<Integer>> cols = new HashMap<>();
        Map<Integer,Set<Integer>> blocks = new HashMap<>();
        return initSudoku(board, rows, cols, blocks);
    }

    public boolean initSudoku(char[][] board, Map<Integer, Set<Integer>> rows, Map<Integer, Set<Integer>> cols, Map<Integer, Set<Integer>> blocks){
        for (int i = 0; i < board.length; i++) {

            Set<Integer> row = getValueByKey(rows, i);
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                // 行不满足
                if (row.contains(board[i][j] - '0')) {
                    return false;
                }
                // 列不满足
                Set<Integer> col = getValueByKey(cols, j);
                if (col.contains(board[i][j] - '0')) {
                    return false;
                }
                // 块不满足
                Set<Integer> block = getValueByKey(blocks, (i / 3) * 3 + j / 3);
                if (block.contains(board[i][j] - '0')) {
                    return false;
                }

                block.add(board[i][j] - '0');
                col.add(board[i][j] - '0');
                row.add(board[i][j] - '0');
            }
        }
        return true;
    }
    public Set<Integer> getValueByKey(Map<Integer,Set<Integer>> map,Integer key) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        HashSet<Integer> temp = new HashSet<>();
        map.put(key, temp);
        return temp;
    }
}
