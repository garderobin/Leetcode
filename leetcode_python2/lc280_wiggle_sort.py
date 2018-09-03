# coding=utf-8
class WiggleSort(object):
    def wiggle_sort(self, nums):
        """
        :type nums: List[int]
        :rtype: void Do not return anything, modify nums in-place instead.
        """
        if not nums:
            return
        for i in xrange(1, len(nums)):
            prev = nums[i - 1]
            if i % 2 == (prev > nums[i]):   # 右边这个括号绝对不能不带，因为python支持连续比较！
                nums[i - 1] = nums[i]
                nums[i] = prev
