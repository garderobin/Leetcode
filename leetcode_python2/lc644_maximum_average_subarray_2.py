# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import deque


class MaximumAverageSubarray:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_max_average(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: float
        """


class MaximumAverageSubarrayImplConvexHullWindow(MaximumAverageSubarray):
    """
    https://leetcode.com/problems/maximum-average-subarray-ii/discuss/105476/Python-Advanced-O(n)-solution-(Convex-Hull-Window)
    Convex-Hull-Window
    斜率代表区间均值 + 同向双指针法 （弹性区间问题，同向双指针法是首选，实在不能构造双指针情况的，二分法要优于各种暴力。）
    """
    def find_max_average(self, nums, k):
        n = len(nums)
        prefix_sums = [0]
        for num in nums:
            prefix_sums.append(prefix_sums[-1] + num)

        # 要保证len(nums) == 1 的时候也能正确算avg, segment density就比slope要合适，虽然本质是一样，但是边界条件不容易出错
        def d(x1, x2):  # 类比下对于斜率的应用，神作 https://arxiv.org/pdf/cs/0311020.pdf
            return (prefix_sums[x2 + 1] - prefix_sums[x1]) / float(x2 + 1 - x1)

        hull = deque()
        ans = float('-inf')

        for j in xrange(k - 1, n):  # 弹性区间问题，同向双指针法是首选，实在不能构造双指针情况的，二分法要优于各种暴力。
            # for each i in increasing order,
            # hull[i+1] is the largest index in [hull[i], j-K+1] s.t. [hull[i], hull[i+1]-1] has minimum density
            while len(hull) >= 2 and d(hull[-2], hull[-1] - 1) >= d(hull[-2], j - k):  # todo 为什么这里是大于号
                hull.pop()

            hull.append(j - k + 1)

            # discarding components with lower density than our current candidate d(hull[0], j)
            while len(hull) >= 2 and d(hull[0], hull[1] - 1) <= d(hull[0], j):
                hull.popleft()
            ans = max(ans, d(hull[0], j))

        return ans


class MaximumAverageSubarrayImplBinarySearch(MaximumAverageSubarray):
    """
    这道题真的很难想到用二分法。但也说明了二分法的妙用：如果求解的答案是一个不确定大小的范围不能滑动窗口的时候，考虑二分，总好过暴力解法。
    主要是看到max range sum这样的特性，当窗口大小确定时可以滑动窗口或者双指针，当窗口大小不确定是可以通过prefix sum为辅助来构造二分性:
    二分出 average 之后，把数组中的每个数都减去 average，然后的任务就是去求这个数组中，是否有长度 >= k 的 subarray，他的和超过 0。
    Time Limit Exceeded
    """
    def find_max_average(self, nums, k):
        start, end = min(nums), max(nums)
        while end - start > 1e-5:
            mid = (start + end) * 1.0 / 2     # 小数二分，千万不要用地板除了, 此外python2和3关于单除号定义不同，切记
            if self.has_subarray_with_larger_average(nums, k, mid):
                start = mid
            else:
                end = mid
        return start        # 因为是比较小数，最后不需要在两个整数中做抉择了

    @staticmethod
    def has_subarray_with_larger_average(nums, k, cur_average):
        prefix_sum = [0]
        for num in nums:
            prefix_sum.append(prefix_sum[-1] + num - cur_average)

        min_prefix_sum = 0
        for i in xrange(k, len(prefix_sum)):
            if prefix_sum[i] - min_prefix_sum >= 0:
                return True
            min_prefix_sum = min(min_prefix_sum, prefix_sum[i - k + 1])     # 千万不要把prefix里面算进去现在window的值
        return False
