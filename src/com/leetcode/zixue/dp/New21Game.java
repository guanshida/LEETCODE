package com.leetcode.zixue.dp;

/**
 * https://leetcode-cn.com/problems/new-21-game/
 *
 * 837. 新21点
 *
 * 爱丽丝参与一个大致基于纸牌游戏 “21点” 规则的游戏，描述如下：
 *
 * 爱丽丝以 0 分开始，并在她的得分少于 K 分时抽取数字。 抽取时，她从 [1, W] 的范围中随机获得一个整数作为分数进行累计，其中 W 是整数。 每次抽取都是独立的，其结果具有相同的概率。
 *
 * 当爱丽丝获得不少于 K 分时，她就停止抽取数字。 爱丽丝的分数不超过 N 的概率是多少？
 *
 * 示例 1：
 *
 * 输入：N = 10, K = 1, W = 10
 * 输出：1.00000
 * 说明：爱丽丝得到一张卡，然后停止。
 * 示例 2：
 *
 * 输入：N = 6, K = 1, W = 10
 * 输出：0.60000
 * 说明：爱丽丝得到一张卡，然后停止。
 * 在 W = 10 的 6 种可能下，她的得分不超过 N = 6 分。
 * 示例 3：
 *
 * 输入：N = 21, K = 17, W = 10
 * 输出：0.73278
 * 提示：
 *
 * 0 <= K <= N <= 10000
 * 1 <= W <= 10000
 * 如果答案与正确答案的误差不超过 10^-5，则该答案将被视为正确答案通过。
 * 此问题的判断限制时间已经减少。
 * 通过次数2,873提交次数9,088
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/new-21-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class New21Game {


    public static void main(String[] args) {
        New21Game a = new New21Game();
        System.out.println(a.new21Game1(1, 0, 2));
        System.out.println(a.new21Game1(0, 0, 1));
        System.out.println(a.new21Game1(10, 1, 10));
        System.out.println(a.new21Game1(6, 1, 10));
        System.out.println(a.new21Game1(21, 17, 10));

        System.out.println(a.new21Game2_2(1, 0, 2));
        System.out.println(a.new21Game2_2(0, 0, 1));
        System.out.println(a.new21Game2_2(10, 1, 10));
        System.out.println(a.new21Game2_2(6, 1, 10));
        System.out.println(a.new21Game2_2(21, 17, 10));
    }

    /**
     *
     * 重新审题（个人感觉题目和答案描述差点东西）：
     *  一开始没懂题目意思，后面才知道是求爱丽丝的胜率。
     *  规则是这样：
     *      1. 她可以从牌面为[1,W]的牌中选择任意一张，这张牌是可以无限重复的，也就是说无论她取多少次，每次取到2（假如2在[1,W]范围内）的概率都是1/W;
     *      2. 如果她手上牌的总额小于K，她就会抽牌，大于等于K时，就停止抽牌;
     *      3. 停止抽牌后，她的牌面小于等于N时，她就获胜了，求她获胜的概率。
     *
     *
     * 动态规划
     * 解法一： 正向思维：dp[i][j] 表示抽了i次，正好抽到j点的概率。 初始化： i = 1, 1<= j<=w 时，dp[1][j] = 1/w.
     *      dp转移方程为：dp[i][j] = (dp[i-1][j - w] + ... + dp[i-1][j-1]) / w 。  最后求出所有 dp[?][k - n] 的和即可。
     *          Time: O(n^2)        Space: O(n^2)
     *       此方法在leetcode中内存溢出。
     *
     * 解法二： 逆向思维： dp[i] 表示以i开始时，爱丽丝取胜的概率。
     *      那么状态转移方程为： dp[i] = dp[i+1]*(1/w) + dp[i+2]*(1/w) + ... + dp[i+w]*(1/w) = (dp[i+1] + dp[i+2] + ... + dp[i+w]) / w
     *      初始化的边界值为： k <= i < min(n,k+w-1) 时，dp[i] = 1 ; 当 min(n,k+w-1) <= i 时，dp[i] = 0
     *      最后 dp[0] 的值即是爱丽丝取胜的概率。
     *          Time: O(nw)         Space: O(max(n,k+w))
     *
     *      据说这样会超时，
     * 解法二_2: 在解法二的基础上优化，可以看到每次都需要计算 (dp[i+1] + dp[i+2] + ... + dp[i+w])，所以可以先计算：total = dp[k+1] + ... +dp[k+w] 作为初始值，然后维护total值即可。
     *          Time: O(max(n,k+w))        Space: O(max(n,k+w))
     *
     * @param n
     * @param k
     * @param w
     * @return
     */
    public double new21Game1(int n, int k, int w) {

        if (k == 0) {
            return 1;
        }

        int len = n > w ? n + 1 : w + 1;
        double[][] arr = new double[len][len];

        for (int j = 1; j <= w ; j++) {
            arr[1][j] = 1.0 / w;
        }
        for (int i = 2; i <= n; i++) {
            int maxJ = Math.min(n, i * w);
            for (int j = i; j <= maxJ ; j++) {
                arr[i][j] = sum(arr[i - 1], Math.max(j - w, 0), Math.min(j, k)) / w;
            }
        }
        double sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += sum(arr[i], k, n + 1);
        }

        return sum;
    }

    private double sum(double[] arr, int start, int end) {
        double sum = 0;
        for (int i = start; i < end; i++) {
            sum += arr[i];
        }
        return sum;
    }


    public double new21Game2_2(int n, int k, int w) {

        if (k == 0) {
            return 1;
        }

        double[] dp = new double[Math.max(n + 1, k + w + 1)];
        double total = 0;
        for (int i = k; i < dp.length; i++) {
            if (i <= Math.min(n, k + w - 1)) {
                dp[i] = 1;
            } else {
                dp[i] = 0;
            }
            if (i > k && i <= k + w) {
                total += dp[i];
            }
        }
        for (int i = k - 1; i >= 0 ; i--) {
            total = total - dp[i + w + 1] + dp[i + 1];
            dp[i] = total / w;
        }
        return dp[0];
    }

}
