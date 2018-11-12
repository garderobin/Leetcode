# coding=utf-8
from abc import ABCMeta, abstractmethod


class NextGreaterElement2:
    __metaclass__ = ABCMeta

    @abstractmethod
    def next_greater_elements(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """


class NextGreaterElement2ImplDecreasingStack(NextGreaterElement2):
    """
    TODO： 复习经典题目：循环数组 + 单调栈
    看到区间极值就想到单调栈
    单调栈相对于DP的优点：
    在复杂度相同的情况下，单调栈是正向遍历， 所以可以轻松改造成iterator，而DP无法动态扩展
    Time: O(N)
    Space: O(N)
    """
    def next_greater_elements(self, nums):
        if not nums:
            return []

        n = len(nums)
        next_greater_elements = [-1] * n
        index_stack = []

        for i, num in enumerate(nums + nums):   # 这种索引方法没有先来一遍arr = nums + nums 保险
            while index_stack and nums[index_stack[-1]] < num:  # 易错！这里非常容易错拿index_stack直接和num比
                next_greater_elements[index_stack.pop()] = num
            if i < n:   # 循环数组精髓所在，超过n - 1的部分只是用来辅助处理栈中未处理的部分
                index_stack.append(i)
        return next_greater_elements


class NextGreaterElement2ImplDPExtendArray(NextGreaterElement2):
    """
    虽然初始化扩展数组会花掉一个O(N), 但这种写法不容易错，而且除余是很慢的，所以扩展数组还是比除余法好
    Time: O(N)  这个千万不要误以为是O(N^2)
    Space: O(N)
    """
    def next_greater_elements(self, nums):
        if not nums:
            return nums

        arr = nums + nums   # 好写，不容易错
        n, m = len(nums), len(arr)
        next_greater_element_index = [-1] * m   # 这里用dict 如{m - 1: -1}理论上能省时但实际上还是数组更快
        for i in xrange(m - 2, -1, -1):
            j = i + 1
            while 0 < j < m and arr[i] >= arr[j]:
                j = next_greater_element_index[j]
            next_greater_element_index[i] = j

        return [nums[next_greater_element_index[i]] if next_greater_element_index[i] != -1 else -1 for i in xrange(n)]


class NextGreaterElement2ImplDPDivide(NextGreaterElement2):
    """
    容易出错
    Time: O(N)
    Space: O(N)
    """
    def next_greater_elements(self, nums):
        if not nums:
            return nums

        n, m = len(nums), len(nums) << 1
        next_greater_element_index = [-1] * m  # 这里用dict 如{m - 1: -1}理论上能省时但实际上还是数组更快
        for i in xrange(m - 2, -1, -1):
            j = i + 1
            while 0 < j < m and nums[i % n] >= nums[j % n]:
                j = next_greater_element_index[j]
            next_greater_element_index[i] = j
        return [nums[next_greater_element_index[i] % n] if next_greater_element_index[i] != -1 else -1
                for i in xrange(n)]
