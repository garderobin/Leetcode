# coding=utf-8
from abc import ABCMeta, abstractmethod


class FindPeakElement:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_peak_element(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """


class FindPeakElementImplBinarySearchSimplify(FindPeakElement):
    """
    通过了leetcode检查，但是正确性存疑
    """
    def find_peak_element(self, nums):
        if not nums:
            return -1

        return self.binary_search(nums, 0, len(nums) - 1)

    def binary_search(self, nums, start, end):
        if start > end:
            return -1
        elif start == end:
            return start
        else:
            if nums[start] > nums[start + 1]:
                return start

            if nums[end] > nums[end - 1]:
                return end

            if end - start < 2:
                return -1

            start += 1
            end -= 1

            mid = start + ((end - start) // 2)
            if nums[mid - 1] < nums[mid] and nums[mid] > nums[mid + 1]:
                return mid
            elif nums[mid - 1] > nums[mid]:
                return self.binary_search(nums, start, mid - 1)     # 这里能保证正确性，仔细想想，有边界保证着呢
            else:
                return self.binary_search(nums, mid + 1, end)


class FindPeakElementImplBinarySearch(FindPeakElement):
    """
    Super slow, 太多dup 检查
    """
    def find_peak_element(self, nums):
        if not nums:
            return -1

        return self.binary_search(nums, 0, len(nums) - 1)

    def binary_search(self, nums, start, end):
        if start > end:
            return -1
        elif start == end:
            return start
        else:
            if nums[start] > nums[start + 1]:
                return start

            if nums[end] > nums[end - 1]:
                return end

            if end - start < 2:
                return -1

            start += 1
            end -= 1

            mid = start + ((end - start) // 2)
            if nums[mid - 1] < nums[mid] and nums[mid] > nums[mid + 1]:
                return mid
            if nums[mid - 1] >= nums[mid]:
                left = self.binary_search(nums, start, mid - 1)
                if left >= 0:
                    return left
            if nums[mid + 1] >= nums[mid]:
                right = self.binary_search(nums, mid + 1, end)
                if right >= 0:
                    return right

            mid_memo = mid
            while mid > start + 1 and nums[mid - 1] <= nums[mid] <= nums[mid + 1]:
                mid -= 1
            left = self.binary_search(nums, mid, end)
            if left >= 0:
                return left

            mid = mid_memo
            while mid < end - 1 and nums[mid - 1] >= nums[mid] >= nums[mid + 1]:
                mid += 1
            right = self.binary_search(nums, mid, end)
            if right >= 0:
                return right
        return -1
