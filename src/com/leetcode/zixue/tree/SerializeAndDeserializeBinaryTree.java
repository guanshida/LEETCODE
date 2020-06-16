package com.leetcode.zixue.tree;

import com.leetcode.tree.TreeNode;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 *
 * 297. 二叉树的序列化与反序列化
 *
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 示例: 
 *
 * 你可以将以下二叉树：
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 *
 * 通过次数26,442提交次数54,750
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SerializeAndDeserializeBinaryTree {

    public static void main(String[] args) {
        SerializeAndDeserializeBinaryTree a = new SerializeAndDeserializeBinaryTree();
        System.out.println("解法一：");
        System.out.println(a.serialize1(a.deserialize1("[1,3,null,null,4,null,null]")));
        System.out.println(a.serialize1(a.deserialize1("[1, 2, 3, null, null, 4, 5]")));
        System.out.println(a.serialize1(a.deserialize1("[]")));
        System.out.println(a.serialize1(a.deserialize1("[1,2,3,1,3,2,4]")));

        System.out.println("解法一_优化部分：");
        System.out.println(a.serialize1_2(a.deserialize1_2("[1, 2, 3, null, null, 4, 5]")));
        System.out.println(a.serialize1_2(a.deserialize1_2("[1,3,null,null,4]")));
        System.out.println(a.serialize1_2(a.deserialize1_2("[]")));
        System.out.println(a.serialize1_2(a.deserialize1_2("[1,2,3,1,3,2,4]")));


        System.out.println("解法三：");
        System.out.println(a.serialize2(a.deserialize2("[1, 2, 3, 4, 5]|[2,1,4,3,5]")));
        System.out.println(a.serialize2(a.deserialize2("[]|[]")));


        System.out.println("解法四：");
        System.out.println(a.serialize4(a.deserialize4("1,2,null,null,3,4,null,null,5,null,null")));
        System.out.println(a.serialize4(a.deserialize4("1,3,null,4,null,null,null")));
        System.out.println(a.serialize4(a.deserialize4("null")));
        System.out.println(a.serialize4(a.deserialize4("1,2,1,3,null,null,null,null,3,2,4,null,null,null,null")));


    }

    /**
     * 解法一： 序列化成：[1,2,3,null,null,4,5] 结构。BFS：序列化时需要使用DFS先获取到树的深度，再初始化数组序列化；反序列化时直接使用BFS遍历即可。
     *          Time: O(n)          Space: O(n)         n代表把树转换成完全二叉树时树的长度。
     *          这种方法在leetcode中会发生超出内存限制。因为有可能是一颗只有左子树的树。这种情况效率太低。
     *      优化方式：当node节点没有左右子树时，可以只序列化两个null。表示此树没有子节点了，这时候后面的层数就不需要他了。从而改造成O(n)  n代表树的节点 的时间和空间复杂度。
     *
     * 解法二： 序列化成两个数组。前序遍历和中序遍历。DFS：序列化时使用前序和中序遍历出来两个数组，然后使用“|”分割；反序列化时先获取前序序列和中序序列，然后根据两个数组使用递归重新构造树。
     *          Time: O(n)          Space: O(n)         n代表树的节点数。
     *          这样有重复节点的问题，可以考虑先对每个节点编个号，使所有节点唯一，再执行上述解法即可。不过因为要序列化对应关系，所以比较浪费内存。
     *
     * 接发三： 作为图存储。用二维数组存储边，还需要使用map存储索引和节点的关系。
     *          Time: O(n^2)        Space: O(n^2)       n代表树的节点数。
     *
     * 解法四： 解法二改造。当把null也遍历出来的时候，只需要一个序列就可以恢复树。
     *          Time: O(n)          Space: O(n)         n代表数的节点数。
     *
     * 解法五： 用括号分割左右子树，DFS，中序遍历。
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public String serialize1(TreeNode root) {
        int depth = this.getDepthByDFS(root);
        if (depth == 0) {
            return arrToString(new Integer[0]);
        }

        Integer[] arr = new Integer[(int) Math.pow(2, depth) - 1];
        int k = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        for (int i = 0; i < depth; i++) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                TreeNode poll = queue.poll();
                arr[k++] = poll == null ? null : poll.val;
                queue.add(poll == null ? null : poll.left);
                queue.add(poll == null ? null : poll.right);
            }
        }
        return this.arrToString(arr);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize1(String data) {
        Integer[] arr = StringToArr(data);
        if (arr.length == 0) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(arr[0]);
        queue.add(root);
        int k = 1;
        while (!queue.isEmpty() && k < arr.length) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                Integer leftInt = k < arr.length ? arr[k++] : null;
                Integer rightInt = k < arr.length ? arr[k++] : null;
                if (poll == null) {
                    queue.add(null);
                    queue.add(null);
                } else {
                    poll.left = leftInt == null ? null : new TreeNode(leftInt);
                    queue.add(poll.left);
                    poll.right = rightInt == null ? null : new TreeNode(rightInt);
                    queue.add(poll.right);
                }
            }
        }
        return root;
    }

    private int getDepthByDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(this.getDepthByDFS(root.left), this.getDepthByDFS(root.right)) + 1;
    }

    private Integer[] StringToArr(String data) {
        String substring = data.substring(1, data.length() - 1);
        if (substring.equals("") || substring.equals(" ")) {
            return new Integer[0];
        }
        String[] split = substring.split(",\\s*");
        Integer[] arr = new Integer[split.length];
        for (int i = 0; i < split.length; i++) {
            arr[i] = "null".equals(split[i]) ? null : Integer.valueOf(split[i]);
        }
        return arr;
    }
    private String arrToString(Integer[] ints) {
        return Arrays.toString(ints);
    }

    public String serialize1_2(TreeNode root) {
        int depth = this.getDepthByDFS(root);
        if (depth == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int k = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        for (int i = 0; i < depth; i++) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                TreeNode poll = queue.poll();
                sb.append(poll == null ? "null" : poll.val).append(",");
                if (poll != null) {
                    queue.add(poll.left);
                    queue.add(poll.right);
                }
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize1_2(String data) {
        String[] arr = new String[0];
        if (!data.equals("") && !data.equals(" ")) {
            arr = data.split(",");
        }
        if (arr.length == 0) {
            return null;
        }


        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(arr[0]));
        queue.add(root);
        int k = 1;
        while (!queue.isEmpty() && k < arr.length) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll != null && k < arr.length) {
                    String leftStr = arr[k++];
                    String rightStr = arr[k++];
                    if (!leftStr.equals("null")) {
                        poll.left = new TreeNode(Integer.valueOf(leftStr));
                        queue.add(poll.left);
                    }
                    if (!rightStr.equals("null")) {
                        poll.right = new TreeNode(Integer.valueOf(rightStr));
                        queue.add(poll.right);
                    }
                }
            }
        }
        return root;
    }



    public String serialize2(TreeNode root) {
        List<Integer> prevList = new ArrayList<>();
        List<Integer> inList = new ArrayList<>();
        prevOrder(root, prevList);
        inOrder(root, inList);

        return this.arrToString(prevList) + "|" + this.arrToString(inList);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        String[] split = data.split("\\|");

        Integer[] prevArr = StringToArr(split[0]);
        Integer[] inArr = StringToArr(split[1]);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inArr.length; i++) {
            map.put(inArr[i], i);
        }
        TreeNode root = digui(prevArr, inArr, map, 0, inArr.length - 1, 0);

        return root;
    }

    private void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }

    private void prevOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        prevOrder(root.left, list);
        prevOrder(root.right, list);
    }


    private String arrToString(List<Integer> ints) {
        return ints.toString();
    }

    private TreeNode digui(Integer[] prevArr, Integer[] inArr, Map<Integer, Integer> inArrMap, int inArrStart, int inArrEnd, int preArrRootIndex) {
        if (inArrStart > inArrEnd) {
            return null;
        }

        if (inArrStart == inArrEnd) {
            return new TreeNode(inArr[inArrStart]);
        }
        TreeNode root = new TreeNode(prevArr[preArrRootIndex]);
        Integer inArrRootIndex = inArrMap.get(prevArr[preArrRootIndex]);
        root.left = digui(prevArr, inArr, inArrMap, inArrStart, inArrRootIndex - 1, preArrRootIndex + 1);
        root.right = digui(prevArr, inArr, inArrMap, inArrRootIndex + 1, inArrEnd, preArrRootIndex + (inArrRootIndex - inArrStart) + 1);

        return root;
    }

    // Encodes a tree to a single string.
    public String serialize4(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        diguiDFS4(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void diguiDFS4(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null,");
            return;
        }
        sb.append(root.val).append(",");
        diguiDFS4(root.left, sb);
        diguiDFS4(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize4(String data) {
        String[] split = data.split(",");

        Queue<String> es = new LinkedList<>(Arrays.asList(split));
        TreeNode root = hufuDFS4(es);
        return root;
    }

    private TreeNode hufuDFS4(Queue<String> queue) {
        if (queue.isEmpty() || queue.peek().equals("null")) {
            queue.poll();
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(queue.poll()));
        root.left = hufuDFS4(queue);
        root.right = hufuDFS4(queue);
        return root;
    }
}
