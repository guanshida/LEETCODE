package com.leetcode.zixue.graphic;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/keys-and-rooms/
 *
 * 841. 钥匙和房间
 * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
 *
 * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
 *
 * 最初，除 0 号房间外的其余所有房间都被锁住。
 *
 * 你可以自由地在房间之间来回走动。
 *
 * 如果能进入每个房间返回 true，否则返回 false。
 *
 * 示例 1：
 *
 * 输入: [[1],[2],[3],[]]
 * 输出: true
 * 解释:
 * 我们从 0 号房间开始，拿到钥匙 1。
 * 之后我们去 1 号房间，拿到钥匙 2。
 * 然后我们去 2 号房间，拿到钥匙 3。
 * 最后我们去了 3 号房间。
 * 由于我们能够进入每个房间，我们返回 true。
 * 示例 2：
 *
 * 输入：[[1,3],[3,0,1],[2],[0]]
 * 输出：false
 * 解释：我们不能进入 2 号房间。
 * 提示：
 *
 * 1 <= rooms.length <= 1000
 * 0 <= rooms[i].length <= 1000
 * 所有房间中的钥匙数量总计不超过 3000。
 * 通过次数42,032提交次数64,367
 */
public class KeysAndRooms {


    /**
     * 可以看做一个图，钥匙为有向边，从0位置开始遍历一遍，看哪一个位置没有遍历到。
     * 解法一：DFS
     *
     * 解法二：BFS
     *
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms1(List<List<Integer>> rooms) {

        if (rooms.size() <= 1) {
            return true;
        }
        BitSet visit = new BitSet(rooms.size());

        DFS(rooms, 0, visit);
        for (int i = 0; i < rooms.size(); i++) {
            if (!visit.get(i)) {
                return false;
            }
        }
        return true;
    }

    private void DFS(List<List<Integer>> rooms, int i, BitSet visit) {
        visit.set(i);
        List<Integer> list = rooms.get(i);
        for (int j = 0; j < list.size(); j++) {
            if (!visit.get(list.get(j))) {
                DFS(rooms, list.get(j), visit);
            }
        }
    }


    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {

        if (rooms.size() <= 1) {
            return true;
        }
        BitSet visit = new BitSet(rooms.size());

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visit.set(0);

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            List<Integer> list = rooms.get(poll);
            for (int i = 0; i < list.size(); i++) {
                if (!visit.get(list.get(i))) {
                    queue.add(list.get(i));
                    visit.set(list.get(i));
                }
            }
        }
        for (int i = 0; i < rooms.size(); i++) {
            if (!visit.get(i)) {
                return false;
            }
        }
        return true;
    }

}
