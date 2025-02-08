import java.util.Deque;
import java.util.LinkedList;

public class Solution2 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new int[0];
        }

        // 双端队列，用来保存元素的索引
        Deque<Integer> deque = new LinkedList<>();
        int[] result = new int[nums.length - k + 1];  // 存储最终的结果

        for (int i = 0; i < nums.length; i++) {
            // 1. 移除队列中不在当前窗口中的索引
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // 2. 移除队列中所有小于当前元素的索引
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // 3. 将当前元素的索引加入队列
            deque.offerLast(i);

            // 4. 记录当前窗口的最大值，窗口索引大于等于 k - 1 时才开始记录
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];  // 队列头部的值就是当前窗口的最大值
            }
        }

        return result;
    }
}