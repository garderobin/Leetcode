# coding=utf-8
from abc import ABCMeta, abstractmethod


class FindTheDuplicateNumber:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_duplicate(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """


class FindTheDuplicateNumberImplBinarySearch(FindTheDuplicateNumber):
    @staticmethod
    def count_smaller_or_equal(nums, target):
        return len(filter(lambda x: x <= target, nums))

    """
    Time: O(nlogn)
    不是最优解法，但是这种可视化问题的思路非常值得借鉴，可以用在实在不知道该怎么做的时候
    https://www.jiuzhang.com/solutions/find-the-duplicate-number/
    记得是按答案来检索而不是按值来检索
    """
    def find_duplicate(self, nums):
        start, end = 1, len(nums) - 1
        while start + 1 < end:
            mid = (start + end) // 2
            if self.count_smaller_or_equal(nums, mid) > mid:    # 什么时候带等于号非常讲究
                end = mid
            else:
                start = mid
        return start if self.count_smaller_or_equal(nums, start) > start else end


class FindTheDuplicateNumberImplFastSlowPointers(FindTheDuplicateNumber):
    """
    Time: O(n)
    将第i位上的值看成是linked list该node的下一个节点index的话，这是一个存在环的linked list, 只需找到环入口
    快慢指针的变种，不再是一个走一步，一个走两步，二是
    """
    def find_duplicate(self, nums):
        if len(nums) <= 1:
            return -1

        slow = nums[0]
        fast = nums[nums[0]]
        while slow != fast:
            slow = nums[slow]
            fast = nums[nums[fast]]

        # 环的入口可能正好是dup这个num，也可能是num的下一个，所以还是需要沿着slow当前线路继续转直到再次走到环入口
        fast = 0
        while fast != slow:
            fast = nums[fast]
            slow = nums[slow]

        return slow
