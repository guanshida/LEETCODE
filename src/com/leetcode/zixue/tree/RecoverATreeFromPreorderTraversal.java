package com.leetcode.zixue.tree;


import com.leetcode.tree.TreeNode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/recover-a-tree-from-preorder-traversal/
 *
 * 1028. 从先序遍历还原二叉树
 *
 * 我们从二叉树的根节点 root 开始进行深度优先搜索。
 *
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
 *
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。
 *
 * 给出遍历输出 S，还原树并返回其根节点 root。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入："1-2--3--4-5--6--7"
 * 输出：[1,2,5,3,4,6,7]
 * 示例 2：
 *
 *
 *
 * 输入："1-2--3---4-5--6---7"
 * 输出：[1,2,5,3,null,6,null,4,null,7]
 * 示例 3：
 *
 *
 *
 * 输入："1-401--349---90--88"
 * 输出：[1,401,null,349,88,90]
 *  
 *
 * 提示：
 *
 * 原始树中的节点数介于 1 和 1000 之间。
 * 每个节点的值介于 1 和 10 ^ 9 之间。
 * 通过次数3,539提交次数5,262
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-a-tree-from-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RecoverATreeFromPreorderTraversal {

    public static void main(String[] args) {
        RecoverATreeFromPreorderTraversal a = new RecoverATreeFromPreorderTraversal();
        System.out.println("解法一");
        System.out.println(a.recoverFromPreorder1("1-2--3--4-5--6--7"));
        System.out.println(a.recoverFromPreorder1("1-2--3---4-5--6---7"));
        System.out.println(a.recoverFromPreorder1("1-401--349---90--88"));

        System.out.println("解法二：");
        System.out.println(a.recoverFromPreorder2("1-2--3--4-5--6--7"));
        System.out.println(a.recoverFromPreorder2("1-2--3---4-5--6---7"));
        System.out.println(a.recoverFromPreorder2("1-401--349---90--88"));
    }





    /**
     * 解法一： 递归，DFS。  参数str，要构造的level。
     *              Time: O(n)          Space: (n)      n为字符串长度。 Time，因为要遍历一边即可。space因为递归栈的最大深度为n。
     * 解法二： 非递归，使用一个栈存储当前的路径。如果--的个数 = 栈深度-1之时，就是栈顶元素的右子节点。
     *              Time: O(n)          Space: O(n)
     * @param str
     * @return
     */
    private int index = 0;
    public TreeNode recoverFromPreorder1(String str) {
        this.index = 0;

        TreeNode root = diguiDFS(str,0);

        return root;
    }

    private TreeNode diguiDFS(String str, int level) {
        if (index >= str.length()) {
            return null;
        }
        int l = getLevel(str);
        if (l < level) {
            // 这里需要把index回置
            index = index - l;
            return null;
        }
        TreeNode root = getNode(str);
        root.left = diguiDFS(str, level + 1);
        root.right = diguiDFS(str, level + 1);

        return root;
    }

    private TreeNode getNode(String str) {
        int num = 0;
        while (index < str.length() && str.charAt(index) - '0' >= 0 && str.charAt(index) - '0' <= 9) {
            num = num * 10 + (str.charAt(index) - '0');
            index ++;
        }

        return new TreeNode(num);
    }

    private int getLevel(String str) {
        int count = 0;
        while (str.charAt(index) == '-') {
            count++;
            index++;
        }
        return count;
    }

    public TreeNode recoverFromPreorder2(String str) {

        index = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = this.getNode(str);
        stack.push(root);

        while (index < str.length()) {
            int level = getLevel(str);
            TreeNode node = getNode(str);
            boolean flag = true;
            while (level < stack.size()) {
                flag = false;
                stack.pop();
            }
            if (flag) {
                stack.peek().left = node;
            } else {
                stack.peek().right = node;
            }
            stack.push(node);
        }



        return root;
    }

}
