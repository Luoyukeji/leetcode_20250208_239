import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 超时
 */
public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new int[0];
        }

        //双栈对列，用来保存元素索引
        //Deque 是一个双端队列（Double-ended Queue），支持从两端进行元素的插入和删除操作。
        Deque<Integer> deque = new LinkedList<>();
        List<Integer> result = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            // 删除队列中已经不在当前窗口中的元素
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // 删除队列中所有比当前元素小的元素，因为它们不可能是最大值
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // 将当前元素的索引加入队列
            deque.offerLast(i);

            // 从第k个元素开始，记录每个窗口的最大值
            if (i >= k - 1) {
                result.add(nums[deque.peekFirst()]);
            }
        }

        // 将结果转换成数组并返回
        int[] res = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        Solution solution = new Solution();
        int[] result = solution.maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(result));  // 输出：[3, 3, 5, 5, 6, 7]

    }
}
