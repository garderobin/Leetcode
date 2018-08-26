# coding=utf-8
from abc import ABCMeta, abstractmethod


class LongestIncreasingSubSequence:
    __metaclass__ = ABCMeta

    @abstractmethod
    def length_of_lis(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """


class LongestIncreasingSubSequenceImplBinarySearchStandardTemplate(LongestIncreasingSubSequence):
    """
    经典的滚动数组应用场景：
    因为答案只关心长度而不关心sub-sequence内容，所以可以用滚动数组，永远存储当前lis,
    当有新的increasing sub-sequence出现的时候，按大小一个一个插入并覆盖滚动数组，如果lis确实变长了，append到结尾就行
    这样就能保证滚动数组的长度一定是lis长度，而内容我们是不关心的
    Time: O(NlogN)
    Space: O(N)
    """
    def length_of_lis(self, nums):
        if not nums:
            return 0
        lis = [nums[0]]
        for index, num in enumerate(nums[1:]):
            if num > lis[-1]:
                lis.append(num)
            else:
                lis[self.get_first_index_equal_or_larger_than_target(lis, num)] = num
        return len(lis)

    @staticmethod
    def get_first_index_equal_or_larger_than_target(array, target):
        start, end = 0, len(array) - 1
        while start + 1 < end:
            mid = (start + end) // 2
            if array[mid] < target:      # 一定要想清楚 == 的时候该往左还是右走，这个条件得反复检查
                start = mid
            else:
                end = mid
        return end if array[start] < target else start


class LongestIncreasingSubSequenceImplBinarySearch(LongestIncreasingSubSequence):
    """
    经典的滚动数组应用场景：
    因为答案只关心长度而不关心sub-sequence内容，所以可以用滚动数组，永远存储当前lis,
    当有新的increasing sub-sequence出现的时候，按大小一个一个插入并覆盖滚动数组，如果lis确实变长了，append到结尾就行
    这样就能保证滚动数组的长度一定是lis长度，而内容我们是不关心的
    Time: O(NlogN)
    Space: O(N)
    """
    def length_of_lis(self, nums):
        if not nums:
            return 0
        lis = [nums[0]]
        for index, num in enumerate(nums[1:]):
            if num > lis[-1]:
                lis.append(num)
            elif num <= lis[0]:     # 一定要带等号， 诶就应该合并了写
                lis[0] = num
            else:
                lis[self.get_first_index_equal_or_larger_than_target(lis, num)] = num
        return len(lis)

    @staticmethod
    def get_first_index_equal_or_larger_than_target(array, target):
        start, end = 0, len(array) - 1
        while start + 1 < end:
            mid = (start + end) // 2
            if array[mid] < target:      # 一定要想清楚 == 的时候该往左还是右走，这个条件得反复检查
                start = mid
            else:
                end = mid
        return end


class LongestIncreasingSubSequenceImplNSquare(LongestIncreasingSubSequence):
    """
    Time: O(N^2)
    Space: O(N)
    """
    def length_of_lis(self, nums):
        max_lis_len = 0
        dp = []     # dp[i] = max length of lis that ends in index i
        for index, num in enumerate(nums):
            cur_lis_len = 1
            for left in xrange(index - 1, -1, -1):
                if nums[left] < num:
                    cur_lis_len = max(cur_lis_len, dp[left] + 1)
            max_lis_len = max(max_lis_len, cur_lis_len)
            dp.append(cur_lis_len)
        return max_lis_len
